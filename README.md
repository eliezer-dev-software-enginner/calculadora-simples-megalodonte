# Calculadora

Megalodonte Android application using **native** UI. The app targets Android 15 (API 35).
Its screen uses only Megalodonte components; JSONC is rendered by the Android bridge.
The [Android JSONC guide](https://github.com/eliezer-dev-software-enginner/megalodonte-android/blob/main/docs/JSONC_ANDROID.md)
lists the supported component types, bindings and platform-specific properties.

Install JDK 25 and Android SDK Platform 35. Clone `megalodonte-android`,
`megalodonte-core`, `megalodonte-theme`, and `megalodonte-reactivity-portable`
under this project's `framework/` directory, or set
`MEGALODONTE_ANDROID_HOME` to the Android framework checkout. Then run:

```sh
./gradlew :app:assembleDebug :app:lintDebug
```

The APK is `app/build/outputs/apk/debug/app-debug.apk`. Open this directory in
Android Studio to run on an Android 15 device or emulator. If its editor shows
an import or constructor error while Gradle builds successfully, use **Sync
Project with Gradle Files** to refresh the local framework modules.

## Example behavior

Perform addition, subtraction, multiplication, and division with decimal numbers. Input errors and division by zero produce an on-screen message.

Enter two numbers (period or comma decimal separator), then choose an operation. Clear resets both fields.

The project was generated with `megalodonte-cli --platform android --mode native` and then completed as a functional sample. Run `./gradlew :app:assembleDebug :app:lintDebug :app:check` to validate it. The UI is native Android through Megalodonte; no WebView is used.
