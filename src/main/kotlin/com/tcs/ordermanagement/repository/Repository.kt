package com.tcs.ordermanagement.repository


import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import com.tcs.ordermanagement.model.Transport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductRepository  : JpaRepository<Product,Int>{
    @Modifying
    @Transactional
    @Query("update Product p SET p.qty= :newQty WHERE p.product_id = :id")
    fun updateQty(@Param("id") prod_id:Int,@Param("newQty") newQty:Int)
}

@Repository
interface ProductPriceRepository  : JpaRepository<ProductPrice,Int>{
    fun findByProductId(@Param("id") prod_id:Int): ProductPrice
}

@Repository
interface OrderRepository  : JpaRepository<Order,Int>

@Repository
interface TransportRepository  : JpaRepository<Transport,Int>{
    fun getTransportByOrderId(id: Int): Transport
}