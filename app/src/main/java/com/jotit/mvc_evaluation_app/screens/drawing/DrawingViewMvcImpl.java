package com.jotit.mvc_evaluation_app.screens.drawing;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.google.android.material.button.MaterialButton;
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
                Bitmap originalBitmap = paint.getOriginalBitmap();
                for (DrawingViewMvc.Listener listener : getListeners()) {
                    listener.onCharacterRecognitionClick(originalBitmap);
                }
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
