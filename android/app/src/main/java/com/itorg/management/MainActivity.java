package com.itorg.management;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    private WebView webView;
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "ITOrgSettings";
    private static final String KEY_SERVER_URL = "server_url";
    private static final String KEY_FIRST_RUN = "first_run";
    private String currentServerUrl = "";
    private ProgressBar progressBar;
    private boolean isLoading = false;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        currentServerUrl = prefs.getString(KEY_SERVER_URL, "");

        if (prefs.getBoolean(KEY_FIRST_RUN, true)) {
            prefs.edit().putBoolean(KEY_FIRST_RUN, false).apply();
            showWelcomeDialog();
        } else {
            checkNetworkAndLoad();
        }
    }

    private void showWelcomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IT Tech Organisation");
        builder.setMessage("Welcome!\n\nThis app connects to your organization's server.\n\nIf server is available, you'll see the full app.\nIf unavailable, you'll see offline information.");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkNetworkAndLoad();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void checkNetworkAndLoad() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                if (capabilities != null) {
                    boolean hasInternet = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                    boolean hasValidTransport = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || 
                                         capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                         capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                    
                    if (hasInternet && hasValidTransport) {
                        findAndConnectToServer();
                        return;
                    }
                }
            }
        }
        loadBundledPage();
    }

    private void findAndConnectToServer() {
        showLoading("Connecting to server...");
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                String serverUrl = findServerUrl();
                
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                        if (serverUrl != null && !serverUrl.isEmpty()) {
                            currentServerUrl = serverUrl;
                            prefs.edit().putString(KEY_SERVER_URL, serverUrl).apply();
                            loadWebApp(serverUrl);
                        } else {
                            showConnectionFailedDialog();
                        }
                    }
                });
            }
        }).start();
    }

    private String findServerUrl() {
        String localIp = getLocalIpAddress();
        if (localIp != null && !localIp.isEmpty()) {
            String baseUrl = "http://" + localIp + ":8000/";
            if (checkServerUrl(baseUrl)) {
                return baseUrl;
            }
            String altUrl = "http://" + localIp + ":8000";
            if (checkServerUrl(altUrl)) {
                return altUrl;
            }
        }
        
        if (checkServerUrl("http://192.168.1.48:8000/")) {
            return "http://192.168.1.48:8000/";
        }
        if (checkServerUrl("http://192.168.1.9:8000/")) {
            return "http://192.168.1.9:8000/";
        }
        if (checkServerUrl("http://10.0.2.2:8000/")) {
            return "http://10.0.2.2:8000/";
        }
        if (checkServerUrl("http://localhost:8000/")) {
            return "http://localhost:8000/";
        }
        
        return null;
    }

    private boolean checkServerUrl(String urlString) {
        try {
            java.net.URL url = new java.net.URL(urlString);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode == 200 || responseCode == 302;
        } catch (Exception e) {
            return false;
        }
    }

    private String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0;
                        if (isIPv4) {
                            return sAddr;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showConnectionFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Server Not Found");
        builder.setMessage("Could not connect to organization server.\n\n" +
                "Make sure:\n" +
                "1. Your device is on the same network as the server\n" +
                "2. The server computer is turned on\n" +
                "3. The Python server is running\n\n" +
                "Tap 'Retry' to try again or 'Offline Mode' to view offline information.");
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 0);

        Button retryBtn = new Button(this);
        retryBtn.setText("🔄 Retry Connection");
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkAndLoad();
            }
        });
        layout.addView(retryBtn);

        Button offlineBtn = new Button(this);
        offlineBtn.setText("📄 View Offline Info");
        offlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBundledPage();
            }
        });
        layout.addView(offlineBtn);

        builder.setView(layout);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebApp(String url) {
        isLoading = false;
        webView = new WebView(this);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, "Connection lost. Loading offline content...", Toast.LENGTH_LONG).show();
                loadBundledPage();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }
        });

        Toast.makeText(this, "Connecting to: " + url, Toast.LENGTH_SHORT).show();
        webView.loadUrl(url);
    }

    public class WebAppInterface {
        @JavascriptInterface
        public void downloadApk() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    downloadAndroidApk();
                }
            });
        }
        
        @JavascriptInterface
        public void connectToServer() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    checkNetworkAndLoad();
                }
            });
        }
        
        @JavascriptInterface
        public void showOffline() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadBundledPage();
                }
            });
        }
    }

    private void downloadAndroidApk() {
        if (currentServerUrl != null && !currentServerUrl.isEmpty()) {
            String apkUrl = currentServerUrl;
            if (!apkUrl.endsWith("/")) {
                apkUrl += "/";
            }
            apkUrl += "media/apk/IT_Tech_Org_Release.apk";
            webView.loadUrl(apkUrl);
        } else {
            showConnectionRequiredDialog();
        }
    }
    
    private void showConnectionRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connection Required");
        builder.setMessage("To download APK, please connect to the server first.\n\nTap 'Connect to Server' to find and connect to the organization server.");
        builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkNetworkAndLoad();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadBundledPage() {
        webView = new WebView(this);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.loadUrl("file:///android_asset/www/index.html");
    }

    private void showLoading(String message) {
        if (isLoading) return;
        isLoading = true;
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IT Tech Organisation");
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 0);
        layout.setGravity(android.view.Gravity.CENTER);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        layout.addView(progressBar);

        Button cancelBtn = new Button(this);
        cancelBtn.setText("Cancel");
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBundledPage();
            }
        });
        layout.addView(cancelBtn);

        builder.setView(layout);
        builder.setCancelable(false);
        builder.show();
    }

    private void hideLoading() {
        isLoading = false;
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit App");
            builder.setMessage("Are you sure you want to exit?");
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
    }
}