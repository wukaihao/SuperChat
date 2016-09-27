package wangwang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends chat.tox.antox.activities.LoginActivity {
//    private EditText edit_login_username,edit_login_userpwd;
//    private Button bt_login_login;
//    private ActionBar sab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        sab=getSupportActionBar();
//        sab.setTitle("登录");
//        init();
    }
//    public void init(){
//        edit_login_username=(EditText)findViewById(R.id.edit_login_username);
//        edit_login_userpwd=(EditText)findViewById(R.id.edit_login_userpwd);
//        bt_login_login=(Button)findViewById(R.id.bt_login_login);
//        bt_login_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(),"用户名："+edit_login_username.getText().toString()+"用户名："+edit_login_userpwd.getText().toString(),Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(LoginActivity.this,FirstActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
//        MenuItem item=menu.findItem(R.id.action_settings);
//        item.setVisible(false);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
