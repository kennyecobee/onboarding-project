package com.example.kennyr.onboardingproject.tasks;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Singleton
@Subcomponent
public interface TaskComponent extends TaskContract.Component {
    void inject(Activity activity);

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        @BindsInstance
        Builder toolbar(Toolbar toolbar);

        TaskComponent build();
    }
}
