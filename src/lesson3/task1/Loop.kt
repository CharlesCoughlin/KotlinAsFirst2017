@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1
import lesson1.task1.sqr
import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var i = n
    while (i != 0) {
        count ++
        i /= 10
    }
    return if (count == 0) 1 else count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var n1 = 1
    var n2 = 1
    var numb = 1
    for (i in 3..n) {
        numb = n1 + n2
        val s = n2
        n2 += n1
        n1 = s
    }
    return numb
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int , n: Int): Int {
    val c = m * n
    var a = m
    var b = n
    while ((a != 0) && (b != 0)) {
        if (a > b) a %= b else b %= a
    }
    return c / (a + b)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var min = 0
    for (i in 2..n) {
        if ((n % i) == 0) {
            min = i
            break
        }
    }
    return min
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var max = 0
    for (i in (n - 1)..1) {
        if ((n % i) == 0) {
            max = i
            break
        }
    }
    return max
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun gcd(a: Int, b: Int): Int {
    var newA = a
    var newB = b
    while (max(newA, newB) % min(newA, newB) != 0) {
        when {
            newA > newB -> newA %= newB
            else -> newB %= newA
        }
    }
    return min(newA, newB)
}

fun isCoPrime(m: Int, n: Int): Boolean = gcd(m,n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int , n: Int): Boolean {
    var a: Int = Math.sqrt(m.toDouble()).toInt()
    if (a * a < m) a ++
    return a <= Math.sqrt(n.toDouble())
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var sin = x % (2 * Math.PI)
    var sinx = sin
    var count = 1
    val sin2 = sin
    while (Math.abs(sinx) >= eps) {
        sinx = (-sinx * sin2) / (((count * 2 + 1) * (count * 2)).toDouble() * sin2)
        count += 1
        sin += sinx
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var count = 1
    var s = 1.0
    var cos = 1.0
    val const = x % (2 * Math.PI)
    while (Math.abs(s) >= eps) {
        s = (s * const) / (((count * 2 - 1) * (count * 2)).toDouble() * const * (-1))
        count += 1
        cos += s
    }
    return  cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var numb = n
    var revert = 0
    while (numb >= 1) {
        revert = (numb % 10) + (revert * 10)
        numb /= 10
    }
    return revert
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var count = digitNumber(n) - 1
    val s = n.toString()
    for (i in 0..count) when {
        s[i] != s[count] -> return false
        else -> count -= 1
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var s = n
    val count = digitNumber(n)
    val c = n % 10
    for (i in 1..count) {
        val a = s % 10
        s /= when {
            a != c -> return true
            else -> 10
        }
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var numb = 0
    var s = 0
    var i = 0.0
    while (n > s) {
        i++
        numb = sqr(i).toInt()
        s += digitNumber(numb)
    }
    if (n == s) return numb % 10
    for (j in 1..s - n) numb /= 10
    return numb % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var numb = 0
    var i = 0
    var l = 1
    var k = 1
    var s = 0
    while (n > s) {
        i++
        when {
            i < 3 -> numb = 1
            else -> {
                numb = l + k
                val c = k
                k += l
                l = c
            }
        }
        s += digitNumber(numb)
    }
    return when (n) {
        s -> numb % 10
        else -> {
            for (j in 1..s - n) numb /= 10
            numb % 10
        }
    }
}
