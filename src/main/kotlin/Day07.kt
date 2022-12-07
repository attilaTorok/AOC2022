open class Directory(
    val name: String, val parent: Directory?, val children: MutableList<Directory> = mutableListOf(), var size: Long = 0
) {
    fun calculateSize() {
        for (child in children) {
            size += child.size
        }
    }
}

fun main() {

    fun createFileHierarchy(fileName: String): Directory {
        val root = Directory("root", null)
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            iterator.next()
            iterator.next()
            var currentNode = root
            while (iterator.hasNext()) {
                val line = iterator.next().split(" ")
                if (line[0] == "$") {
                    if (line[1] == "cd") {
                        currentNode = if (line[2] == "..") {
                            currentNode.calculateSize()
                            currentNode.parent!!
                        } else {
                            currentNode.children.find { child -> child.name == line[2] }!!
                        }
                    }
                } else {
                    if (line[0] == "dir") {
                        currentNode.children.add(Directory(line[1], currentNode))
                    } else {
                        currentNode.size += line[0].toLong()
                    }
                }
            }
            var node: Directory? = currentNode
            while (node != null) {
                node.calculateSize()
                node = node.parent
            }
        }

        return root
    }

    fun part1(node: Directory, limit: Int): Long {
        var result = 0L

        if (node.size <= limit) {
            result += node.size
        }

        for (child in node.children) {
            result += part1(child, limit)
        }

        return result
    }

    fun findSmallestDirectoryWithEnoughSpace(node: Directory, lowerBound: Long, upperBound: Long): Long {
        var result = node.size

        for (child in node.children) {
            if (child.size in lowerBound until upperBound) {
                val childSize = findSmallestDirectoryWithEnoughSpace(child, lowerBound, child.size)
                if (result > childSize) {
                    result = childSize
                }
            }
        }

        return result
    }

    fun part2(node: Directory): Long {
        val neededDiskSpace = node.size - 40000000
        return findSmallestDirectoryWithEnoughSpace(node, neededDiskSpace, node.size)
    }

    val testRoot = createFileHierarchy("Day07_test")
    println("Test")
    println("The directories with a total size of at most 100000 is 95437! Answer: ${part1(testRoot, 100000)}")
    println(
        "The smallest directory that, if deleted, would free up enough space on the" +
                "filesystem to run the update is 24933642! Answer: ${part2(testRoot)}"
    )

    val root = createFileHierarchy("Day07")
    println()
    println("Exercise")
    println("The directories with a total size of at most 100000 is 1513699! Answer: ${part1(root, 100000)}")
    println(
        "The smallest directory that, if deleted, would free up enough space on the" +
                "filesystem to run the update is 7991939! Answer: ${part2(root)}"
    )
}