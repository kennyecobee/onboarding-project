package com.example.kennyr.onboardingproject.app;

import com.example.kennyr.onboardingproject.tasks.TaskDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    TaskDataSource provideTaskDataSource() {
        return TaskDataSource.getInstance();
    }
}
