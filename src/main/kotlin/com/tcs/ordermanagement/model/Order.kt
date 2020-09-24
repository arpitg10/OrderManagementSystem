package com.tcs.ordermanagement.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "order_details")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val order_id: Int,

    @NotNull
    val product_id: Int,

    @NotNull
    val totalPrice: Double
)