package app.ripenow.android.login

import android.content.Intent
import androidx.fragment.app.Fragment
import app.ripenow.android.R
import com.firebase.ui.auth.AuthUI


class FirebaseAuthentication : Authenticable {

    private val providers by lazy {
        arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
        )
    }

    override fun startAuthentication(fragment: Fragment) {
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.ic_ripe_now)
            .setTheme(R.style.AppTheme)
            .build()
        fragment.startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onAuthenticationResult(resultCode: Int, data: Intent?, listener: (Boolean) -> Unit) {
        listener(true)
    }

}