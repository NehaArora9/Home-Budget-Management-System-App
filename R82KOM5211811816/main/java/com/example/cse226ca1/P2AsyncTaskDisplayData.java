package com.example.cse226ca1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class P2AsyncTaskDisplayData extends AppCompatActivity {
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    ProgressDialog p;
    TextView tv,tv1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2_async_task_display_data);
        Button button = findViewById(R.id.btnid);
        tv = findViewById(R.id.tvid);
        tv1 = findViewById(R.id.tv1id);
        imageView = findViewById(R.id.ivid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P2AsyncTask asyncTask = new P2AsyncTask();
                asyncTask.execute("https://image.shutterstock.com/z/stock-vector-editable-colorful-text-of-happy-navratri-vector-with-holy-footprint-of-goddess-durga-laxmi-for-1028086372.jpg");
            }
        });

    }

    private class P2AsyncTask extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(P2AsyncTaskDisplayData.this);
            p.setMessage("Please Wait... It is downloading");
            p.setCancelable(false);
            p.show();
        }

        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (imageView != null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
                tv.setText("Title: Navratri 2017: 3 ways to wear purple on Navami");
                tv1.setText("Category: Life & Style");
            } else {
                p.show();
            }

        }
    }


}