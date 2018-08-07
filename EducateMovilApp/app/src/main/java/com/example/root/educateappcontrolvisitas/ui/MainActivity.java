package com.example.root.educateappcontrolvisitas.ui;

import com.example.root.educateappcontrolvisitas.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link androidx.viewpager.widget.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * androidx.fragment.app.FragmentStatePagerAdapter.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 123;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public String getmUsername() {
        return mUsername;
    }

    private String mUsername;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialize Firebase components
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = ANONYMOUS;


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    onSignedInInitialize(user.getEmail());
                    // Create the adapter that will return a fragment for each of the three
                    // primary sections of the activity.

                    //FragmentManager miFragmentManager = getSupportFragmentManager();





                } else {
                    // User is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false).setLogo(R.drawable.logoeducate).build(),
                            RC_SIGN_IN);
                }
            }
        };



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                finish();
                startActivity(getIntent());

                Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Inicio de sesión cancelado", Toast.LENGTH_SHORT).show();
                finish();


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
            //finish();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem nombreUser = menu.findItem(R.id.username);
        nombreUser.setTitle(mUsername);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSignedInInitialize(String username) {

        mUsername = username;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        String SubStr1 = new String("@");
        String SubStr2 = username;
        String nombreUsuario = SubStr2.substring(0,SubStr2.indexOf( SubStr1 ));

        mSectionsPagerAdapter.nombreUsuario = nombreUsuario;

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //mViewPager.setAdapter(mSectionsPagerAdapter);


    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     *
     *
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String nombreUsuario;

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);

        }



        @Override
        public Fragment getItem(int position) {


            if(position == 0){
                return new VisitasHoyFragment().newInstance(nombreUsuario);
            }
            else{
                return new OtrasVisitasFragment().newInstance(nombreUsuario);


            }
        }

        @Override
        public int getCount() {
            // Son 2 tabs en total
            return 2;
        }
    }
}
