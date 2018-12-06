package app.ripenow.android.login

import android.content.Intent
import android.support.v4.app.Fragment

const val RC_SIGN_IN = 1001

interface Authenticable {

    fun startAuthentication(fragment: Fragment)
    fun onAuthenticationResult(resultCode: Int, data: Intent?, listener: (Boolean) -> Unit)

}