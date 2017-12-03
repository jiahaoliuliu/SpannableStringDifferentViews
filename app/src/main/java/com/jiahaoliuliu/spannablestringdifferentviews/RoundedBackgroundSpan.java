package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import android.util.TypedValue;

/**
 * Created by jiahaoliuliu on 11/29/17.
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class RoundedBackgroundSpan extends ReplacementSpan {

    private static final int DEFAULT_CORNER_RADIUS = 8;
    private static final int DEFAULT_HORIZONTAL_PADDING = 3; //dp
    private static final int DEFAULT_VERTICAL_PADDING = 3; //dp
    private final Context context;
    private final int backgroundColor;
    private final int textColor;
    private final float horizontalPaddingFloat;
    private final float verticalPaddingFloat;

    private int size;

    /**
     * Default constructor. The values will be set by default.
     * @param context
     *      The context of the application
     * @param backgroundColor
     *      The background colour to apply
     * @param textColor
     *      The text color to apply
     */
    public RoundedBackgroundSpan(Context context, int backgroundColor, int textColor) {
        super();
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.horizontalPaddingFloat = convertDpToPixels(DEFAULT_HORIZONTAL_PADDING);
        this.verticalPaddingFloat = convertDpToPixels(DEFAULT_VERTICAL_PADDING);
    }

    /**
     * Extended constructor.
     * @param context
     *      The context of the application
     * @param backgroundColor
     *      The background colour to apply
     * @param textColor
     *      The text color to apply
     * @param horizontalPadding
     *      The horizontal padding on dp
     * @param verticalPadding
     *      The vertical padding on dp
     */
    public RoundedBackgroundSpan(Context context, int backgroundColor, int textColor,
                                 int horizontalPadding, int verticalPadding) {
        super();
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.horizontalPaddingFloat = convertDpToPixels(horizontalPadding);
        this.verticalPaddingFloat = convertDpToPixels(verticalPadding);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {

        // Set background
        RectF rectF = new RectF(x, top - verticalPaddingFloat,x + size,
                bottom + verticalPaddingFloat);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rectF, DEFAULT_CORNER_RADIUS, DEFAULT_CORNER_RADIUS, paint);

        // Set text
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + horizontalPaddingFloat, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        size = Math.round(paint.measureText(text, start, end) + 2 * horizontalPaddingFloat);
        return size;
    }

    public float convertDpToPixels(int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }
}
