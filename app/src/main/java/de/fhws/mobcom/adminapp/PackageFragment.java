package de.fhws.mobcom.adminapp;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.fhws.mobcom.adminapp.adapter.PackageAdapter;
import de.fhws.mobcom.adminapp.adapter.PackageProviderAdapter;
import de.fhws.mobcom.adminapp.model.Package;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageFragment extends ListFragment {

    private static final String TAG = PackageFragment.class.getSimpleName();

    private PackageProviderAdapter mProviderAdapter;
    private PackageManager mManager;
    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        mProviderAdapter = new PackageProviderAdapter( getContext() );
        mManager = this.getContext().getPackageManager();
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

        if( mProviderAdapter.has( name ) ){
            // uncheck
            Log.d(TAG, name);
            appHidden.setChecked( false );
            mProviderAdapter.disable( name );
        } else {
            // check
            appHidden.setChecked( true );
            mProviderAdapter.enable( name);
        }
    }

    private void renewListAdapter(){
        //ArrayList<PackageInfo> packages = (ArrayList<PackageInfo>)mManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        ArrayList<ApplicationInfo> packagesHaveActivity = new ArrayList<>();
        /*for (PackageInfo p : packages){
            if(p.) packagesHaveActivity.add(p);
        }*/
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = mManager.queryIntentActivities(mainIntent, 0);
        for(ResolveInfo info : resolveInfoList){
            if(info == null || info.activityInfo.applicationInfo == null) continue;
            packagesHaveActivity.add(info.activityInfo.applicationInfo);
        }

        //ArrayList<ApplicationInfo> hidden = mProviderAdapter.getAll();

        Log.d( TAG, "Installed apps: " + packagesHaveActivity.size() );
        //Log.d( TAG, "Hidden apps: " + hidden.size() );

        /*for( ApplicationInfo pack : packages ){
            if( mProviderAdapter.has( pack.mName ) )
                pack.mIsHidden = true;
        }*/

        PackageAdapter adapter = new PackageAdapter( getContext(), packagesHaveActivity );
        setListAdapter( adapter );
    }
}
