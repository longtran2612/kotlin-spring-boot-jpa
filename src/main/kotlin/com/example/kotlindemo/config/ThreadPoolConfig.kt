package com.example.springoracledemo.config

import com.example.kotlindemo.base.BaseLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Configuration
open class ThreadPoolConfig : BaseLogger() {
    @Bean(name = ["executorService"])
    open fun createExecutorService(): ExecutorService? {
        val executorService: ExecutorService = ThreadPoolExecutor(10, 3000,
                60L, TimeUnit.SECONDS,
                SynchronousQueue())
        Runtime.getRuntime().addShutdownHook(Thread {
            try {
                executorService.shutdown()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        return executorService
    }
}
