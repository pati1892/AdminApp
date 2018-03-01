package de.fhws.mobcom.adminapp.model;

import android.graphics.drawable.Drawable;

/**
 * Created by kanga on 27.02.2018.
 */

public class Package {
    public String mId;
    public String mName;
    public String mLabel;
    public Drawable mIcon;
    public boolean mIsHidden;

    public Package( String id, String name, String label, Drawable icon ){
        mId = id;
        mName = name;
        mLabel = label;
        mIcon = icon;
        mIsHidden = false;
    }
}
