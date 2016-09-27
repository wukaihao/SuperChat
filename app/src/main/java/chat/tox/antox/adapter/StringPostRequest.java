package chat.tox.antox.adapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
public class StringPostRequest extends StringRequest {

    private Map<String,String> myData;

    public StringPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public StringPostRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST,url, listener, errorListener);
        myData=new HashMap<>();
    }
    public void putParams(String key,String values){
        myData.put(key,values);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return myData;
    }
}
