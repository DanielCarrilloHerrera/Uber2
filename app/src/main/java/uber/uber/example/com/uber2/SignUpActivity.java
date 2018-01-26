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

public class SignUpActivity extends AppCompatActivity {

    EditText e5_name, e6_email, e7_password;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        e5_name = (EditText) findViewById(R.id.editText5);
        e6_email = (EditText) findViewById(R.id.editText6);
        e7_password = (EditText) findViewById(R.id.editText7);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

    }

    public void signUpUser(View v){

        dialog.setMessage("Registering. Please wait");
        dialog.show();

        String name = e5_name.getText().toString();
        String email = e6_email.getText().toString();
        String password = e7_password.getText().toString();

        if(name.equals("") || email.equals("") || password.equals("")){

            Toast.makeText(getApplicationContext(), "Fields cannot be blank", Toast.LENGTH_SHORT).show();


        }
        else{
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User registered succesfull", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User could not be registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
