package nz.co.test.transactions.services

import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.math.BigDecimal
import java.time.OffsetDateTime

@JsonClass(generateAdapter = true)
data class Transaction(
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debit: Double,
    val credit: Double
): Serializable