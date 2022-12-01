package day01

import readInput

fun main() {
    val input = readInput("day01", "input")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var maxCalories = 0
    var elfCalories = 0

    input.forEach {
        if (it.isBlank()) {
            if (maxCalories < elfCalories) {
                maxCalories = elfCalories
            }
            elfCalories = 0
        } else {
            elfCalories += it.toInt()
        }
    }

    return maxCalories
}

private fun part2(input: List<String>): Int {
    val elvesCalories = mutableListOf<Int>()
    var elfCalories = 0

    input.forEach {
        if (it.isBlank()) {
            elvesCalories += elfCalories
            elfCalories = 0
        } else {
            elfCalories += it.toInt()
        }
    }

    return elvesCalories.sortedDescending()
        .take(3)
        .sum()
}