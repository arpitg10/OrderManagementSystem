package com.tcs.ordermanagement.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@NamedQuery(name="Transport.getTransportByOrder", query="SELECT p FROM Transport p WHERE p.order_id = :id")
data class Transport(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @NotNull
    val order_id: Int,

    @NotNull
    val stage_id: Int,

    @get : NotBlank
    val status: String,

    val start_time: Date,

    val end_time: Date?
)