package com.xentaqsys.oet.VolleyRequests;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.xentaqsys.oet.app.AppController;

import org.json.JSONArray;

import static com.xentaqsys.oet.app.AppController.TAG;


/**
 * Created by Swatantra.Singh on 9/8/17.
 */

public class ArrayRequest {
    String tag_json_arry = "json_array_req";

    public void requestMethod(Context context,String url){

       final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

}
