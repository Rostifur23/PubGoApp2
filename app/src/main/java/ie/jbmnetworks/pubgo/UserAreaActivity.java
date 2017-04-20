package ie.jbmnetworks.pubgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserAreaActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    //MapActivity Transfer;
    ArrayList<String> placeIdArray = new ArrayList<String>();
    ArrayList<String> pubIdArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        requestQueue = Volley.newRequestQueue(this);
        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etAge = (EditText) findViewById(R.id.etAge);


        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        //Test Button


        //map link button.
        Button bMap = (Button) findViewById(R.id.bMap);

        bMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(UserAreaActivity.this, MapActivity.class));
                Intent i = new Intent(UserAreaActivity.this, MapActivity.class);

                //Sending off Volley request
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://pubgo-jackbourkemckenna.c9users.io/AppplaceId.php", null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    JSONArray jsonArray = response.getJSONArray("arr");


                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        //creating two JSON Arrays to store our array from the DB
                                        JSONObject Place_id = jsonArray.getJSONObject(i);
                                        JSONObject Pub_id = jsonArray.getJSONObject(i);
                                        //setting them to the two arrayLists declared at the top of the class
                                        placeIdArray.add(Place_id.getString("place_id"));
                                        pubIdArray.add(Pub_id.getString("pub_id"));


                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley", "Error");

                            }
                        }
                );
                //putString method is for passing the arrayList to the next activity identifying it by a string
                i.putStringArrayListExtra("place", placeIdArray);
                i.putStringArrayListExtra("pub", pubIdArray);
                requestQueue.add(jsonObjectRequest);
                //start the next activity
                startActivity(i);


            }


        });
    }


}
