package com.example.kotlindemo.domain.dto.request

import com.example.kotlindemo.base.BaseSearchRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.*



@JsonIgnoreProperties(ignoreUnknown = true)
class SearchUserRequest : BaseSearchRequest() {
    var ids: List<Long>? = ArrayList();
    var name: String? = null
    var email: String? = null
    var age: Int? = null
    var phone: String? = null
}