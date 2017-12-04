package com.jiahaoliuliu.spannablestringdifferentviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;

/**
 * Created by jiahaoliuliu on 11/29/17.
 * Special class that extends from SpannableString, which by providing
 * <ul>
 * <li> the application context</li>
 * <li> the original text </li>
 * <li> the word to be highlighted </li>
 * <li> [Optional] the highlight colour </li>
 * <li> [Optional] the text color </li>
 * </ul>
 * Will highlight the desired text. <br>
 *
 * TODO: Set it case insensitive <br>
 * This is based on:
 * <ul>
 * <li>https://stackoverflow.com/questions/19292838/android-spannablestring-set-background-behind-part-of-text</li>
 * <li>https://stackoverflow.com/questions/27198155/adding-a-padding-margin-to-a-spannable</li>
 * </ul>
 */
public class HighlightedSpannableString extends SpannableString {

    // Default values
    private static final int DEFAULT_BACKGROUND_COLOUR = android.R.color.black;
    private static final int DEFAULT_TEXT_COLOUR = android.R.color.white;

    /**
     * Inherited constructor. Do not use
     * @param source
     */
    private HighlightedSpannableString(CharSequence source) {
        super(source);
    }

    public static HighlightedSpannableString create(Context context, String originalText,
                                                    String wordToBeHighLighted) {
        return create(context, originalText, wordToBeHighLighted, DEFAULT_BACKGROUND_COLOUR,
                DEFAULT_TEXT_COLOUR);
    }

    public static HighlightedSpannableString create(Context context,
                                                    String originalText, String wordToBeHighlighted,
                                                    int highlightColour, int textColour) {
        HighlightedSpannableString highlightedSpannableString =
                new HighlightedSpannableString(originalText);

        int highlightWordStartPosition = originalText.indexOf(wordToBeHighlighted);

        // If the text does not exist, do not do anything
        if (highlightWordStartPosition < 0) {
            return highlightedSpannableString;
        }

        highlightedSpannableString.setSpan(
                new RoundedBackgroundSpan(context,
                        ContextCompat.getColor(context, highlightColour),
                        ContextCompat.getColor(context, textColour)), highlightWordStartPosition,
                highlightWordStartPosition + wordToBeHighlighted.length(), 0);
        return highlightedSpannableString;
    }

    public static HighlightedSpannableString create(Context context,
           String originalText, String wordToBeHighlighted, int highlightColour, int textColour,
           int cornerRadius, int horizontalPadding, int verticalPadding) {

        HighlightedSpannableString highlightedSpannableString =
                new HighlightedSpannableString(originalText);

        int highlightWordStartPosition = originalText.indexOf(wordToBeHighlighted);

        // If the text does not exist, do not do anything
        if (highlightWordStartPosition < 0) {
            return highlightedSpannableString;
        }

        highlightedSpannableString.setSpan(
                new RoundedBackgroundSpan(context,
                        ContextCompat.getColor(context, highlightColour),
                        ContextCompat.getColor(context, textColour), cornerRadius,
                        horizontalPadding, verticalPadding), highlightWordStartPosition,
                highlightWordStartPosition + wordToBeHighlighted.length(), 0);
        return highlightedSpannableString;
    }

}
