package com.mentor.app.utils;

import androidx.viewpager.widget.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {

    /**
     * Tabs / dots / whatever to show in edit mode.
     */
    static final int EDIT_MODE_COUNT = 5;

    /**
     * Selected page in edit mode.
     */
    static final int EDIT_MODE_PAGE = EDIT_MODE_COUNT / 2;

    /**
     * Tab title in edit mode;
     */
    static final String EDIT_MODE_TITLE = "Page %d";

    /**
     * Title text used when no title is provided by the adapter.
     */
    static final CharSequence EMPTY_TITLE = "";

    /**
     * Not really part of a PageIndicator, but used everywhere.
     */
    static final int INVALID_POINTER = -1;

    /**
     * Bind the indicator to a ViewPager.
     *
     * @param view
     */
    void setViewPager(ViewPager view);

    /**
     * Bind the indicator to a ViewPager.
     *
     * @param view
     * @param initialPosition
     */
    void setViewPager(ViewPager view, int initialPosition);

    /**
     * <p>Set the current page of both the ViewPager and indicator.</p>
     *
     * <p>This <strong>must</strong> be used if you need to set the page before
     * the views are drawn on screen (e.g., default start page).</p>
     *
     * @param item
     */
    void setCurrentItem(int item);

    /**
     * Set a page change listener which will receive forwarded events.
     *
     * @param listener
     */
    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    /**
     * Notify the indicator that the fragment list has changed.
     */
    void notifyDataSetChanged();
}