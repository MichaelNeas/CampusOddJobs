package uconn.campusoddjobs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity {


    private EditText fname;
    private EditText lname;
    private EditText rEmailView;
    private EditText username;
    private EditText rPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        rEmailView = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.uname);
        rPasswordView = (EditText) findViewById(R.id.password);

        Button rRegisterButton = (Button) findViewById(R.id.register);
        rRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });
        Button rBackToLoginButton = (Button) findViewById(R.id.bktologin);
        rBackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });
    }

    private void attemptRegistration() {
        // some magical database shit happens here
    }

    private void toLogin(){
        Intent intent = new Intent(Register.this,LoginActivity.class);
        Register.this.startActivity(intent);
        Register.this.finish();
    }



}
