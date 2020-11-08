package com.kotlin.api.restful.service.imp

import com.kotlin.api.restful.entity.Product
import com.kotlin.api.restful.error.NotFoundException
import com.kotlin.api.restful.model.CreateProductRequest
import com.kotlin.api.restful.model.ListProductRequest
import com.kotlin.api.restful.model.ProductResponse
import com.kotlin.api.restful.model.UpdateProductRequest
import com.kotlin.api.restful.repository.ProductRepository
import com.kotlin.api.restful.service.ProductService
import com.kotlin.api.restful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository,
        val validationUtil: ValidationUtil
    ) : ProductService{

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {

        validationUtil.validate(createProductRequest)

        val product = Product(
                id = createProductRequest.id,
                name = createProductRequest.name,
                price = createProductRequest.price,
                quantity = createProductRequest.quantity,
                createdAt = Date(),
                updatedAt = null

        )
        productRepository.save(product)
        return convertProductToProductResponse(product)

    }

    override fun get(id: String): ProductResponse {
        val product = findProductOrThrow(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductOrThrow(id)

        validationUtil.validate(updateProductRequest)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)

    }

    override fun delete(id: String) {
        val product = findProductOrThrow(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToProductResponse(it) }
    }


    private fun convertProductToProductResponse(product: Product) : ProductResponse{
        return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
        )
    }

    private fun findProductOrThrow(id: String) : Product{
        val product = productRepository.findByIdOrNull(id)
        if(product == null){
            throw NotFoundException()
        }else{
            return product
        }
    }
}