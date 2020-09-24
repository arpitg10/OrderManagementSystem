package com.tcs.ordermanagement.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@NamedQuery(name="Product.updateQuantity", query="update Product p SET p.qty= :newQty WHERE p.product_id = :id")
data class Product (
    @Id
    val product_id: Int,

    @get : NotBlank
    val name: String,

    @get : NotBlank
    val descr: String,

    @get : NotBlank
    val category: String,

    @NotNull
    val valid_From: LocalDate,

    @NotNull
    val valid_To: LocalDate,

    @NotNull
    val qty: Int
)