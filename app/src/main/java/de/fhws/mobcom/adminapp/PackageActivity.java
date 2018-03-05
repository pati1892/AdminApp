package de.fhws.mobcom.adminapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class PackageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        FragmentManager fm = getFragmentManager();

        if( fm.findFragmentById( R.id.activity_package ) == null ){
            PackageFragment fragment = new PackageFragment();
            fm.beginTransaction().add( R.id.activity_package, fragment ).commit();
        }
    }
}
