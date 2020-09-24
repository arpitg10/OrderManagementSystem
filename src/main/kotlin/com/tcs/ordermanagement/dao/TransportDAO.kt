package com.tcs.ordermanagement.dao

import com.tcs.ordermanagement.model.Transport
import com.tcs.ordermanagement.repository.TransportRepository
import org.springframework.stereotype.Component

@Component
class TransportDAO (val transportRepository: TransportRepository){
    fun getTransport(id: Int)=
        transportRepository.findById(id)

    fun addTransport(transport: Transport)=
        transportRepository.save(transport)

    fun getTransportByOrder(id: Int)=
        transportRepository.getTransportByOrder(id)
}