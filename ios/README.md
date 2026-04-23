# IT Tech Organisation Management System - iOS App

## Building the iOS App

### Prerequisites (on Mac):
1. **Xcode** - Install from Mac App Store
2. **Node.js** - Already installed (v10.8.2)
3. **CocoaPods** - `sudo gem install cocoapods`

### To Build:

#### Option 1: Using Capacitor (Recommended)

```bash
# Navigate to iOS folder
cd ios

# Install dependencies
npm install

# Build iOS project
npx capacitor build ios
```

#### Option 2: Manual Xcode Build

1. Open `ios/App/App.xcworkspace` in Xcode
2. Select your development team
3. Build for simulator or device:
   - Device: Product → Build (⌘B)
   - Simulator: Select Simulator → Build (⌘B)

### Generating the App:

1. **For App Store**:
   - Xcode → Product → Archive
   - Window → Organizer → Distribute App
   - Follow Apple instructions

2. **For Testing**:
   - Select device/simulator
   - Product → Run (⌘R)

---

## Project Structure:

```
ios/
├── App/
│   ├── App/
│   │   ├── AppDelegate.swift
│   │   ├── Assets.xcassets/
│   │   └── Info.plist
│   ├── App.xcodeproj/
│   └── App.xcworkspace/
├── capacitor.config.json
└── package.json
```

---

## App Configuration:

- **Bundle Identifier**: com.itorg.management
- **App Display Name**: IT Tech Organisation Management System
- **Version**: 1.0.0
- **Minimum iOS**: 12.0

---

## For Cloud Build (No Mac Required):

Use these services to build iOS without a Mac:

### 1. Codemagic (Free for hobby):
1. Go to: https://codemagic.io
2. Connect GitHub repository
3. Automatic build & export .ipa

### 2. Bitrise (Free tier):
1. Go to: https://bitrise.io
2. Create new app
3. Connect repository
4. Build for iOS

### 3. AppCenter (Microsoft):
1. Go to: https://appcenter.ms
2. Create new app
3. Connect repo and build

---

## To Use with Cloud Build:

1. Copy the ios/ folder to your repository
2. Connect to cloud build service
3. The service will build .ipa for you
4. Upload .ipa to App Store Connect