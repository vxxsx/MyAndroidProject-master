package com.example.myandroidproject.myandroidproject;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.content.Context;

import com.example.wang.myandroidproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String array[] = new String[]{"校园任务", "二手商品"};
    private Button btfirst, btn_body;

    private Context mContext=this;
    @Override
    public void onBackPressed() {
        Intent MyIntent = new Intent(Intent.ACTION_MAIN);
        MyIntent.addCategory(Intent.CATEGORY_HOME);
        startActivity(MyIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("send_info","7");
        editor.apply();
        replaceFragment(new index());
        btfirst = findViewById(R.id.personal);
        btn_body = findViewById(R.id.btn_index);
        btfirst.setOnClickListener(this);
        btn_body.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_index:
                replaceFragment(new index());
                break;

            case R.id.personal:
                replaceFragment(new User());
                break;

            case R.id.btn_add:
                AlertDialog.Builder ad =new AlertDialog.Builder(MainActivity.this).setTitle("请选择")
                        .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ListView lw = ((AlertDialog) dialogInterface).getListView();
                                Object checkedItem = lw.getAdapter().getItem(i);
                                dialogInterface.dismiss();
                                String xz=(String) checkedItem;
                                if (xz.equals("校园任务")){
                                    Intent intent = new Intent(mContext, Publish_task.class);
                                    startActivity(intent);}
                                else{
                                    Intent intent = new Intent(mContext, Publish_product.class);
                                    startActivity(intent);
                                }
                            }

                        });
                AlertDialog dialog = ad.create();
                dialog.show();
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout,fragment);
        transaction.commit();
    }
}

