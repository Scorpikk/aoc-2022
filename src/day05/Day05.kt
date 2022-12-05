package day05

import readInput
import java.util.Stack

fun main() {
    val input = readInput("day05", "input")
    println(part1(input))
    println(part2(input))
}

private val procedureRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

private fun part1(input: List<String>): Any {
    val stacks = stacks(input)

    input.drop(10)
        .forEach { line ->
            val (size, from, to) = procedureRegex.find(line)!!.groupValues.mapNotNull { it.toIntOrNull() }
            repeat(size) {
                stacks[to - 1].push(stacks[from - 1].pop())
            }
        }

    return stacks.mapNotNull { it.lastOrNull() }.joinToString(separator = "")
}

private fun part2(input: List<String>): Any {
    val stacks = stacks(input)

    val tempStack = Stack<Char>()
    input.drop(10)
        .forEach { line ->
            val (size, from, to) = procedureRegex.find(line)!!.groupValues.mapNotNull { it.toIntOrNull() }
            repeat(size) {
                tempStack.push(stacks[from - 1].pop())
            }
            while (tempStack.isNotEmpty()) {
                stacks[to - 1].push(tempStack.pop())
            }
        }

    return stacks.mapNotNull { it.lastOrNull() }.joinToString(separator = "")
}

private fun stacks(input: List<String>): ArrayList<Stack<Char>> {
    val stacks = arrayListOf<Stack<Char>>()
    input.take(8)
        .reversed()
        .forEach { line ->
            line.chunked(4)
                .forEachIndexed { index, crate ->
                    if (crate.isNotBlank()) {
                        if (stacks.size >= index) {
                            stacks.add(Stack())
                        }
                        stacks[index].push(crate[1])
                    }
                }
        }
    return stacks
}
