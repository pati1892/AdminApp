package de.fhws.mobcom.adminapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferences(Context.MODE_PRIVATE);


        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();

        if( fm.findFragmentById( R.id.activity_main ) == null ){
            PackageFragment fragment = new PackageFragment();
            fm.beginTransaction().add( R.id.activity_main, fragment ).commit();
        }
    }
}
