package travelator

import java.util.Comparator.comparing
import java.util.Comparator.comparingDouble
import java.util.stream.Collectors
import java.util.stream.Stream

fun <T> Iterable<T>.sorted(ordering: Comparator<in T>): List<T> = sortedWith(ordering)

fun <T> withoutItemAt(shortlist: List<T>, index: Int): List<T> {
    return Stream.concat(
        shortlist.stream().limit(index.toLong()),
        shortlist.stream().skip((index + 1).toLong())
    ).collect(Collectors.toUnmodifiableList())
}

fun byPriceLowToHigh(): Comparator<HasPrice> = comparing(HasPrice::price)

fun byRating(): Comparator<HasRating> = compareByDescending(HasRating::rating)


fun <T> byValue(): Comparator<T> where T : HasPrice?, T : HasRating? =
    comparingDouble { t: T -> t!!.rating / t.price }.reversed()

fun byRelevance(): Comparator<HasRelevance> = compareByDescending(HasRelevance::relevance)
