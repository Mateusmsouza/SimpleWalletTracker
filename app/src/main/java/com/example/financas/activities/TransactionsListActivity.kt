package com.example.financas.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.activities.adapter.TransactionAdapter
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import com.example.financas.activities.view.ResumeView
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class TransactionsListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transactionsList = listOf(
            Transaction(BigDecimal(10), "Comida", TransacionType.OUTCOME),
            Transaction(BigDecimal(10000), "Adiantamento Salário - 40%", TransacionType.INCOME)
        )

        lista_transacoes_listview.adapter = TransactionAdapter(transactionsList, this)
        populateResume(transactionsList)

    }

    private fun populateResume(transactionsList: List<Transaction>) {
        val resumeView = ResumeView(window.decorView, transactionsList)

        resumeView.getIncome()
        resumeView.getOutcome()
        resumeView.getTotal()
    }


}