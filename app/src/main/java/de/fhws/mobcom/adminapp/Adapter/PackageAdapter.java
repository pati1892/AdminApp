package de.fhws.mobcom.adminapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.R;
import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 28.02.2018.
 */

public class PackageAdapter extends ArrayAdapter<Package> {

    public PackageAdapter(Context context, ArrayList<Package> packages) {
        super( context, 0, packages );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){

        Package item = getItem( position );

        if( convertView == null )
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.item_package, parent, false );

        ImageView appIcon = ( ImageView ) convertView.findViewById( R.id.appIcon );
        appIcon.setImageDrawable( item.mIcon );

        TextView appLabel = ( TextView ) convertView.findViewById( R.id.appLabel );
        appLabel.setText( item.mLabel );

        TextView appName = ( TextView ) convertView.findViewById( R.id.appName );
        appName.setText( item.mName );

        if( item.mIsHidden ) {
            CheckBox appHidden = (CheckBox) convertView.findViewById(R.id.appChecked);
            appHidden.setChecked(true);
        }

        return convertView;
    }
}
