package com.tripoin.rmu.view.fragment.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.CameraUtil;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import java.nio.channels.FileChannel;

/**
 * Created by Ginanjar Aji Sanjaya on 4/29/2015.
 */

public class FragmentUserProfile extends Fragment {

    private String temp;
    private TextView nameUser;
    private TextView jabatanUser;
    private TextView emailUser;
    private TextView summmaryUser;
    private TextView lblEditText;
    private TextView lblViewPhoto;
    private TextView lblChangePhotoGallery;
    private TextView lblChangePhotoCamera;
    private ImageView imgUserProfile, imgVIewDetailPhoto, imgNameUser, imgJabatanUser, imgEmailUser, imgSummmaryUses;
    private EditText editText;
    private PropertyUtil propertyUtil;
    private Uri picUri;
    private CameraUtil cameraUtil;

    public FragmentUserProfile newInstance(String text) {
        return new FragmentUserProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), rootView.getContext());
        cameraUtil = new CameraUtil();
        getActivity().setTitle(ViewConstant.FRAGMENT_PROFILE_TITLE.toString());
        imgUserProfile = (ImageView) rootView.findViewById(R.id.imgUserProfile);
        final Bitmap bmp = decodeSampledBitmapFromPath(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_PHOTO.toString()), 360, 270);
        if(bmp!=null) imgUserProfile.setImageBitmap(bmp);

        imgUserProfile.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(PropertyConstant.PROPERTIES_PATH.toString(), PropertyConstant.PROFILE_PHOTO_NAME.toString());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 2);
                return true;
            }
        });

        imgUserProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_user_profile_photo_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                lblViewPhoto = (TextView) dialogView.findViewById(R.id.lbl_view_photo);
                lblViewPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                        View dialogView = layoutInflater.inflate(R.layout.fragment_view_photo_profile, null);

                        if(bmp!=null){
                            imgVIewDetailPhoto = (ImageView) dialogView.findViewById(R.id.imgViewDetailPhoto);
                            imgVIewDetailPhoto.setImageBitmap(bmp);
                        }

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                        alertDialogBuilder.setView(dialogView);

                        AlertDialog alertD = alertDialogBuilder.create();
                        alertD.show();
                    }
                });

                lblChangePhotoGallery = (TextView) dialogView.findViewById(R.id.lbl_change_photo_gallery);
                lblChangePhotoGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(cameraUtil.takePictFromGallery(), 1);
                    }
                });

                lblChangePhotoCamera = (TextView) dialogView.findViewById(R.id.lbl_change_photo_camera);
                lblChangePhotoCamera.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        File f = new File(PropertyConstant.PROPERTIES_PATH.toString(), "photo_profile_rmu.jpg");
//                        picUri = Uri.fromFile(f);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
//                        // ******** code for crop image
//                        intent.putExtra("crop", "true");
//                        intent.putExtra("aspectX", 0);
//                        intent.putExtra("aspectY", 0);
//                        intent.putExtra("outputX", 600);
//                        intent.putExtra("outputY", 600);
                        startActivityForResult(cameraUtil.takePictFromCamera(), 2);
                    }
                });

                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        nameUser = (TextView) rootView.findViewById(R.id.txNameUser);
        Typeface fontFaceName = Typeface.createFromAsset(nameUser.getResources().getAssets(),"font/Roboto-Medium.ttf");
        nameUser.setTypeface(fontFaceName);
        temp=propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_NAME.toString());
        if(temp!=null){
            nameUser.setText("Nama : "+temp);
        }
        imgNameUser = (ImageView) rootView.findViewById(R.id.imgNameUser);
        imgNameUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Input your name here :");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_NAME.toString()));

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_NAME.toString(), editText.getText().toString());
                        nameUser.setText("Name : ".concat(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_NAME.toString())));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();

            }
        });

        jabatanUser = (TextView) rootView.findViewById(R.id.txJabatanUser);
        Typeface fontFaceJabatan = Typeface.createFromAsset(jabatanUser.getResources().getAssets(),"font/Roboto-Medium.ttf");
        jabatanUser.setTypeface(fontFaceJabatan);
        temp=propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_POSITION.toString());
        if(temp!=null){
            jabatanUser.setText("Jabatan : "+temp);
        }
        imgJabatanUser = (ImageView) rootView.findViewById(R.id.imgJabatanUser);
        imgJabatanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Input your position here : ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_POSITION.toString()));

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_POSITION.toString(), editText.getText().toString());
                        jabatanUser.setText("Position : " + propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_POSITION.toString()));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();

            }
        });

        emailUser = (TextView) rootView.findViewById(R.id.txEmailUser);
        Typeface fontFaceEmail = Typeface.createFromAsset(emailUser.getResources().getAssets(),"font/Roboto-Medium.ttf");
        emailUser.setTypeface(fontFaceEmail);
        temp=propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_EMAIL.toString());
        if(temp!=null){
            emailUser.setText("Email : "+temp);
        }
        imgEmailUser = (ImageView) rootView.findViewById(R.id.imgEmailUser);
        imgEmailUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Input your email here : ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_EMAIL.toString()));

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_EMAIL.toString(), editText.getText().toString());
                        emailUser.setText("Email : " + propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_EMAIL.toString()));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        summmaryUser = (TextView) rootView.findViewById(R.id.txSummmaryUser);
        Typeface fontFaceSummary = Typeface.createFromAsset(summmaryUser.getResources().getAssets(),"font/Roboto-Medium.ttf");
        summmaryUser.setTypeface(fontFaceSummary);
        temp=propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_SUMMARY.toString());
        if(temp!=null){
            summmaryUser.setText("Summary :\n"+temp);
        }
        imgSummmaryUses = (ImageView) rootView.findViewById(R.id.imgSummaryUser);
        imgSummmaryUses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Input your summary here : ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_SUMMARY.toString()));

                alertDialogBuilder.setCancelable(true).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_SUMMARY.toString(), editText.getText().toString());
                        summmaryUser.setText("Summary :\n"+propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_SUMMARY.toString()));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();

            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                cameraUtil.actionTakeFromGallery(data, getActivity());
                if(cameraUtil.getTakenImagePath()!=null) propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_PHOTO.toString(), cameraUtil.getTakenImagePath() + "");
                if(cameraUtil.getTakenImage()!=null) imgUserProfile.setImageBitmap(cameraUtil.getTakenImage());
            } else if (requestCode == 2) {
                cameraUtil.actionTakeFromCamera();
                propertyUtil.saveSingleProperty(PropertyConstant.WAITRESS_PHOTO.toString(), cameraUtil.getTakenImagePath());
                imgUserProfile.setImageBitmap(cameraUtil.getTakenImage());
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromPath(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }


}
