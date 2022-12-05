import java.util.Stack

class Instruction(
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
        return Instruction(split[1].toInt(), split[3].toInt()-1, split[5].toInt()-1)
    }

    fun getTopCreates(fileName: String): String {
        var result = ""

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            val stacks = readStacks(iterator)
            iterator.next()
            while (iterator.hasNext()) {
                val instruction = createInstruction(iterator.next())
//                for (i in 0 until instruction.count) {
//                    stacks[instruction.to].add(stacks[instruction.from].pop())
//                }

                val list = mutableListOf<Char>()
                for (i in 0 until instruction.count) {
                    list.add(stacks[instruction.from].pop())
                }
                for (i in list.size-1 downTo 0) {
                    stacks[instruction.to].add(list[i])
                }
            }
            for (stack in stacks) {
                if (stack.isNotEmpty()) result += stack.pop()
            }
        }

        return result
    }



    println("Test")
    println("Highest calorie: ${getTopCreates("Day05_test")}")
    //println("Highest 3 calorie: ${function("Day01_test")}")

    println()
    println("Exercise")
    println("Highest calorie: ${getTopCreates("Day05")}")
    //println("Highest 3 calorie: ${function("Day01")}")
}