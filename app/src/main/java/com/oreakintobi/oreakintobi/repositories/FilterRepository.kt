package com.oreakintobi.oreakintobi.repositories

import com.oreakintobi.oreakintobi.data.SafeApiRequest

// We need to interact with UserRepository from our AuthViewModel,
// LoginActivity will interact with AuthViewModel
class FilterRepository(
    // injects MyApi and our AppDatabase as dependencies

// class extends SafeApiRequest class so that we can use it in our Repository
) : SafeApiRequest() {
    // this function performs the ACTUAL LOGIN
    // function previously returned a LiveData of type String for testing
    // now returns an AuthResponse return type because MyApi no longer returns LiveData


}