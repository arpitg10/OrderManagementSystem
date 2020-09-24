package com.tcs.ordermanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class OrdermanagementApplication

fun main(args: Array<String>) {
	runApplication<OrdermanagementApplication>(*args)
}