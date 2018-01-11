package com.example.kennyr.onboardingproject;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ToolbarModule {

    @Provides
    Toolbar provideToolbar(Activity activity) {
        return new Toolbar((android.support.v7.widget.Toolbar) activity.findViewById(R.id.toolbar));
    }
}
