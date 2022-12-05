import java.util.Stack

data class Instruction(
    val count: Int,
    val from: Int,
    val to: Int,
)

fun main() {

    fun readStacks(iterator: Iterator<String>): List<Stack<Char>> {
        val stacks = mutableListOf<Stack<Char>>()
        val lines = Stack<String>()
        var numberOfStacks = 0

        while (iterator.hasNext()) {
            val line = iterator.next()
            if (line.contains("1")) {
                val split = line.split(" ")
                numberOfStacks = Integer.valueOf(split[split.size - 1])
                break
            } else {
                lines.add(line)
            }
        }

        for (i in 0 until numberOfStacks) {
            stacks.add(Stack<Char>())
        }

        while (lines.isNotEmpty()) {
            val split = lines.pop().toCharArray()
            for (i in 0 until numberOfStacks) {
                val elementIndex = 1 + 4 * i
                if (elementIndex < split.size) {
                    if (split[elementIndex].isLetter()) {
                        stacks[i].add(split[1 + 4 * i])
                    }
                }
            }
        }

        return stacks
    }

    fun createInstruction(line: String): Instruction {
        val split = line.split(" ")
        return Instruction(split[1].toInt(), split[3].toInt() - 1, split[5].toInt() - 1)
    }

    fun getTopCreates(fileName: String, movingStrategy: (List<Stack<Char>>, Instruction) -> Unit): String {
        var result = ""

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            val stacks = readStacks(iterator)
            //empty line
            iterator.next()
            while (iterator.hasNext()) {
                movingStrategy(stacks, createInstruction(iterator.next()))
            }
            for (stack in stacks) {
                if (stack.isNotEmpty()) result += stack.pop()
            }
        }

        return result
    }

    val lifo = { stacks: List<Stack<Char>>, instruction: Instruction ->
        for (i in 0 until instruction.count) {
            stacks[instruction.to].add(stacks[instruction.from].pop())
        }
    }

    val fifo = { stacks: List<Stack<Char>>, instruction: Instruction ->
        val list = mutableListOf<Char>()
        for (i in 0 until instruction.count) {
            list.add(stacks[instruction.from].pop())
        }
        for (i in list.size - 1 downTo 0) {
            stacks[instruction.to].add(list[i])
        }
    }

    println("Test")
    println("Top elements after rearrangements should be CMZ! Answer: ${getTopCreates("Day05_test", lifo)}")
    println("Top elements after rearrangements should be MCD! Answer: ${getTopCreates("Day05_test", fifo)}")

    println()
    println("Exercise")
    println("Top elements after rearrangements should be PTWLTDSJV! Answer: ${getTopCreates("Day05", lifo)}")
    println("Top elements after rearrangements should be WZMFVGGZP! Answer: ${getTopCreates("Day05", fifo)}")
}