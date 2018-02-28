package de.fhws.mobcom.adminapp.Helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import de.fhws.mobcom.adminapp.Model.Package;

/**
 * Created by kanga on 27.02.2018.
 */

public class PackageHelper {

    public static ArrayList<Package> INSTALLED( Context context ){
        ArrayList<Package> toReturn = new ArrayList<Package>();

        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages( PackageManager.GET_META_DATA );

        for( PackageInfo curInfo : packages ){
            Package curPackage = new Package(null, curInfo.packageName, (String) pm.getApplicationLabel( curInfo.applicationInfo ), pm.getApplicationIcon( curInfo.applicationInfo ) );
            toReturn.add( curPackage );
        }

        return toReturn;
    }
}
