package com.example.kotlindemo.utils

import com.example.kotlindemo.base.BasePageRequest
import com.example.kotlindemo.base.BaseSort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * 9:24 AM 15-Oct-22
 * Long Tran
 */
object PageableUtils {
    fun convertPageable(pageRequest: BasePageRequest, sorts: Array<String>?): Pageable {
        var sort: Sort? = null
        val pageable: Pageable
        if (sorts != null) {
            for (item in sorts) {
                val elephantList = listOf(*item.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                val value = elephantList[0]
                val asc = if (elephantList.size == 1) null else elephantList[1]
                if (asc != null) {
                    sort = if (asc == "DESC") {
                        sort?.and(Sort.by(value).descending()) ?: Sort.by(value).descending()
                    } else {
                        sort?.and(Sort.by(value)) ?: Sort.by(value)
                    }
                } else if (value != "DESC" && value != "ASC") {
                    sort = sort?.and(Sort.by(value)) ?: Sort.by(value)
                }
            }
            pageable = pageRequest.pageNumber.let { pageRequest.pageSize.let { it1 ->
                if (sort != null) {
                    PageRequest.of(it, it1, sort)
                }
                else {
                    PageRequest.of(it, it1)
                }
            } }
        } else {
            pageable = pageRequest.pageNumber.let { pageRequest.pageSize.let { it1 -> PageRequest.of(it, it1) } }!!
        }
        return pageable
    }

    fun convertPageable(pageNumber: Int?, pageSize: Int?): Pageable {
        val pageable: Pageable
        pageable = PageRequest.of(pageNumber!!, pageSize!!)
        return pageable
    }

    fun convertPageableAndSort(pageNumber: Int?, pageSize: Int?, sorts: MutableList<BaseSort?>?): Pageable {
        var sort: Sort? = null
        val pageable: Pageable
        if (!sorts.isNullOrEmpty()) {
            for (item in sorts) {
                if (item != null) {
                    sort = if (!item.asc) {
                        sort?.and(Sort.by(item.key).descending()) ?: Sort.by(item.key).descending()
                    } else {
                        sort?.and(Sort.by(item.key)) ?: Sort.by(item.key)
                    }
                }
            }
            pageable = sort?.let { PageRequest.of(pageNumber!!, pageSize!!, it) }!!
        } else {
            pageable = PageRequest.of(pageNumber!!, pageSize!!)
        }
        return pageable
    }
}