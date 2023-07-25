package com.example.loginanimation.data

import java.util.Random

class Repository {
    @kotlin.jvm.Throws
    fun login(email: String, password: String){
        if(Random().nextBoolean())
            throw Exception("Login failed")
    }
}