package travelator

data class EmailAddress(
    val localPart: String,
    val domain: String
) {

    override fun toString() = "$localPart@$domain"

}

fun String.isValidEmail(): Boolean = IntRange(1, length - 2).contains(lastIndexOf('@'))

fun parse(value: String): EmailAddress = emailAddress(value, value.lastIndexOf('@'))

private fun emailAddress(value: String, atIndex: Int): EmailAddress =
    when {
        value.isValidEmail() -> EmailAddress(value.substring(0, atIndex), value.substring(atIndex + 1))
        else -> throw IllegalArgumentException("EmailAddress must be two parts separated by @")
    }