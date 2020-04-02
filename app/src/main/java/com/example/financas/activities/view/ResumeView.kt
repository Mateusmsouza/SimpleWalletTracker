package com.example.financas.activities.view

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.financas.R
import com.example.financas.activities.extension.formatBrazilianCurrency
import com.example.financas.activities.model.Resume
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumeView (private val context: Context,
                  private val view: View,
                  transactions: List<Transaction>){

    private val resumeModel: Resume = Resume(transactions = transactions)

    fun getIncome(){
        view.resumo_card_receita.setTextColor(ContextCompat.getColor(context, R.color.receita))
        view.resumo_card_receita.text = resumeModel.calculateIncome().formatBrazilianCurrency()
    }

    fun getOutcome(){
        view.resumo_card_despesa.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        view.resumo_card_despesa.text = resumeModel.calculateOutcome().formatBrazilianCurrency()
    }

    fun getTotal(){
        val total : BigDecimal = resumeModel.calculateTotal()
        val colorId: Int = if(total >= BigDecimal.ZERO){
            R.color.receita
        }else{
            R.color.despesa
        }

        view.resumo_card_total.setTextColor(ContextCompat.getColor(context, colorId))
        view.resumo_card_total.text = total.formatBrazilianCurrency()
    }
}