 package com.example.gamelink.activity

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.gamelink.R
import kotlinx.android.synthetic.main.webview_activity.*

class WebViewActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)

        webview.webViewClient = WebViewClient()
        webview.loadUrl("https://discord.gg/rdtzm7RQ6h")
    }
}