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

/*
A class that simplifies the use of Android Volley to send requests to the server.
 */
public class ServerRequestMaker {

    /*
    The URL this ServerRequestMaker will be pointed to
     */
    private String API_URL;

    /*
    The map of request parameters that will be used by ServerRequestMaker
     */
    private HashMap<String, String> mParams;


    /*
    Default Constructor
     */
    public ServerRequestMaker(){
        API_URL = "http://coms-309-ug-09.cs.iastate.edu/database/";
        mParams = new HashMap<String, String>();
    }

    /*
    Constructor
    @param url - The target URL this ServerRequestMaker will send requests to
     */
    public ServerRequestMaker(String url){
        API_URL = url;
        mParams = new HashMap<String, String>();
    }

    /*
    Constructor
    @param url - The target URL this ServerRequestMaker will send requests to
    @param params - A HashMap containing all parameters to send with a request
     */
    public ServerRequestMaker(String url, HashMap<String, String> params)
    {
        API_URL = url;
        mParams = new HashMap<String, String>(params);
    }

    /*
    Sends a request to the target URL with the given extension
    @param action - The URL extension to send the request to
    @param responseListener - the Volley Response.Listener to be used for this request
    @param context - The context in which a new request queue should be set up in
     */
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
                        Log.d("ERROR", "[ServerRequestMaker.java] Server Request Error: " + debugAction + " - " + error.toString());
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
    }

    /*
    Set a function parameter to be used by the server
    @param key - The parameter name
    @param value - The variable assigned to the parameter
     */
    public void setParam(String key, String value)
    {
        mParams.put(key, value);
    }

    /*
    Set all parameters to those in the given HashMap
    @param params - A HashMap containing all parameters to send with a request
     */
    public void useParams(HashMap<String, String> params)
    {
        mParams = new HashMap<String, String>(params);
    }

    /*
    Clear all parameters from the current request
    Should be called after every sendRequest call
     */
    public void clearParams()
    {
        mParams.clear();
    }

    /*
    Sets the target URL of this ServerRequestMaker
    @param url - The target url to set this ServerRequestMaker to
     */
    public void setUrl(String url)
    {
        API_URL = url;
    }
}
