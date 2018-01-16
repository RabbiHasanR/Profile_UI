package com.example.rabbihasan.profile_ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Fragment fragment;
    private static Button button;
    private static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call onClickListner method
       /* profileButtonOnClickListner();
        editButtonOnClickListner();*/

        //call setToolbar method
        setToolbar();
    }

    //fragment change code
   /* public void changeFragment(View view){

        if(view==findViewById(R.id.profile_button)){
            fragment=new Profile();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }
        if(view==findViewById(R.id.edit_button)){
            fragment=new Edit();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }
    }*/

    //profile button onClickListner
    /*public void profileButtonOnClickListner(){
        button=(Button)findViewById(R.id.profile_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeFragment(v);
                    }
                }
        );
    }

    //Edit button onClickListner
    public void editButtonOnClickListner(){
        button=(Button)findViewById(R.id.edit_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeFragment(v);
                    }
                }
        );
    }*/

    //set Toolbar method
    public  void setToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        toolbar.setSubtitle("Welcome");
    }

    //Toolbar code

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    //Toolbar work as a actionbar...functionality of toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg="";
        switch (item.getItemId()){
            case R.id.profile:
                fragment=new Profile();
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment_profile,fragment);
                ft.commit();
                break;
            case R.id.edit:
                fragment=new Edit();
                FragmentManager fm1=getSupportFragmentManager();
                FragmentTransaction ft1=fm1.beginTransaction();
                ft1.replace(R.id.fragment_profile,fragment);
                ft1.commit();
                break;
            case R.id.setting:
                msg="You click Setting..under construction";
                break;
            case R.id.logout:
                msg="You click Logout..under construction";
                break;
            case R.id.exit:
                alertDialog();
                break;
        }
        Toast.makeText(this, msg+"Checked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
    //create alertDialog
  public void alertDialog(){
      AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
      alertDialogBuilder.setMessage("Do u want to close the app!!");
      alertDialogBuilder.setCancelable(false);
      alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              finish();

          }
      });
      alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
          }
      });
      AlertDialog alertDialog=alertDialogBuilder.create();
      alertDialog.show();

  }
}
