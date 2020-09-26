package com.tcs.ordermanagement.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
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
    val qty: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name="product_id",referencedColumnName = "product_id")
    //@OneToMany(mappedBy = "productId")
    val productPrice: ProductPrice
)