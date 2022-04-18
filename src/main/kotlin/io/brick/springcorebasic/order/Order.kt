package io.brick.springcorebasic.order

data class Order(
    var memberId: Long,
    var itemName: String,
    var itemPrice: Int,
    var discountPrice: Int
) {
    fun calculatePrice(): Int {
        return itemPrice - discountPrice
    }
}