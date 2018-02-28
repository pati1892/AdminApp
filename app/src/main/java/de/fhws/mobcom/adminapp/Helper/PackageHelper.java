package de.fhws.mobcom.adminapp.Helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageHelper {

    public static ArrayList<String> INSTALLED( Context context ){
        ArrayList<String> toReturn = new ArrayList<String>();

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages( PackageManager.GET_META_DATA );

        for( PackageInfo curInfo : packages ){
            toReturn.add( curInfo.packageName );
        }

        return toReturn;
    }
}
