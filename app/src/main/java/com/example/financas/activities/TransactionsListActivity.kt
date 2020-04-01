package com.example.financas.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.activities.Extension.formatBrazilianCurrency
import com.example.financas.activities.adapter.TransactionAdapter
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.activity_lista_transacoes.view.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.util.Calendar

class TransactionsListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transactionsList = listOf(
            Transaction(BigDecimal(10), "Comida", TransacionType.OUTCOME),
            Transaction(BigDecimal(10000), "Adiantamento Salário - 40%", TransacionType.INCOME)
        )

        lista_transacoes_listview.adapter = TransactionAdapter(transactionsList, this)

        resumo_card_receita.text = calculateIncome(transactionsList).formatBrazilianCurrency()
    }

    private fun calculateIncome(transactions: List<Transaction>): BigDecimal{
        var totalIncome = BigDecimal.ZERO

        for (transaction in transactions){
            if (transaction.type == TransacionType.INCOME){
                totalIncome = totalIncome.plus(transaction.valor)
            }
        }

        return totalIncome
    }
}