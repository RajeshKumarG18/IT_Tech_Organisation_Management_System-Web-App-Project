# Google Play Store & App Store Listing Guide

## App Information

**App Name:** IT Tech Organisation Management System

**Package Name:** com.itorg.management

**Category:** Business

## Google Play Store Submission

### 1. Create Developer Account
- Go to: https://play.google.com/console
- Pay $25 one-time registration fee
- Verify your identity

### 2. Upload App
- Sign in to Google Play Console
- Create new app → Select "Android App"
- Fill in:

#### App Details:
```
Title: IT Tech Organisation Management System
Short Description: Professional IT Organization Management System with comprehensive features for managing organizational structure, employees, roles, departments, attendance, work logs, events, and more.

Full Description:
IT Tech Organisation Management System is a comprehensive digital solution designed to streamline and automate organizational operations.

Features:
✓ Employee Management - Complete employee CRUD operations with profiles, departments, and reporting structure
✓ Department Hierarchy - Organize departments with parent-child relationships
✓ Role Management - Define roles with 6 organizational levels from Executive to Support
✓ Attendance Tracking - Check in/out with photo capture, location tracking
✓ Work Logs - Daily work logging with project, task, category, and duration tracking
✓ Event & Schedule Management - Manage meetings, training, holidays
✓ Interactive Dashboard & Reports - Interactive charts with employee stats
✓ AI ChatBot Assistant - Organization AI assistant with LLM support
✓ Organization Chart - Visual organization chart with hierarchy display

Download now and transform your organization's management!
```

### 3. App Artifacts
- **Release APK**: `android/app/build/outputs/apk/release/app-release-unsigned.apk`
- Sign the APK with your keystore before upload

### 4. Graphic Assets (Upload these 5 images):

| Asset | Size | Description |
|-------|------|-------------|
| Feature Graphic | 1024 x 500 | Shown in Play Store |
| Phone Screenshot | 1080 x 1920 | App Main Screen |
| Phone Screenshot | 1080 x 1920 | Dashboard |
| Phone Screenshot | 1080 x 1920 | Employee List |
| 7-inch Screenshot | 1920 x 1080 | Tablet view |

### 5. Content Rating
- Answer the questionnaire about your app's content
- Select "Executive Leadership" category

### 6. Pricing & Distribution
- Choose: Free or Paid
- Select countries

### 7. Publish
- Review and publish

---

## Apple App Store Submission

### 1. Create Developer Account
- Go to: https://developer.apple.com
- Pay $99/year membership
- Enroll in Apple Developer Program

### 2. Prepare iOS Build
- Requires macOS and Xcode
- Or use cloud build services like:
  - Codemagic (codemagic.io)
  - Bitrise (bitrise.io)
  - AppCenter (visualstudio.com)

### 3. Create App Listing
- Sign in to App Store Connect
- Create new app

#### App Details:
```
Name: IT Tech Organisation
Prefix: $(bundleID)
Language: English
SKU: it-tech-org-management
```

#### Description:
IT Tech Organisation Management System is a comprehensive digital solution designed to streamline and automate organizational operations. Download now and transform your organization's management!

### 4. Upload Build
- Use Xcode or Transporter app
- Upload .ipa file

### 5. Submit for Review
- Fill in contact details
- Provide demo account
- Submit for review

---

## Current Files Ready for Store:

| File | Location |
|------|----------|
| Release APK | `android/app/build/outputs/apk/release/app-release-unsigned.apk` |
| Debug APK | `media/apk/IT_Org_Management.apk` |

---

## To Sign APK for Play Store:

1. **Create Keystore** (one-time):
```bash
keytool -genkeypair -v -storetype PKCS12 -keystore it_tech_org.jks -alias it_tech_org -keyalg RSA -keysize 2048 -validity 10000
```

2. **Sign the APK**:
```bash
jarsigner -keystore it_tech_org.jks -storepass YOUR_PASSWORD android/app/build/outputs/apk/release/app-release-unsigned.apk it_tech_org
```

3. **Upload to Play Store Console**

---

## Need Help?

- **Play Store**: https://developer.android.com/distribute
- **App Store**: https://developer.apple.com/support