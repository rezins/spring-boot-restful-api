package com.kotlin.api.restful.repository

import com.kotlin.api.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String>{
}