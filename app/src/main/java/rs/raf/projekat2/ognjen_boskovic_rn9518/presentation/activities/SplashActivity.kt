package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.ActivitySplashBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.UserContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.UserViewModel

class SplashActivity : AppCompatActivity() {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (userViewModel.isLoggedIn()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        finish()

    }
}