package de.fhws.mobcom.adminapp.model;

import android.graphics.drawable.Drawable;

/**
 * Created by kanga on 27.02.2018.
 */

public class Package {
    public String mId;
    public String mPackageName;
    public String mApplicationName;
    public Drawable mIcon;
    public boolean mIsHidden;

    public Package( String id, String packageName, String applicationName, Drawable icon ){
        mId = id;
        mPackageName = packageName;
        mApplicationName = applicationName;
        mIcon = icon;
        mIsHidden = false;
    }
}
