package com.jiahaoliuliu.spannablestringdifferentviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Simple example of how to use Spannable String in Android. This is based on:
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class MainActivity extends AppCompatActivity {

    private static final String ORIGINAL_TEXT = "This text should be highlighted";
    // TODO: Set it case insensitive
    private static final String WORD_TO_BE_HIGHLIGHTED = "text";
    private static final int HIGHLIGHT_COLOR_RESOURCE = R.color.highLightedColor;
    private static final int TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE = android.R.color.white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the view
        TextView spannableTV = findViewById(R.id.spannable_tv);

        spannableTV.setText(
                HighlightedSpannableString.create(this, ORIGINAL_TEXT, WORD_TO_BE_HIGHLIGHTED
                        // Uncomment this to use customized colours
//                        ,HIGHLIGHT_COLOR_RESOURCE, TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE
                ));
    }
}
