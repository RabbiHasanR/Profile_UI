package com.example.rabbihasan.profile_ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Profile extends Fragment {

    private static TextView userName,userEmail,userPhone,userPassword,userAge,userGender,userRelationship;
    private static ImageView image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        userName=(TextView)view.findViewById(R.id.username);
        userEmail=(TextView)view.findViewById(R.id.user_email);
        userPhone=(TextView)view.findViewById(R.id.user_phone);
        userAge=(TextView)view.findViewById(R.id.user_age);
        userGender=(TextView)view.findViewById(R.id.user_gender);
        userRelationship=(TextView)view.findViewById(R.id.user_relationship);
        image = (ImageView)view. findViewById(R.id.profile_image);

        //call getValue method
        getValue(view);
        // Inflate the layout for this fragment
        return view;
    }

    //get value from another fragment
    public void getValue(View view){
        //retrieving data using bundle
        Bundle bundle=getArguments();
        if(bundle!=null){
            userName.setText(String.valueOf(bundle.getString("userName")));
            userEmail.setText(String.valueOf(bundle.getString("userEmail")));
            userPhone.setText(String.valueOf(bundle.getString("userPhone")));
            userAge.setText(String.valueOf(bundle.getString("userAge")));
            userGender.setText(String.valueOf(bundle.getString("userGender")));
            userRelationship.setText(String.valueOf(bundle.getString("userRelationship")));
            //get byteArray form edit profile
            byte[] byteArray = bundle.getByteArray("image");
            //convert byteArray to bitmap
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            //set bitmap
            image.setImageBitmap(bmp);

        }
    }
}
