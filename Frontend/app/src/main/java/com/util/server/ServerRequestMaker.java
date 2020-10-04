package com.util.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ServerRequestMaker {

    /*
    The URL this ServerRequestMaker will be pointed to
     */
    private String API_URL;

    /*
    The map of request parameters that will be used by ServerRequestMaker
     */
    private HashMap<String, String> mParams;

    public ServerRequestMaker(String url){
        API_URL = url;
        mParams = new HashMap<String, String>();
    }

    public ServerRequestMaker(String url, HashMap<String, String> params)
    {
        API_URL = url;
        mParams = new HashMap<String, String>(params);
    }


    public void sendRequest(String action, Response.Listener<String> responseListener, Context context)
    {
        String requestURL = API_URL + action;
        final String debugAction = action;
        String ret;

        //Get Boolean
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestURL,
                responseListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", debugAction + " - " + error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mParams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        clearParams();
    }

    public void setParam(String key, String value)
    {
        mParams.put(key, value);
    }

    public void clearParams()
    {
        mParams.clear();
    }
}
