package edu.mirea.remsely.anylogic.practice11.oilcompanyrevenue.dto

data class CreateRequest(
    val scenarioNumber: Int,
    val drillingRate: Double,
    val oilPrice: Double,
    val exchangeRate: Double,
)
