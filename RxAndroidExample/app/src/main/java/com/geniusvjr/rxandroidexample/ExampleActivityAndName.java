package com.geniusvjr.rxandroidexample;

import android.app.Activity;

/**
 * Created by dream on 16/4/12.
 */
public class ExampleActivityAndName {

    public final Class<? extends Activity> mExampleActivityClass;
    public final String mExampleName;

    public ExampleActivityAndName(Class<? extends Activity> mExampleActivityClass, String mExampleName) {
        this.mExampleActivityClass = mExampleActivityClass;
        this.mExampleName = mExampleName;
    }
}
