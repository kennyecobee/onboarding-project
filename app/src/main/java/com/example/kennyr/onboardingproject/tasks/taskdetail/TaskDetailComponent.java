package com.example.kennyr.onboardingproject.tasks.taskdetail;

import android.support.v4.app.Fragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent
public interface TaskDetailComponent {

    void inject(TaskDetailFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder fragment(Fragment fragment);

        TaskDetailComponent build();
    }
}
