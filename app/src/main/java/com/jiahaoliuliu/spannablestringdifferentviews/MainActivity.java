package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
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
    private static final int WORD_POSITION_TO_BE_HIGHLIGHTED_FROM_END = 2;
    private static final int HIGHLIGHT_COLOR_RESOURCE = R.color.highLightedColor;
    private static final int TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE = android.R.color.white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the view
        TextView spannableTV = findViewById(R.id.spannable_tv);

        spannableTV.setText(
                createSpannableString(this, ORIGINAL_TEXT, WORD_POSITION_TO_BE_HIGHLIGHTED_FROM_END,
                        HIGHLIGHT_COLOR_RESOURCE, TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE));
    }

    private SpannableString createSpannableString(
            Context context, String text, int wordPositionCountingFromEnd, int highlightColor,
                                                           int textColor) {
        String convertedText = convertTextForHighLight(text, wordPositionCountingFromEnd);

        SpannableString spannableString =
                new SpannableString(convertedText);

        int lastWordStartPosition = getWordStartPosition(convertedText, wordPositionCountingFromEnd);

        spannableString.setSpan(
                new RoundedBackgroundSpan(context.getResources().getColor(highlightColor),
                        context.getResources().getColor(textColor)),
                lastWordStartPosition, spannableString.length(), 0);

        return spannableString;
    }

    /**
     * Convert the original text for highlight. Basically some extra space is added
     * at the beginning at the bottom of the last word
     * @param originalText
     *      The original text to be converted
     * @return
     *      The converted text to have the last word highlighted
     */
    private String convertTextForHighLight(String originalText, int wordPositionCountingFromEnd) {
        String[] words = originalText.split(" ");

        int highlighterTextStartPosition =
                originalText.indexOf(words[words.length-wordPositionCountingFromEnd]);

        // Get the first part of the text
        String convertedText =
                originalText.substring(0, highlighterTextStartPosition);

        // Add the last word and some white spaces
        // 4 white space at the beginning = original white space + space for the margins
        convertedText += "    ";

        // Attach the rest of the words
        convertedText +=
                originalText.substring(
                        highlighterTextStartPosition, originalText.length()) + " ";

        return convertedText;
    }

    /**
     * Return the position that the last word should start. This is one position
     * before the index of the last word. That extra space is used for padding
     * @param text
     *      The text to check
     * @return
     *      The position on the text which the highlight should start
     */
    private int getWordStartPosition(String text, int wordPositionCountingFromEnd) {
        String[] words = text.split(" ");

        return text.indexOf(words[words.length - wordPositionCountingFromEnd]) - 1;
    }

}
