package io.jrb.common.test

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomUtils
import java.util.stream.IntStream

interface Testable {

    fun <T> buildList(maxSize: Int, supplier: (Int) -> T): List<T> {
        val size: Int = RandomUtils.nextInt(1, maxSize)
        val builder: ImmutableList.Builder<T> = ImmutableList.builder()
        IntStream.range(1, size + 1).forEach { i: Int -> builder.add(supplier.invoke(i)) }
        return builder.build()
    }

    fun <K, V> buildMap(
            maxSize: Int,
            keySupplier: (Int) -> K,
            valueSupplier: (Int) -> V
    ): Map<K, V> {
        val size: Int = RandomUtils.nextInt(1, maxSize)
        val builder: ImmutableMap.Builder<K, V> = ImmutableMap.builder()
        IntStream.range(1, size + 1)
                .forEach { i: Int -> builder.put(keySupplier.invoke(i), valueSupplier.invoke(i)) }
        return builder.build()
    }

    fun randomStringList(maxSize: Int): List<String> {
        return buildList(maxSize) { i -> randomAlphabetic(10, 25) }
    }

}
