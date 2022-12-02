fun main() {

    fun function(fileName: String): Int {
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val line = iterator.next()
            }
        }

        return 0
    }

    println("Test")
    println("Highest calorie: ${function("Day01_test")}")
    println("Highest 3 calorie: ${function("Day01_test")}")

    println()
    println("Exercise")
    println("Highest calorie: ${function("Day01")}")
    println("Highest 3 calorie: ${function("Day01")}")
}