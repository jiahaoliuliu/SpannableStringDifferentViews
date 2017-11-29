package com.jiahaoliuliu.spannablestringdifferentviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableString;
import android.util.Log;

/**
 * Created by jiahaoliu on 11/29/17.
 * TODO: Set it case insensitive
 * This is based on:
 * https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text
 * https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable
 */
public class HighlightedSpannableString extends SpannableString {

    private static final String TAG = "HighlightedSpannableString";

    /**
     * Inherited constructor. Do not use
     * @param source
     */
    private HighlightedSpannableString(CharSequence source) {
        super(source);
    }

    public static HighlightedSpannableString create(Context context, String originalText,
                                                    String wordToBeHighLighted) {
        return create(context, originalText, wordToBeHighLighted, android.R.color.black,
                android.R.color.white);
    }

    @SuppressLint("LongLogTag")
    public static HighlightedSpannableString create(Context context,
           String originalText, String wordToBeHighlighted, int highlightColour, int textColour) {

        String convertedText = convertTextForHighlight(originalText, wordToBeHighlighted);

        if (convertedText == null) {
            Log.e(TAG, "Error converting the text");
            return new HighlightedSpannableString(originalText);
        }

        HighlightedSpannableString highlightedSpannableString =
                new HighlightedSpannableString(convertedText);

        int highlightWordStartPosition = getWordToBeHighlightedPosition(convertedText,
                wordToBeHighlighted) - 1;
        highlightedSpannableString.setSpan(
                new RoundedBackgroundSpan(context.getResources().getColor(highlightColour),
                        context.getResources().getColor(textColour)), highlightWordStartPosition,
                highlightWordStartPosition + wordToBeHighlighted.length() + 2, 0);
        return highlightedSpannableString;
    }

    /**
     * Convert the original text for highlight. Basically some extra space is added
     * at the beginning at the bottom of the last word
     * @return
     *      The converted text to have the last word highlighted
     */
    private static String convertTextForHighlight(String originalText, String wordToBeHighlighted) {

        int highlighterTextStartPosition =
                getWordToBeHighlightedPosition(originalText, wordToBeHighlighted);
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

    private static int getWordToBeHighlightedPosition(String originalText, String wordToBeHighlighted) {
        String wordToBeHighlightedTrimmed = wordToBeHighlighted.trim();
        return originalText.indexOf(wordToBeHighlightedTrimmed);
    }
}
