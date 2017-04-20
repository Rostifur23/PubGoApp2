package ie.jbmnetworks.pubgo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by x14322491 on 19/04/2017.
 */

public class SurveyRequest extends StringRequest {
    private static final String SURVEY_REQUEST_URL = "https://pubgo-jackbourkemckenna.c9users.io/app/survey.php";
    private Map<String,String> params;
    public  SurveyRequest(String gender, int age, String drinkP,Response.Listener<String>listener){
        super(Method.POST, SURVEY_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("gender", gender);
        params.put("age", age + "");
        params.put("drinkP", drinkP);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
