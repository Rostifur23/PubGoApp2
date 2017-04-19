package ie.jbmnetworks.pubgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by Rostifur on 19/04/2017.
 */

public class PrefMan {

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private String startHour = "startHour";
    private PrefMan() {}

    private PrefMan(Context mContext) {
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = prefs.edit();
    }

    public static PrefMan getInstance(Context mContext)
    {
        PrefMan _app = null;
        if (_app == null)
            _app = new PrefMan(mContext);
        return _app;
    }

    public void setPID(int PID){
        editor.putInt(startHour, PID);
        editor.apply();

    }
    public int getPID(){
        int selectPID = prefs.getInt(startHour, -1);
        return selectPID;
    }
}
