package com.example.kotlindemo.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.data.domain.Page
import java.util.function.BiFunction
import java.util.stream.Collectors

object MappingUtils {
    private val modelMapper = ModelMapper()
    private val objectMapper = ObjectMapper()

    init {
        modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        //        modelMapper.getConfiguration().setDeepCopyEnabled(false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    fun <S, T> mapList(source: List<S>, targetClass: Class<T>?): List<T> {
        return source
                .stream()
                .map { element: S -> modelMapper.map(element, targetClass) }
                .collect(Collectors.toList())
    }

    fun <D, T> mapListV2(entityList: Collection<T>?, outCLass: Class<D>?): List<D>? {
        return entityList?.stream()?.map { entity: T -> mapObject(entity, outCLass) }?.collect(Collectors.toList())
    }

    fun <S, T> mapList(source: List<S>, targetClass: Class<T>?, func: BiFunction<S, T, T>): List<T> {
        return source
                .stream()
                .map { element: S ->
                    val result = modelMapper.map(element, targetClass)
                    func.apply(element, result)
                    result
                }
                .collect(Collectors.toList())
    }

    fun <S, T> mapSet(source: Set<S>, targetClass: Class<T>?, func: BiFunction<S, T, T>): Set<T> {
        return source
                .stream()
                .map { element: S ->
                    val result = modelMapper.map(element, targetClass)
                    func.apply(element, result)
                    result
                }
                .collect(Collectors.toSet())
    }

    fun <S, T> mapSet(source: Set<S>, targetClass: Class<T>?): Set<T> {
        return source
                .stream()
                .map { element: S -> modelMapper.map(element, targetClass) }
                .collect(Collectors.toSet())
    }

    fun <D, T> map(page: Page<T>?, outCLass: Class<D>?): Page<D>? {
        return page?.map { objectEntity: T -> modelMapper.map(objectEntity, outCLass) }
    }

    @JvmStatic
    fun <D> mapObject(source: Any?, targetClass: Class<D>?): D {
        return modelMapper.map(source, targetClass)
    }

    fun <D> mapObject(source: Any?, des: D): D {
//        BeanUtils.copyProperties(source,des);
        modelMapper.map(source, des)
        return des
    }

    fun <T> readValue(request: String?, clazz: Class<T>?): T {
        return try {
            objectMapper.readValue(request, clazz)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> readValue(request: Any?, clazz: Class<T>?): T {
        return try {
            objectMapper.convertValue(request, clazz)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException(e)
        }
    }
}