package com.example.kotlindemo.base

import lombok.*
import lombok.experimental.SuperBuilder

/**
 * 12:32 PM 26-Mar-23
 * Long Tran
 */

open class BaseSearchRequest : BasePageAndSortRequest() {
     var keyword: String? = null
     var isPageAble: Boolean? = true
}
