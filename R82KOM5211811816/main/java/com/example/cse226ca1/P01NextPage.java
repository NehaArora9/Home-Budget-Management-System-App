package com.example.cse226ca1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class P01NextPage extends AppCompatActivity {
    TextView tv;
    ImageView image;
    private static final int pic = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p01_next_page);
        tv = (TextView) findViewById(R.id.tv);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        tv.setText(name);
        Toast.makeText(this, "Name Added", Toast.LENGTH_SHORT).show();

        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,pic);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == pic) {
            Bitmap img = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(img);
            Toast.makeText(this, "image set", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

