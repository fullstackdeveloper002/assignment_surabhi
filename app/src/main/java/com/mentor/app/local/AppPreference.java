package com.mentor.app.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * The type App preference.
 */
public class AppPreference
{

    private static final String PREFS_FILE_NAME = "unicharm_ref";
    private static SharedPreferences settings;
    private static AppPreference instance;


    /**
     * GETTING INSTANCE OF APP PREFERENCE
     *
     * @param context the context
     * @return instance
     */
    public static AppPreference getInstance(Context context)
    {
        if (instance == null)
        {
            settings = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
            instance = new AppPreference();
            return instance;
        }
        return instance;
    }

    /**
     * ADD VALUES IN PREFRENCE WITH SPECIFIC KEY
     *
     * @param preferencesKey the preferences key
     * @param text           the text
     */
    public void addValue(PreferenceKeys preferencesKey, String text)
    {
        Editor editor;
        editor = settings.edit();
        editor.putString(preferencesKey.getKey(), text);
        editor.apply();
    }

    /**
     * ADD BOOLEAN VALUES IN PREFRENCE KEY
     *
     * @param preferencesKey the preferences key
     * @param value          the value
     */
    public void addBoolean(PreferenceKeys preferencesKey, Boolean value)
    {
        try
        {
            Editor editor;
            editor = settings.edit();
            editor.putBoolean(preferencesKey.getKey(), value);
            editor.apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ADD INTEGER VALUE WITH SPECIFIC KEY
     *
     * @param preferencesKey the preferences key
     * @param value          the value
     */
    public void addInt(PreferenceKeys preferencesKey, Integer value)
    {
        try
        {
            Editor editor;
            editor = settings.edit();
            editor.putInt(preferencesKey.getKey(), value);
            editor.apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * GET PREFRENCE SAVED VALUE BY SPECIFIC KEY
     *
     * @param preferencesKey the preferences key
     * @return value
     */
    public String getValue(PreferenceKeys preferencesKey)
    {
        String text;
        text = settings.getString(preferencesKey.getKey(), "");
        return text;
    }

    /**
     * Gets int.
     *
     * @param preferencesKey the preferences key
     * @return the int
     */
    public int getInt(PreferenceKeys preferencesKey)
    {
        return settings.getInt(preferencesKey.getKey(), 0);
    }


    /**
     * GET BOOLEAN PREFRENCES
     *
     * @param preferencesKey the preferences key
     * @return boolean
     */
    public boolean getBoolean(PreferenceKeys preferencesKey)
    {
        return settings.getBoolean(preferencesKey.getKey(), false);
    }


    /**
     * CLEAR ALL PREFRENCE
     */
    public void clearSharedPreference()
    {
        Editor editor;
        editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * REMOVE SPECIFIC VALUE
     *
     * @param preferencesKey the preferences key
     */
    public void removeValue(PreferenceKeys preferencesKey)
    {
        Editor editor;
        editor = settings.edit();
        editor.putString(preferencesKey.getKey(), "");
        editor.apply();
    }


}