package day02

import readInput

fun main() {
    val input = readInput("day02", "input")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var score = 0

    input.forEach { line ->
        val (opponent, player) = line.split(' ').map { it.single() }
        score += player.figureScore()
        score += roundResult(opponent, player)
    }

    return score
}

private fun part2(input: List<String>): Int {
    var score = 0

    input.forEach {
        val (opponent, result) = it.split(' ').map { it.single() }
        val player = chooseFigure(opponent, result)
        score += player.figureScore()
        score += roundResult(opponent, player)
    }

    return score
}

private fun Char.figureScore() = this - 'X' + 1

private const val WON = 6
private const val DRAW = 3
private const val LOST = 0

private fun roundResult(opponent: Char, player: Char) = when {
    opponent == 'A' && player == 'Y' || opponent == 'B' && player == 'Z' || opponent == 'C' && player == 'X' -> WON
    opponent == 'A' && player == 'X' || opponent == 'B' && player == 'Y' || opponent == 'C' && player == 'Z' -> DRAW
    else -> LOST
}

private val opponentFigures = charArrayOf('A', 'B', 'C')
private val playerFigures = charArrayOf('X', 'Y', 'Z')

private fun chooseFigure(opponent: Char, result: Char): Char {
    val shift = result - 'Y'
    return playerFigures[(opponentFigures.indexOf(opponent) + shift + 3) % 3]
}
