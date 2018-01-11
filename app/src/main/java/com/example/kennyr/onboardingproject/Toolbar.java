package com.example.kennyr.onboardingproject;

import android.view.View;

public class Toolbar {

    private android.support.v7.widget.Toolbar toolbar;

    public Toolbar(android.support.v7.widget.Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void showTitle(CharSequence text) {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(text);
    }
}
