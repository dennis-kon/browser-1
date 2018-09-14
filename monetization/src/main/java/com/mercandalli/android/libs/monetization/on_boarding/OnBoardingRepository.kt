package com.mercandalli.android.libs.monetization.on_boarding

interface OnBoardingRepository {

    fun isOnBoardingEnded(): Boolean

    fun markOnBoardingEnded()

    fun isOnBoardingStorePageSkipped(): Boolean

    fun markOnBoardingStorePageSkipped()
}