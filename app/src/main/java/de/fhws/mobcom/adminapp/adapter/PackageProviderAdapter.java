package de.fhws.mobcom.adminapp.adapter;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.model.Package;

/**
 * Created by kanga on 01.03.2018.
 */

public class PackageProviderAdapter {

    private static final String TAG = PackageProviderAdapter.class.getSimpleName();
//    private static final Uri CONTENT_URL = Uri.parse( "content://de.fhws.mobcom.adminapp.PackageProvider/apps" );

    private Context mContext;
    private ContentResolver mContentResolver;
    private ArrayList<ApplicationInfo> mPackages;
    private PackageManager mManager;

    public PackageProviderAdapter( Context context ){
        mContext = context;
        mManager = context.getPackageManager();
        init();
    }

    private void init(){
        // reset current packages
        mPackages = new ArrayList<>(mManager.getInstalledApplications(0));

       /* String[] projection = { "id", "name", "label" };
        Cursor cursor = mContentResolver.query( CONTENT_URL, projection, null, null, null );

        if( !cursor.moveToFirst() )
            return;

        do {

            String id = cursor.getString( cursor.getColumnIndex( "id" ) );
            String name = cursor.getString( cursor.getColumnIndex( "name" ) );
            String label = cursor.getString( cursor.getColumnIndex( "label" ) );

            Package pack = new Package( id, name, label, null );
            mPackages.add( pack );

        } while( cursor.moveToNext() );*/

        printCurrentPackages();
    }

    private void printCurrentPackages(){
        //int max = mPackages.size();
        /*for(ApplicationInfo pi : mPackages){
            Log.d(TAG, "Name " + pi.packageName + " Label: " + mManager.getApplicationLabel(pi.applicationInfo) );
        }*/
        /*for( int i = 0 ; i < max ; i++ ){
            Package cur = mPackages.get( i );
            Log.d( TAG, "Name: " + cur.mName + ", Label: " + cur.mLabel );
        }*/
    }

    public boolean has( ApplicationInfo pack ){
        return has( pack.name );
    }

    public boolean has( String name ){
        /*int max = mPackages.size();
        for( int i = 0 ; i < max ; i++ ){
            Package cur = mPackages.get( i );
            if( cur.mName.equals( name ) )
                return true;
        }
        return false;*/
        for(ApplicationInfo ai: mPackages){

            if(ai.packageName!= null && ai.packageName.equals(name)) return true;
        }
        return false;

    }

    public ArrayList<ApplicationInfo> getAll(){
        return mPackages;
    }

    public ApplicationInfo getByName( String name ){
        /*int max = mPackages.size();
        for( int i = 0 ; i < max ; i++ ){
            Package cur = mPackages.get( i );
            if( cur.mName == name )
                return cur;
        }*/
        Log.d(TAG, name);
        for(ApplicationInfo ai: mPackages){
            if(ai.packageName != null && ai.packageName.equals(name)) return ai;
        }
        return null;
    }

    public void disable( String name ){
        /*if( has( pack ) )
            return;

        Log.d( TAG, "Inserting..." );

        ContentValues values = new ContentValues();
        values.put( "name", pack.mName );
        values.put( "label", pack.mLabel );
COMPONENT_ENABLED_STATE_DISABLED
        mContentResolver.insert( CONTENT_URL, values );

        init();*/
        ApplicationInfo appInfo = getByName(name);
        mManager.setApplicationEnabledSetting(appInfo.packageName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

    }

    public void enable( String name ){

        ApplicationInfo appInfo = getByName(name);

        Log.d("PackageName", appInfo.packageName);
//        Log.d("AppName", appInfo.applicationInfo.name);
    //    Log.d("ClassName", appInfo.applicationInfo.className);

      //  Log.d("ApplicationPackageName", appInfo.applicationInfo.packageName);
       // Log.d(TAG, appInfo.activities.toString());

        //ComponentName cn = new ComponentName(appInfo.packageName, appInfo.applicationInfo.);

        //mManager.setComponentEnabledSetting(cn, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        mManager.setApplicationEnabledSetting(appInfo.packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        //mManager.setApplicationEnabledSetting(appInfo.packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
       /* Package toDelete = getByName( name );

        String selection = "id = ?";d
        String[] selectionArgs = { toDelete.mId };

        mContentResolver.delete( CONTENT_URL, selection, selectionArgs );

        init();*/
    }
}
