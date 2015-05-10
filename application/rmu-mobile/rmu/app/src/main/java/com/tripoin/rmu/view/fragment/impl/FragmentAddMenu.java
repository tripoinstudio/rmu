package com.tripoin.rmu.view.fragment.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
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
import com.tripoin.rmu.model.persist.ImageModel;
import com.tripoin.rmu.model.persist.MenuModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.persistence.orm_persistence.service.ImageMenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.MenuDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.ui.RoundedImageView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;


/**
 * Created by Achmad Fauzi on 4/18/2015 : 1:41 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentAddMenu extends Fragment {
    private TextView menuName;
    private Button buttonminus;
    private Button buttonplus;
    private TextView lblavail;
    private TextView lblstock;
    private TextView lblnoin;
    private TextView lblprice;
    private TextSliderView textSliderView;
    private RoundedImageView menuImage;
    private RoundedImageView menuImage1;
    private RoundedImageView menuImage2;
    private Canvas canvasRoundedCorner;
    private TextView lblquantity;
    private SliderLayout mDemoSlider;
    private boolean mSearchCheck;
    private PropertyUtil securityUtil;
    private int quantity;
    private BigDecimal priceList;
    private BigDecimal priceItem;
    private String menuNameData;
    View rootView;
    private Button btnOrder, btnBack;
    private OrderTempModel orderTempModel;
    private OrderTempModel orderTempModelCompare;

    public FragmentAddMenu newInstance(OrderTempModel orderTempModel){
        FragmentAddMenu mFragment = new FragmentAddMenu();
        Bundle bundle = new Bundle();
        bundle.putString(ModelConstant.MENU_CODE, orderTempModel.getMenuCode());
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_menu, container, false);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());
        ImageMenuDBManager.init(rootView.getContext());
        MenuDBManager.init(rootView.getContext());
        OrderTempDBManager.init(rootView.getContext());
        orderTempModelCompare = OrderTempDBManager.getInstance().getDataFromQuery(ModelConstant.MENU_CODE, getArguments().getString(ModelConstant.MENU_CODE));
        MenuModel menuModelDetail = MenuDBManager.getInstance().getDataFromQuery(ModelConstant.MENU_CODE, getArguments().getString(ModelConstant.MENU_CODE));
        initMenuDetail(menuModelDetail);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }

    private void initMenuDetail(MenuModel menuModelDetail){
        List<ImageModel> imageModels = ImageMenuDBManager.getInstance().getDataFromQueryByIdParent(ModelConstant.MENU_ID, menuModelDetail.getId());
        menuNameData = menuModelDetail.getMenuName();
        priceItem = new BigDecimal(menuModelDetail.getMenuPrice());
        menuName = (TextView)rootView.findViewById(R.id.menuName);
        menuName.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf"));
        lblavail = (TextView)rootView.findViewById(R.id.lbl_available);
        lblavail.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf"));
        lblstock = (TextView)rootView.findViewById(R.id.lbl_stock_information);
        lblstock.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf"));
        lblnoin = (TextView)rootView.findViewById(R.id.lbl_no_information);
        lblnoin.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf"));
        lblprice = (TextView)rootView.findViewById(R.id.lbl_price);
        lblprice.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"font/Roboto-Light.ttf"));
        buttonplus = (Button) rootView.findViewById(R.id.bt_plus);
        if(orderTempModelCompare != null){
            lblquantity = (TextView) rootView.findViewById(R.id.lbl_quantity);
            quantity = Integer.parseInt(orderTempModelCompare.getQuantity());
            lblquantity.setText(orderTempModelCompare.getQuantity());
        }
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity +=1;
                TextView t = (TextView) rootView.findViewById(R.id.lbl_quantity);
                t.setText(quantity +"");
            }
        });
        buttonminus = (Button) rootView.findViewById(R.id.bt_minus);
        buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity -=1;
                TextView t = (TextView) rootView.findViewById(R.id.lbl_quantity);
                if(quantity == -1) {
                    quantity = 0;
                } else {
                    t.setText(quantity + "");
                }
            }
        });
        btnOrder = (Button) rootView.findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity != 0) {
                    priceList = new BigDecimal(quantity).multiply(priceItem);
                    orderTempModel = new OrderTempModel();
                    orderTempModel.setQuantity(String.valueOf(quantity));
                    orderTempModel.setPrice(String.valueOf(priceList));
                    if(orderTempModelCompare == null){
                        orderTempModel.setMenuCode(getArguments().getString(ModelConstant.MENU_CODE));
                        orderTempModel.setMenuName(menuNameData);
                        OrderTempDBManager.getInstance().insertEntity(orderTempModel);
                    }else{
                        orderTempModelCompare.setQuantity(String.valueOf(quantity));
                        orderTempModelCompare.setPrice(String.valueOf(priceList));
                        OrderTempDBManager.getInstance().updateEntity(orderTempModelCompare);
                    }
                    FragmentAddOrder fragmentAddOrder = new FragmentAddOrder().newInstance("");
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddOrder).commit();
                }
            }
        });

        btnBack = (Button) rootView.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMenuList fragmentMenuList = new FragmentMenuList();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.container, fragmentMenuList).addToBackStack(null).commit();
            }
        });

        /*menuImage = (RoundedImageView)rootView.findViewById(R.id.menuImage);
        menuImage.setImageResource(R.drawable.nasi_goreng_1);
        menuImage1 = (RoundedImageView)rootView.findViewById(R.id.menuImage1);
        menuImage1.setImageResource(R.drawable.image_food1);
        menuImage2 = (RoundedImageView)rootView.findViewById(R.id.menuImage2);
        menuImage2.setImageResource(R.drawable.nasi_goreng_1);*/
        /*HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.drawable.nasi_goreng_1);
        file_maps.put("Big Bang Theory", R.drawable.image_food1);
        file_maps.put("House of Cards", R.drawable.nasi_goreng_1);
        file_maps.put("Game of Thrones", R.drawable.image_food1);*/
        menuName.setText(menuNameData);

        if("1".equals(menuModelDetail.getMenuType()))
            lblavail.setText("Makanan");
        else
            lblavail.setText("Minuman");

        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatIDR = new DecimalFormatSymbols();
        formatIDR.setCurrencySymbol("IDR ");
        formatIDR.setGroupingSeparator('.');
        formatIDR.setMonetaryDecimalSeparator(',');

        decimalFormat.setDecimalFormatSymbols(formatIDR);

        lblprice.setText(decimalFormat.format(priceItem));
        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);
        for(ImageModel imageModel : imageModels){
            textSliderView = new TextSliderView(rootView.getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(menuModelDetail.getMenuName())
                    .image(RestConstant.BASE_URL.toString().concat(RestConstant.IMAGE.toString()).concat(imageModel.getImageName()))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
                    /*.setOnSliderClickListener(rootView.getContext())*/
            //add your extra information
            textSliderView.getBundle().putString(menuModelDetail.getMenuCode().concat(String.valueOf(imageModel.getId())), menuModelDetail.getMenuName());
            mDemoSlider.addSlider(textSliderView);
            Log.d(menuModelDetail.getMenuName(), imageModel.getImageName());
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);

        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        ratingBar.setRating((float)Float.valueOf(menuModelDetail.getMenuRating()));
        /*LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(218,165,32), Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.rgb(218,165,32), Mode.SRC_ATOP);*/
        /*stars.getDrawable(0).setColorFilter(Color.rgb(218,165,32), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.rgb(218,165,32), PorterDuff.Mode.SRC_ATOP);*/
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

}
