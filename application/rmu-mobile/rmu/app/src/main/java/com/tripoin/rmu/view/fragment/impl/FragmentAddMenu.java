package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.tripoin.rmu.R;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMenuList;
import com.tripoin.rmu.view.ui.RoundedImageView;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Achmad Fauzi on 4/18/2015 : 1:41 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentAddMenu extends Fragment implements ISynchronizeMenuList {
    private TextView menuName;
    private RoundedImageView menuImage;
    private RoundedImageView menuImage1;
    private RoundedImageView menuImage2;
    private Canvas canvasRoundedCorner;
    private SliderLayout mDemoSlider;
    private boolean mSearchCheck;
    private PropertyUtil securityUtil;
    private int numtest;
    View rootView;
    private String menuCode;
    private Button btnOrder;
    private final String MENU_CODE = "menu_code";
    private MenuModel menuModel;

    public FragmentAddMenu newInstance(String menuCode){
        Log.d("menu code f add menu", menuCode);
        FragmentAddMenu mFragment = new FragmentAddMenu();
        Bundle bundle = new Bundle();
        bundle.putString(MENU_CODE, menuCode);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_menu, container, false);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());
        MenuDBManager.init(rootView.getContext());
        new MenuASync().execute();
        menuName = (TextView)rootView.findViewById(R.id.menuName);
        menuName.setText(getArguments().getString(MENU_CODE));
        TextView txus2=(TextView)rootView.findViewById(R.id.tx_dates);
        Typeface faces1=Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf");
        menuName.setTypeface(faces1);
        btnOrder = (Button) rootView.findViewById(R.id.btn_order);

        Button buttonplus = (Button) rootView.findViewById(R.id.bt_plus);
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numtest+=1;
                TextView t = (TextView) rootView.findViewById(R.id.lbl_quantity);
                t.setText(numtest+"");
            }
        });
        Button buttonminus = (Button) rootView.findViewById(R.id.bt_minus);
        buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numtest-=1;
                TextView t = (TextView) rootView.findViewById(R.id.lbl_quantity);
                if(numtest == -1) {
                    numtest = 0;
                } else {
                    t.setText(numtest + "");
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentAddOrder fragmentAddOrder = new FragmentAddOrder().newInstance("");
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddOrder).commit();
            }
        });


        /*menuImage = (RoundedImageView)rootView.findViewById(R.id.menuImage);
        menuImage.setImageResource(R.drawable.nasi_goreng_1);
        menuImage1 = (RoundedImageView)rootView.findViewById(R.id.menuImage1);
        menuImage1.setImageResource(R.drawable.image_food1);
        menuImage2 = (RoundedImageView)rootView.findViewById(R.id.menuImage2);
        menuImage2.setImageResource(R.drawable.nasi_goreng_1);*/

        TextView lblavail = (TextView)rootView.findViewById(R.id.lbl_available);
        lblavail.setTypeface(faces1);

        TextView lblstock = (TextView)rootView.findViewById(R.id.lbl_stock_information);
        lblstock.setTypeface(faces1);

        TextView lblnoin = (TextView)rootView.findViewById(R.id.lbl_no_information);
        lblnoin.setTypeface(faces1);

        TextView lblprice = (TextView)rootView.findViewById(R.id.lbl_price);
        lblprice.setTypeface(faces1);


        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.nasi_goreng_1);
        file_maps.put("Big Bang Theory", R.drawable.image_food1);
        file_maps.put("House of Cards", R.drawable.nasi_goreng_1);
        file_maps.put("Game of Thrones", R.drawable.image_food1);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(rootView.getContext());
// initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
            //        .setOnSliderClickListener(rootView.getContext())
            ;
//add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(218,165,32), Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.rgb(218,165,32), Mode.SRC_ATOP);
//        stars.getDrawable(0).setColorFilter(Color.rgb(218,165,32), PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(2).setColorFilter(Color.rgb(218,165,32), PorterDuff.Mode.SRC_ATOP);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public Bitmap roundCornerImage(Bitmap src, float round) {
        // Source image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create result bitmap output
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // set canvas for painting
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        // configure paint
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        // configure rectangle for embedding
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        // draw Round rectangle to canvas
        canvas.drawRoundRect(rectF, round, round, paint);

        // create Xfer mode
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        // draw source image to canvas
        canvas.drawBitmap(src, rect, rect, paint);

        // return final image
        return result;
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

    @Override
    public void onPostFirstSyncOrderList(List<MenuModel> menuModels) {
//        initCards(menuModels);
    }

    @Override
    public void onPostContSyncOrderList(List<MenuModel> menuModels) {
//        initCards(menuModels);
    }

    private class MenuASync extends AsyncTask {

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
            synchronizeMenu = new SynchronizeMenu(securityUtil, rootView.getContext(), ModelConstant.REST_MENU_TABLE.toString(), FragmentAddMenu.this);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.d("MENU", "detect version");
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
