package nz.co.test.transactions.activities
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import nz.co.test.transactions.adapter.TransactionAdapter
import nz.co.test.transactions.databinding.ActivityMainBinding
import nz.co.test.transactions.viewmodel.TransactionViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel
    private lateinit var adapter: TransactionAdapter
    private lateinit var binding: ActivityMainBinding
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[TransactionViewModel::class.java]

        viewModel.transactions.observe(this) { transactions ->
            binding.pBar.visibility = View.GONE
            adapter = TransactionAdapter(transactions) { transaction ->
                val intent = Intent(this, TransactionDetailActivity::class.java)
               intent.putExtra("transaction", transaction)
                startActivity(intent)
            }
            binding.recyclerView.adapter = adapter
        }

        binding.headerTitle.back.visibility = View.GONE

        binding.headerTitle.title.text = buildString {
            append("Transaction")
        }

        viewModel.retrieveTransactions()
    }
}