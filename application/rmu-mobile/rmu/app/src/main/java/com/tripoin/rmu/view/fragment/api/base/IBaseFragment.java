package com.tripoin.rmu.view.fragment.api.base;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Achmad Fauzi on 11/24/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Interface ini digunakan sebagai fungsi- fungsi common sebuah fragment
 */
public interface IBaseFragment {

    /**
     * Method ini digunakan untuk inisiasi widget- widget activity yang sedang aktif termasuk variabel
     */
    public void initWidget();

    public int getViewLayoutId();

    /**
     * Mengkoleksi TextView dalam grup Title dalam sebuah Activity
     * @return List<TextView>
     */
    public List<TextView> getTitleTextViews();

    /**
     * Mengkoleksi TextView dalam grup content dalam sebuah Activity
     * @return List<TextView>
     */
    public List<TextView> getContentTextViews();

    /**
     * Mengkoleksi EditText dalam sebuah Activity
     * @return List<EditText>
     */
    public List<EditText> getEditTexts();

    /**
     * Mengkoleksi Button dalam sebuah Fragment
     * @return List<Button>
     */
    public List<Button> getButtons();

    public String[] initAssetName();

}
