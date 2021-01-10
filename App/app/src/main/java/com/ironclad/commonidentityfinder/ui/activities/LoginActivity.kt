package com.ironclad.commonidentityfinder.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ironclad.commonidentityfinder.R
import com.ironclad.commonidentityfinder.databinding.ActivityLoginBinding
import com.ironclad.commonidentityfinder.utils.Constants.Companion.LOGIN_DELAY
import com.ironclad.commonidentityfinder.utils.Constants.Companion.SCREEN_TAG
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(R.drawable.splash_image).into(binding.ivSplash)

        auth = Firebase.auth

        if (currentUser != null) {
            val directIntent = Intent(this, HomeActivity::class.java)
            Log.d(SCREEN_TAG, "Going to Home")
            startActivity(directIntent)
        }

        binding.btnLogin.setOnClickListener {
            val email = etLogin.editText?.text.toString()
            val password = etPass.editText?.text.toString()

            if (email.isEmpty() or password.isEmpty()) {
                Toast.makeText(this, "Enter email or password", Toast.LENGTH_SHORT).show()
            } else {
                checkEmail(email, password)
            }
        }
    }

    private fun checkEmail(email: String, password: String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener {
                if (it.result?.signInMethods?.isEmpty()!!) {
                    Log.d("Ishaan", "Going for signup")
                    signup(email, password)
                } else {
                    Log.d("Ishaan", "Going for login")
                    login(email, password)
                }
            }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    val loginIntent = Intent(this, HomeActivity::class.java)
                    startActivity(loginIntent)
                }, LOGIN_DELAY)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    val loginIntent = Intent(this, HomeActivity::class.java)
                    startActivity(loginIntent)
                }, LOGIN_DELAY)
            }.addOnFailureListener {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}