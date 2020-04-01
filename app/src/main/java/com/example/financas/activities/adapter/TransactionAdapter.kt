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
    private val max_length_category = 14;

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transaction: Transaction = transactions[position]

        if (transaction.type == TransacionType.INCOME){
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
            viewCriada.transacao_valor
                .setTextColor(ContextCompat.getColor(context, R.color.receita))
        }
        else{
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
            viewCriada.transacao_valor
                .setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }
        viewCriada.transacao_valor.text = transaction.valor.formatBrazilianCurrency()
        viewCriada.transacao_categoria.text = transaction.category.sliceIfHasMoreSizeThan(max_length_category)
        viewCriada.transacao_data.text = transaction.data.formatToBrazilianFormat()
        return viewCriada
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

}