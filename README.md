# PokerMachine2

Modernized version of the classic Video Poker Android app — Jacks or Better.

## Overview

**PokerMachine2** is a direct port of the original *PokerMachine* by Eugene (2010) to modern Android. It preserves 100% of the gameplay logic while updating the project structure, SDK targets and manifest for current devices.

The app recreates a traditional casino video poker machine with a simple fullscreen interface: deal 5 cards, hold/discard, draw, and get paid according to Jacks or Better rules.

## What changed from v1

* **Build system:** Migrated from Ant to Gradle
* **SDK:** `minSdk 21` (Android 5.0), `targetSdk 34` (Android 14)
* **Package / code:** Unchanged gameplay – `com.eb.poker` with the original Java source
* **Manifest:** Removed deprecated `debuggable=true`, added explicit `targetSdk`
* **Structure:** Standard Android Studio layout under `app/src/main/`

Gameplay is identical to the original:
* Deal 5 cards from a 52-card deck
* Tap cards to hold, Draw replaces unheld cards
* Starting credit 20, bet controls BET ONE / BET MAX
* Payouts: Royal Flush 250× → Straight Flush 50× → Four of a Kind 25× → Full House 9× → Flush 6× → Straight 4× → Three of a Kind 3× → Two Pair 2× → Pair Jacks+ 1×

## Project structure

```
PokerMachine2/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/eb/poker/
│       │   ├── About.java
│       │   ├── Help.java
│       │   ├── HighScore.java
│       │   ├── MainMenu.java
│       │   ├── Play.java
│       │   ├── PokerEngine.java
│       │   ├── SplashScreen.java
│       │   ├── SqlHelper.java
│       │   └── VideoPoker.java
│       └── res/
│           ├── anim/
│           ├── drawable-*/
│           ├── layout/
│           └── values/
├── build.gradle
└── settings.gradle
```

## Build & Run in Android Studio

### Prerequisites
* Android Studio Hedgehog or newer (AGP 8.2.0 requires Gradle 8.2–8.7)
* JDK 21 (see `gradle.properties` `org.gradle.java.home`)
* Android SDK Platform 34

### Open project
1. Android Studio → **File → Open** → `C:\Users\Eugen\Harry_Workspace\git\PokerMachine2`
2. Accept Gradle sync when prompted. Studio will download Gradle 8.7 and Android Gradle Plugin 8.2.0 automatically.
3. Wait for “Gradle sync finished”.

### Create an emulator
1. Tools → **Device Manager** → **Create Virtual Device**
2. Choose **Pixel 7** → **API 34** (Android 14) → **Next**
3. Finish and start the AVD.

### Build & Run
* Click the green **Run ▶** button, select the AVD, or use:
  ```
  ./gradlew assembleDebug
  ```
* The app installs on the emulator and launches `VideoPoker` → SplashScreen → MainMenu.

### Command line
With an Android SDK installed:
```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Build

Open in Android Studio or run:

```bash
./gradlew assembleDebug
```

## Release Build & Google Play Upload

### 1. Keystore
A release keystore already exists at project root:
`my-release-key.keystore`

`app/build.gradle` signing config points to it:
```gradle
signingConfigs {
    release {
        storeFile file("../my-release-key.keystore")
        storePassword "<store_password>"
        keyAlias "<key_alias>"
        keyPassword "<key_password>"
    }
}
```
**Security note:** Do not commit passwords. For production, move passwords to `local.properties` or environment variables and reference them in `build.gradle`.

To create a new keystore if needed:
```bash
keytool -genkey -v -keystore my-release-key.keystore -alias pokerkey -keyalg RSA -keysize 2048 -validity 10000
```

### 2. Build release APK / AAB
From project root:
```bash
./gradlew clean assembleRelease
```
Output:
* APK: `app/build/outputs/apk/release/app-release.apk`
* AAB: `app/build/outputs/bundle/release/app-release.aab` (if bundle enabled)

Verify signing:
```bash
./gradlew signingReport
```

### 3. Test release build
```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```
Or open in Android Studio → **Build → Generate Signed Bundle / APK**.

### 4. Google Play Console upload
1. Go to https://play.google.com/console
2. Create new app → fill store listing, screenshots, description
3. **Release → Production** → Create new release
4. Upload `app-release.aab` (preferred) or `app-release.apk`
5. Fill release notes, set rollout
6. Review and roll out

Versioning:
Increment in `app/build.gradle` before each upload:
```gradle
versionCode 2
versionName "2.1"
```
Each Play Store upload requires a higher `versionCode`.

### Gradle notes
* AGP 8.2.0 requires Gradle 8.2–8.7. Wrapper is pinned to 8.7 in `gradle/wrapper/gradle-wrapper.properties`.
* `gradle.properties` sets:
  * `org.gradle.java.home` to JDK 21
  * `org.gradle.daemon=false` and `org.gradle.configureondemand=false` to avoid configuration mutation issues.

## License

Apache License 2.0 – same as original PokerMachine.

---

Original PokerMachine: https://github.com/eugenebell/PokerMachine
