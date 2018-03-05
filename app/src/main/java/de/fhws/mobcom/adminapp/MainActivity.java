package de.fhws.mobcom.adminapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by kanga on 02.03.2018.
 */

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String KEY_DISABLE_WIFI = "key_disable_wifi";
    private static final String KEY_DISABLE_BLUETOOTH = "key_disable_bluetooth";
    private static final String KEY_DISABLE_NFC = "key_disable_nfc";
    private static final String KEY_HIDDEN_APPS = "key_hidden_apps";

    @Override
    protected void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
        setContentView( R.layout.activity_main );
        getFragmentManager().beginTransaction().replace( R.id.activity_main, new MainPreferenceFragment() ).commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

        private CheckBoxPreference mWifiDisabled;
        private CheckBoxPreference mBluetoothDisabled;
        private CheckBoxPreference mNfcDisabled;
        private Preference mHiddenApps;

        @Override
        public void onCreate( Bundle savedInstance ){
            super.onCreate( savedInstance );

            addPreferencesFromResource( R.xml.preferences );

            mWifiDisabled = ( CheckBoxPreference ) findPreference( KEY_DISABLE_WIFI );
            mWifiDisabled.setOnPreferenceChangeListener( this );
            mWifiDisabled.setOnPreferenceClickListener( this );

            mBluetoothDisabled = ( CheckBoxPreference ) findPreference( KEY_DISABLE_BLUETOOTH );
            mBluetoothDisabled.setOnPreferenceChangeListener( this );
            mBluetoothDisabled.setOnPreferenceClickListener( this );

            mNfcDisabled = ( CheckBoxPreference ) findPreference( KEY_DISABLE_NFC );
            mNfcDisabled.setOnPreferenceChangeListener( this );
            mNfcDisabled.setOnPreferenceClickListener( this );

            mHiddenApps = ( Preference ) findPreference( KEY_HIDDEN_APPS );
            mHiddenApps.setOnPreferenceClickListener( this );
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            return false;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if( mWifiDisabled == preference ){

            } else if( mBluetoothDisabled == preference ){

            } else if( mNfcDisabled == preference ){

            } else if( mHiddenApps == preference ){
                // start PackageActivity
                Intent intent = new Intent( getActivity(), PackageActivity.class );
                startActivity( intent );
            }
            return true;
        }
    }
}
