package com.predposhitay.android.library;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.predposhitay.android.library.NetWork.Regist;
import com.predposhitay.android.library.api.MessageServise;
import com.predposhitay.android.library.ui.chat.SendFragment;
import com.predposhitay.android.library.ui.contact.ContactFragment;
import com.predposhitay.android.library.ui.info.MobilInfo;
import com.predposhitay.android.library.ui.news.NewsFragment;
import com.predposhitay.android.library.ui.proLong.ProLongFragment;
import com.predposhitay.android.library.ui.reference.ReferenceFragment;
import com.predposhitay.android.library.ui.search.Searchfragment;
import com.predposhitay.android.library.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity{

    SharedPreferences user;
    BottomNavigationView navigation;
    SendFragment sendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MainActivity.this, MessageServise.class);
        startService(intent);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(user.getString("id", "-1").equals("-1")){
            Regist regist = new Regist(user);
            regist.regist();
        }
        else{
            Log.e("ID", user.getString("id", "-1"));
        }


        NewsFragment youFragment = new NewsFragment();
        final android.app.FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("read", "63");
        youFragment.setArguments(bundle);
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        Log.e("menu", "home");
                        NewsFragment youFragment = new NewsFragment();

                        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                                .replace(R.id.content, youFragment)
                                .addToBackStack("myStack")
                                .commit();
                        return true;
                    }
                    case R.id.navigation_chat: {
                        sendFragment = new SendFragment();

                        fragmentManager.beginTransaction()
                                .replace(R.id.content, sendFragment)
                                .addToBackStack("myStack")
                                .commit();
                        Log.e("menu", "dashboard");
                        return true;
                    }
                    case R.id.navigation_read: {

                        NewsFragment youFragment = new NewsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("read", "63");
                        youFragment.setArguments(bundle);
                        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                                .replace(R.id.content, youFragment)
                                .addToBackStack("myStack")
                                .commit();
                        Log.e("menu", "read");
                        return true;
                    }
                }
                return false;


            }
        });
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        switch (item.getItemId()){
            case R.id.menu_setting:{
                Log.e("menu", "1");
                SettingsFragment settingsFragment = new SettingsFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content, settingsFragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;
            }
            case R.id.menu_reference:{
                Log.e("menu", "2");
                ReferenceFragment referenceFragment = new ReferenceFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content, referenceFragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;
            }
            case R.id.menu_search:{
                Searchfragment fragment = new Searchfragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content,fragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;
            }
            case R.id.menu_contact:{
                ContactFragment referenceFragment = new ContactFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content, referenceFragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;

            }
            case R.id.menu_book:{
                ProLongFragment fragment = new ProLongFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content,fragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;
            }
            case R.id.menu_mobils:{
                MobilInfo fragment = new MobilInfo();
                fragmentManager.beginTransaction()
                        .replace(R.id.content,fragment)
                        .addToBackStack("myStack")
                        .commit();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if(navigation.getVisibility() == View.GONE){
            sendFragment.backpress();
        }
        else{
            android.app.FragmentManager fragmentManager = getFragmentManager();
            NewsFragment youFragment = new NewsFragment();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
            navigation.setSelectedItemId(R.id.navigation_home);
            //super.onBackPressed();
        }

    }
}
