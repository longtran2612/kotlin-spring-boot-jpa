package com.example.kotlindemo.base

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.experimental.SuperBuilder

/**
 * 9:24 AM 15-Oct-22
 * Long Tran
 */

open class BasePageAndSortRequest : BasePageRequest() {
     var  sort: MutableList<BaseSort?>? = null
}