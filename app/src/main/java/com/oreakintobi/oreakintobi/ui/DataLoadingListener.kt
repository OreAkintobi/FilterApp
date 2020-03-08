package com.oreakintobi.oreakintobi.ui


// AuthListener interface creates callbacks from the AuthViewModel to our Login Activity to display error/success messages
// AuthListener is implemented in Login Activity
interface DataLoadingListener {

    // logging in is takes time, so onStarted will be called to display a progress bar to the user
    fun onStarted()

    // this is called when authentication is successful
    fun onSuccess()

    //this is called when authentication fails
    fun onFailure(message: String)
}