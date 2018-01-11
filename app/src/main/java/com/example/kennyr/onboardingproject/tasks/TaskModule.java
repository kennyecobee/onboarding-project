package com.example.kennyr.onboardingproject.tasks;

import android.app.Activity;
import android.content.Context;

import com.example.kennyr.onboardingproject.util.StringProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {

    private final Activity activity;

    TaskModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    Context provideApplicationContext() {
        return activity.getApplicationContext();
    }

    @Provides
    StringProvider provideStringProvider() {
        return new StringProvider(activity.getApplicationContext());
    }
}
