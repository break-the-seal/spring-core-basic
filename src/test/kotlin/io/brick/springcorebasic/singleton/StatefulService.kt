package io.brick.springcorebasic.singleton

class StatefulService(
    // stateful한 상태임
    private var price: Int = 0
) {
    // stateless하게 관리를 해야함
    fun order(name: String, price: Int) {
        println("name = $name, price = $price")
        this.price = price
    }

    fun getPrice(): Int {
        return price
    }
}