package de.fhws.mobcom.adminapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageProvider extends ContentProvider {

    static final String PROVIDER_NAME = "de.fhws.mobcom.adminapp.PackageProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/apps";
    static final Uri CONTENT_URL = Uri.parse( URL );

    static final String id = "id";
    static final String name = "name";
    static final int ALL = 1;
    static final int SINGLE = 2;

    private static HashMap<String, String> values;

    static final UriMatcher uriMatcher = new UriMatcher( UriMatcher.NO_MATCH );
    static {
        uriMatcher.addURI( PROVIDER_NAME, "apps", ALL );
        uriMatcher.addURI( PROVIDER_NAME, "apps/#", SINGLE );
    }

    private SQLiteDatabase sqlDb;
    static final String DATABASE_NAME = "admin";
    static final String TABLE_NAME = "apps";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL);";

    @Override
    public boolean onCreate() {

        DatabaseHelper dbHelper = new DatabaseHelper( getContext() );
        sqlDb = dbHelper.getWritableDatabase();

        if( sqlDb != null )
            return true;

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables( TABLE_NAME );

        switch( uriMatcher.match( uri ) ){
            case ALL:
                queryBuilder.setProjectionMap( values );
                break;
            case SINGLE:

                break;
            default:
                throw new IllegalArgumentException( "Unknown URI " + uri );
        }

        Cursor cursor = queryBuilder.query( sqlDb, projection, selection, selectionArgs, null, null, sortOrder );
        cursor.setNotificationUri( getContext().getContentResolver(), uri );

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch( uriMatcher.match( uri ) ){
            case ALL:
                return "vnd.android.cursor.dir/apps";
            case SINGLE:
                return "vnd.android.cursor.dir/apps";
            default:
                throw new IllegalArgumentException( "Unknown URI " + uri );
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        switch( uriMatcher.match( uri ) ){
            case ALL:
                break;
            case SINGLE:
                throw new IllegalArgumentException( "Cannot insert an item with an id." );
            default:
                throw new IllegalArgumentException( "Unknown URI " + uri );
        }

        long rowId = sqlDb.insert( TABLE_NAME, null, contentValues );

        if( rowId > 0 ){
            Uri _uri = ContentUris.withAppendedId( CONTENT_URL, rowId );
            getContext().getContentResolver().notifyChange( _uri, null );
            return _uri;
        } else {
            Toast.makeText( getContext(), "Row Insert Failed", Toast.LENGTH_SHORT ).show();
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;

        switch( uriMatcher.match( uri ) ) {
            case ALL:
                rowsDeleted = sqlDb.delete( TABLE_NAME, selection, selectionArgs );
                break;
            case SINGLE:
                throw new IllegalArgumentException( "Cannot delete an item on this route." );
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange( uri, null );
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int rowsUpdated = 0;

        switch( uriMatcher.match( uri ) ) {
            case ALL:
                rowsUpdated = sqlDb.update( TABLE_NAME, contentValues, selection, selectionArgs );
                break;
            case SINGLE:
                throw new IllegalArgumentException( "Cannot update an item on this route." );
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange( uri, null );
        return rowsUpdated;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper( Context context ){
            super( context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d( PROVIDER_NAME, "onCreate(..)" );
            sqLiteDatabase.execSQL( CREATE_DB_TABLE );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
            onCreate( sqLiteDatabase );
        }
    }
}

