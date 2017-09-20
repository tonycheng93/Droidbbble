package com.sky.droidbbble.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/13.
 */

public class FontsManager {

    private static final String INIT_EXCEPTION = "must init before use this";

    /**
     * default typeface
     */
    private static Typeface defaultTypeface = null;

    private FontsManager() {
        //utility class，no instance
    }

    /**
     * initial form Assets
     *
     * @param context Context
     * @param path    String
     */
    public static void initFromAssets(@NonNull Context context, @NonNull String path) {
        try {
            defaultTypeface = Typeface.createFromAsset(context.getAssets(), path);
        } catch (Exception e) {
            throw new IllegalStateException("initial typeface failed,please check font path.");
        }
    }

    /**
     * change font
     *
     * @param activity Activity
     */
    public static void changeFonts(Activity activity) {
        if (defaultTypeface == null) {
            throw new IllegalStateException(INIT_EXCEPTION);
        }
        changeFonts((ViewGroup) activity.findViewById(android.R.id.content), defaultTypeface);
    }

    /**
     * change font
     *
     * @param view View
     */
    public static void changeFonts(View view) {
        if (defaultTypeface == null) {
            throw new IllegalStateException(INIT_EXCEPTION);
        }
        changeFonts(view, defaultTypeface);
    }

    /**
     * change font
     *
     * @param viewGroup ViewGroup
     */
    public static void changeFonts(ViewGroup viewGroup) {
        if (defaultTypeface == null) {
            throw new IllegalStateException(INIT_EXCEPTION);
        }
        changeFonts(viewGroup, defaultTypeface);
    }

    /**
     * change font
     *
     * @param view     View
     * @param typeface Typeface
     */
    public static void changeFonts(View view, Typeface typeface) {
        try {
            if (view instanceof ViewGroup) {
                changeFonts((ViewGroup) view, typeface);
            } else if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
                //PS:Button,EditTextView all extends TextView
            }
        } catch (Exception e) {
            Timber.e(e.toString());
        }
    }

    /**
     * change font
     *
     * @param viewGroup ViewGroup
     * @param typeface  Typeface
     */
    private static void changeFonts(ViewGroup viewGroup, Typeface typeface) {
        try {
            for (int i = 0, count = viewGroup.getChildCount(); i < count; i++) {
                final View view = viewGroup.getChildAt(i);
                if (view instanceof ViewGroup) {
                    changeFonts((ViewGroup) view, typeface);
                } else if (view != null) {
                    changeFonts(view, typeface);
                }
            }
        } catch (Exception e) {
            Timber.e(e.toString());
        }
    }
}
