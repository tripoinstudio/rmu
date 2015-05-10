package com.tripoin.rmu.view.fragment.impl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeOrderList;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.SeatDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderList;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;
import com.tripoin.rmu.view.ui.CustomCardOrderList;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:32 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 * Order List
 */
public class FragmentOrderList extends ABaseNavigationDrawerFragment implements ISynchronizeOrderList{

    private boolean mSearchCheck;
    private PropertyUtil securityUtil;

    private String array_spinner_crrg[];
    private String array_spinner_seat[];

    CarriageDialogAdapter carriageDialogAdapter;
    SeatDialogAdapter seatDialogAdapter;
    EditText srcOrderId;
    Spinner srcStatus, srcSeat, srcCrrg;

    private final String ORDER_ID = "ORDER_ID";

    @InjectView(R.id.listOrder) CardListView listView;

    public FragmentOrderList newInstance(String data){
        FragmentOrderList mFragment = new FragmentOrderList();
        if(data.length()>0){
            Bundle mBundle = new Bundle();
            mBundle.putString(ORDER_ID, data);
            mFragment.setArguments(mBundle);
        }
        return mFragment;
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

        final MenuItem menuItem =menu.findItem(R.id.menu_search);
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

                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_orderlist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);
                alertDialogBuilder.create();


                List<CarriageModel> carriageModels = CarriageDBManager.getInstance().getAllData();
                initSpinnerCarriage(carriageModels);

                List<SeatModel> seatModels = SeatDBManager.getInstance().getAllData();
                initSpinnerSeat(seatModels);

                srcOrderId = (EditText) dialogView.findViewById(R.id.src_orderId);
                srcStatus = (Spinner) dialogView.findViewById(R.id.src_status);
                srcSeat = (Spinner) dialogView.findViewById(R.id.src_seat);
                srcCrrg = (Spinner) dialogView.findViewById(R.id.src_crrg);

                carriageDialogAdapter = new CarriageDialogAdapter(getActivity());
                srcCrrg.setAdapter(carriageDialogAdapter);

                seatDialogAdapter = new SeatDialogAdapter(getActivity());
                srcSeat.setAdapter(seatDialogAdapter);

                alertDialogBuilder.setCancelable(false).setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //OnClick Searching
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();

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
                Toast.makeText(getActivity(), "SEARCH", Toast.LENGTH_LONG).show();
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
            if( orderListModels.get(i).getProcessStatus() != IOrderStatusConstant.DONE ){
                Card card = new CustomCardOrderList(getActivity(), R.layout.row_card, orderListModels.get(i));
                cards.add(card);
            }
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    public String getFragmentTitle() {
        return ViewConstant.FRAGMENT_ORDER_LIST_TITLE.toString();
    }

    @Override
    public void initWidget() {
        Bundle bundle = getArguments();
        if( bundle != null){
            FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
            FragmentOrderDetail fragmentOrderDetail = new FragmentOrderDetail().newInstance(bundle.getString("ORDER_ID"));
            mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderDetail).commit();
        }else{
            securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), getActivity());
            new OrderListAsync().execute();
        }

        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });*/
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_order_list;
    }

    public void initSpinnerCarriage(List<CarriageModel> carriageModel){
        int array =  carriageModel.size();
        array_spinner_crrg = new String[array];

        for(int i = 0; i<array;i++){
            array_spinner_crrg[i] = carriageModel.get(i).getCarriageNo();
            Log.d("carriageDB", carriageModel.toString());
        }

    }

    public void initSpinnerSeat(List<SeatModel> seatModel){
        int array =  seatModel.size();
        array_spinner_seat = new String[array];

        for(int i = 0; i<array;i++){
            array_spinner_seat[i] = seatModel.get(i).getSeatNo();
        }

    }


    private class CarriageDialogAdapter extends BaseAdapter {

        LayoutInflater layoutInflaters;

        public CarriageDialogAdapter(Context context) {
            layoutInflaters = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return array_spinner_crrg.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListContentCrrg holder;
            View v = convertView;
            if(v == null){
                v = layoutInflaters.inflate(R.layout.fragmentaddorder_style, null);
                holder = new ListContentCrrg();
                holder.crrg = (TextView) v.findViewById(R.id.textView1);
                v.setTag(holder);

            } else {
                holder = (ListContentCrrg) v.getTag();
            }
            holder.crrg.setTextSize(15);
            holder.crrg.setText(""+array_spinner_crrg[position]);
            return v;
        }
    }

    static class ListContentCrrg {
        TextView crrg;
    }

    private class SeatDialogAdapter extends BaseAdapter {

        LayoutInflater layoutInflaters;

        public SeatDialogAdapter(Context context) {
            layoutInflaters = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return array_spinner_seat.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListContentSeat holder;
            View v = convertView;
            if(v == null){
                v = layoutInflaters.inflate(R.layout.fragmentaddorder_styleseat, null);
                holder = new ListContentSeat();
                holder.seat = (TextView) v.findViewById(R.id.textView2);
                v.setTag(holder);

            } else {
                holder = (ListContentSeat) v.getTag();
            }
            holder.seat.setTextSize(15);
            holder.seat.setText(""+array_spinner_seat[position]);
            return v;
        }
    }

    static class ListContentSeat {
        TextView seat;
    }


    private class OrderListAsync extends AsyncTask{

        private ProgressDialog progressDialog;
        SynchronizeOrderList synchronizeOrderList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            OrderListDBManager.init(getActivity());
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading order list");
            progressDialog.setCancelable(false);
            progressDialog.show();
            CarriageDBManager.init(getActivity());
            SeatDBManager.init(getActivity());

            synchronizeOrderList = new SynchronizeOrderList(securityUtil, getActivity(), ModelConstant.REST_ORDER_HEADER_TABLE.toString(), FragmentOrderList.this);
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

/*    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BackgroundSynchronizeOrderList synchronizeOrderList = new BackgroundSynchronizeOrderList(securityUtil, rootView.getContext(), ModelConstant.REST_ORDER_HEADER_TABLE.toString(),FragmentOrderList.this);
                synchronizeOrderList.detectVersionDiff();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2500);
    }*/
}
