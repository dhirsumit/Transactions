package nz.co.test.transactions.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nz.co.test.transactions.databinding.ActivityTransactionDetailBinding
import nz.co.test.transactions.services.Transaction
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Suppress("DEPRECATION")
class TransactionDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("transaction", Transaction::class.java)
        } else {
            intent.getSerializableExtra("transaction") as Transaction
        }

        binding.headerTitle.title.text = buildString {
        append("Transaction Detail")
    }

        binding.headerTitle.back.setOnClickListener {
          finish()
        }

        transaction?.let { it ->
            binding.detailSummery.text = buildString {
                append("Summary: ")
                append(it.summary)
            }
            it.debit.toString().also { binding.detailDebit.text = buildString {
                append("Debit: ")
                append(it)
            } }
            it.credit.toString().also { binding.detailCredit.text = buildString {
                append("Credit: ")
                append(it)
            } }
            binding.detailTransactionDate.text = buildString {
                append("Date Time: ")
                append(changeFormat(it.transactionDate))
            }

            if(it.credit != 0.0) {
                binding.detailCreditGst.text = buildString {
                    append("15% GST of credit is: ")
                    append(calculateActualCost(it.credit).toString())
                }
            }

            if(it.debit != 0.0) {
                binding.detailDebitGst.text = buildString {
                    append("15% GST of debit is: ")
                    append(calculateActualCost(it.debit).toString())
                }
            }
        }
    }

    private fun calculateActualCost(value: Double) :Double {
        val tax = Math.round((value * 15) / 100).toDouble()
        return (tax)
    }

    private fun changeFormat(time: String): String {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
        val outputPattern = "dd-MMM-yyyy h:mm a"
        val inputFormat = SimpleDateFormat(inputPattern,Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern,Locale.getDefault())

        val date: Date
        var str = ""

        try {
            date = inputFormat.parse(time)!!
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}