package com.iruda.servicesexample

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE_KEY, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyWorker: $message")
    }

    companion object {

        private const val PAGE_KEY = "page_key"

        const val WORK_NAME = "work_name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE_KEY to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints() = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
    }
}
