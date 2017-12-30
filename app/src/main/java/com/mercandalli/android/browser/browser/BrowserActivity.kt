package com.mercandalli.android.browser.browser

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import com.mercandalli.android.browser.R
import com.mercandalli.android.browser.main.MainApplication
import com.mercandalli.android.browser.theme.Theme
import com.mercandalli.android.browser.theme.ThemeManager

class BrowserActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, BrowserActivity::class.java)
            if (context !is Activity) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }
    }

    private var webView: BrowserWebView? = null
    private var progress: ProgressBar? = null
    private val themeManager: ThemeManager = MainApplication.getAppComponent().provideThemeManager()
    private val themeListener = createThemeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.activity_main_web_view)
        webView!!.loadUrl("http://www.google.com/")

        progress = findViewById(R.id.activity_main_progress)

        findViewById<View>(R.id.activity_main_home).setOnClickListener {
            webView!!.loadUrl("http://www.google.com/")
        }

        webView!!.browserWebViewListener = object : BrowserWebView.BrowserWebViewListener {
            override fun onProgressChanged() {
                val progressPercent = webView!!.progress
                if (progressPercent >= 100) {
                    progress!!.visibility = GONE
                    return
                }
                progress!!.visibility = VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progress!!.setProgress(progressPercent, true)
                } else {
                    progress!!.progress = progressPercent
                }
            }
        }

        themeManager.registerThemeListener(themeListener)
        updateTheme()
    }

    override fun onDestroy() {
        webView!!.browserWebViewListener = null
        themeManager.unregisterThemeListener(themeListener)
        super.onDestroy()
    }

    private fun createThemeListener(): ThemeManager.ThemeListener {
        return object : ThemeManager.ThemeListener {
            override fun onThemeChanged() {
                updateTheme()
            }
        }
    }

    private fun updateTheme() {
        updateTheme(themeManager.theme)
    }

    private fun updateTheme(theme: Theme) {
        window.setBackgroundDrawable(
                ColorDrawable(
                        ContextCompat.getColor(
                                this,
                                theme.windowBackgroundColorRes)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(
                    this,
                    theme.statusBarBackgroundColorRes)
        }
    }
}
