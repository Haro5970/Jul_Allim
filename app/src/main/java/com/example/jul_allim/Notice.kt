package com.example.jul_allim

data class Notice(val id: String,val lines: String) {
    override fun toString(): String {
        return "=[${id}:${lines}]="
    }
}