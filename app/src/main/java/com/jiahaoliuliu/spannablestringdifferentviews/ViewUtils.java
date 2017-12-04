package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by jiahaoliu on 12/4/17.
 */
public class ViewUtils {

    public static float convertDpToPixels(Context context, int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }
}
