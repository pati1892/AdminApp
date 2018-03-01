package de.fhws.mobcom.adminapp;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.Adapter.PackageAdapter;
import de.fhws.mobcom.adminapp.Adapter.PackageProviderAdapter;
import de.fhws.mobcom.adminapp.Adapter.StringAdapter;
import de.fhws.mobcom.adminapp.Helper.PackageHelper;
import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageFragment extends ListFragment {

    private static final String TAG = PackageFragment.class.getSimpleName();

    private PackageProviderAdapter mProviderAdapter;

    private ArrayList<Package> mHiddenPackages;
    private int mCurSelected = -1;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );

        mProviderAdapter = new PackageProviderAdapter( getContext() );
        mHiddenPackages = getHiddenPackages();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        renewListAdapter();

        return super.onCreateView( inflater, container, savedInstanceState );
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState ){
        super.onActivityCreated( savedInstanceState );
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id ){
        Log.d( TAG, "Item clicked.");

        // get package to view
        TextView appName = (TextView) view.findViewById( R.id.appName );
        TextView appLabel = ( TextView ) view.findViewById( R.id.appLabel );
        CheckBox appHidden = ( CheckBox ) view.findViewById( R.id.appChecked );

        // appname
        String name = appName.getText().toString();
        String label = appLabel.getText().toString();

        if( isPackageHidden( name ) ){
            // uncheck
            appHidden.setChecked( false );
            mProviderAdapter.delete( new Package( ) );
        } else {
            // check
            appHidden.setChecked( true );
            // & add to hidden packages
            mProviderAdapter.insert( new Package( null, name, label, null ) );
        }
    }

    private Boolean isPackageHidden( String name ){
        int max = mHiddenPackages.size();
        for( int i = 0 ; i < max ; i++ ){
            Package cur = mHiddenPackages.get( i );
            if( cur.mName == name )
                return true;
        }
        return false;
    }

    private void renewListAdapter(){
        ArrayList<Package> packages = PackageHelper.INSTALLED( getContext() );
        ArrayList<Package> hidden = mProviderAdapter.getAll();

        for( Package pack : packages ){
            for( Package hid : hidden ){
                if( pack.mName == hid.mName )
                    pack.mIsHidden = true;
            }
        }

        PackageAdapter adapter = new PackageAdapter( getContext(), packages );
        setListAdapter( adapter );
    }
}
