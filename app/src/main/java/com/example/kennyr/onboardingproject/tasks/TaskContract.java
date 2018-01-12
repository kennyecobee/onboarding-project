package com.example.kennyr.onboardingproject.tasks;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;

import com.example.kennyr.onboardingproject.tasks.taskdetail.TaskDetailComponent;
import com.example.kennyr.onboardingproject.tasks.tasklist.TaskListComponent;

public interface TaskContract {

    interface TaskActivityContract {

        void replaceFragment(Fragment fragment, String tag, Bundle bundle);
    }

    interface ComponentProvider {
        Component getTaskComponent();
    }

    interface Component {
        TaskListComponent.Builder provideTaskListBuilder();

        TaskDetailComponent.Builder provideTaskDetailBuilder();
    }

    @StringDef({FragmentTag.LIST, FragmentTag.DETAIL})
    @interface FragmentTag {
        String LIST = "_fragment_task_list";
        String DETAIL = "_fragment_task_detail";
    }
}
