package day04

import readInput

fun main() {
    val input = readInput("day04", "input")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var intersectedPairs = 0

    input.forEach { line ->
        val (range1, range2) = parseRanges(line)
        if (range1.first in range2 && range1.last in range2 || range2.first in range1 && range2.last in range1) {
            intersectedPairs++
        }
    }

    return intersectedPairs
}

private fun part2(input: List<String>): Int {
    var overlappingPairs = 0

    input.forEach { line ->
        val (range1, range2) = parseRanges(line)
        if (range1.first in range2 || range1.last in range2 || range2.first in range1 || range2.last in range1) {
            overlappingPairs++
        }
    }

    return overlappingPairs
}

private fun parseRanges(line: String) = line.split(',')
    .map { pair ->
        val (start, end) = pair.split("-")
            .map { it.toInt() }
        start..end
    }

