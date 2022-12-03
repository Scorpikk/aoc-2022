package day03

import readInput

fun main() {
    val input = readInput("day03", "input")
    println(part1(input))
    println(part2(input))
}

private val Char.priority: Int
    get() {
        val priority = this - 'a' + 1
        return if (priority > 0) {
            priority
        } else {
            this - 'A' + 27
        }
    }

private fun part1(input: List<String>): Int {
    var prioritySum = 0

    input.forEach { line ->
        val (rucksack1, rucksack2) = line.chunked(line.length / 2)
            .map { it.toSet() }
        val commonItem = rucksack1.intersect(rucksack2).single()
        prioritySum += commonItem.priority
    }

    return prioritySum
}

private fun part2(input: List<String>): Int {
    var prioritySum = 0

    input.chunked(3)
        .forEach { chunk ->
            val rucksacks = chunk.map { it.toSet() }
            val badge = rucksacks.fold(rucksacks[0]) { acc, chars -> acc.intersect(chars) }
                .single()
            prioritySum += badge.priority
        }

    return prioritySum
}

