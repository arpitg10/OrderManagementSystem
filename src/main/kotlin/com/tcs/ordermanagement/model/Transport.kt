package com.tcs.ordermanagement.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Transport(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @NotNull
    @Column(name="order_id")
    val orderId: Int,

    @NotNull
    val stage_id: Int,

    @get : NotBlank
    val status: String,

    val start_time: Date,

    val end_time: Date?
)