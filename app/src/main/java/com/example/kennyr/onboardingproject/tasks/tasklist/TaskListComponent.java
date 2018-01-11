package com.example.kennyr.onboardingproject.tasks.tasklist;

import dagger.Subcomponent;

@Subcomponent(modules = {TaskListModule.class})
public interface TaskListComponent {

    void inject(TaskListFragment fragment);
}
