import kotlin.streams.toList

fun main() {

    fun getFullyContainedPairsCount(fileName: String): Int {
        var sumOfFullyContainedPairs = 0

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val assignmentPairs = iterator.next().split(",")
                val assignmentA = assignmentPairs[0].split("-").stream().mapToInt(String::toInt).toList()
                val assignmentB = assignmentPairs[1].split("-").stream().mapToInt(String::toInt).toList()
                if (assignmentA[0] <= assignmentB[0] && assignmentA[1] >= assignmentB[1] ||
                    assignmentB[0] <= assignmentA[0] && assignmentB[1] >= assignmentA[1]) {
                    sumOfFullyContainedPairs++
                }
            }
        }

        return sumOfFullyContainedPairs
    }

    fun getOverlappingPairCount(fileName: String): Int {
        var sumOfFullyContainedPairs = 0

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val assignmentPairs = iterator.next().split(",")
                val assignmentA = assignmentPairs[0].split("-").stream().mapToInt(String::toInt).toList()
                val assignmentB = assignmentPairs[1].split("-").stream().mapToInt(String::toInt).toList()
                if (assignmentA[0] <= assignmentB[0] && assignmentA[1] >= assignmentB[0] ||
                    assignmentB[0] <= assignmentA[0] && assignmentB[1] >= assignmentA[0]) {
                    sumOfFullyContainedPairs++
                }
            }
        }

        return sumOfFullyContainedPairs
    }

    println("Test")
    println("Number of pairs where one range fully contain the other should be 2! Counted: ${getFullyContainedPairsCount("Day04_test")}")
    println("Number of pairs where's an overlap is 4! Counted: ${getOverlappingPairCount("Day04_test")}")

    println()
    println("Exercise")
    println("Number of pairs where one range fully contain the other should be 547! Counted: ${getFullyContainedPairsCount("Day04")}")
    println("Number of pairs where's an overlap is 843! Counted: ${getOverlappingPairCount("Day04")}")
}