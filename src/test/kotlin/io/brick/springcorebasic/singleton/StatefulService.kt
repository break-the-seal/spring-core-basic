package io.brick.springcorebasic.singleton

class StatefulService(
    private var price: Int = 0
) {
    fun order(name: String, price: Int) {
        println("name = $name, price = $price")
        this.price = price
    }

    fun getPrice(): Int {
        return price
    }
}