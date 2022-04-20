package io.brick.springcorebasic.singleton

class SingletonService {

    companion object {
        // static으로 선언하여 전역적으로 관리되도록 함
        private val instance = SingletonService()

        fun getInstance(): SingletonService {
            return instance
        }
    }

    // 생성자를 private하게 설정해야 외부에서 객체 생성 위험성 방지
    private constructor() {}

    fun logic() {
        println("싱글톤 객체 로직 호출")
    }
}