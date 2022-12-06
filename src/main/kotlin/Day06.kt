import java.util.Vector

fun main() {

    fun findMarkerIndex(line: String, markerIndex: Int, bufferSize: Int): Int {
        val buffer = Vector<Char>(bufferSize)
        for (index in markerIndex until line.length) {
            val actual = line[index]
            if (buffer.size >= bufferSize) {
                buffer.removeFirst()
            }
            if (buffer.contains(actual)) {
                while (true) {
                    val removeFirst = buffer.removeFirst()
                    if (removeFirst == actual) {
                        break
                    }
                }
            }
            buffer.add(line[index])

            if (buffer.size == bufferSize) {
                return index + 1
            }
        }

        return -1
    }

    fun findFirstMessageMarkerLastElementIndex(fileName: String): Int {
        //Since the line is long it would be better to read only one char at a time
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val line = iterator.next()
                val markerIndex = findMarkerIndex(line, 0, 4)
                return findMarkerIndex(line, markerIndex - 4, 14)
            }
        }

        return -1
    }

    fun findFirstMarkerLastElementIndex(fileName: String): Int {
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val line = iterator.next()
                return findMarkerIndex(line, 0, 4)
            }
        }

        return -1
    }

    println("Test")
    //println("The first marker appears after: 7! Answer: ${findFirstMarkerLastElementIndex("Day06_1_test")}")
    //println("The first marker appears after: 5! Answer: ${findFirstMarkerLastElementIndex("Day06_2_test")}")
    //println("The first marker appears after: 10! Answer: ${findFirstMarkerLastElementIndex("Day06_3_test")}")
    println("The first message marker appears after 19! Answer: ${findFirstMessageMarkerLastElementIndex("Day06_1_test")}")
    println("The first message marker appears after 23! Answer: ${findFirstMessageMarkerLastElementIndex("Day06_2_test")}")
    println("The first message marker appears after 29! Answer: ${findFirstMessageMarkerLastElementIndex("Day06_3_test")}")

    println()
    println("Exercise")
    println("The first marker appears after: 1109! Answer: ${findFirstMarkerLastElementIndex("Day06")}")
    println("The first marker appears after: 3965! Answer: ${findFirstMessageMarkerLastElementIndex("Day06")}")
}