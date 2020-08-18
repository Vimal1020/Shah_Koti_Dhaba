import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
   EditText username,password;
   Button login;
   TextView register;
   DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        databaseHelper=new DatabaseHelper(this);
        username=(EditText)findViewById(R.id.username_login);
        password=(EditText)findViewById(R.id.password_login);
        login=(Button)findViewById(R.id.login_but);
        register=(TextView)findViewById(R.id.login_text);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(Login_Activity.this,Register.class);
                startActivity(registerIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString().trim();
                String pwd=password.getText().toString().trim();
                boolean res=databaseHelper.checkUser(user,pwd);
                if(res==true)
                {
                    Toast.makeText(Login_Activity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Login_Activity.this,"Not Logged In",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
