package com.example.loginanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.loginanimation.data.Repository
import com.example.loginanimation.utils.LoginAnimationAttributeName
import com.example.loginanimation.utils.LoginAnimationState
import com.example.loginanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository = Repository()

        binding.email.doOnTextChanged { _, _, _, count ->
            handleAnimationState(
                LoginAnimationState.NumberState(
                    name = LoginAnimationAttributeName.numLook.name, value = count.toFloat()
                )
            )
        }

        binding.email.setOnFocusChangeListener { _, isFocus ->
            handleAnimationState(
                LoginAnimationState.BooleanState(
                    name = LoginAnimationAttributeName.isChecking.name, state = isFocus
                )
            )
        }

        binding.password.setOnFocusChangeListener { _, isFocus ->
            handleAnimationState(
                LoginAnimationState.BooleanState(
                    name = LoginAnimationAttributeName.isHandsUp.name, state = isFocus
                )
            )

        }

        binding.loginWithEmailButton.setOnClickListener {
            try {
                repository.login(
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString()
                )
                handleAnimationState(
                    LoginAnimationState.TriggerState(
                        LoginAnimationAttributeName.trigSuccess.name
                    )
                )
            } catch (e: Exception) {
                handleAnimationState(
                    LoginAnimationState.TriggerState(
                        LoginAnimationAttributeName.trigFail.name
                    )
                )
            }
        }
    }

    private fun handleAnimationState(loginAnimationState: LoginAnimationState) {
        val stateMachineName = "Login Machine"
        val controller = binding.loginAnimation.controller

        when (loginAnimationState) {
            is LoginAnimationState.BooleanState -> controller.setBooleanState(
                stateMachineName, loginAnimationState.name, loginAnimationState.state
            )

            is LoginAnimationState.NumberState -> controller.setNumberState(
                stateMachineName, loginAnimationState.name, loginAnimationState.value
            )

            is LoginAnimationState.TriggerState -> controller.fireState(
                stateMachineName,
                loginAnimationState.name
            )
        }

    }

}