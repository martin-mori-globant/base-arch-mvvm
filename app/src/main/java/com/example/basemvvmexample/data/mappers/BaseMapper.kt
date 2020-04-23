package com.example.basemvvmexample.data.mappers

interface BaseMapper<E, D> {
    fun transform(type: E): D
    fun transformToRepository(type: D): E
}
