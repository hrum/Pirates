package com.shuvzero.pirates.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

public abstract class GeneralView extends View {

    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    public static final int FONT_COLOR = 0xFFF2E3A7;

    public GeneralView(Context context) {
        super(context);
    }

    protected final Drawable getDrawable(int id) {
        if(id != 0)
            return ContextCompat.getDrawable(getContext(), id);
        return null;
    }

}
