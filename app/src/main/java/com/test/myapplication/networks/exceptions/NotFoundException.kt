package com.test.myapplication.networks.exceptions

import java.io.IOException

class NotFoundException : IOException() {

    override val message: String
        get() = "Not Found"
}