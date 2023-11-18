package com.example.pincodepage

fun StringBuilder.deleteLast() {
    if (this.isEmpty()) {
        return
    }
    this.deleteAt(this.length - 1)
}