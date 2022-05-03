package com.mentor.pipes.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;

/**
 * The type Base view model.
 *
 * @param <N> the type parameter
 */
public abstract class BaseViewModel<N> extends BaseObservable {

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private N mNavigator;

    /**
     * Instantiates a new Base view model.
     */
    public BaseViewModel() {

    }

    /**
     * Gets is loading.
     *
     * @return the is loading
     */
    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    /**
     * Sets is loading.
     *
     * @param isLoading the is loading
     */
    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    /**
     * Gets navigator.
     *
     * @return the navigator
     */
    public N getNavigator() {
        return mNavigator;
    }

    /**
     * Sets navigator.
     *
     * @param navigator the navigator
     */
    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }


}
