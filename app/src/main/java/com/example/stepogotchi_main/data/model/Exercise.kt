package com.example.stepogotchi_main.data.model

data class Exercise(
    private val dateCreated: String,
    private val steps: Int = 0,
    private val expGained: Int = when(steps) {
        in(0..1000) -> 10
        in 1001..5000 -> 30
        in 5001..100000 -> 100
        else -> 100000

},
    private val moneyGained: Int = when(steps) {
        in(0..1000) -> 100
        in 1001..5000 -> 300
        in 5001..100000 -> 1000
        else -> 100000

    }
)
