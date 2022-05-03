package com.mentor.app.local;


/**
 * The enum Preference keys.
 */
public enum PreferenceKeys {


    BOOK_MEETING("BOOK_MEETING");

    private String text;

    PreferenceKeys(final String text) {
        this.text = text;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}

