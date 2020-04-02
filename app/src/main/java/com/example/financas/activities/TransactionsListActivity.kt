package com.example.financas.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.activities.adapter.TransactionAdapter
import com.example.financas.activities.delegate.ITransactionDelegate
import com.example.financas.activities.dialog.CreateTransaction
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import com.example.financas.activities.view.ResumeView
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class TransactionsListActivity: AppCompatActivity() {

        private val transactionsList: MutableList<Transaction> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        lista_transacoes_listview.adapter = TransactionAdapter(transactionsList, this)
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                CreateTransaction(window.decorView as ViewGroup, this)
                    .configureDialog(TransacionType.INCOME, object : ITransactionDelegate {
                        override fun delegate(transaction: Transaction){
                            addTransaction(transaction)
                            lista_transacoes_adiciona_menu.close((true))

                        }
                    })
            }


        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                CreateTransaction(window.decorView as ViewGroup, this)
                    .configureDialog(TransacionType.OUTCOME, object : ITransactionDelegate {
                        override fun delegate(transaction: Transaction){
                            addTransaction(transaction)
                            lista_transacoes_adiciona_menu.close((true))

                        }
                    })
            }

    }

    private fun addTransaction(transaction: Transaction){
        transactionsList.add(transaction)
        populateResume()
    }

    private fun populateResume() {
        val resumeView = ResumeView(this, window.decorView, transactionsList)

        resumeView.getIncome()
        resumeView.getOutcome()
        resumeView.getTotal()
    }


}