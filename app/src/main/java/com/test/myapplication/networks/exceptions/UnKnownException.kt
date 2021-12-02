package com.test.myapplication.networks.exceptions

import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String
        get() = "Some Unknown Error Occurred"
}