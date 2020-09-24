package com.tcs.ordermanagement.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_price")
@NamedQuery(name="ProductPrice.findAmtByProductId",query="SELECT p.amount FROM ProductPrice p WHERE p.product_id = :id")
data class ProductPrice (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    val product_id:Int,

    @NotNull
    val amount: Double,

    @get : NotBlank
    val currency: String,

    @NotNull
    var valid_from: Date,

    @NotNull
    var valid_to: Date
)