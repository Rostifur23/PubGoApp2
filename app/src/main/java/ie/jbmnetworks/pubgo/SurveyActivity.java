package ie.jbmnetworks.pubgo;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        final EditText etGender =(EditText) findViewById(R.id.etGender);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etDrinkP = (EditText) findViewById(R.id.etDrinkP);

        final Button bSubmit =(Button) findViewById(R.id.bSubmit);



        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String gender = etGender.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String drinkP = etDrinkP.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Intent intent = new Intent(SurveyActivity.this, LoginActivity.class);
                                SurveyActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
                                builder.setMessage("Survey Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };


                SurveyRequest surveyRequest = new SurveyRequest(gender, age, drinkP,responseListener );
                RequestQueue queue = Volley.newRequestQueue(SurveyActivity.this);
                queue.add(surveyRequest);



            }
        });

    }
}
