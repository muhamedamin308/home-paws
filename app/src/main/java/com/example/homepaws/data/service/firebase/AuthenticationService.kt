package com.example.homepaws.data.service.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class AuthenticationService(
    private val auth: FirebaseAuth,
) {
    val isLoggedIn: Boolean
        get() = auth.currentUser != null

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { it.user?.let { onSuccess(it) } }
            .addOnFailureListener { onFailure(it) }
    }

    fun signupWithEmailAndPassword(
        name: String,
        email: String,
        password: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result?.user
                    user?.let {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user.updateProfile(profileUpdates)
                            .addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful)
                                    onSuccess(it)
                                else
                                    onFailure(
                                        updateTask.exception
                                            ?: Exception("Failed to update profile")
                                    )
                            }
                    } ?: onFailure(Exception("User is null"))
                } else
                    onFailure(it.exception ?: Exception("Unknown error"))
            }
    }

    fun fetchUserProfile(): FirebaseUser =
        if (isLoggedIn) auth.currentUser!! else throw IllegalStateException("User not logged in")

    fun signOut() {
        auth.signOut()
    }

}