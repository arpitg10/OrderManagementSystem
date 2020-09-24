package com.tcs.ordermanagement.dao

import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import com.tcs.ordermanagement.repository.OrderRepository
import com.tcs.ordermanagement.repository.ProductPriceRepository
import com.tcs.ordermanagement.repository.ProductRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductDAO(val productRepository: ProductRepository, val productPriceRepository: ProductPriceRepository){
    fun addProduct(prod: Product): Product=
        productRepository.save(prod)

    fun getProduct(id: Int): Optional<Product> =
        productRepository.findById(id)

    fun addPrice(price: ProductPrice) =
        productPriceRepository.save(price)

    fun getPrice(id: Int)=
        productPriceRepository.findAmtByProductId(id)

    fun updateQuantity(id: Int, newQty: Int) {
        productRepository.updateQuantity(id,newQty)
    }
}