package com.xentaqsys.oet.VolleyRequests;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.xentaqsys.oet.app.AppController;

import org.json.JSONObject;

import static com.xentaqsys.oet.app.AppController.TAG;


/**
 * Created by Swatantra.Singh on 9/8/17.
 */

public class ObjectRequest {

    String tag_json_obj = "json_obj_req";

    public void requestMethod(Context context,String url){

      final ProgressDialog pDialog = new ProgressDialog(context);
       pDialog.setMessage("Loading...");
       pDialog.show();

       JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
               url, null,
               new Response.Listener<JSONObject>() {

                   @Override
                   public void onResponse(JSONObject response) {
                       Log.d(TAG, response.toString());
                       pDialog.hide();
                   }
               }, new Response.ErrorListener() {

           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d(TAG, "Error: " + error.getMessage());
               // hide the progress dialog
               pDialog.hide();
           }
       });

// Adding request to request queue
       AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);



   }


}
