////My Sql Applicon Login.Java 
package com.e.statusduniya_708;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText emaillg, passwordlg;
    private AwesomeValidation awesomeValidation;
    Button loginlg, registerlg;
    String str_email, str_password;
    String url = "https://shayariapps.xyz/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emaillg = findViewById(R.id.emailg);
        passwordlg = findViewById(R.id.Passwordlg);
        loginlg = findViewById(R.id.loginbtn);
        registerlg = findViewById(R.id.Signupbtnlg);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.emailg,
                Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.Passwordlg,
                ".{6,}", R.string.invalid_password);
        loginlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Please Wait..");

                    progressDialog.show();

                    str_email = emaillg.getText().toString().trim();
                    str_password = passwordlg.getText().toString().trim();


                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            if (response.equalsIgnoreCase("logged in successfully")) {

                                emaillg.setText("");
                                passwordlg.setText("");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                                UserLogout userLogout = new UserLogout(Login.this);
                                userLogout.setEmail(str_email);
                            } else {
                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", str_email);
                            params.put("password", str_password);
                            return params;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    requestQueue.add(request);
                }

            }
        });

        registerlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}