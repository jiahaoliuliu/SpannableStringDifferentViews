package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/**
 * Created by jiahaoliuliu on 11/29/17.
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class RoundedBackgroundSpan extends ReplacementSpan {

    private static final int DEFAULT_CORNER_RADIUS = 3; // dp
    private static final int DEFAULT_HORIZONTAL_PADDING = 3; //dp
    private static final int DEFAULT_VERTICAL_PADDING = 3; //dp
    private final Context context;
    private final int backgroundColor;
    private final int textColor;
    private final float cornerRadiusPixel;
    private final float horizontalPaddingPixel;
    private final float verticalPaddingPixel;

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
        this.cornerRadiusPixel = ViewUtils.convertDpToPixels(context, DEFAULT_CORNER_RADIUS);
        this.horizontalPaddingPixel =
                ViewUtils.convertDpToPixels(context, DEFAULT_HORIZONTAL_PADDING);
        this.verticalPaddingPixel = ViewUtils.convertDpToPixels(context, DEFAULT_VERTICAL_PADDING);
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
                                 int cornerRadius, int horizontalPadding, int verticalPadding) {
        super();
        this.context = context;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.cornerRadiusPixel = ViewUtils.convertDpToPixels(context, cornerRadius);
        this.horizontalPaddingPixel = ViewUtils.convertDpToPixels(context, horizontalPadding);
        this.verticalPaddingPixel = ViewUtils.convertDpToPixels(context, verticalPadding);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {

        // Set background
        RectF rectF = new RectF(x, top - verticalPaddingPixel,x + size,
                bottom + verticalPaddingPixel);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rectF, cornerRadiusPixel, cornerRadiusPixel, paint);

        // Set text
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + horizontalPaddingPixel, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        size = Math.round(paint.measureText(text, start, end) + 2 * horizontalPaddingPixel);
        return size;
    }
}
