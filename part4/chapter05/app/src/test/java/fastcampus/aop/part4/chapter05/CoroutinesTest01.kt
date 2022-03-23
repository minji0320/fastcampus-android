package fastcampus.aop.part4.chapter05

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

class CoroutinesTest01 {
    @Test
    fun test01() = runBlocking {
        val time = measureTimeMillis {
            val firstName = getFirstName()
            val lastName = getLastName()
            println("Hello, $firstName $lastName")
        }
        println("measure time : $time")
    }

    @Test
    fun test02() = runBlocking {
        val time = measureTimeMillis {
            val firstName = async { getFirstName() }
            val lastName = async { getLastName() }
            println("Hello, ${firstName.await()} ${lastName.await()}")
        }
        println("measure time : $time")
    }

    suspend fun getFirstName(): String {
        delay(1000)
        return "민지"
    }

    suspend fun getLastName(): String {
        delay(1000)
        return "김"
    }
}