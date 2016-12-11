package com.example.administrator.camerasystemdemo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String imgpath;
    private ImageView imgview;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"打开相机");
        menu.add(0,2,0,"打开相机，显示结果");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent in=new Intent();
                in.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(in);
                break;
            case 2:
                takephoto();break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void takephoto(){
        imgpath=android.os.Environment.getExternalStorageDirectory().getPath();
        imgview=(ImageView) this.findViewById(R.id.imagev);
        SimpleDateFormat sfor=new SimpleDateFormat("yyyyMMddhhmmss");
        Date curdate=new Date(System.currentTimeMillis());
        String str=sfor.format(curdate);
        imgpath=imgpath+"/"+str+"jpeg";
        file=new File(imgpath);
        Uri u=Uri.fromFile(file);
        Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        in.putExtra(MediaStore.EXTRA_OUTPUT,u);
        startActivityForResult(in,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(file.exists()){
            imgview.setImageURI(Uri.fromFile(file));
        }
    }
}
