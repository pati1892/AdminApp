package de.fhws.mobcom.adminapp.adapter;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.mobcom.adminapp.R;
import de.fhws.mobcom.adminapp.model.Package;

/**
 * Created by kanga on 28.02.2018.
 */

public class PackageAdapter extends ArrayAdapter<ApplicationInfo> {

    private static final String TAG = PackageAdapter.class.getSimpleName();
    private PackageManager mManager;

    public PackageAdapter(Context context, ArrayList<ApplicationInfo> packages ) {
        super( context, 0, packages );
        mManager = context.getPackageManager();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){

        ApplicationInfo item = getItem( position );

        if( convertView == null )
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.item_package, parent, false );

        ImageView appIcon = ( ImageView ) convertView.findViewById( R.id.appIcon );
        appIcon.setImageDrawable( item.loadIcon(mManager));

        TextView appLabel = ( TextView ) convertView.findViewById( R.id.appLabel );
        appLabel.setText( mManager.getApplicationLabel(item) );

        TextView appName = ( TextView ) convertView.findViewById( R.id.appName );
        appName.setText( item.packageName);

        CheckBox appHidden = ( CheckBox ) convertView.findViewById(R.id.appChecked);
        if( item.enabled ) {
            appHidden.setChecked( true );
        } else {
            appHidden.setChecked( false );
        }

        return convertView;
    }
}
