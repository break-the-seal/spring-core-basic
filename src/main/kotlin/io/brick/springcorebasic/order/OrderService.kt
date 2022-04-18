package io.brick.springcorebasic.order

interface OrderService {
    fun createOrder(
        memberId: Long,
        itemName: String,
        itemPrice: Int
    ): Order
}