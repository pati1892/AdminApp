package de.fhws.mobcom.adminapp;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.Adapter.StringAdapter;
import de.fhws.mobcom.adminapp.Helper.PackageHelper;
import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageFragment extends ListFragment {

    static final Uri CONTENT_URL = Uri.parse( "content://de.fhws.mobcom.adminapp.AppProvider/apps" );

    private ContentResolver mResolver;
    private ArrayList<Package> mHiddenPackages;
    private int mCurSelected = -1;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );

        mResolver = getContext().getContentResolver();
        mHiddenPackages = getHiddenPackages();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        StringAdapter adapter = new StringAdapter( getContext(), PackageHelper.INSTALLED( getContext() ) );
        setListAdapter( adapter );

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

    }

    private ArrayList<Package> getHiddenPackages(){
        String[] projection = new String[]{ "id", "name" };
        Cursor cursor = mResolver.query( CONTENT_URL, projection, null, null, null );
        ArrayList<Package> toReturn = new ArrayList<Package>();

        if( !cursor.moveToFirst() )
            return toReturn;

        do {
            String id = cursor.getString( cursor.getColumnIndex( "id" ) );
            String name = cursor.getString( cursor.getColumnIndex( " name" ) );

            Package app = new Package( id, name );
            toReturn.add( app );
        } while( cursor.moveToNext() );

        return toReturn;
    }

    private void deleteApp( String id ){

        long rowId = mResolver.delete( CONTENT_URL, "id = ?", new String[]{ id } );
        mHiddenPackages = getHiddenPackages();
    }

    private void insertApp( String name ){
        //long rowId = mResolver.insert( CONTENT_URL, )
    }

    private Package currentlySelected(){
        if( mCurSelected < 0 )
            return null;
        return mHiddenPackages.get( mCurSelected );
    }
}
