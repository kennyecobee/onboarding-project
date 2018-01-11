package com.example.kennyr.onboardingproject.tasks.taskdetail;

import dagger.Subcomponent;

@Subcomponent(modules = {TaskDetailModule.class})
public interface TaskDetailComponent {

    void inject(TaskDetailFragment fragment);
}
