# Ondato Android SDK

## Overview

This SDK provides a drop-in set of screens and tools for Android applications to allow capturing of identity documents and face photos/live videos for the purpose of identity verification. The SDK offers a number of benefits to help you create the best onboarding/identity verification experience for your customers:

- Carefully designed UI to guide your customers through the entire photo/video-capturing process
- Modular design to help you seamlessly integrate the photo/video-capturing process into your application flow
- Advanced image quality detection technology to ensure the quality of the captured images meets the requirement of the Ondato identity verification process, guaranteeing the best success rate
- Direct image upload to the Ondato service, to simplify integration\*

\* Note: the SDK is only responsible for capturing and uploading photos/videos. You still need to access the [Ondato API](https://documenter.getpostman.com/view/6997242/S1TZwaZe?version=latest) to create and manage checks.

The SDK supports API level 21 and above.

### 1. Adding the SDK dependency

Add repository to project level build.gradle file:

```
allprojects {
    repositories {
        maven { url "https://raw.githubusercontent.com/ondato/ondato-sdk-android/main/repos/" }
    }
}

```

Add SDK dependency to module level build.gradle file:

```
dependencies {
    implementation "com.kyc.ondato:sdk:1.7.1"
}
```         

### 2. Creating the SDK configuration

Create an `OndatoConfig` using your username, along with the password, choose mode of :"TEST" and "LIVE" environment, default mode is TEST. Also you can initialize SDK with token, token parameter is availble only for LIVE mode.

```kotlin
        val config = OndatoConfig.Builder()
            .setToken("accessToken")
            .setIdentificationId("identification id")
            .setCredentials("username", "password")
            .showStartScreen(true) //default is true
            .showConsentScreen(true) //default is true
            .showSelfieWithDocumentScreen(true) //default is true
            .showSuccessScreen(true) //default is true
            .recordProcess(true) //default is true
            .setMode(OndatoConfig.Mode.TEST) //default is TEST
            .setLanguage(Language.English) // default is English
            .setLivenessCheckMode(LivenessCheck.Active) //Choose Active or Passive, default is Active
            .setLoadingScreenProvider { CustomLoadingFragment() }
            .setDriverLicenseBacksideRequired(true) //default is false
            .setStartScreenProvider { callback -> CustomStartFragment.newInstance(callback) }
            .setSuccessScreenProvider { callback -> CustomSuccessFragment.newInstance(callback) }
            .build()

```

In case, Passive Liveness check is configured, please, contact Ondato support team support@ondato.com to check if your account is configured accordingly.


### 3. Starting the flow

```kotlin
        Ondato.init(config)
        Ondato.starIdentification(this, object : Ondato.ResultListener {
            override fun onSuccess(identificationId: String?) {
                //Success
            }

            override fun onFailure(identificationId: String?, error: OndatoError) {
                //Failure
            }
        })
```

Congratulations! You have successfully started the flow. 


## Customising SDK

### 1. Loading Screen
You can override the default fragment used to show loading state by setting `.setLoadingScreenProvider()` when building config.

### 2. Start Screen
You can override the default fragment used to start the verification process by setting `.setStartScreenProvider()` with your own implementation of `StartScreenProvider`. Note that, when custom start screen is used, it is up to you to start the verification process. Custom start fragment can start it by calling provided callback.

### 3. Success Screen
You can define custom success screen which is shown when user data is successfully submited. Set it using `.setsetSuccessScreenProvider()` and pass your `SuccessScreenProvider` implementation. When success screen is overriden is up to you to call the provided callback, so the process can continue.

### 4. Localisation
Ondato Android SDK already comes with out-of-the-box translations for the following locales:
- English (en) 🇬🇧
- Lithuanian (lt) 🇱🇹
- German (de) 🇩🇪
- Latvian (lv) 🇱🇻
- Estonian (et) 🇪🇪
- Russian (ru) 🇷🇺

### 5. Theme Customization
In order to enhance the user experience on the transition between your application and the SDK, you can provide some customisation by defining certain colors inside your own colors.xml file:

ondatoColorProgressBarAccent: Defines the color of the ProgressBarView which guides the user through the flow

ondatoColorButtonText: Defines the background color of the primary action buttons text

ondatoColorButtonBackgroundUnfocused: Defines the background color of the primary action buttons

ondatoColorButtonBackgroundFocusedStart: Defines the background color of the primary action buttons gradient start when pressed

ondatoColorButtonBackgroundFocusedCenter: Defines the background color of the primary action buttons gradient center when pressed

ondatoColorButtonBackgroundFocusedEnd: Defines the background color of the primary action buttons gradient end when pressed

ondatoColorErrorBg: Defines the background color of the error message background

ondatoColorErrorText: Defines the background color of the error message text color

ondatoColorPrimaryDark: Defines the taskbar color
