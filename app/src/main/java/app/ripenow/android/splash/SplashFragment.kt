package app.ripenow.android.splash


import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import app.ripenow.android.BuildConfig
import app.ripenow.android.R
import app.ripenow.android.login.Authenticable
import app.ripenow.android.login.FirebaseAuthentication
import app.ripenow.android.login.RC_SIGN_IN
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Splash [Fragment] subclass to welcome to the app.
 *
 */
class SplashFragment : Fragment(), Authenticable by FirebaseAuthentication()  {

    companion object {
        const val DELAY = 5000L
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startDelay(DELAY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtVersion.text = BuildConfig.VERSION_NAME
        backgroundAnimation()
    }

    private fun startDelay(delay: Long) {
        GlobalScope.launch {
            delay(delay)
            startAuthentication(this@SplashFragment)
        }
    }

    private fun backgroundAnimation() {
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(500)
        animationDrawable.setExitFadeDuration(500)
        animationDrawable.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            onAuthenticationResult(resultCode, data) {
                layout.findNavController().navigate(R.id.action_splashFragment_to_storeListFragment)
            }
        }
    }

}
