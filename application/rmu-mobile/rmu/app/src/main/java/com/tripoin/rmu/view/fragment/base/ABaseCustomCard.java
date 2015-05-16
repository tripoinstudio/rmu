package com.tripoin.rmu.view.fragment.base;

import android.content.Context;

import com.tripoin.rmu.view.fragment.api.base.IBaseCustomCard;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 5/11/2015 : 8:42 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class ABaseCustomCard extends Card implements IBaseCustomCard {

    public ABaseCustomCard(Context context) {
        super(context);
        initActions();
    }

    public ABaseCustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
        initActions();
    }
}
