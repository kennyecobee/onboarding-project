package com.example.kennyr.onboardingproject.tasks.tasklist;

import android.support.v4.app.Fragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent
public interface TaskListComponent {

    void inject(TaskListFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder fragment(Fragment fragment);

        TaskListComponent build();
    }
}
