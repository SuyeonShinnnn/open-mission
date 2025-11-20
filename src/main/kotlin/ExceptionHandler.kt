package org.example

    object ExceptionHandler {
        fun handleException(e: Throwable) {
            when(e) {
                is NumberFormatException -> println("\n[ERROR] ${ErrorMessage.INVALID_INPUT.message}\n");
                is IllegalArgumentException -> println("\n[ERROR] ${e.message}\n" )
                else -> println("\n[ERROR] ${ErrorMessage.UNEXPECTED_ERROR.message}\n")
            }
        }
    }