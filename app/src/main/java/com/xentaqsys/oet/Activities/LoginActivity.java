package com.xentaqsys.oet.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xentaqsys.oet.Helpers.AlertDialogManager;
import com.xentaqsys.oet.Helpers.ConnectionDetector;
import com.xentaqsys.oet.R;
import com.xentaqsys.oet.app.AppController;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xentaqsys.oet.app.AppController.TAG;

import static com.xentaqsys.oet.Utils.Util.SERVICE_URL;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    ConnectionDetector cd;
    AlertDialogManager alert;
    private ProgressBar mRegistrationProgressBar;
    private static int SPLASH_TIME_OUT = 1000;
    CardView loginView;

    View focusView = null;
    boolean cancel = false;
    String URL;

    public static String tag_json_arry = "json_array_req";
    String previousUser;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "myprefs";
    ProgressDialog pDialog;
    LinearLayout relativeLayout;

//    Login Card

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    TextView textViewRegister,textViewForget;

//    Registeration Card
    EditText editTextRegisterName,editTextRegisterMobile,editTextRegisterEmail,editTextRegisterPassword,editTextRegisterConfirmPassword;
    Button buttonRegister;
    TextView textViewRegisterLogin;


//    Forget Card
    EditText editTextForgetEmail,editTextForgetMobile;
    Button buttonSendOTP;
    TextView textViewFogetRegister,textViewForgetLogin;

//    Send OTP Card
    EditText editTextConfirmOTP;
    Button buttonConfirmOTP;
    TextView textViewConfirmOTPRegister,textViewConfirmOTPLogin;

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        previousUser = sharedpreferences.getString("Success", "123");
        setContentView(R.layout.activity_login2);
        relativeLayout = (LinearLayout) findViewById(R.id.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setHomeButtonEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.toolbar_logotest);
//        getSupportActionBar().setTitle("");

        if (previousUser.equals("")) {

            ifNotPreviousUser();

        } else {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    private void ifNotPreviousUser() {
        showLoginCard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        // Get the notifications MenuItem and
//        // its LayerDrawable (layer-list)
//        MenuItem item = menu.findItem(R.id.action_search);
//        //LayerDrawable icon = (LayerDrawable) item.getIcon();
//
//        // Update LayerDrawable's BadgeDrawable
//        //Utils.setBadgeCount(this, icon, mNotificationsCount);
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                //shownotification.edit().putInt("COUNT",-1).apply();
////                Intent intt=new Intent(AppController.getContext(),Notification_Activity.class);
////                startActivity(intt);
//                return true;
//            }
//        });
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    private void showLoginCard() {
        relativeLayout.removeAllViews();
        final LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addLoginView = layoutInflater.inflate(R.layout.login_card, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        params.gravity = Gravity.CENTER;
        addLoginView.setLayoutParams(params);
        relativeLayout.addView(addLoginView);

        buttonLogin = (Button) addLoginView.findViewById(R.id.btn_server_login);
        editTextUsername = (EditText) addLoginView.findViewById(R.id.et_email);
        editTextPassword = (EditText) addLoginView.findViewById(R.id.et_password);
        textViewRegister = (TextView) addLoginView.findViewById(R.id.textViewRegister);
        textViewForget = (TextView) addLoginView.findViewById(R.id.textViewForget);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Verifying...");
        pDialog.setCanceledOnTouchOutside(false);


        cd = new ConnectionDetector(getApplicationContext());


        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(LoginActivity.this,
                    "Internet Connection Error!",
                    "Please connect to working Internet connection", false);
            mRegistrationProgressBar.setVisibility(ProgressBar.GONE);

            // stop executing code by return

            return;
        } else {
            buttonLogin.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    onButtonLoginClick();
                }
            });
        }
        textViewRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {  textViewRegisterClicked();
            }


        });

        textViewForget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             textViewForgetClicked();
            }
        });
    }

    private void textViewForgetClicked()  {
        showForgetCard();



    }

    private void showForgetCard() {

        relativeLayout.removeAllViews();
        final LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addLoginView = layoutInflater.inflate(R.layout.forget_card, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        params.gravity = Gravity.CENTER;
        addLoginView.setLayoutParams(params);
        relativeLayout.addView(addLoginView);
        buttonSendOTP = (Button) addLoginView.findViewById(R.id.btn_send_otp);
        editTextForgetEmail = (EditText) addLoginView.findViewById(R.id.et_forget_mail);
        editTextForgetMobile = (EditText) addLoginView.findViewById(R.id.et_forget_mobile);
        textViewFogetRegister = (TextView) addLoginView.findViewById(R.id.textViewRegister);
        textViewForgetLogin = (TextView) addLoginView.findViewById(R.id.textViewForget);

        textViewFogetRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewRegisterClicked();
            }
        });
        textViewForgetLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginCard();
            }
        });
        buttonSendOTP.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

