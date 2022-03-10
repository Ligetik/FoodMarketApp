package com.example.testactivityandroid_9;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //---------delete------
/*        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //---------/delete------
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_login,R.id.nav_profile, /*R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_logout,*/ R.id.nav_callback)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        Menu menu = navigationView.getMenu();

        if(FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_profile).setVisible(false);
        }
        else {
                String displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                menu.findItem(R.id.nav_profile).setVisible(true);
                menu.findItem(R.id.nav_profile).setTitle(displayName);
                menu.findItem(R.id.nav_login).setVisible(false);
        }


/*        menu.findItem(R.id.nav_profile).setVisible(false);*/

/*        if (menu.findItem(R.id.nav_profile).setVisible(true)) {
            LogInActivity logInActivity= new LogInActivity();
            logInActivity.extracted();
        }*/






        //menu.findItem(R.id.nav_profile).setTitle("My Account");
        /*extracted(menu);*/
        /*menu.findItem(R.id.nav_profile).setVisible(false);*/

        /*navigationView.setNavigationItemSelectedListener(this);*/
        /*extracted(navigationView);*/

    }
/*    public static void extracted(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_profile).setTitle("My Account");
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


   /* @Override
        public void onReceive(String str) {
           *//* Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();*//*
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_profile).setTitle(str);
    }*/
}