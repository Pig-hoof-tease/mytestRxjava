package com.mytestrxjava.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yomoo on 2017/12/4.
 */

public class DependentBehavior extends CoordinatorLayout.Behavior{
    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

//        ViewCompat.offsetLeftAndRight();
        return super.onDependentViewChanged(parent, child, dependency);
    }



}
