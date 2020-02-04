package com.example.aaveg2020.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.aaveg2020.R;

public class HopeThisWorks extends AppCompatActivity {

    VideoView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hope_this_works);

        WebView myWebView = (WebView) findViewById(R.id.mWebView);
        myWebView.loadUrl("https://drive.google.com/open?id=11ijWxyYDZ7u-EajHsHhtQ7UrowlWqzsM");

//        WebSettings webSettings = myWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadData(frameVideo, "text/html", "utf-8");
//        v=findViewById(R.id.videoView);
//        String videoPath="https://drive.google.com/open?id=11ijWxyYDZ7u-EajHsHhtQ7UrowlWqzsM";
////        videoPath = videoPath.replace("open?", "uc?authuser=0&");
//        videoPath = videoPath + "&export=download";

//# try to play
//        Uri uri = Uri.parse(videoPath);
//        v.setVideoURI(uri);
//        MediaController mediaController = new MediaController(this);
//        v.setMediaController(mediaController);
//        mediaController.setAnchorView(v);
//        v.setVideoPath("https://drive.google.com/open?id=11ijWxyYDZ7u-EajHsHhtQ7UrowlWqzsM");
        //v.start();
    }
}
