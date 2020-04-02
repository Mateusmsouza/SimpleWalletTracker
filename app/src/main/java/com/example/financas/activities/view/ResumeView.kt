package com.example.financas.activities.view

import android.view.View
import com.example.financas.activities.extension.formatBrazilianCurrency
import com.example.financas.activities.model.Resume
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumeView (private val view: View,
                  transactions: List<Transaction>){

    private val resumeModel: Resume = Resume(transactions = transactions)

    fun getIncome(){
        view.resumo_card_receita.text = resumeModel.calculateIncome().formatBrazilianCurrency()
    }

    fun getOutcome(){
        view.resumo_card_despesa.text = resumeModel.calculateOutcome().formatBrazilianCurrency()
    }

    fun getTotal(){
        view.resumo_card_total.text = resumeModel.calculateTotal().formatBrazilianCurrency()
    }
}