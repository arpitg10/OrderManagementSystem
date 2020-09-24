package com.tcs.ordermanagement.controller

import com.tcs.ordermanagement.service.OrderService
import com.tcs.ordermanagement.model.Product
import com.tcs.ordermanagement.model.ProductPrice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/home")
class Controller (val orderService: OrderService){

    @PostMapping("/newProduct")
    fun addProduct(@Valid @RequestBody prod: Product)=
        ResponseEntity.ok(orderService.addProduct(prod))


    @GetMapping("/product/{id}/{qty}")
    fun getProduct(@PathVariable(value = "id") id:Int,@PathVariable(value = "qty") qty:Int)=
        ResponseEntity.ok(orderService.getProduct(id,qty))

    @PostMapping("/price/add")
    fun addPrice(@Valid @RequestBody price: ProductPrice)=
        orderService.addPrice(price)

    @GetMapping("/totalPrice/{id}/{qty}")
    fun getTotalPrice(@PathVariable(value = "id") id:Int,@PathVariable(value = "qty") qty:Int)=
        ResponseEntity.ok(orderService.getTotalPrice(id,qty))

    @PostMapping("/order/{id}/{qty}")
    fun newOrder(@PathVariable(value = "id") id:Int,@PathVariable(value = "qty") qty:Int)=
        ResponseEntity.ok(orderService.newOrder(id,qty))

    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable(value = "id") id:Int)=
        ResponseEntity.ok(orderService.getOrder(id))

    @GetMapping("/transport/{id}")
    fun getTransport(@PathVariable(value = "id") id:Int)=
        ResponseEntity.ok(orderService.getTransport(id))

    @GetMapping("/transport/order/{orderId}")
    fun getTransportByOrder(@PathVariable(value="orderId") id: Int)=
        ResponseEntity.ok(orderService.getTransportByOrder(id))
}