//                TODO CHECK IF USER NAME AND MOBILE NO. PRESENT IS DATABASE OR NOT IF YES THEN
//                if (present){
                    showSendOTPCard();
//                }
//                else {
//                    Toast.makeText(LoginActivity.this,"UserName or Password not correct",Toast.LENGTH_LONG).show();
//                }


            }
        });
    }

    private void showSendOTPCard() {
        relativeLayout.removeAllViews();
        relativeLayout.removeAllViews();
        final LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addLoginView = layoutInflater.inflate(R.layout.send_otp_card, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        params.gravity = Gravity.CENTER;
        addLoginView.setLayoutParams(params);
        relativeLayout.addView(addLoginView);
        buttonConfirmOTP = (Button) addLoginView.findViewById(R.id.btn_confirm_otp);
        editTextConfirmOTP = (EditText) addLoginView.findViewById(R.id.et_otp);
        textViewConfirmOTPLogin = (TextView) addLoginView.findViewById(R.id.textViewConfirmOTPLogin);
        textViewConfirmOTPRegister = (TextView) addLoginView.findViewById(R.id.textViewConfirmOTPRegister);

        textViewConfirmOTPLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginCard();
            }
        });
        textViewConfirmOTPRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {   textViewRegisterClicked();
            }
        });
    }

    private void textViewRegisterClicked() {
        relativeLayout.removeAllViews();
        final LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View addLoginView = layoutInflater.inflate(R.layout.register_card, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        params.gravity = Gravity.CENTER;
        addLoginView.setLayoutParams(params);
        relativeLayout.addView(addLoginView);


//        TODO INSERT REGISTERATION DETAILS WHEN PASSWORD AND CONFIRM PASSWORD MATCHES TO SERVER BY VOLLEY AND MOVE TOLOGIN CARD

        buttonRegister = (Button) addLoginView.findViewById(R.id.btn_server_register);

        editTextRegisterName = (EditText) addLoginView.findViewById(R.id.et_register_name);
//        editTextRegisterQualification = (EditText) addLoginView.findViewById(R.id.et_register_qualification);
        editTextRegisterMobile = (EditText) addLoginView.findViewById(R.id.et_register_mobile);
        editTextRegisterEmail = (EditText) addLoginView.findViewById(R.id.et_register_email);
        editTextRegisterPassword = (EditText) addLoginView.findViewById(R.id.et_register_password);
        editTextRegisterConfirmPassword = (EditText) addLoginView.findViewById(R.id.et_register_password_confirm);

        textViewRegisterLogin = (TextView) addLoginView.findViewById(R.id.textViewRegsiterLogin);
        textViewRegisterLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                                textViewRegisterLoginClicked();
            }
        });


        buttonRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonRegisterationClicked();

    }

    private void onButtonRegisterationClicked() {
        final String name= editTextRegisterName.getText().toString();
//                final String qualif= editTextRegisterQualification.getText().toString();
        final String mobileno= editTextRegisterMobile.getText().toString();
        final String email= editTextRegisterEmail.getText().toString();
        final String password= editTextRegisterPassword.getText().toString();
        String confirmpass= editTextRegisterConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(name) ){
            editTextRegisterName.setError(getString(R.string.error_field_required));
            focusView = editTextRegisterName;
            cancel = true;

        }
