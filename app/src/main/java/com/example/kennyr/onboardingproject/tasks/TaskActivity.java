package com.example.kennyr.onboardingproject.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kennyr.onboardingproject.R;
import com.example.kennyr.onboardingproject.app.App;
import com.example.kennyr.onboardingproject.tasks.tasklist.TaskListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TaskActivity extends AppCompatActivity implements TaskContract.ComponentProvider, TaskContract.TaskActivityContract {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TaskComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        component = ((App) getApplication()).getComponent().provideTaskBuilder().context(this).toolbar(toolbar).build();
        component.inject(this);

        if (savedInstanceState == null) {
            replaceFragment(new TaskListFragment(), TaskContract.FragmentTag.LIST, null);
        }
    }

    @Override
    public TaskContract.Component getTaskComponent() {
        return component;
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (tag.equalsIgnoreCase(TaskContract.FragmentTag.DETAIL)) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment, tag);

        if (!tag.equalsIgnoreCase(TaskContract.FragmentTag.DETAIL)) {
            getSupportFragmentManager().popBackStack();
        }

        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
