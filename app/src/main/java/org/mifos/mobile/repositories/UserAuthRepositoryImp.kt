package org.mifos.mobile.repositories

import io.reactivex.Observable
import okhttp3.ResponseBody
import org.mifos.mobile.api.DataManager
import org.mifos.mobile.models.User
import org.mifos.mobile.models.payload.LoginPayload
import org.mifos.mobile.models.UpdatePasswordPayload
import org.mifos.mobile.models.register.RegisterPayload
import javax.inject.Inject

class UserAuthRepositoryImp @Inject constructor(private val dataManager: DataManager) : UserAuthRepository {

    override fun registerUser(
        accountNumber: String?,
        authenticationMode: String?,
        email: String?,
        firstName: String?,
        lastName: String?,
        mobileNumber: String?,
        password: String?,
        username: String?
    ): Observable<ResponseBody?>? {
        val registerPayload = RegisterPayload().apply {
            this.accountNumber = accountNumber
            this.authenticationMode = authenticationMode
            this.email = email
            this.firstName = firstName
            this.lastName = lastName
            this.mobileNumber = mobileNumber
            this.password = password
            this.username = username
        }
        return dataManager.registerUser(registerPayload)
    }

    override fun login(username: String, password: String): Observable<User?>? {
        val loginPayload = LoginPayload().apply {
            this.username = username
            this.password = password
        }
        return dataManager.login(loginPayload)
    }


    override fun updateAccountPassword(
        newPassword: String, confirmPassword: String
    ): Observable<ResponseBody?>? {
        val payload = UpdatePasswordPayload().apply {
            this.password = newPassword
            this.repeatPassword = confirmPassword
        }

        return dataManager.updateAccountPassword(payload)
    }

}