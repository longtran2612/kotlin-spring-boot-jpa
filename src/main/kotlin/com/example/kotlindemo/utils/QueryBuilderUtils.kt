package com.example.kotlindemo.utils

import jakarta.persistence.criteria.*
import java.util.*
import org.apache.commons.lang3.StringUtils

/**
 * 9:24 AM 15-Oct-22
 * Long Tran
 */
object QueryBuilderUtils {
    fun  <T> addSingleValueFilter(predicates: MutableCollection<Predicate>,
                             criteriaBuilder: CriteriaBuilder,
                             root: Root<T>,
                             fieldName: String,
                             value: Any?) {
        if (Objects.nonNull(value)) {
            val predicate = criteriaBuilder.equal(root.get<Any?>(fieldName), value)
            predicates.add(predicate)
        }
    }

    fun <T> addSingleRegex(predicates: MutableCollection<Predicate>,
                           criteriaBuilder: CriteriaBuilder,
                           root: Root<T>,
                           fieldName: String,
                           value: String?) {
        if (StringUtils.isNotBlank(value)) {
            val predicate = criteriaBuilder.like(root.get(fieldName), "%$value%")
            predicates.add(predicate)
        }
    }

    fun <T> addMultipleRegexSearch(
        predicates: MutableCollection<Predicate>,
        criteriaBuilder: CriteriaBuilder,
        root: Root<T>,
        value: String?,
        vararg fieldNames: String?
    ) {
        if (Objects.nonNull(value) && Objects.nonNull(fieldNames)) {
            for (fieldName in fieldNames) {
                val predicate = criteriaBuilder.like(root.get(fieldName), "%$value%", '\\')
                predicates.add(predicate)
            }
        }
    }

    fun <T> addDateFilter(predicates: MutableCollection<Predicate>,
                          criteriaBuilder: CriteriaBuilder,
                          root: Root<T>,
                          fieldName: String,
                          startDate: Date?,
                          endDate: Date?) {
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            val predicate = criteriaBuilder.between(root.get(fieldName), startDate, endDate)
            predicates.add(predicate)
        } else if (Objects.nonNull(startDate)) {
            val predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), startDate)
            predicates.add(predicate)
        }
        if (Objects.nonNull(endDate)) {
            val predicate = criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), endDate)
            predicates.add(predicate)
        }
    } //    public static void addDateFilter(Collection<Predicate> predicates, CriteriaBuilder criteriaBuilder, String fieldName, Date startDate, Date endDate) {
    //        if (startDate != null && endDate != null) {
    //            Predicate predicate = criteriaBuilder.between(criteriaBuilder.literal(fieldName),startDate,endDate);
    //            predicates.add(predicate);
    //        } else if (startDate != null) {
    //            Predicate predicate = criteriaBuilder.greaterThan(criteriaBuilder.literal(fieldName),startDate);
    //            predicates.add(predicate);
    //        } else if (endDate != null) {
    //            Predicate predicate = criteriaBuilder.lessThan(criteriaBuilder.literal(fieldName),endDate);
    //            predicates.add(predicate);
    //        }
    //    }
    //    public static void addDateFilter(Collection<Criteria> filterList, String fieldName, Date startDate, Date endDate) {
    //        if (startDate != null && endDate != null) {
    //            Criteria criteria = Criteria.where(fieldName).between(startDate,endDate);
    //            filterList.add(criteria);
    //        } else if (startDate != null) {
    //            Criteria criteria = Criteria.where(fieldName).greaterThan(startDate);
    //            filterList.add(criteria);
    //        } else if (endDate != null) {
    //            Criteria criteria = Criteria.where(fieldName).lessThan(endDate);
    //            filterList.add(criteria);
    //        }
    //    }
    //    public static void addSingleRegexSearch(Collection<Criteria> filterList, String fieldName, String value) {
    //        if (StringUtils.isNotEmpty(value)) {
    //            Criteria criteria = Criteria.where(fieldName).like(value);
    //            filterList.add(criteria);
    //        }
    //    }
    //    public static void addMultipleRegexSearch(Collection<Criteria> filterList,String value,String ...fieldNames) {
    //        if (Objects.nonNull(value) && Objects.nonNull(fieldNames)) {
    //          Arrays.stream(fieldNames).map(fieldName -> Criteria.where(fieldName).like(value)).forEach(filterList::add);
    //        }
    //    }
    //
    //    public static void addSingleValueFilter(Collection<Criteria> filterList, String fieldName, Object value) {
    //        if (Objects.nonNull(value) && !value.toString().isEmpty()) {
    //            Criteria criteria = Criteria.where(fieldName).is(value);
    //            filterList.add(criteria);
    //        }
    //    }
    //    public static void addMultipleValuesFilter(Collection<Criteria> filterList, String fieldName, Collection<String> value) {
    //        if (!CollectionUtils.isEmpty(value)) {
    //            Criteria criteria = Criteria.where(fieldName).in(value);
    //            filterList.add(criteria);
    //        }
    //    }
    //
    //
    //    public static void addSingleValuesNinFilter(Collection<Criteria> filterList, String fieldName, Collection<String> value) {
    //        if (!CollectionUtils.isEmpty(value)) {
    //            Criteria criteria = Criteria.where(fieldName).notIn(value);
    //            filterList.add(criteria);
    //        }
    //    }
    //    public static void addSingleValueNeFilter(Collection<Criteria> filterList, String fieldName, String value) {
    ////        if (StringUtils.nonNullOrEmptyString(value)) {
    //            Criteria criteria = Criteria.where(fieldName).not(value);
    //            filterList.add(criteria);
    ////        }
    //    }
    //
    //    public static void addPageable(Query query, @NotNull Pageable pageable, @NotNull Boolean isPageAble) {
    //        if(isPageAble){
    //            query.with(pageable);
    //        }else {
    //            pageable = Pageable.unpaged();
    //        }
    //    }
}