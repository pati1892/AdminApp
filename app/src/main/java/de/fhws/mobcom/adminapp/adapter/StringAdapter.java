package de.fhws.mobcom.adminapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.R;

/**
 * Created by kanga on 27.02.2018.
 */

public class StringAdapter extends ArrayAdapter<String> {

    public StringAdapter(Context context, ArrayList<String> packages) {
        super( context, 0, packages );
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ){

        String packageName = getItem( position );

        if( convertView == null )
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.item_package, parent, false );

        TextView appLabel = ( TextView ) convertView.findViewById( R.id.appLabel );
        appLabel.setText( packageName );

        TextView appName = ( TextView ) convertView.findViewById( R.id.appName );
        appName.setText( packageName );

        return convertView;
    }
}
