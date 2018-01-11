package com.example.kennyr.onboardingproject;

import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class DatePickerDialog implements android.app.DatePickerDialog.OnDateSetListener {

    private android.app.DatePickerDialog dialog;
    private ObservableEmitter<Date> emitter;

    public DatePickerDialog(Context context, int year, int month, int dayOfMonth) {
        dialog = new android.app.DatePickerDialog(context, this, year, month, dayOfMonth);
    }

    public Observable<Date> create() {
        return Observable.create(subscriber -> {
            this.emitter = subscriber;
            dialog.show();
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(i, i1, i2);
        emitter.onNext(calendar.getTime());
    }
}
