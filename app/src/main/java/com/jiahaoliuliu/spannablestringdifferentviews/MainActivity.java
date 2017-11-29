package com.jiahaoliuliu.spannablestringdifferentviews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Simple example of how to use Spannable String in Android. This is based on:
 * http://androidcocktail.blogspot.ae/2014/03/android-spannablestring-example.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the view
        TextView spannableTV = findViewById(R.id.spannable_tv);

        spannableTV.setText(createAndModifySpannableString());
    }

    private SpannableString createAndModifySpannableString() {
        String text = "This text should be highlighted";

        SpannableString spannableString =
                new SpannableString(text);

        int lastStringStartPosition = getLastStringStartPosition(text);

        // change text color
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), lastStringStartPosition,
                spannableString.length(), 0);

        // highlight text
        spannableString.setSpan(
                new BackgroundColorSpan(getColorFromResources(R.color.highLightedColor)),
                lastStringStartPosition, spannableString.length(), 0);

        return spannableString;
    }

    private int getLastStringStartPosition(String text) {
        String[] words = text.split(" ");

        return text.indexOf(words[words.length-1]);
    }

    private int getColorFromResources(int res) {
        return getResources().getColor(res);
    }
}
