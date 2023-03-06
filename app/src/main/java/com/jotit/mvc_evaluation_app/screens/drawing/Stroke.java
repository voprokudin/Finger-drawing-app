package com.jotit.mvc_evaluation_app.screens.drawing;

import android.graphics.Path;

public class Stroke {

    public int color;
    public int strokeWidth;
    public Path path;

    public Stroke(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
