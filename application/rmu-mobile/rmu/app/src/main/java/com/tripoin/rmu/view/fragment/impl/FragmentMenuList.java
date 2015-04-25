package com.tripoin.rmu.view.fragment.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardGridView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 1:31 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentMenuList extends Fragment {

    protected ScrollView mScrollView;
    private boolean mSearchCheck;
    View rootView = null;

    public FragmentMenuList newInstance(String text){
        FragmentMenuList mFragment = new FragmentMenuList();
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        initCards();
    }

    private void initCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 200; i++) {
            GplayGridCard card = new GplayGridCard(getActivity());
            card.headerTitle = "App example " + i;
            card.secondaryTitle = "Some text here " + i;
            card.rating = (float) (Math.random() * (5.0));
            if ((i % 6 == 0)) {
                card.resourceIdThumbnail = R.drawable.ic_ic_dh_bat;
            } else if ((i % 6 == 1)) {
                card.resourceIdThumbnail = R.drawable.ic_ic_dh_net;
            } else if ((i % 6 == 2)) {
                card.resourceIdThumbnail = R.drawable.ic_tris;
            } else if ((i % 6 == 3)) {
                card.resourceIdThumbnail = R.drawable.ic_info;
            } else if ((i % 6 == 4)) {
                card.resourceIdThumbnail = R.drawable.ic_smile;
            }
            card.init();
            cards.add(card);
        }

        CardGridArrayAdapter mCardArrayAdapter = new CardGridArrayAdapter(getActivity(), cards);

        CardGridView listView = (CardGridView) getActivity().findViewById(R.id.carddemo_grid_base1);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

        menu.findItem(R.id.menu_add).setVisible(true);

        mSearchCheck = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_search:
                mSearchCheck = true;
                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public class GplayGridCard extends Card {

        protected TextView mTitle;
        protected TextView mSecondaryTitle;
        protected RatingBar mRatingBar;
        protected int resourceIdThumbnail = -1;
        protected int count;

        protected String headerTitle;
        protected String secondaryTitle;
        protected float rating;

        public GplayGridCard(Context context) {
            super(context, R.layout.carddemo_gplay_inner_content);
        }

        public GplayGridCard(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        private void init() {
            CardHeader header = new CardHeader(getContext());
            header.setButtonOverflowVisible(true);
            header.setTitle(headerTitle);
            header.setPopupMenu(R.menu.popupmain, new CardHeader.OnClickCardHeaderPopupMenuListener() {
                @Override
                public void onMenuItemClick(BaseCard card, MenuItem item) {
                    Toast.makeText(getContext(), "Item " + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            addCardHeader(header);

            GplayGridThumb thumbnail = new GplayGridThumb(getContext());
            if (resourceIdThumbnail > -1)
                thumbnail.setDrawableResource(resourceIdThumbnail);
            else
                thumbnail.setDrawableResource(R.drawable.ic_launcher);
            addCardThumbnail(thumbnail);

            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    //Do something
                }
            });
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
            TextView title = (TextView) view.findViewById(R.id.carddemo_gplay_main_inner_title);
            title.setText("FREE");

            TextView subtitle = (TextView) view.findViewById(R.id.carddemo_gplay_main_inner_subtitle);
            subtitle.setText(secondaryTitle);

            RatingBar mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_gplay_main_inner_ratingBar);

            mRatingBar.setNumStars(5);
            mRatingBar.setMax(5);
            mRatingBar.setStepSize(0.5f);
            mRatingBar.setRating(rating);
        }

        class GplayGridThumb extends CardThumbnail {

            public GplayGridThumb(Context context) {
                super(context);
            }

            @Override
            public void setupInnerViewElements(ViewGroup parent, View viewImage) {
                //viewImage.getLayoutParams().width = 196;
                //viewImage.getLayoutParams().height = 196;

            }
        }
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck){
                // implement your search here
            }
            return false;
        }
    };
}
