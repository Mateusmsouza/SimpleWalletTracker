package com.example.financas.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.financas.R
import com.example.financas.activities.Extension.formatBrazilianCurrency
import com.example.financas.activities.Extension.formatToBrazilianFormat
import com.example.financas.activities.Extension.sliceIfHasMoreSizeThan
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransactionAdapter(transactions: List<Transaction>,
                         context: Context
) : BaseAdapter() {

    private val transactions = transactions
    private val context = context
    private val maxLengthTransactionDescription = 14;

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCreated = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transaction: Transaction = transactions[position]

        applyColor(transaction, viewCreated)
        populateViewInformation(transaction, viewCreated)

        return viewCreated
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactions.count()
    }

    private fun applyColor(transaction: Transaction,
                           viewCreated: View){
        var colorNumber = 0
        var iconNumber = 0

        if (transaction.type == TransacionType.INCOME){
            colorNumber = ContextCompat.getColor(context, R.color.receita)
            iconNumber = R.drawable.icone_transacao_item_receita
        }
        else{
            colorNumber = ContextCompat.getColor(context, R.color.despesa)
            iconNumber = R.drawable.icone_transacao_item_despesa
        }

        viewCreated.transacao_icone.setBackgroundResource(iconNumber)
        viewCreated.transacao_valor.setTextColor(colorNumber)
    }

    private fun populateViewInformation(transaction: Transaction,
                                        viewCreated: View){
        viewCreated.transacao_valor.text = transaction.valor.formatBrazilianCurrency()
        viewCreated.transacao_categoria.text = transaction.category.sliceIfHasMoreSizeThan(maxLengthTransactionDescription)
        viewCreated.transacao_data.text = transaction.data.formatToBrazilianFormat()
    }

}