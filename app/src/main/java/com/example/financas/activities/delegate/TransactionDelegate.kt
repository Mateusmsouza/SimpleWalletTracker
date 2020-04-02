package com.example.financas.activities.delegate

import com.example.financas.activities.model.Transaction

interface ITransactionDelegate {
    fun delegate(transaction: Transaction)
}
