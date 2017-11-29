package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

/**
 * Simple example of how to use Spannable String in Android. This is based on:
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SpannableString";
    private static final String ORIGINAL_TEXT = "This text should be highlighted";
    private static final String WORD_TO_BE_HIGHLIGHTED = "should";
    private static final int HIGHLIGHT_COLOR_RESOURCE = R.color.highLightedColor;
    private static final int TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE = android.R.color.white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the view
        TextView spannableTV = findViewById(R.id.spannable_tv);

        spannableTV.setText(
                createSpannableString(this, ORIGINAL_TEXT, WORD_TO_BE_HIGHLIGHTED,
                        HIGHLIGHT_COLOR_RESOURCE, TEXT_COLOR_HIGHLIGHTED_COLOR_RESOURCE));
    }

    /**
     * Create a spannable string with some text highlighted. The highlight starts counting the words
     * from the end
     * @param context
     *      The context needed to get the highlight colours
     * @param text
     *      The text to be highlighted
     * @param wordToBeHighlighted
     *      The word to be highlighted. Note this word should be part of the text
     * @param highlightColor
     *      The color of the highlight (background color)
     * @param textColor
     *      The color of the text after highlight. This should contrast wit the highlight color
     * @return
     *      Spannable string with the text highlighted
     */
    private SpannableString createSpannableString(
            Context context, String text, String wordToBeHighlighted,
            int highlightColor,int textColor) {
        String convertedText = convertTextForHighlight(text, wordToBeHighlighted);

        // If there were some problem on converting the text, then don't do anything
        if (convertedText == null) {
            Log.e(TAG, "Error converting the text");
            return new SpannableString(text);
        }

        SpannableString spannableString = new SpannableString(convertedText);

        int highlightWordStartPosition = getHighlightWordStartPosition(convertedText, wordToBeHighlighted);

        if (highlightWordStartPosition < 0) {
            Log.e(TAG, "Error getting the highlight word start position");
            return new SpannableString(text);
        }

        spannableString.setSpan(
                new RoundedBackgroundSpan(context.getResources().getColor(highlightColor),
                        context.getResources().getColor(textColor)), highlightWordStartPosition,
                highlightWordStartPosition + wordToBeHighlighted.length() + 2, 0);

        return spannableString;
    }

    /**
     * Convert the original text for highlight. Basically some extra space is added
     * at the beginning at the bottom of the last word
     * @param originalText
     *      The original text to be converted
     * @param wordToBeHighlighted
     *      The word to be highlighted
     * @return
     *      The converted text to have the last word highlighted
     */
    private String convertTextForHighlight(String originalText, String wordToBeHighlighted) {
        String wordToBeHighlightedTrimmed = wordToBeHighlighted.trim();

        int highlighterTextStartPosition =
                originalText.indexOf(wordToBeHighlightedTrimmed);

        if (highlighterTextStartPosition < 0) {
            return null;
        }

        // Get the first part of the text
        String convertedText =
                originalText.substring(0, highlighterTextStartPosition);

        // Add the last word and some white spaces
        // 4 white space at the beginning = original white space + space for the margins
        convertedText += "    ";

        // Attach the highlighted text
        convertedText += wordToBeHighlighted;

        // Attach some spaces at the end
        convertedText += "  ";

        // Attach the rest of the word
        convertedText += originalText.substring(
                originalText.indexOf(wordToBeHighlighted) + wordToBeHighlighted.length(),
                originalText.length());

        return convertedText;
    }

    /**
     * Return the position that the last word should start. This is one position
     * before the index of the last word. That extra space is used for padding
     * @param text
     *      The text to check
     * @param wordToBeHighlighted
     *      The word to be highlighted
     * @return
     *      The position on the text which the highlight should start
     */
    private int getHighlightWordStartPosition(String text, String wordToBeHighlighted) {
        int index = text.indexOf(wordToBeHighlighted);
        if (index < 0) {
            return index;
        }

        return index - 1;
    }

}
