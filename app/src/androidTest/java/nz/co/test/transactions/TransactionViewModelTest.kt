package nz.co.test.transactions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import nz.co.test.transactions.repositories.TransactionRepository
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.viewmodel.TransactionViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class TransactionViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TransactionRepository
    private lateinit var viewModel: TransactionViewModel

    @Before
    fun setup() {
        viewModel = TransactionViewModel(repository)
    }

    @Test
    fun retrieveTransactions_returnsExpectedData() = runBlocking {
        val transactions = listOf(
            Transaction(1, "2024-06-01", "Test 1",100.0, 0.0),
            Transaction(2, "2024-06-02", "Test 2",0.0, 200.0),
            Transaction(3, "2024-06-03", "Test 3",0.0, 200.0)
        )

        Mockito.`when`(repository.getTransactions()).thenReturn(transactions)

        viewModel.retrieveTransactions()

        assertEquals(3, viewModel.transactions.value!!.size)
        assertEquals("Test 2", viewModel.transactions.value!![0].summary)
    }
}