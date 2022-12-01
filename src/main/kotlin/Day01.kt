fun main() {
    fun findHighestCalorie(fileName: String): Int {
        var max = 0; var sum = 0
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val currentLine = iterator.next()
                if (currentLine.isNotEmpty()) {
                    sum += currentLine.toInt()
                } else if (sum > max) {
                    max = sum
                    sum = 0
                } else {
                    sum = 0
                }
            }
        }

        return if (sum > max) sum else max
    }

    fun findTheSumOfTheThreeHighestCalorie(fileName: String): Int {
        val highestCalories = mutableListOf<Int>()
        var sum = 0
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val currentLine = iterator.next()
                if (currentLine.isNotEmpty()) {
                    sum += currentLine.toInt()
                } else if (highestCalories.size != 3) {
                    highestCalories.add(sum)
                    highestCalories.sort()
                    sum = 0
                } else if (sum > highestCalories[0]) {
                    highestCalories[0] = sum
                    highestCalories.sort()
                    sum = 0
                } else {
                    sum = 0
                }
            }
        }

        if (sum > highestCalories[0]) highestCalories[0] = sum

        return highestCalories.sum()
    }

    println("Test")
    println("Highest calorie: ${findHighestCalorie("Day01_test")}")
    println("Highest 3 calorie: ${findTheSumOfTheThreeHighestCalorie("Day01_test")}")

    println()
    println("Exercise")
    println("Highest calorie: ${findHighestCalorie("Day01")}")
    println("Highest 3 calorie: ${findTheSumOfTheThreeHighestCalorie("Day01")}")
}
