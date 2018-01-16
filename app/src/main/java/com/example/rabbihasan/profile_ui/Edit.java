package com.example.rabbihasan.profile_ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.rabbihasan.profile_ui.R.id;



public class Edit extends Fragment {
    private static Spinner spinner;
    private  static Spinner userAge_sp,userGender_sp,userRelationship_sp;
    private static ArrayAdapter<CharSequence> adapter;
    private static ArrayAdapter<Integer> integerArrayAdapter;
    private static ImageView captreImage;
    private static FloatingActionButton fab;
    private static Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    private static Intent intent;
    private static EditText userName_et,userEmail_et,userPhone_et;//userPassword_et;
    private static String userName,userEmail,userPhone,userPassword; //userAge,userGender,userRelationship
    private static Button signUpButton;
    private static FragmentTransaction transection;
    private static Bundle bundle;
    private static FragmentManager fm;
    private static Fragment fragment;
    private static String gender,age,relationShip,imageUri;
    private byte[] byteArray ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_edit, container, false);
        //call gender spinner method
        userName_et=(EditText)view.findViewById(id.input_username);
        userEmail_et=(EditText)view.findViewById(id.input_user_email);
        userPhone_et=(EditText)view.findViewById(id.input_user_phone);
        userAge_sp=(Spinner)view.findViewById(id.input_user_age);
        userGender_sp=(Spinner)view.findViewById(id.input_user_gender);
        userRelationship_sp=(Spinner)view.findViewById(id.input_user_relationship);
        //userPassword_et=(EditText)view.findViewById(id.input_user_password);

        captreImage=(ImageView)view.findViewById(id.capture_image);
        genderSelected(view);
        relationshipSelected(view);
        ageSelected(view);

        //call cameraOnClickListner()
        cameraOnClickListner(view);

        //call singUpButtonOnClickListner
        signUpOnClickListner(view);
        // Inflate the layout for this fragment
        return view;
    }


    //gender spinner method
    public void genderSelected(View view){
        //spinner=(Spinner)view.findViewById(id.input_user_gender);

        //create an ArrayAdapter using the string array and a default spinner layout
        adapter=ArrayAdapter.createFromResource( this
                .getActivity(), R.array.gender_array,android.R.layout.simple_spinner_item);
        //spcify the layout to use when the list of choice appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adpter to the spinner
        userGender_sp.setAdapter(adapter);

        //spinner listner

        userGender_sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       gender=   userGender_sp.getItemAtPosition(position).toString();
                        gender = (String) parent.getItemAtPosition(position);
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );


    }

    //age spinner method
    public void ageSelected(View view){
        //age list
        List ageList= new ArrayList<Integer>();
        for(int i=1;i<=100;i++){
            ageList.add(Integer.toString(i));
        }
        //spinner=(Spinner) view.findViewById(id.input_user_age);

        //create an ArrayAdapter using the string array and a default spinner layout
        integerArrayAdapter=new ArrayAdapter<Integer> (this.getActivity(),android.R.layout.simple_spinner_item,ageList);
        //spcify the layout to use when the list of choice appears
        integerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adpter to the spinner
        userAge_sp.setAdapter(integerArrayAdapter);

        //spinner listner

       userAge_sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        age=userAge_sp.getItemAtPosition(position).toString();
                        age = (String) parent.getItemAtPosition(position);
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

    }


    //relationship spinner method
    public void relationshipSelected(View view){
        //spinner=(Spinner) view.findViewById(id.input_user_relationship);

        //create an ArrayAdapter using the string array and a default spinner layout
        adapter=ArrayAdapter.createFromResource( this
                .getActivity(), R.array.relation_array,android.R.layout.simple_spinner_item);
        //spcify the layout to use when the list of choice appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adpter to the spinner
        userRelationship_sp.setAdapter(adapter);

        //spinner listner

        userRelationship_sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        relationShip=userRelationship_sp.getItemAtPosition(position).toString();
                        relationShip = (String) parent.getItemAtPosition(position);
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

    }

    //camera onClickListner method

    public void cameraOnClickListner(View view){
        captreImage=(ImageView)view.findViewById(id.capture_image);
        fab=(FloatingActionButton)view.findViewById(id.camera_button);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //call selectImage()
                        selectImage();

                    }
                }
        );
    }

    //selectImage method
    public void selectImage(){
        final CharSequence[] item={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Add Image");
        builder.setItems(item,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(item[which].equals("Camera")){
                            intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,REQUEST_CAMERA);
                            //startActivity(intent);

                        }else if (item[which].equals("Gallery")){
                            intent=new Intent(intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent,SELECT_FILE);
                            //startActivity(intent);

                        }else if (item[which].equals("Cancel")){
                            dialog.dismiss();
                        }
                    }
                });
        //show alertDialog box
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
            if(requestCode== REQUEST_CAMERA){
                Bundle bundle=data.getExtras();
                 Bitmap bmp=(Bitmap)bundle.get("data");
                captreImage.setImageBitmap(bmp);
                //create object ByteArrayOutputStream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //convert bitmap to byteArray
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

            }else if (requestCode==SELECT_FILE){
                Uri selectImageData=data.getData();
                captreImage.setImageURI(selectImageData);

            }
        }
    }

    //get value
    public void getValue(View view){



        userName=userName_et.getText().toString();
        userEmail=userEmail_et.getText().toString();
        userPhone=userPhone_et.getText().toString();
        //userPassword=userPassword_et.getText().toString();

        //fragment transaction

        //using bundel to send data
         bundle=new Bundle();
        bundle.putString("userName",userName);
        bundle.putString("userEmail",userEmail);
        bundle.putString("userPhone",userPhone);
        bundle.putString("userPassword",userPassword);
        bundle.putString("userGender",gender);
        bundle.putString("userAge",age);
        bundle.putString("userRelationship",relationShip);
        //put byte array
        bundle.putByteArray("image",byteArray);
        fm=getActivity().getSupportFragmentManager();
        transection=fm.beginTransaction();
        fragment=new Profile();
        fragment.setArguments(bundle);
       transection.replace(id.fragment_profile,fragment);
       transection.commit();
    }

    //pass value using sign up button
    public void signUpOnClickListner(View view){
        signUpButton=(Button)view.findViewById(id.sign_up);
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getValue(v);
                    }
                }
        );
    }
}
