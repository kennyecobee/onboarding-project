package com.example.kennyr.onboardingproject.tasks;

import android.app.Activity;

import com.example.kennyr.onboardingproject.ToolbarModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {TaskModule.class, ToolbarModule.class})
public interface TaskComponent extends TaskContract.Component {
    void inject(Activity activity);
}
