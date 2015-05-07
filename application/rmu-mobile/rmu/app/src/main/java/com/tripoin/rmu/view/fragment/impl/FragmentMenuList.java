package com.tripoin.rmu.view.fragment.impl;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeMenu;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMenuList;
import com.tripoin.rmu.view.ui.CustomCardSource;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardGridView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 1:31 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentMenuList extends Fragment implements ISynchronizeMenuList {

    protected ScrollView mScrollView;
    private boolean mSearchCheck;
    View rootView = null;
    private PropertyUtil securityUtil;
    private String imageName;
    private String subtitle;
    private String price;
    private GplayGridCard card;
    //  FragmentDetailOrderList fragmentDetailOrderList = null;


    public FragmentMenuList newInstance(String text){
        FragmentMenuList mFragment = new FragmentMenuList();
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());
        new MenuASync().execute();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private void initSearchCards(String s) {
        List<MenuModel> menuModels = MenuDBManager.getInstance().getAllDataFromQuery(s);
        ArrayList<Card> cards = new ArrayList<Card>();
        for (MenuModel menuModel : menuModels) {
            if(menuModel.getMenuType().equalsIgnoreCase("1"))
                subtitle = "Makanan";
            else
                subtitle = "Minuman";
            price = menuModel.getMenuPrice();
            card = new GplayGridCard(getActivity(), menuModel.getMenuName(), subtitle, price);
            card.rating = (float)(Float.valueOf(menuModel.getMenuRating()));
            imageName = menuModel.getMenuImageURL();
            CardThumbnail.CustomSource customSource = new CustomCardSource(rootView.getContext(),imageName ).getCustomSource();
            card.init(customSource);
            cards.add(card);
        }

        CardGridArrayAdapter mCardArrayAdapter = new CardGridArrayAdapter(getActivity(), cards);

        CardGridView listView = (CardGridView) getActivity().findViewById(R.id.carddemo_grid_base1);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }

    }

    private void initCards(List<MenuModel> menuModels) {
        ArrayList<Card> cards = new ArrayList<Card>();

        for (final MenuModel menuModel : menuModels) {
            if(menuModel.getMenuType().equalsIgnoreCase("1"))
                subtitle = "Makanan";
            else
                subtitle = "Minuman";
            price = menuModel.getMenuPrice();

            card = new GplayGridCard(getActivity(), menuModel.getMenuName(), subtitle, price);
            card.rating = (float)(Float.valueOf(menuModel.getMenuRating()));
            card.stock = menuModel.getMenuStock();
            imageName = menuModel.getMenuImageURL();

            CardThumbnail.CustomSource customSource = new CustomCardSource(rootView.getContext(),imageName ).getCustomSource();
            card.init(customSource);
        //    final FragmentManager mFragmentManager = ((FragmentActivity) rootView.getContext()).getSupportFragmentManager();
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {

//                    Toast.makeText(view.getContext(), "Click listener TesCode MENU = "+menuModel.getMenuCode() , Toast.LENGTH_SHORT).show();
//                    Log.d("mnucde",menuModel.getMenuCode());
                    FragmentAddMenu fragmentAddMenu = new FragmentAddMenu().newInstance(menuModel.getMenuCode());
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddMenu).commit();

                }

            });
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
                FragmentAddOrder fragmentAddOrder = new FragmentAddOrder().newInstance("");
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddOrder).commit();
//                Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
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

        protected String secondaryTitle;
        protected String price;
        protected float rating;
        protected String headerTitle;
        protected String stock;

        public GplayGridCard(Context context, String headerTitle, String secondaryTitle, String price) {
            super(context, R.layout.carddemo_gplay_inner_content);
            this.secondaryTitle = secondaryTitle;
            this.price = price;
            this.headerTitle = headerTitle;
        }

        public GplayGridCard(Context context, int innerLayout) {
            super(context, innerLayout);
        }

        private void init(CardThumbnail.CustomSource customSource) {
            GplayGridThumb thumbnail = new GplayGridThumb(getContext());
            if (customSource != null)
                thumbnail.setCustomSource(customSource);
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
            TextView headerTitle = (TextView) view.findViewById(R.id.card_title);
            headerTitle.setText(this.headerTitle);

            TextView title = (TextView) view.findViewById(R.id.carddemo_gplay_main_inner_title);
            title.setText(ViewConstant.CURRENCY_IDR.toString().concat(price).concat(",-"));

            TextView subtitle = (TextView) view.findViewById(R.id.carddemo_gplay_main_inner_subtitle);
            subtitle.setText(secondaryTitle);

            TextView stockTitle = (TextView) view.findViewById(R.id.card_stock);
            stockTitle.setText(ViewConstant.STOCK.toString().concat(this.stock));

            RatingBar mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_gplay_main_inner_ratingBar);

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
                initSearchCards(s);
            }
            return false;
        }
    };

    @Override
    public void onPostFirstSyncOrderList(List<MenuModel> menuModels) {
        initCards(menuModels);
    }

    @Override
    public void onPostContSyncOrderList(List<MenuModel> menuModels) {
        initCards(menuModels);
    }

    private class MenuASync extends AsyncTask{

        private ProgressDialog progressDialog;
        SynchronizeMenu synchronizeMenu;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MenuDBManager.init(rootView.getContext());
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setMessage("Loading menu list");
            progressDialog.setCancelable(false);
            progressDialog.show();
            synchronizeMenu = new SynchronizeMenu(securityUtil, rootView.getContext(), ModelConstant.REST_MENU_TABLE.toString(), FragmentMenuList.this);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            synchronizeMenu.detectVersionDiff();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }
    }


}
