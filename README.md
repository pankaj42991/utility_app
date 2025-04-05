# Utility App - All-in-One Android Toolkit

![App Logo](app/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

A comprehensive utility application for Android that combines multiple essential tools into one lightweight package.

## Features

### Document Management
- PDF Scanner with OCR (Optical Character Recognition)
- Image to Text extraction (AI-powered OCR)
- PDF Tools (Merge, Split, Compress, Convert)
- QR & Barcode Scanner
- eSignature & Watermark

### Smart Conversion Tools
- Unit Converter (Currency, Weight, Length, Area)
- Currency Converter with live exchange rates
- Loan & EMI Calculator
- GST & Tax Calculator
- Date & Age Calculator
- Tip Calculator

### Productivity Tools
- To-Do List & Task Manager with notifications
- Notes & Voice Recorder
- Pomodoro Timer
- Habit Tracker
- World Clock & Time Zone Converter

### Phone Optimization
- Battery Saver & Charge Alarm
- Junk Cleaner & Cache Remover
- App Lock & Privacy Guard
- File Manager & Explorer
- Clipboard Manager

### Media Tools
- Photo Editor & Collage Maker
- Background Remover (AI-powered)
- Video Compressor & Trimmer
- GIF Maker
- Text-to-Speech & Speech-to-Text

## Tech Stack

- **Frontend**: Jetpack Compose, Material 3
- **Database**: Room Database (SQLite)
- **Camera**: CameraX
- **OCR**: Google ML Kit
- **QR Scanning**: ZXing
- **Dependency Injection**: Hilt
- **Navigation**: Compose Navigation
- **State Management**: ViewModel + Flow
- **CI/CD**: GitHub Actions

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/utility-app.git
   ```

2. Open the project in Android Studio (Arctic Fox or later recommended)

3. Configure your signing config in `app/build.gradle`:
   ```gradle
   signingConfigs {
       release {
           storeFile file("your-keystore.jks")
           storePassword "yourpassword"
           keyAlias "youralias"
           keyPassword "yourpassword"
       }
   }
   ```

4. Add your Google Services configuration file if using Firebase features

5. Build and run the app

## Building the APK

To build a release APK:

```bash
./gradlew assembleRelease
```

The APK will be generated at:
`app/build/outputs/apk/release/app-release.apk`

## CI/CD

The project includes GitHub Actions workflow that:
- Builds the APK on every push to main branch
- Uploads the APK as an artifact
- Uploads the mapping file for crash reporting

## License

MIT License - See [LICENSE](LICENSE) for details.