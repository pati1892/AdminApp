package de.fhws.mobcom.adminapp.Adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 01.03.2018.
 */

public class PackageProviderAdapter {

    private static final Uri CONTENT_URL = Uri.parse( "content://de.fhws.mobcom.adminapp.PackageProvider/apps" );

    private Context mContext;
    private ContentResolver mContentResolver;
    private ArrayList<Package> mPackages;

    public PackageProviderAdapter( Context context ){
        mContext = context;
        mContentResolver = context.getContentResolver();

        init();
    }

    private void init(){
        // reset current packages
        mPackages = new ArrayList<Package>();

        String[] projection = { "id", "name", "label" };
        Cursor cursor = mContentResolver.query( CONTENT_URL, projection, null, null, null );

        if( !cursor.moveToFirst() )
            return;

        do {

            String id = cursor.getString( cursor.getColumnIndex( "id" ) );
            String name = cursor.getString( cursor.getColumnIndex( "name" ) );
            String label = cursor.getString( cursor.getColumnIndex( "label" ) );

            Package pack = new Package( id, name, label, null );
            mPackages.add( pack );

        } while( cursor.moveToNext() );
    }

    public boolean has( Package pack ){
        int max = mPackages.size();
        for( int i = 0 ; i < max ; i++ ){
            Package cur = mPackages.get( i );
            if( cur.mName == pack.mName )
                return true;
        }
        return false;
    }

    public ArrayList<Package> getAll(){
        return mPackages;
    }

    public void insert( Package pack ){
        if( has( pack ) )
            return;

        ContentValues values = new ContentValues();
        values.put( "name", pack.mName );
        values.put( "label", pack.mLabel );

        mContentResolver.insert( CONTENT_URL, values );

        init();
    }

    public void delete( Package pack ){
        if( !has( pack ) )
            return;

        String selection = "id = ?";
        String[] selectionArgs = { pack.mId };

        mContentResolver.delete( CONTENT_URL, selection, selectionArgs );

        init();
    }
}
