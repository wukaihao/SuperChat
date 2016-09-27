package chat.tox.antox.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import chat.tox.antox.R;
import retrofit.RestAdapter;
import wangwang.activity.*;

public class HuabeiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huabei);

        final Button button  = (Button) this.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setClass(HuabeiActivity.this, wangwang.activity.MainActivity.class);
                startActivity(intent);
//                String foo = "toxme.io" ;
//                Object o = chat.tox.antox.toxme.ToxMe.lookupPublicKey( foo);
//                Log.d("X_TAG",String.valueOf(o)) ;
//                Log.d("X_TAG",o.getClass().toString()) ;

            }
        });
    }

}
