package ie.jbmnetworks.pubgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by Rostifur on 19/04/2017.
 */
//this file is for saving the int Pub ID to the users shared references
public class PrefMan {

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private String startHour = "startHour";
    private PrefMan() {}

    private PrefMan(Context mContext) {
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = prefs.edit();
    }
    //collecting the current version of the int you want to save
    public static PrefMan getInstance(Context mContext)
    {
        PrefMan _app = null;
        if (_app == null)
            _app = new PrefMan(mContext);
        return _app;
    }
    //setting the Pub ID int var
    public void setPID(int PID){
        editor.putInt(startHour, PID);
        editor.apply();

    }
    //getting the pub ID int var
    public int getPID(){
        int selectPID = prefs.getInt(startHour, -1);
        return selectPID;
    }
}
