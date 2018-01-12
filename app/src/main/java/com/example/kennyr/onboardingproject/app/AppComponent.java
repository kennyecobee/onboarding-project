package com.example.kennyr.onboardingproject.app;

import com.example.kennyr.onboardingproject.tasks.TaskComponent;

import dagger.Component;

@Component
public interface AppComponent {

    void injectMembers(App app);

    TaskComponent.Builder provideTaskBuilder();
}
