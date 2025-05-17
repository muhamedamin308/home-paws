package com.example.homepaws.data.service.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class AuthenticationService(
    private val auth: FirebaseAuth,
) {
    val currentUser: FirebaseUser?
        get() = auth.currentUser

    val isLoggedIn: Boolean
        get() = currentUser != null

    suspend fun signIn(email: String, password: String): Result<FirebaseUser> = try {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        result.user?.let { Result.success(it) }
            ?: Result.failure(Exception("User not found"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun signUp(name: String, email: String, password: String): Result<FirebaseUser> = try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val user = result.user
            ?: return Result.failure(Exception("User creation failed"))

        val profileUpdates =
            UserProfileChangeRequest.Builder().setDisplayName(name).build()

        user.updateProfile(profileUpdates).await()
        Result.success(user)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getCurrentUser(): FirebaseUser =
        currentUser ?: throw IllegalStateException("Not authenticated")

    fun signOut() = auth.signOut()
}