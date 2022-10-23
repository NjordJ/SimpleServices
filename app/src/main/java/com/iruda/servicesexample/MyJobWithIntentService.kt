package com.iruda.servicesexample

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

class MyJobWithIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        // if true -> same as START_REDELIVER_INTENT
        // if false -> same as START_NOT_STICKY
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE_KEY, 0) ?: 0
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "JobWithIntentService: $message")
    }


    companion object {

        private const val NAME = "my_intent_service"
        private const val PAGE_KEY = "page_key"

        private const val EXTRA_START = "start"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobWithIntentService::class.java).apply {
                putExtra(PAGE_KEY, page)
            }
        }
    }
}