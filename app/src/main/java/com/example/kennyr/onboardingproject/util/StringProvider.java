package com.example.kennyr.onboardingproject.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;

import javax.inject.Inject;

public class StringProvider {

    private Resources resources;

    @Inject
    StringProvider(Context context) {
        resources = context.getResources();
    }

    public String getString(@StringRes int stringRes) {
        return resources.getString(stringRes);
    }
}
