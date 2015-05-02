package com.tripoin.rmu.view.fragment.impl;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.order_list.OrderListDTO;
import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.view.ui.CustomCardOrderList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:32 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentOrderList extends Fragment {

    private boolean mSearchCheck;
    private View rootView = null;
    private UserDTO userDTO;

    public FragmentOrderList newInstance(UserDTO userDTO){
        FragmentOrderList mFragment = new FragmentOrderList();
        this.userDTO = userDTO;
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_list, container, false);

        List<OrderListDTO> orderListDTOs = new ArrayList<OrderListDTO>();

        OrderListDTO orderListDTO1 = new OrderListDTO();
        orderListDTO1.setOrderId("ORD-12319230912309");
        orderListDTO1.setCarriageNumber("2");
        orderListDTO1.setSeatNumber("5A");
        orderListDTO1.setTotalPaid("20000");
        orderListDTO1.setOrderTime("08.00");
        orderListDTO1.setProcessStatus(1);

        OrderListDTO orderListDTO2 = new OrderListDTO();
        orderListDTO2.setOrderId("ORD-12319230912309");
        orderListDTO2.setCarriageNumber("12");
        orderListDTO2.setSeatNumber("7B");
        orderListDTO2.setTotalPaid("10000");
        orderListDTO2.setOrderTime("07.30");
        orderListDTO2.setProcessStatus(4);

        OrderListDTO orderListDTO3 = new OrderListDTO();
        orderListDTO3.setOrderId("ORD-12319230912309");
        orderListDTO3.setCarriageNumber("10");
        orderListDTO3.setSeatNumber("10A");
        orderListDTO3.setTotalPaid("17500");
        orderListDTO3.setOrderTime("08.30");
        orderListDTO3.setProcessStatus(2);

        OrderListDTO orderListDTO4 = new OrderListDTO();
        orderListDTO4.setOrderId("ORD-12319230912309");
        orderListDTO4.setCarriageNumber("2");
        orderListDTO4.setSeatNumber("12C");
        orderListDTO4.setTotalPaid("12000");
        orderListDTO4.setOrderTime("09.00");
        orderListDTO4.setProcessStatus(3);

        OrderListDTO orderListDTO5 = new OrderListDTO();
        orderListDTO5.setOrderId("ORD-12319230912309");
        orderListDTO5.setCarriageNumber("6");
        orderListDTO5.setSeatNumber("2A");
        orderListDTO5.setTotalPaid("19000");
        orderListDTO5.setOrderTime("09.00");
        orderListDTO5.setProcessStatus(4);

        orderListDTOs.add(orderListDTO1);
        orderListDTOs.add(orderListDTO2);
        orderListDTOs.add(orderListDTO3);
        orderListDTOs.add(orderListDTO4);
        orderListDTOs.add(orderListDTO5);

        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i<orderListDTOs.size(); i++) {
            Card card = new CustomCardOrderList(rootView.getContext(), R.layout.row_card, orderListDTOs.get(i));
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        CardListView listView = (CardListView) rootView.findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
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
                break;

            case R.id.menu_search:
                mSearchCheck = true;
                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    class getImageAsync extends AsyncTask{

        private URL url;
        private String url2 = "http://0.tqn.com/d/webclipart/1/0/5/l/4/floral-icon-5.jpg";

        getImageAsync(URL url) {
            this.url = url;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                BitmapFactory.Options options = new BitmapFactory.Options();
                //Picasso.with(rootView.getContext()).load(url2).into();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
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
                Toast.makeText(rootView.getContext(), "SEARCH", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    };
}
