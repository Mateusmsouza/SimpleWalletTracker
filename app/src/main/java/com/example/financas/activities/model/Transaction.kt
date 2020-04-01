package com.example.financas.activities.model

import java.math.BigDecimal
import java.util.Calendar

class Transaction(val valor: BigDecimal,
                  val category: String,
                  val type: TransacionType,
                  val  data: Calendar = Calendar.getInstance()) {

}