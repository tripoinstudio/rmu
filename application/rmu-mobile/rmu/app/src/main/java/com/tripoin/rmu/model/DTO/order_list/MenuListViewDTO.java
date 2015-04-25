package com.tripoin.rmu.model.DTO.order_list;

import android.media.Rating;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 5:55 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class MenuListViewDTO {

    private TextView menuId;
    private TextView product;
    private TextView categoryProduct;
    private RatingBar ratingProduct;
    private TextView priceProduct;
    private ImageView thumbProduct;

    public TextView getMenuId() {
        return menuId;
    }

    public void setMenuId(TextView menuId) {
        this.menuId = menuId;
    }

    public TextView getProduct() {
        return product;
    }

    public void setProduct(TextView product) {
        this.product = product;
    }

    public TextView getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(TextView categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public RatingBar getRatingProduct() {
        return ratingProduct;
    }

    public void setRatingProduct(RatingBar ratingProduct) {
        this.ratingProduct = ratingProduct;
    }

    public TextView getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(TextView priceProduct) {
        this.priceProduct = priceProduct;
    }

    public ImageView getThumbProduct() {
        return thumbProduct;
    }

    public void setThumbProduct(ImageView thumbProduct) {
        this.thumbProduct = thumbProduct;
    }
}
