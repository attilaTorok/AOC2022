fun main() {

    fun isVisibleFromUp(x: Int, y: Int, grid: MutableList<List<String>>): Boolean {
        val pivot = grid[x][y]
        var x1 = x
        while (x1 > 0) {
            if (pivot <= grid[--x1][y]) {
                return false
            }
        }

        return true
    }

    fun isVisibleFromBottom(x: Int, y: Int, grid: MutableList<List<String>>): Boolean {
        val pivot = grid[x][y]
        var x1 = x
        while (x1 < grid.size - 1) {
            if (pivot <= grid[++x1][y]) {
                return false
            }
        }

        return true
    }

    fun isVisibleFromLeft(x: Int, y: Int, grid: MutableList<List<String>>): Boolean {
        val pivot = grid[x][y]
        var y1 = y
        while (y1 > 0) {
            if (pivot <= grid[x][--y1]) {
                return false
            }
        }

        return true
    }

    fun isVisibleFromRight(x: Int, y: Int, grid: MutableList<List<String>>): Boolean {
        val pivot = grid[x][y]
        var y1 = y
        while (y1 < grid.size - 1) {
            if (pivot <= grid[x][++y1]) {
                return false
            }
        }

        return true
    }

    fun isVisible(x: Int, y: Int, grid: MutableList<List<String>>): Boolean {
        return !(!isVisibleFromUp(x , y, grid)
                && !isVisibleFromBottom(x , y, grid)
                && !isVisibleFromLeft(x , y, grid)
                && !isVisibleFromRight(x , y, grid))
    }

    fun scoreUpwards(x: Int, y: Int, grid: MutableList<List<String>>): Int {
        val pivot = grid[x][y]
        var x1 = x
        var score = 0
        while (x1 > 0) {
            score++
            if (pivot <= grid[--x1][y]) {
                break
            }
        }

        return score
    }

    fun scoreDownwards(x: Int, y: Int, grid: MutableList<List<String>>): Int {
        val pivot = grid[x][y]
        var x1 = x
        var score = 0
        while (x1 < grid.size - 1) {
            score++
            if (pivot <= grid[++x1][y]) {
                break
            }
        }

        return score
    }

    fun scoreLeftwards(x: Int, y: Int, grid: MutableList<List<String>>): Int {
        val pivot = grid[x][y]
        var y1 = y
        var score = 0
        while (y1 > 0) {
            score++
            if (pivot <= grid[x][--y1]) {
                break
            }
        }

        return score
    }

    fun scoreRightwards(x: Int, y: Int, grid: MutableList<List<String>>): Int {
        val pivot = grid[x][y]
        var y1 = y
        var score = 0
        while (y1 < grid.size - 1) {
            score++
            if (pivot <= grid[x][++y1]) {
                break
            }
        }

        return score
    }

    fun numberOfVisibleTrees(grid: MutableList<List<String>>): Int {
        var count = grid[0].size * 2 + (grid.size - 2) * 2
        for (x in 1 until grid[0].size - 1) {
            for (y in 1 until grid.size - 1) {
                if (isVisible(x , y, grid)) {
                    count++
                }
            }
        }

        return count
    }

    fun calculateCurrentScore(x: Int, y: Int, grid: MutableList<List<String>>): Int {
        return scoreUpwards(x, y, grid) * scoreDownwards(x, y, grid) * scoreLeftwards(x, y, grid) * scoreRightwards(x, y, grid)
    }

    fun getHighestScenicScore(grid: MutableList<List<String>>): Int {
        var highScore = 0
        for (x in 1 until grid[0].size - 1) {
            for (y in 1 until grid.size - 1) {
                val current = calculateCurrentScore(x, y, grid)
                if (current > highScore) {
                    highScore = current
                }
            }
        }

        return highScore
    }

    fun getGrid(fileName: String): MutableList<List<String>> {
        val grid: MutableList<List<String>> = mutableListOf()
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val line = iterator.next()
                val a = line.split("").subList(1, line.length + 1)
                grid.add(a)
            }
        }

        return grid
    }

    val testGrid = getGrid("Day08_test")
    println("Test")
    println("The number of visible trees is 21! Answer: ${numberOfVisibleTrees(testGrid)}")
    println("Highest scenic score is 8! Answer: ${getHighestScenicScore(testGrid)}")

    val grid = getGrid("Day08")
    println()
    println("Exercise")
    println("The number of visible trees is 1560! Answer: ${numberOfVisibleTrees(grid)}")
    println("Highest scenic score is 252000! Answer: ${getHighestScenicScore(grid)}")
}