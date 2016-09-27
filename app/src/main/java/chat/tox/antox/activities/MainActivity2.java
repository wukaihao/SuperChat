package chat.tox.antox.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import chat.tox.antox.R;

/*
* @引导页
* */

public class MainActivity2 extends AppCompatActivity {
    private Button bt_main_login,bt_main_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        bt_main_login=(Button)findViewById(R.id.bt_main_login);
        bt_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, LoginActivity1.class);
                startActivity(intent);
                finish();
            }
        });
        bt_main_register=(Button)findViewById(R.id.bt_main_register);
        bt_main_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, CreateAccountActivity4.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
