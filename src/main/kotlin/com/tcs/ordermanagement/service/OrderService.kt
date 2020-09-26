package com.tcs.ordermanagement.service

import com.tcs.ordermanagement.dao.OrderDAO
import com.tcs.ordermanagement.dao.ProductDAO
import com.tcs.ordermanagement.dao.TransportDAO
import com.tcs.ordermanagement.model.Order
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import com.tcs.ordermanagement.model.Transport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class OrderService(val dao: ProductDAO, val daoOrder: OrderDAO, val daoTransport: TransportDAO){

    val logger: Logger =LoggerFactory.getLogger("service logs")

    companion object{
        fun productStatus(id: Int,qty: Int, product: Product): String{
            if(product==null)
                return "No Product found with id $id"
            else if (product.qty<qty)
                return "Product ${product.name} with ID ${product.product_id} doesn't have sufficient quantity"
            else if (!(product.valid_To.isAfter(LocalDate.now()) && product.valid_From.isBefore(LocalDate.now())))
                return "Product ${product.name} with ID ${product.product_id} doesn't have validity"
            else return "available"
        }

        fun calInstance()=Calendar.getInstance()
    }

    //Product Functions

    fun addProduct(prod: Product)=
        dao.addProduct(prod)

    fun addPrice(price: ProductPrice) =
        dao.addPrice(price)

    fun getProduct(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        val res= productStatus(id,qty,daoResponse)
        if(res.equals("available"))
            return daoResponse.toString()
        else return res
    }

    fun getTotalPrice(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        val res= productStatus(id,qty,daoResponse)
        if(res.equals("available"))
            return "The total amount of ${daoResponse.name} is ${(dao.getPrice(id).amount*qty)}"
        else return res
    }
    //Order Functions

    fun newOrder(id: Int,qty: Int): String{
        val daoResponse=dao.getProduct(id).orElse(null)
        val res= productStatus(id,qty,daoResponse)
        if(res.equals("available")){
            val orderResponse= daoOrder.newOrder(Order(0,id, dao.getPrice(id).amount*qty))
            dao.updateQuantity(id,daoResponse.qty-qty)
            val c= calInstance()
            c.add(Calendar.DATE,10)
            daoTransport.addTransport(Transport(0,orderResponse.order_id,1,"Ready To Ship",Date(),c.time))
            return orderResponse.toString()
        }
        else return res
    }

    fun getOrder(id: Int)=
        daoOrder.getOrder(id)

    //Transport functions

    fun getTransport(id: Int)=
        daoTransport.getTransport(id)

    fun getTransportByOrder(id: Int)=
        daoTransport.getTransportByOrder(id).toString()
}