package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.ActivityLoginBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.UserContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){
        binding.loginLogInBtn.setOnClickListener {
            val username = binding.loginUsernameEt.text.toString()
            val pinString = binding.loginPinEt.text.toString()

            if(username.isEmpty() || pinString.isEmpty()){
                Toast.makeText(this, "Wrong username or password! Please try again", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pinString)

            if(userViewModel.login(username, pin)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}