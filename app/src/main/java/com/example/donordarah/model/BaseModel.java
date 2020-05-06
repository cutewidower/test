package com.example.donordarah.model;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.donordarah.R;
import es.dmoral.toasty.Toasty;

public abstract class BaseModel extends AppCompatActivity {

    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void toastSuccess(Context context, String msg) {
        Toasty.success(context, msg, Toasty.LENGTH_SHORT, true).show();
    }

    public static void toastWarning(Context context, String msg) {
        Toasty.warning(context, msg, 0, true).show();
    }

    public static void toastError(Context context, String msg) {
        Toasty.error(context, msg, Toasty.LENGTH_LONG, true).show();
    }

    private final int[] iconDrawable = {
            R.drawable.ic_calendar,
            R.drawable.ic_calendar_1,
            R.drawable.ic_calendar_2,
            R.drawable.ic_calendar_3,
            R.drawable.ic_calendar_4,
            R.drawable.ic_calendar_5,
            R.drawable.ic_calendar_6
    };

/*
      private final int[] iconDrawable = {
            R.drawable.ic_drum,
            R.drawable.ic_balloons,
            R.drawable.ic_confetti,
            R.drawable.ic_garland,
            R.drawable.ic_circus
       };
 */

}
