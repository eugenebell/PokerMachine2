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
* Android Studio Hedgehog or newer
* JDK 17+
* Android SDK Platform 34

### Open project
1. Android Studio → **File → Open** → `C:\Users\Eugen\Harry_Workspace\git\PokerMachine2`
2. Accept Gradle sync when prompted. Studio will download Gradle 8.4 and Android Gradle Plugin 8.2.0 automatically.
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

## License

Apache License 2.0 – same as original PokerMachine.

---

Original PokerMachine: https://github.com/eugenebell/PokerMachine
