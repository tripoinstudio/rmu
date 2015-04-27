package com.tripoin.rmu.view.fragment.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tripoin.rmu.view.fragment.api.INavigationDrawerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 11:20 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class ABaseNavigationDrawerFragment extends Fragment implements INavigationDrawerFragment{

    protected Typeface typeFaceContent;
    protected List<TextView> textViews = new ArrayList<TextView>();
    protected List<Button> buttons = new ArrayList<Button>();

    protected ABaseNavigationDrawerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
