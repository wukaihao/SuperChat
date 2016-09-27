package wangwang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import chat.tox.antox.R;
import wangwang.adapter.FragmentAdapter;


public class FirstActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    private RadioButton rb_friends;
    private FragmentAdapter mAdapter;


    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE= 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().hide();
        mAdapter=new FragmentAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);
    }
    private void bindViews() {
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_friends=(RadioButton)findViewById(R.id.rb_friends);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_channel:
                        viewPager.setCurrentItem(PAGE_ONE);
                        break;
                    case R.id.rb_message:
                        viewPager.setCurrentItem(PAGE_TWO);
                        break;
                    case R.id.rb_friends:
                        viewPager.setCurrentItem(PAGE_THREE);
                        break;
                    case R.id.rb_better:
                        viewPager.setCurrentItem(PAGE_FOUR);
                        break;
                    case R.id.rb_setting:
                        viewPager.setCurrentItem(PAGE_FIVE);
                        break;

                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_first_layout);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    switch (viewPager.getCurrentItem()) {
                        case PAGE_ONE:
                            rb_channel.setChecked(true);
                            break;
                        case PAGE_TWO:
                            rb_message.setChecked(true);
                            break;
                        case PAGE_THREE:
                            rb_friends.setChecked(true);
                            break;
                        case PAGE_FOUR:
                            rb_better.setChecked(true);
                            break;
                        case PAGE_FIVE:
                            rb_setting.setChecked(true);
                            break;
                    }
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_first, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
