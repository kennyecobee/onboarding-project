package com.example.kennyr.onboardingproject.tasks.tasklist;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskListModule {

    private Fragment fragment;

    public TaskListModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }
}
