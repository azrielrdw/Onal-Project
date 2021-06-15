package onal.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserActivity extends AppCompatActivity {
    ImageView imageViewWallpaper;
    ImageView imageViewFeedback;
    ImageView imageViewDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        imageViewWallpaper = (ImageView)findViewById(R.id.image_wallpaper);
        imageViewFeedback = (ImageView)findViewById(R.id.image_feedback);
        imageViewDownload = (ImageView)findViewById(R.id.image_download);

        imageViewWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadView();
            }
        });

        imageViewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackView();
            }
        });

        imageViewDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadView();
            }
        });
    }

    public void uploadView(){
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    public void feedbackView(){
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void downloadView(){
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }
}