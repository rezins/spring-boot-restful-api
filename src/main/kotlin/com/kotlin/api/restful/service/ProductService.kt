package com.kotlin.api.restful.service

import com.kotlin.api.restful.model.CreateProductRequest
import com.kotlin.api.restful.model.ListProductRequest
import com.kotlin.api.restful.model.ProductResponse
import com.kotlin.api.restful.model.UpdateProductRequest

interface ProductService  {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String) : ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest) : ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest) : List<ProductResponse>

}