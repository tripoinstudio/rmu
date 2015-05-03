package com.tripoin.rmu.view.fragment.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
import com.tripoin.rmu.util.impl.PropertyUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Ginanjar Aji Sanjaya on 4/29/2015.
 */

public class FragmentUserProfile extends Fragment {

    private TextView nameUser, jabatanUser, emailUser, summmaryUser, lblEditText, lblViewPhoto, lblChangePhotoGallery, lblChangePhotoCamera;
    private ImageView imgUserProfile, imgNameUser, imgJabatanUser, imgEmailUser, imgSummmaryUses;
    private EditText editText;
    private PropertyUtil propertyUtil;

    public FragmentUserProfile newInstance(String text) {
        FragmentUserProfile mFragment = new FragmentUserProfile();
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        imgUserProfile = (ImageView) rootView.findViewById(R.id.imgUserProfile);
        imgUserProfile.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
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
                        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                    }
                });

                lblChangePhotoCamera = (TextView) dialogView.findViewById(R.id.lbl_change_photo_camera);
                lblChangePhotoCamera.setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        startActivityForResult(intent, 2);
                    }
                });

                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        nameUser = (TextView) rootView.findViewById(R.id.txNameUser);
        Typeface fontFaceName = Typeface.createFromAsset(nameUser.getResources().getAssets(),"font/Roboto-Medium.ttf");
        nameUser.setTypeface(fontFaceName);
        imgNameUser = (ImageView) rootView.findViewById(R.id.imgNameUser);
        imgNameUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Masukan Nama: ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(nameUser.getText().toString());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty("NAMA_USER", editText.getText().toString());
                        nameUser.setText(propertyUtil.getValuePropertyMap("NAMA_USER"));
                        Toast.makeText(rootView.getContext(), "Nama profil change to : " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
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
        imgJabatanUser = (ImageView) rootView.findViewById(R.id.imgJabatanUser);
        imgJabatanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Masukan Jabatan baru: ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(jabatanUser.getText().toString());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty("JABATAN_USER", editText.getText().toString());
                        jabatanUser.setText(propertyUtil.getValuePropertyMap("JABATAN_USER"));
                        Toast.makeText(rootView.getContext(), "Profil Jabatan change to : " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
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
        imgEmailUser = (ImageView) rootView.findViewById(R.id.imgEmailUser);
        imgEmailUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Masukkan email baru: ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(emailUser.getText().toString());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty("EMAIL_USER", editText.getText().toString());
                        emailUser.setText(propertyUtil.getValuePropertyMap("EMAIL_USER"));
                        Toast.makeText(rootView.getContext(),"Email change to : " +editText.getText().toString(), Toast.LENGTH_SHORT).show();
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
        imgSummmaryUses = (ImageView) rootView.findViewById(R.id.imgSummaryUser);
        imgSummmaryUses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_dialog_edit_text, null);

                lblEditText = (TextView) dialogView.findViewById(R.id.lbl_edit_text);
                lblEditText.setText("Masukkan data diri : ");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editText = (EditText) dialogView.findViewById(R.id.tf_edit_text);
                editText.setText(summmaryUser.getText().toString());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty("SUMMARY_USER", editText.getText().toString());
                        summmaryUser.setText(propertyUtil.getValuePropertyMap("SUMMARY_USER"));
                        Toast.makeText(rootView.getContext(), "Summary changed : " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
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

        propertyUtil = new PropertyUtil("UserProfileData.txt" , rootView.getContext());

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image:", picturePath + "");
                imgUserProfile.setImageBitmap(thumbnail);
            } else if (requestCode == 2) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    System.gc();
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmapOptions.inJustDecodeBounds = false;
                    bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmapOptions.inDither = true;
                    System.gc();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    imgUserProfile.setImageBitmap(bitmap);
                    String path = android.os.Environment.getExternalStorageDirectory()+File.separator+"Phoenix"+File.separator+"default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }


}
