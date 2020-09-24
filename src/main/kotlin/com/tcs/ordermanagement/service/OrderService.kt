package com.tcs.ordermanagement.service

import com.tcs.ordermanagement.dao.OrderDAO
import com.tcs.ordermanagement.dao.ProductDAO
import com.tcs.ordermanagement.dao.TransportDAO
import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import com.tcs.ordermanagement.model.Transport
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class OrderService(val dao: ProductDAO, val daoOrder: OrderDAO, val daoTransport: TransportDAO){

    val logger=LoggerFactory.getLogger("service logs")

    //Product Functions

    fun addProduct(prod: Product)=
        dao.addProduct(prod)

    fun addPrice(price: ProductPrice) =
        dao.addPrice(price)

    fun getProduct(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        if(daoResponse==null)
            return "No Product found with id $id"
        else if (daoResponse.qty<qty)
            return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have sufficient quanity"
        else if (daoResponse.valid_To.isAfter(LocalDate.now()) && daoResponse.valid_From.isBefore(LocalDate.now()))
            return daoResponse.toString()
        else return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have validity"
    }

    fun getTotalPrice(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        if(daoResponse==null)
            return "No Product found with id $id"
        else if (daoResponse.qty<qty)
            return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have sufficient quanity"
        else if (!(daoResponse.valid_To.isAfter(LocalDate.now()) && daoResponse.valid_From.isBefore(LocalDate.now())))
            return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have validity"
        else return "The total amount of ${daoResponse.name} is ${(dao.getPrice(id)*qty).toString()}"
    }
    //Order Functions

    fun newOrder(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        if(daoResponse==null)
            return "No Product found with id $id"
        else if (daoResponse.qty<qty)
            return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have sufficient quanity"
        else if (!(daoResponse.valid_To.isAfter(LocalDate.now()) && daoResponse.valid_From.isBefore(LocalDate.now())))
            return "Product ${daoResponse.name} with ID ${daoResponse.product_id} doesn't have validity"
        else{
            var orderResponse= daoOrder.newOrder(Order(0,id, dao.getPrice(id)*qty))
            //dao.updateQuantity(id,daoResponse.qty-qty)
            val c=Calendar.getInstance()
            c.add(Calendar.DATE,10)
            daoTransport.addTransport(Transport(0,orderResponse.order_id,1,"Ready To Ship",Date(),c.time))
            return orderResponse.toString()
        }
    }

    fun getOrder(id: Int)=
        daoOrder.getOrder(id)

    //Transport functions

    fun getTransport(id: Int)=
        daoTransport.getTransport(id)

    fun getTransportByOrder(id: Int)=
        daoTransport.getTransportByOrder(id).toString()
}