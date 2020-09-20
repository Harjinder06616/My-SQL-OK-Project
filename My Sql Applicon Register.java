//My Sql Applicon Register.java

package com.e.statusduniya_708;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText username, email, password,confpassword;
    private AwesomeValidation awesomeValidation;
    Button signupbtn;
    String str_fullname, str_email, str_password, str_confpasswoord;
    String url = "https://shayariapps.xyz/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = findViewById(R.id.UserName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        confpassword = findViewById(R.id.Confpassword);
        signupbtn = findViewById(R.id.Signupbtn);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.UserName,
                RegexTemplate.NOT_EMPTY, R.string.invalid_username);
        awesomeValidation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.Password,
                ".{6,}", R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.Confpassword
        ,R.id.Password,R.string.invalid_confpassword);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    final ProgressDialog progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMessage("Please Wait..");

                    progressDialog.show();
                    str_fullname = username.getText().toString().trim();
                    str_email = email.getText().toString().trim();
                    str_password = password.getText().toString().trim();
                    str_confpasswoord = confpassword.getText().toString().trim();



                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            username.setText("");
                            email.setText("");
                            password.setText("");
                            confpassword.setText("");
                            Intent i = new Intent(Register.this, Login.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            params.put("fullname", str_fullname);
                            params.put("email", str_email);
                            params.put("password", str_password);
                            params.put("confpassword",str_confpasswoord);
                            return params;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                    requestQueue.add(request);

                }
            }
        });
    }
}
 