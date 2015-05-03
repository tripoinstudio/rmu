package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderList;
import com.tripoin.rmu.view.ui.CustomCardOrderList;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:32 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentOrderList extends Fragment implements ISynchronizeOrderList{

    private boolean mSearchCheck;
    private View rootView = null;
    /*private UserDTO userDTO;
    private List<OrderListModel> orderListModels;*/
    private PropertyUtil securityUtil;

    public FragmentOrderList newInstance(UserDTO userDTO, List<OrderListModel> orderListModels){
        FragmentOrderList mFragment = new FragmentOrderList();
        /*if( userDTO != null ){
            this.userDTO = userDTO;
        }else{
            this.orderListModels = orderListModels;
            Log.d("ORDELISTMODEL", orderListModels.toString());
        }*/

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_list, container, false);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());
        new OrderListAsync().execute();
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        //Select search item
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search_order_list));

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
                FragmentAddOrder fragmentAddOrder = new FragmentAddOrder();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddOrder).commit();
                break;

            case R.id.menu_search:
                mSearchCheck = true;
                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck){
                Toast.makeText(rootView.getContext(), "SEARCH", Toast.LENGTH_LONG).show();

            }
            return false;
        }
    };

    @Override
    public void onPostFirstSyncOrderList(List<OrderListModel> orderListModels) {
        initCards(orderListModels);
    }

    @Override
    public void onPostContSyncOrderList(List<OrderListModel> orderListModels) {
        initCards(orderListModels);
    }

    private void initCards(List<OrderListModel> orderListModels){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderListModels.size(); i++) {
            Card card = new CustomCardOrderList(getActivity(), R.layout.row_card, orderListModels.get(i));
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        CardListView listView = (CardListView) rootView.findViewById(R.id.listOrder);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    private class OrderListAsync extends AsyncTask{

        private ProgressDialog progressDialog;
        SynchronizeOrderList synchronizeOrderList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            OrderListDBManager.init(rootView.getContext());
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setMessage("Loading order list");
            progressDialog.setCancelable(false);
            progressDialog.show();
            synchronizeOrderList = new SynchronizeOrderList(securityUtil, rootView.getContext(), ModelConstant.REST_ORDER_HEADER_TABLE.toString(), FragmentOrderList.this);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            synchronizeOrderList.detectVersionDiff();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }
    }
}
