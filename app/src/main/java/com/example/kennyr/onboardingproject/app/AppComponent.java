package com.example.kennyr.onboardingproject.app;

import com.example.kennyr.onboardingproject.tasks.TaskComponent;
import com.example.kennyr.onboardingproject.tasks.TaskModule;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectMembers(App app);

    TaskComponent with(TaskModule module);
}
