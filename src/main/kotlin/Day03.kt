fun main() {

    fun getItemValue(item: Char) = if (item.isUpperCase()) {
        item.code - 38
    } else {
        item.code - 96
    }

    fun sumOfThePriorities(fileName: String): Int {
        var result = 0

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val line = iterator.next()
                val compartmentA = line.substring(0, line.length / 2)
                val compartmentB = line.substring(line.length / 2)
                var sum = 0
                val checkedItems = mutableSetOf<Char>()
                for (item in compartmentA) {
                    if (compartmentB.contains(item, ignoreCase = false) && !checkedItems.contains(item)) {
                        sum += getItemValue(item)
                        checkedItems.add(item)
                    }
                }

                result += sum
            }
        }

        return result
    }

    fun isRucksackContainsItem(items: String, item: Char) =
        items.contains(item, ignoreCase = false)

    fun getGroupPriority(groupRucksack: MutableList<String>): Int {
        var result = 0

        groupRucksack.sort()
         groupRucksack[0].filter { item ->
            isRucksackContainsItem(groupRucksack[1], item)
        }.filter { item ->
            isRucksackContainsItem(groupRucksack[2], item)
        }.toSet()
            .forEach { item ->
                result += getItemValue(item)
            }

        return result
    }

    fun sumOfThePrioritiesInGroups(fileName: String): Int {
        var result = 0

        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            var groupRucksack = mutableListOf<String>()
            var numberOfIteration = 0
            while (iterator.hasNext()) {
                groupRucksack.add(iterator.next())
                if (++numberOfIteration % 3 == 0) {
                    result += getGroupPriority(groupRucksack)
                    groupRucksack = mutableListOf()
                }
            }
        }

        return result
    }

    println("Test")
    println("Sum of the priorities should be 157! My answer: ${sumOfThePriorities("Day03_test")}")
    println("Sum of the priorities in three groups should be 70! My answer: ${sumOfThePrioritiesInGroups("Day03_test")}")

    println()
    println("Exercise")
    println("Sum of the priorities should be 7785! My answer: ${sumOfThePriorities("Day03")}")
    println("Sum of the priorities in three groups should be 2633! My answer: ${sumOfThePrioritiesInGroups("Day03")}")
}