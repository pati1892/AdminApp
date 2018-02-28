package de.fhws.mobcom.adminapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 28.02.2018.
 */

@RunWith(AndroidJUnit4.class)
public class PackageProviderTest extends ProviderTestCase2<PackageProvider> {

    public PackageProviderTest(){
        super( PackageProvider.class, PackageProvider.PROVIDER_NAME );
    }

    @Before
    @Override
    public void setUp() throws Exception {
        setContext( InstrumentationRegistry.getTargetContext() );
        super.setUp();
        //setContext( getMockContext() );
    }

    @Test
    public void insertItem(){

        // create data
        ContentValues values = new ContentValues();
        values.put( "name", "com.android.bla" );

        // insert
        ContentProvider provider = getProvider();
        provider.insert( PackageProvider.CONTENT_URL, values );

        // check if valid
        Cursor cursor = provider.query( PackageProvider.CONTENT_URL, new String[]{ "id", "name" }, null, null, null );
        cursor.moveToNext();
        String name = cursor.getString( cursor.getColumnIndex( "name" ) );

        assertEquals( "com.android.bla", name );
    }

    @Test
    public void deleteItem(){

        // create data
        String selection = "id = ?";
        String[] selectionArgs = { "1" };

        // delete
        ContentProvider provider = getProvider();
        provider.delete( PackageProvider.CONTENT_URL, selection, selectionArgs );

        // check if valid
        Cursor cursor = provider.query( PackageProvider.CONTENT_URL, new String[]{ "id", "name" }, null, null, null );
        assertEquals( cursor.moveToNext(), false );
    }

    @Test
    public void getMany(){

        // insert a few
        ContentValues one = new ContentValues();
        one.put( "name", "com.fhws.bla" );

        ContentValues two = new ContentValues();
        two.put( "name", "com.biermaster.pfandmaster3000" );

        ContentProvider provider = getProvider();
        provider.insert( PackageProvider.CONTENT_URL, one );
        provider.insert( PackageProvider.CONTENT_URL, two );

        Cursor cursor = provider.query( PackageProvider.CONTENT_URL, new String[]{ "id", "name" }, null, null, null );
        cursor.moveToNext();

        String nameOne = cursor.getString( cursor.getColumnIndex( "name" ) );
        assertEquals( nameOne, "com.fhws.bla" );

        cursor.moveToNext();

        String nameTwo = cursor.getString( cursor.getColumnIndex( "name" ) );
        assertEquals( nameTwo, "com.biermaster.pfandmaster3000" );
    }
}
