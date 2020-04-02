package com.example.financas.activities.model

import com.example.financas.activities.extension.formatBrazilianCurrency
import java.math.BigDecimal

class Resume (private val transactions: List<Transaction>){

    fun calculateIncome() : BigDecimal {
        var totalIncome = BigDecimal.ZERO

        for (transaction in transactions) {
            if (transaction.type == TransacionType.INCOME) {
                totalIncome = totalIncome.plus(transaction.valor)
            }
        }

        return totalIncome
    }

    fun calculateOutcome() : BigDecimal {
        var totalOutcome = BigDecimal.ZERO

        for (transaction in transactions) {
            if (transaction.type == TransacionType.OUTCOME) {
                totalOutcome = totalOutcome.plus(transaction.valor)
            }
        }

        return totalOutcome
    }

    fun calculateTotal(): BigDecimal {
        return calculateIncome().subtract(calculateOutcome())
    }
}