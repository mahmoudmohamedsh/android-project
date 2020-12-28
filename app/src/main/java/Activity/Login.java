package Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finaltry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Button callSignUp ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    private Button loginButton;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callSignUp = (Button) findViewById(R.id.loButton_move);
        mAuth = FirebaseAuth.getInstance();
        loginButton = (Button) findViewById(R.id.Login);
        emailField = (TextInputEditText) findViewById(R.id.login_email);
        passwordField = (TextInputEditText) findViewById(R.id.login_password);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if(mUser != null){
                    if(mUser.getEmail().toString().toLowerCase().equals("mo@mo.com")) {
                        Toast.makeText(Login.this,"admen", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, AddPostActivity.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(Login.this, HomeActivity.class));
                        finish();
                    }

                }else{
                    Toast.makeText(Login.this,"Not Signed In",Toast.LENGTH_LONG).show();
                }
            }
        };
       loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!TextUtils.isEmpty(emailField.getText().toString())
                    && !TextUtils.isEmpty(passwordField.getText().toString())){
                String email = emailField.getText().toString();
                String pwd = passwordField.getText().toString();
                login(email , pwd);
            }else{

            }
        }
    });
        callSignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Login.this,SignUP.class);
            startActivity(intent);
            finish();

        }
    });
}

    private  void login(String email , String pwd){
        mAuth.signInWithEmailAndPassword(email , pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(mUser.getEmail().toString().toLowerCase().equals("mo@mo.com")) {
                                Toast.makeText(Login.this,"admen", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Login.this, AddPostActivity.class));
                                finish();

                            }
                            else{
                                Toast.makeText(Login.this,"aha", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Login.this, HomeActivity.class));
                                finish();
                            }

                        }else{
                            Toast.makeText(Login.this,"dont Signed in ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_signout){
            mAuth.signOut();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}