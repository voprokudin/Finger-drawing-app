package com.jotit.mvc_evaluation_app.screens.drawing;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.screens.common.ViewMvcFactory;

public class DrawingViewMvcImpl extends DrawingViewMvc {

    private final DrawView paint;

    public DrawingViewMvcImpl(LayoutInflater inflater, ViewGroup container, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_drawing, container, false));
        paint = findViewById(R.id.draw_view);
        MaterialButton btnUndo = findViewById(R.id.btnUndo);
        MaterialButton btnClear = findViewById(R.id.btnClear);
        MaterialButton btnRecognizeMlKit = findViewById(R.id.btnRecognizeMlKit);
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.undo();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.clear();
            }
        });
        btnRecognizeMlKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = paint.save();
                TextRecognizer textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
                textRecognizer.process(bmp, 0).addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(Text text) {
                        String recognizedText = text.getText();
                        for (DrawingViewMvc.Listener listener : getListeners()) {
                            if (recognizedText.isEmpty()) {
                                listener.onTextRecognitionError();
                            } else {
                                listener.onTextRecognized(recognizedText);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        for (DrawingViewMvc.Listener listener : getListeners()) {
                            listener.onTextRecognitionError();
                        }
                    }
                });
            }
        });
        paint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                paint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = paint.getMeasuredWidth();
                int height = paint.getMeasuredHeight();
                paint.init(height, width);
            }
        });
    }
}
