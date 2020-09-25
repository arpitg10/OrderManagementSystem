package com.tcs.ordermanagement.dao

import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.repository.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderDAO(val orderRepository: OrderRepository){
    fun newOrder(order: Order) =
        orderRepository.save(order)

    fun getOrder(id: Int)=
        orderRepository.findById(id)
}

