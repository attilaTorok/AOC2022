/**
 * The shape what the opponent chooses.
 *
 * The value represents how much does the shape worth. The method score calculates the point for the second part of the
 * exercise.
 */
enum class OpponentShape(val value: Int) {
    A(1) {
        override fun score(playerShape: PlayerShape): Int {
            return when (playerShape.name) {
                PlayerShape.X.name -> 3
                PlayerShape.Y.name -> 4
                PlayerShape.Z.name -> 8
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    },
    B(2) {
        override fun score(playerShape: PlayerShape): Int {
            return when (playerShape.name) {
                PlayerShape.X.name -> 1
                PlayerShape.Y.name -> 5
                PlayerShape.Z.name -> 9
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    },
    C(3) {
        override fun score(playerShape: PlayerShape): Int {
            return when (playerShape.name) {
                PlayerShape.X.name -> 2
                PlayerShape.Y.name -> 6
                PlayerShape.Z.name -> 7
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    };

    abstract fun score(playerShape: PlayerShape): Int
}

enum class PlayerShape(val value: Int) {
    X(1) {
        override fun score(opponentShape: OpponentShape): Int {
            return when (opponentShape.name) {
                OpponentShape.A.name -> 4
                OpponentShape.B.name -> 1
                OpponentShape.C.name -> 7
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    },
    Y(2) {
        override fun score(opponentShape: OpponentShape): Int {
            return when (opponentShape.name) {
                OpponentShape.A.name -> 8
                OpponentShape.B.name -> 5
                OpponentShape.C.name -> 2
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    },
    Z(3) {
        override fun score(opponentShape: OpponentShape): Int {
            return when (opponentShape.name) {
                OpponentShape.A.name -> 3
                OpponentShape.B.name -> 9
                OpponentShape.C.name -> 6
                else -> {
                    throw RuntimeException("Wrong value")
                }
            }
        }
    };

    abstract fun score(opponentShape: OpponentShape): Int
}

fun main() {

    fun calculateScore(fileName: String): Int {
        var score = 0
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val split = iterator.next().split(" ")
                score += PlayerShape.valueOf(split[1]).score(OpponentShape.valueOf(split[0]))
            }
        }

        return score
    }

    fun calculateScorePart2(fileName: String): Int {
        var score = 0
        readInputWithStream(fileName).useLines {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val split = iterator.next().split(" ")
                score += OpponentShape.valueOf(split[0]).score(PlayerShape.valueOf(split[1]))
            }
        }

        return score
    }

    println("Test")
    println("The score should be 15! Score: ${calculateScore("Day02_test")}")
    println("The score should be 12! Score: ${calculateScorePart2("Day02_test")}")

    println()
    println("Exercise")
    println("The score should be 13052! Score: ${calculateScore("Day02")}")
    println("The score should be 13693! Score: ${calculateScorePart2("Day02")}")
}