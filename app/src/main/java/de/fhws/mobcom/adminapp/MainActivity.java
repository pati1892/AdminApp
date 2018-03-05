package de.fhws.mobcom.adminapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by kanga on 02.03.2018.
 */

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
        setContentView( R.layout.activity_main );
        getFragmentManager().beginTransaction().replace( R.id.activity_main, new MainPreferenceFragment() ).commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

        SharedPreferences mPreferences;

        private CheckBoxPreference mWifiDisabled;
        private CheckBoxPreference mBluetoothDisabled;
        private CheckBoxPreference mNfcDisabled;
        private Preference mHiddenApps;

        @Override
        public void onCreate( Bundle savedInstance ){
            super.onCreate( savedInstance );

            addPreferencesFromResource( R.xml.preferences );

            // get sharedpreferences
            mPreferences = PreferenceManager.getDefaultSharedPreferences( getActivity().getApplicationContext() );

            mWifiDisabled = ( CheckBoxPreference ) findPreference( getString( R.string.KEY_DISABLE_WIFI ) );
            mWifiDisabled.setOnPreferenceChangeListener( this );
            mWifiDisabled.setOnPreferenceClickListener( this );
            mWifiDisabled.setChecked( mPreferences.getBoolean( getString( R.string.KEY_DISABLE_WIFI ), false ) );

            mBluetoothDisabled = ( CheckBoxPreference ) findPreference( getString( R.string.KEY_DISABLE_BLUETOOTH ) );
            mBluetoothDisabled.setOnPreferenceChangeListener( this );
            mBluetoothDisabled.setOnPreferenceClickListener( this );
            mBluetoothDisabled.setChecked( mPreferences.getBoolean( getString( R.string.KEY_DISABLE_BLUETOOTH ), false ) );

            mNfcDisabled = ( CheckBoxPreference ) findPreference( getString( R.string.KEY_DISABLE_NFC ) );
            mNfcDisabled.setOnPreferenceChangeListener( this );
            mNfcDisabled.setOnPreferenceClickListener( this );
            mNfcDisabled.setChecked( mPreferences.getBoolean( getString( R.string.KEY_DISABLE_NFC ), false ) );


            mHiddenApps = ( Preference ) findPreference( getString( R.string.KEY_HIDDEN_APPS ) );
            mHiddenApps.setOnPreferenceClickListener( this );
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            return false;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            SharedPreferences.Editor editor = mPreferences.edit();

            if( mWifiDisabled == preference ){
                boolean oldValue = mPreferences.getBoolean( getString( R.string.KEY_DISABLE_WIFI ), false );
                mWifiDisabled.setChecked( !oldValue );
                // write update
                editor.putBoolean( getString( R.string.KEY_DISABLE_WIFI ), !oldValue );
                editor.commit();
            } else if( mBluetoothDisabled == preference ){
                boolean oldValue = mPreferences.getBoolean( getString( R.string.KEY_DISABLE_BLUETOOTH ), false );
                mBluetoothDisabled.setChecked( !oldValue );
                editor.putBoolean( getString( R.string.KEY_DISABLE_BLUETOOTH ), !oldValue );
                editor.commit();
            } else if( mNfcDisabled == preference ){
                boolean oldValue = mPreferences.getBoolean( getString( R.string.KEY_DISABLE_NFC ), false );
                mBluetoothDisabled.setChecked( !oldValue );
                editor.putBoolean( getString( R.string.KEY_DISABLE_NFC ), !oldValue );
                editor.commit();
            } else if( mHiddenApps == preference ){
                // start PackageActivity
                Intent intent = new Intent( getActivity(), PackageActivity.class );
                startActivity( intent );
            }
            return true;
        }
    }
}
