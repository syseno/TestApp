package com.test.myapplication.networks.exceptions

import java.io.IOException

class NoInternetException : IOException() {

    override val message: String
        get() = "No Internet Connection"
}