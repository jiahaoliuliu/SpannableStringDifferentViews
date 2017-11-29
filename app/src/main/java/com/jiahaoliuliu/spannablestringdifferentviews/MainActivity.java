package com.jiahaoliuliu.spannablestringdifferentviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.widget.TextView;

/**
 * Simple example of how to use Spannable String in Android. This is based on:
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class MainActivity extends AppCompatActivity {

    private static final String ORIGINAL_TEXT = "This text should be highlighted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the view
        TextView spannableTV = findViewById(R.id.spannable_tv);

        spannableTV.setText(createAndModifySpannableString());
    }

    private SpannableString createAndModifySpannableString() {
        String convertedText = convertTextForHighLight(ORIGINAL_TEXT);

        SpannableString spannableString =
                new SpannableString(convertedText);

        int lastWordStartPosition = getLastWordStartPosition(convertedText);

        spannableString.setSpan(
                new RoundedBackgroundSpan(getColorFromResources(R.color.highLightedColor),
                        getColorFromResources(android.R.color.white)),
                lastWordStartPosition, spannableString.length(), 0);

        return spannableString;
    }

    /**
     * Return the position that the last word should start. This is one position
     * before the index of the last word. That extra space is used for padding
     * @param text
     *      The text to check
     * @return
     *      The position on the text which the highlight should start
     */
    private int getLastWordStartPosition(String text) {
        String[] words = text.split(" ");

        return text.indexOf(words[words.length-1]) - 1;
    }

    private int getColorFromResources(int res) {
        return getResources().getColor(res);
    }

    /**
     * Convert the original text for highlight. Basically some extra space is added
     * at the beginning at the bottom of the last word
     * @param originalText
     *      The original text to be converted
     * @return
     *      The converted text to have the last word highlighted
     */
    private String convertTextForHighLight(String originalText) {
        String[] words = originalText.split(" ");

        // Get the first part of the text
        String convertedText =
                originalText.substring(0, originalText.indexOf(words[words.length-1]));

        // Add the last word and some white spaces
        // 4 white space at the beginning = original white space + space for the margins
        // 1 white space at the end
        convertedText += "    " + words[words.length -1] + " ";

        return convertedText;
    }
}
