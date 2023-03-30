package travelator.money

import java.math.BigDecimal
import java.util.*
import java.util.function.Function
import java.util.stream.Collector
import java.util.stream.Collectors

data class Money
private constructor(
    val amount: BigDecimal,
    val currency: Currency
) {

    override fun toString(): String {
        return amount.toString() + " " + currency.currencyCode
    }

    fun add(that: Money): Money {
        require(currency == that.currency) { "cannot add Money values of different currencies" }
        return of(amount.add(that.amount), currency)
    }
    companion object {
        @JvmStatic
        fun of(amountStr: String?, currency: Currency): Money {
            return of(BigDecimal(amountStr), currency)
        }

        @JvmStatic
        fun of(amount: Int, currency: Currency): Money {
            return of(BigDecimal(amount), currency)
        }

        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency): Money {
            return Money(
                amount.setScale(currency.defaultFractionDigits),
                currency
            )
        }

        @JvmStatic
        fun zero(userCurrency: Currency): Money {
            return of(BigDecimal.ZERO, userCurrency)
        }

        @JvmStatic
        fun sumByCurrency(): Collector<Money, *, Map<Currency, Money>> {
            return Collectors.toMap(
                { obj: Money -> obj.currency },
                Function.identity()
            ) { obj: Money, that: Money -> obj.add(that) }
        }
    }
}
