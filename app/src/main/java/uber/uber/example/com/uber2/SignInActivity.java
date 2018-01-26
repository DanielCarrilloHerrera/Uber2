package uber.uber.example.com.uber2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText e2, e3;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public void signInUser(View v){
        dialog.setMessage("Signing in. Please wait");
        dialog.show();

        String email = e2.getText().toString();
        String password = e3.getText().toString();

        if(email.equals("") || password.equals("")){

            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT);
        }
        else{
            //Autenticación con email y password

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "User succesfully signed in", Toast.LENGTH_SHORT);
                                Intent i = new Intent(SignInActivity.this, MainPageActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT);
                            }
                        }
                    });
        }
    }

}
