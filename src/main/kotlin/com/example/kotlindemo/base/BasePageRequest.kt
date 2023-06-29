package com.example.kotlindemo.base

import lombok.*
import lombok.experimental.SuperBuilder

/**
 * 9:24 AM 15-Oct-22
 * Long Tran
 */

open class BasePageRequest {
    var pageNumber: Int = 0
    var pageSize: Int = 10
}