package com.example.kotlindemo.base

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * 10:11 AM 06-Jan-23
 * Long Tran
 */
open class BaseLogger {
     val logger: Logger? = LogManager.getLogger(this.javaClass)
}