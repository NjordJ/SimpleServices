package com.iruda.servicesexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.*

class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    // onStartJob method type means whether the service is still running
    // if method synchronous should return false
    // if method contains asynchronous should return true and stop service manually
    override fun onStartJob(params: JobParameters?): Boolean {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("Timer $i")
            }
            // Second param means should it be restarted or not
            jobFinished(params, true)
        }
        return true
    }

    // Called only if the service was not manually destroyed
    override fun onStopJob(p0: JobParameters?): Boolean {
        log("onStopJob")
        // Whether to be restarted if the system killed the service
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "JobService: $message")
    }

    companion object {

        const val JOB_ID = 11
    }
}