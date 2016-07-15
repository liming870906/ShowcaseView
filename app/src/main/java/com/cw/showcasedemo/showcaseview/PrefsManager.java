package com.cw.showcasedemo.showcaseview;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefsManager {

    public static int SEQUENCE_NEVER_STARTED = 0;
    public static int SEQUENCE_FINISHED = -1;


    private static final String PREFS_NAME = "material_showcaseview_prefs";
    private static final String STATUS = "status_";
    private String showcaseID = null;
    private Context context;

    public PrefsManager(Context context, String showcaseID) {
        this.context = context;
        this.showcaseID = showcaseID;
    }


    /***
     * METHODS FOR INDIVIDUAL SHOWCASE VIEWS
     */
    public boolean hasFired() {
        int status = getSequenceStatus();
        return (status == SEQUENCE_FINISHED);
    }

    public void setFired() {
        setSequenceStatus(SEQUENCE_FINISHED);
    }

    /***
     * METHODS FOR SHOWCASE SEQUENCES
     */
    public int getSequenceStatus() {
        return context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED);

    }

    public void setSequenceStatus(int status) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, status).apply();
    }

    /**
     * 重置SharedPreferences的存储
     */
    public void resetShowcase() {
        resetShowcase(context, showcaseID);
    }

    public static void resetShowcase(Context context, String showcaseID) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().putInt(STATUS + showcaseID, SEQUENCE_NEVER_STARTED).apply();
    }

    public static void resetAll(Context context) {
        SharedPreferences internal = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        internal.edit().clear().apply();
    }

    public void close() {
        context = null;
    }
}
