package com.example.loginanimation.utils

sealed class LoginAnimationState {
   class BooleanState (val name : String , val state: Boolean) : LoginAnimationState()
   class NumberState (val name : String, val value: Float) : LoginAnimationState()
   class TriggerState (val name : String) : LoginAnimationState()
}

