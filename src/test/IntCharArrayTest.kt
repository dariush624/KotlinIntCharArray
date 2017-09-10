package test

import org.dariushmoshiri.intchararray.IntCharArray
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by MillenniumBug on 01/07/2017.
 */
class IntCharArrayTest {

    @Test
    fun testSize() {
        val array: IntCharArray = IntCharArray(10)
        Assert.assertEquals(array.size, 10)
    }

    @Test
    fun testSetAndGet() {
        val array: IntCharArray = IntCharArray(10)
        array[0] = 5 to 9
        array[9] = 8 to 6
        array[3] = 255 to 45
        Assert.assertEquals(array[0].first, 5)
        Assert.assertEquals(array[3].first, 255)
        Assert.assertEquals(array[9].second, 6)
    }

    @Test
    fun testForEach() {
        val array: IntCharArray = IntCharArray(10)
        array[1] = 50 to 254
        array[5] = 8 to 60
        array[6] = 255 to 255
        var i = 0
        for(tuple: Pair<Int, Int> in array) {
            if(i == 1)
                Assert.assertEquals(tuple.second, 254)
            else if(i == 5)
                Assert.assertEquals(tuple.first, 8)
            else if(i == 6)
                Assert.assertEquals(tuple.second, 255)
            else
                println(tuple)
            ++i
        }
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun testSetArgumentsPositive() {
        val array: IntCharArray = IntCharArray(10)
        array[1] = 50 to 256
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun testSetArgumentsNegative() {
        val array: IntCharArray = IntCharArray(10)
        array[1] = -50 to 255
    }


}