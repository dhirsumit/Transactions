package nz.co.test.transactions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.databinding.ItemTransactionBinding
import nz.co.test.transactions.services.Transaction

class TransactionAdapter(
    private val transactions: List<Transaction>,
    private val clickListener: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions[position], clickListener)
    }

    override fun getItemCount(): Int = transactions.size

    class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction, clickListener: (Transaction) -> Unit) {
            binding.detailSummery.text = buildString {
                append("Summary: ")
                append(transaction.summary)
            }
            transaction.debit.toString().also { binding.detailAmount.text =
                buildString {
                    append("Debit: ")
                    append(it)
                } }
            binding.root.setOnClickListener { clickListener(transaction) }
        }
    }
}