//                if (TextUtils.isEmpty(qualif) ){
//                    editTextRegisterQualification.setError(getString(R.string.error_field_required));
//                    focusView = editTextRegisterQualification;
//                    cancel = true;
//
//                }
        if (TextUtils.isEmpty(mobileno) ){
            editTextRegisterMobile.setError(getString(R.string.error_field_required));
            focusView = editTextRegisterMobile;
            cancel = true;

        }
        if (TextUtils.isEmpty(email) ){
            editTextRegisterEmail.setError(getString(R.string.error_field_required));
            focusView = editTextRegisterEmail;
            cancel = true;

        }
        if (TextUtils.isEmpty(password) ){
            editTextRegisterPassword.setError(getString(R.string.error_field_required));
            focusView = editTextRegisterPassword;
            cancel = true;

        }
        if (TextUtils.isEmpty(confirmpass)){
            editTextRegisterConfirmPassword.setError(getString(R.string.error_field_required));
            focusView = editTextRegisterConfirmPassword;
            cancel=true;
        }

        if (!isPasswordValid(password)){
            editTextPassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        }
        if (!isValidEmail(email)){
            editTextUsername.setError(getString(R.string.error_invalid_email));
            focusView = editTextUsername;
            cancel = true;
        }
        if (cancel){

            focusView.requestFocus();
        }

        else {
            URL = SERVICE_URL + "RegisterUser?Name=" + name +  "&Mobile=" + mobileno + "&Email=" + email + "&Password=" + password;
            URL = SERVICE_URL + "RegisterUser";

//            url = "http://httpbin.org/post";

            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response

//                           TODO  IF SUCCESSFULLY INSERTED OR RESIGTERED INTO DATABASE THEN MOVE TO LOGIN CARD
                            showLoginCard();

                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", "$$$$$$$");
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Name", name);
//                            params.put("Qualification", qualif);
                    params.put("Mobile", mobileno);
                    params.put("Email", email);
                    params.put("Password", password);
                    return params;
                }
            };
            queue.add(postRequest);
//            req.setRetryPolicy(new DefaultRetryPolicy(0, -1,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            req.setShouldCache(false);
//            AppController.getInstance().addToRequestQueue(req, tag_json_arry);

        }
    }
        });


    }

    private void textViewRegisterLoginClicked() {
        showLoginCard();
    }

    private void onButtonLoginClick() {
        pDialog.show();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();


        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            editTextPassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        }
        else if (isPasswordValid(password)){
            editTextPassword.setError("Password lenght is too short");
            focusView = editTextPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError(getString(R.string.error_field_required));
            focusView = editTextUsername;
            cancel = true;

        } else if (isValidEmail(username)) {
            editTextUsername.setError(getString(R.string.error_invalid_email));
            focusView = editTextUsername;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            URL = SERVICE_URL + "UserAuthentication?Username=" + username + "&Password=" + password;
            JsonArrayRequest req = new JsonArrayRequest(URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());

                            pDialog.hide();

                            try {


                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject detail = (JSONObject) response
                                            .get(i);


                                    if (detail.getString("Success").equals("Yes")) {
//                                  NEXT TIME USER WILL BE REDIRECTED TO THE DASHBOARD
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString("Success", detail.getString("Success"));
                                        editor.apply();

                                        loginView.setVisibility(View.INVISIBLE);

                                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (detail.getString("Status").equals("No")) {
                                        loginView.setVisibility(View.VISIBLE);

                                    }


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                loginView.setVisibility(View.VISIBLE);

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    pDialog.hide();
                    loginView.setVisibility(View.VISIBLE);

                    Toast.makeText(LoginActivity.this, "FAILURE: Try After Sometime !", Toast.LENGTH_LONG).show();
                }
            });

//                    int socketTimeout = 30000;//30 seconds - change to what you want
//                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//                    req.setRetryPolicy(policy);
            req.setRetryPolicy(new DefaultRetryPolicy(0, -1,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            req.setShouldCache(false);
            AppController.getInstance().addToRequestQueue(req, tag_json_arry);


        }
    }


    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }

    private boolean isValidEmail(String username) {

        return username.contains("@") && username.contains(".");

    }
}

