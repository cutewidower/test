package com.example.donordarah.model;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public abstract class BaseModel extends AppCompatActivity {

    public static void toastSuccess(Context context, String msg) {
        Toasty.success(context, msg, Toasty.LENGTH_SHORT, true).show();
    }

    public static void toastWarning(Context context, String msg) {
        Toasty.warning(context, msg, 0, true).show();
    }

    public static void toastError(Context context, String msg) {
        Toasty.error(context, msg, Toasty.LENGTH_LONG, true).show();
    }
}
