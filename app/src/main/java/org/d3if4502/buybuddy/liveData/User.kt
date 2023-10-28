package org.d3if4502.buybuddy.liveData

import androidx.annotation.Keep

@Keep
data class User(
    val email:    String?= "",
    val fullname: String?= "",
    val password: String?= "",
    val username: String?= "",
    val wimage: String?= ""

)