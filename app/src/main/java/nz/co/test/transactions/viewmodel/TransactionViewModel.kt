package nz.co.test.transactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.repositories.TransactionRepository
import nz.co.test.transactions.services.Transaction
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> = _transactions

    fun retrieveTransactions() {
        viewModelScope.launch {
            val result = repository.getTransactions().sortedBy { it.transactionDate }
            _transactions.postValue(result)
        }
    }
}