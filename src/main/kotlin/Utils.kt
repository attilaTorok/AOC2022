import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

val dummy = object {}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

fun readInputWithStream(name: String) = File(dummy.javaClass.getResource("$name.txt")!!.toURI()).bufferedReader()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
