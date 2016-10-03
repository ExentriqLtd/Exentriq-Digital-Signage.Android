package com.example.exentriqdigitalsignage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
public class MainActivity extends Activity {
	
	private WebView mWebView;

	//private String url = "http://managerv3.3rdi-technology.com/digitalSignage";
	private String url = "http://exentriq.com/digitalSignage";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
		{
        super.onCreate(savedInstanceState);
        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath(); 
 	   
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        View decorView = getWindow().getDecorView();
	    // Hide both the navigation bar and the status bar.
	    // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
	    // a general rule, you should design your app to hide the status bar whenever you
	    // hide the navigation bar.
	    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	                   | View.SYSTEM_UI_FLAG_FULLSCREEN;
	    decorView.setSystemUiVisibility(uiOptions);
        
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        mWebView = (WebView) findViewById(R.id.webview);

		mWebView.getSettings().setLoadsImagesAutomatically(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setAllowFileAccess(true);
		
		mWebView.getSettings().setDatabasePath(databasePath);
		mWebView.getSettings().setPluginState(PluginState.ON);
		
		
		mWebView.setBackgroundColor(Color.BLACK);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		 
		//mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36");
	    //webView.addJavascriptInterface(this, "nativeInterface");

	    if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) 
	    	{
	    	mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
	    	}

	    if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1 ) 
			{
	    	System.out.println("setMediaPlaybackRequiresUserGesture");
			mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
			}

	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.setWebChromeClient(new WebChromeClient());
		
		mWebView.loadUrl(url+"?rand="+System.currentTimeMillis());
		
		}

    
}

