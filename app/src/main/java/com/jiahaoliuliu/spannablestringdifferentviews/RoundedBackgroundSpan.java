package com.jiahaoliuliu.spannablestringdifferentviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/**
 * Created by jiahaoliu on 11/29/17.
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class RoundedBackgroundSpan extends ReplacementSpan {

    private static final int CORNER_RADIUS = 8;
    private static final float HORIZONTAL_PADDING = 20.0f;
    private static final float VERTICAL_PADDING = 20.0f;
    private int backgroundColor = 0;
    private int textColor = 0;

    public RoundedBackgroundSpan(int backgroundColor, int textColor) {
        super();
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        // Set background
        RectF rectF = new RectF(x-HORIZONTAL_PADDING, top-VERTICAL_PADDING,
                x + paint.measureText(text, start, end)+HORIZONTAL_PADDING, bottom+VERTICAL_PADDING);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rectF, CORNER_RADIUS, CORNER_RADIUS, paint);

        // Set text
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end) + HORIZONTAL_PADDING);
    }
}
