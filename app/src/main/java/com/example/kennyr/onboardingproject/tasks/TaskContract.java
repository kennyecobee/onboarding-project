package com.example.kennyr.onboardingproject.tasks;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;

import com.example.kennyr.onboardingproject.tasks.taskdetail.TaskDetailComponent;
import com.example.kennyr.onboardingproject.tasks.taskdetail.TaskDetailModule;
import com.example.kennyr.onboardingproject.tasks.tasklist.TaskListComponent;
import com.example.kennyr.onboardingproject.tasks.tasklist.TaskListModule;

public interface TaskContract {

    interface TaskActivityContract {

        void replaceFragment(Fragment fragment, String tag, Bundle bundle);

        void updateTitle(String title);
    }

    interface ComponentProvider {
        Component getTaskComponent();
    }

    interface Component {
        TaskListComponent with(TaskListModule module);

        TaskDetailComponent with(TaskDetailModule module);
    }

    @StringDef({FragmentTag.LIST, FragmentTag.DETAIL})
    @interface FragmentTag {
        String LIST = "_fragment_task_list";
        String DETAIL = "_fragment_task_detail";
    }
}
