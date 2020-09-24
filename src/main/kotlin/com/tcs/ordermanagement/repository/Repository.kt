package com.tcs.ordermanagement.repository


import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import com.tcs.ordermanagement.model.Transport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository  : JpaRepository<Product,Int>{
    fun updateQuantity(@Param("id") prod_id:Int,@Param("newQty") newQty:Int)
}

@Repository
interface ProductPriceRepository  : JpaRepository<ProductPrice,Int>{
    fun findAmtByProductId(@Param("id") prod_id:Int):Double
}

@Repository
interface OrderRepository  : JpaRepository<Order,Int>

@Repository
interface TransportRepository  : JpaRepository<Transport,Int>{
    fun getTransportByOrder(@Param("id") id: Int?): Transport?
}