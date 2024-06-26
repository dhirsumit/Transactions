package nz.co.test.transactions.repositories

import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(private val service: TransactionsService) {

    suspend fun getTransactions(): List<Transaction> {
        return service.retrieveTransactions()
    }
}