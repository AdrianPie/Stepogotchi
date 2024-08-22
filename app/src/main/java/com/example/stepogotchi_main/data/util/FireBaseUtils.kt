package com.example.stepogotchi_main.data.util

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resumeWithException


suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener{
            if (it.exception != null) {
                cont.resumeWithException(it.exception!!)
            } else {
               cont.resume(it.result, null)
            }
        }

    }
}