package ie.jbmnetworks.pubgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import static ie.jbmnetworks.pubgo.R.id.tvPlace;

public class DrinkListActivity extends AppCompatActivity {

    TextView placeIdText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);


        placeIdText = (TextView) findViewById(R.id.PIDTextView);

        //calling in the instance of the array so we can use in a volley method to display the drinks
        int pid = PrefMan.getInstance(this).getPID();
        placeIdText.setText("Pub Reference in ArrayList " + pid);
    }
}
