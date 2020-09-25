package com.tcs.ordermanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
//@ComponentScan("com.tcs.ordermanagement.dao")
open class OrdermanagementApplication

fun main(args: Array<String>) {
	runApplication<OrdermanagementApplication>(*args)
}