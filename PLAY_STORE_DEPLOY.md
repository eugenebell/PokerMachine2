# Play Store Deployment Guide

## 1. Generate a Keystore

In Android Studio:
1. Build → Generate Signed Bundle / APK
2. Choose **APK** → Next
3. Create new keystore
4. Save `my-release-key.keystore` in project root
5. Fill in password, alias, etc.
6. Copy details into `app/build.gradle` signingConfigs

## 2. Build Release APK

**Android Studio:**
1. Build → Generate Signed Bundle / APK
2. Select release variant
3. Output: `app/build/outputs/apk/release/app-release.apk`

**Command line:**
```bash
./gradlew assembleRelease
```

APK will be at `app/build/outputs/apk/release/`

## 3. Test on Android Phone

1. Enable Developer Options on phone
2. Enable USB Debugging
3. Connect via USB
4. In Android Studio: Run → select device
5. Or via ADB:
```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```

## 4. Upload to Google Play Console

1. Go to https://play.google.com/console
2. Create new app
3. Fill in store listing, screenshots, description
4. App bundle / APK → Upload `app-release.apk`
5. Create **Internal testing** track
6. Add testers emails
7. Roll out

## 5. Versioning

Increment in `app/build.gradle`:
```gradle
versionCode 2
versionName "2.1"
```

Each Play Store upload needs a higher `versionCode`.

## Notes

- Keep your keystore safe — lose it and you can't update the app
- First upload should be **Internal testing** to validate
- Test on multiple screen sizes before production rollout
