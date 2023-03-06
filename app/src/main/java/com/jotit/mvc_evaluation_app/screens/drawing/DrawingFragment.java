package com.jotit.mvc_evaluation_app.screens.drawing;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;
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
    public ScreenName getScreenName() {
        return ScreenName.DRAWING;
    }

    @Override
    public void onTextRecognized(String text) {
        showSuccessfullyRecognizedMessageDialog(text);
    }

    @Override
    public void onTextRecognitionError() {
        showRecognitionErrorMessageDialog();
    }

    private void showSuccessfullyRecognizedMessageDialog(String text) {
        View view = getLayoutInflater().inflate(R.layout.dialog_successfully_recognized_message, null);
        MaterialButton positiveButton = view.findViewById(R.id.btnGotIt);
        AppCompatTextView tvDescription = view.findViewById(R.id.tvDescription);
        AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).create();
        dialog.getWindow().getDecorView().setBackgroundResource(R.drawable.bg_primary_round_corners_12);
        dialog.setCancelable(false);
        tvDescription.setText(text);
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
