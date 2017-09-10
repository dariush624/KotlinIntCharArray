package org.dariushmoshiri.intchararray

/**
 * IntCharArray represents an array of int pairs each one stored in a char.
 * Due to char size (16 bit) each int of the pair must be between 0 and 255 (8 bit each)
 * @author Dariush Moshiri
 */
open class IntCharArray(val size: Int) : Iterable<Pair<Int, Int>> {

    private val delegate: CharArray = CharArray(size)

    private fun encode(tuple : Pair<Int, Int>) : Char {
        return (tuple.first.shl(8)).or(tuple.second).toChar()
    }

    private fun decode(element : Char) : Pair<Int, Int> {
        val first: Int = element.toInt().shr(8)
        val second: Int = element.toInt().xor((element.toInt().shr(8)).shl(8))
        return Pair(first, second)
    }

    open operator fun get(index: Int): Pair<Int, Int> {
        if(index >= size || index < 0)
            throw IllegalArgumentException("Index must be between zero and the size of IntCharArray")
        return decode(delegate[index])
    }

    open operator fun set(index: Int, tuple: Pair<Int, Int>) {
        if(index >= size || index < 0)
            throw IllegalArgumentException("Index must be between zero and the size of IntCharArray")
        if((tuple.first !in 0..255) || (tuple.second !in 0..255))
            throw IllegalArgumentException("First and second number must be between 0 and 255")
        delegate[index] = encode(tuple)
    }

    override fun iterator(): Iterator<Pair<Int, Int>> {
        return object : Iterator<Pair<Int, Int>> {

            private var current: Int = 0

            override fun hasNext(): Boolean {
                return current < size
            }

            override fun next(): Pair<Int, Int> {
                return decode(delegate[current++])
            }
        }
    }

}

