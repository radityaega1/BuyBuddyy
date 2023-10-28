package org.d3if4502.buybuddy.Ui.login

import androidx.lifecycle.ViewModel
import org.d3if4502.buybuddy.liveData.FirebaseUserLiveData

class LoginViewModel : ViewModel() {
    val authState = FirebaseUserLiveData()
}