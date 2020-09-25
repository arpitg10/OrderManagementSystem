package com.tcs.ordermanagement.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_price")
data class ProductPrice (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @Column(name="product_id")
    val productId:Int,

    @NotNull
    val amount: Double,

    @get : NotBlank
    val currency: String,

    @NotNull
    var valid_from: Date,

    @NotNull
    var valid_to: Date
)