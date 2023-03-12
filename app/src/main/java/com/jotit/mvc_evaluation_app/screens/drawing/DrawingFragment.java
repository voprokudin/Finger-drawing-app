package com.jotit.mvc_evaluation_app.screens.drawing;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.jotit.mvc_evaluation_app.R;
import com.jotit.mvc_evaluation_app.screens.ScreensNavigator;
import com.jotit.mvc_evaluation_app.screens.common.BaseFragment;
import com.jotit.mvc_evaluation_app.screens.common.ScreenName;
import com.jotit.mvc_evaluation_app.screens.common.ViewMvcFactory;
import javax.inject.Inject;

public class DrawingFragment extends BaseFragment implements DrawingViewMvc.Listener {

    public static DrawingFragment newInstance() {
        return new DrawingFragment();
    }

    @Inject ViewMvcFactory viewMvcFactory;
    @Inject ScreensNavigator screensNavigator;
    private DrawingViewMvc viewMvc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.newDrawingViewMvc(container);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onCharacterRecognitionClick(Bitmap originalBitmap) {
        Bitmap adjustedBitmap = adjustBitmap(originalBitmap);
        TextRecognizer textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        textRecognizer.process(adjustedBitmap, 0).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {
                String recognizedText = text.getText();
                if (recognizedText.isEmpty()) {
                    showRecognitionErrorMessageDialog();
                } else {
                    String mostFrequentElement = provideMostFrequentElement(recognizedText);
                    showSuccessfullyRecognizedMessageDialog(mostFrequentElement, originalBitmap);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showRecognitionErrorMessageDialog();
            }
        });
    }

    @Override
    public ScreenName getScreenName() {
        return ScreenName.DRAWING;
    }

    public Bitmap adjustBitmap(Bitmap bitmap) {
        Bitmap adjustedBitmap = Bitmap.createBitmap(4 * bitmap.getWidth(), 4 * bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas combinedCanvas = new Canvas(adjustedBitmap);
        float bitmapWidth = (float) bitmap.getWidth();
        float bitmapHeight = (float) bitmap.getHeight();
        for (int i = 0; i < 4; i ++) {
            combinedCanvas.drawBitmap(bitmap, 0 * bitmapWidth, i * bitmapHeight, null);
            combinedCanvas.drawBitmap(bitmap, 1 * bitmapWidth, i * bitmapHeight, null);
            combinedCanvas.drawBitmap(bitmap, 2 * bitmapWidth, i * bitmapHeight, null);
            combinedCanvas.drawBitmap(bitmap, 3 * bitmapWidth, i * bitmapHeight, null);
        }
        return adjustedBitmap;
    }

    private String provideMostFrequentElement(String recognizedText) {
        char[] charArray = recognizedText.toCharArray();
        int maxCount = 0;
        char mostFrequentCharacter = ' ';
        for (char character : charArray) {
            int count = 0;
            for (char value : charArray) {
                if (character == value) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentCharacter = character;
            }
        }
        return String.valueOf(mostFrequentCharacter);
    }

    private void showSuccessfullyRecognizedMessageDialog(String mostFrequentElement, Bitmap bitmap) {
        View view = getLayoutInflater().inflate(R.layout.dialog_successfully_recognized_message, null);
        MaterialButton positiveButton = view.findViewById(R.id.btnGotIt);
        AppCompatTextView tvDescription = view.findViewById(R.id.tvDescription);
        AppCompatImageView ivResult = view.findViewById(R.id.ivResult);
        AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).create();
        dialog.getWindow().getDecorView().setBackgroundResource(R.drawable.bg_primary_round_corners_12);
        dialog.setCancelable(false);
        tvDescription.setText(mostFrequentElement);
        ivResult.setImageBitmap(bitmap);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!requireActivity().isFinishing()) dialog.show();
    }

    private void showRecognitionErrorMessageDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_recognition_error_message, null);
        MaterialButton positiveButton = view.findViewById(R.id.btnTryAgain);
        AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).create();
        dialog.getWindow().getDecorView().setBackgroundResource(R.drawable.bg_primary_round_corners_12);
        dialog.setCancelable(false);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!requireActivity().isFinishing()) dialog.show();
    }
}
