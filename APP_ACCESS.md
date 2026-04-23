# IT Tech Organisation Management System
## How to Access from Any Network

---

## Method 1: Local WiFi Network (Same WiFi)

Your phone must be connected to the same WiFi as this computer:

**URL: http://192.168.1.48:8000/**

From this URL you can:
- Download the Android APK
- View the website
- Access all features

---

## Method 2: Download APK Directly

The APK file is ready at:

**Direct Download: http://192.168.1.48:8000/download/**

Or copy the APK file from:
```
media/apk/IT_Tech_Org_Release.apk
```

---

## Method 3: Publish to Google Play Store (Best!)

This allows anyone to install from Play Store without network setup!

### Steps to Publish:

1. **Go to Google Play Console**
   - URL: https://play.google.com/console
   - Cost: $25 (one-time)

2. **Create New App**
   - Select "Android App"
   - App name: "IT Tech Organisation Management System"

3. **Upload APK**
   - Use file: `media/apk/IT_Tech_Org_Release.apk`

4. **Fill App Details**

   **App name:** IT Tech Organisation Management System
   
   **Short description:** (30-50 chars)
   "Professional IT Organization Management System"
   
   **Full description:** (4000 chars)
   "IT Tech Organisation Management System is a comprehensive digital solution designed to streamline and automate organizational operations. Features include employee management, department hierarchy, role management, attendance tracking with photo capture, work logs, event scheduling, interactive dashboard, AI ChatBot, and organization chart visualization. Download now and transform your organization's management!"

   **What's new:** (500 chars)
   "Initial release with all features"

5. **Upload Graphics** (in media/store-assets/)
   - App icon: `app_icon_512.png` (512x512)
   - Feature graphic: `feature_graphic_1024x500.png` (1024x500)
   - Screenshots: All 3 files

6. **Content Rating**
   - Answer: "None of the content applies"

7. **Pricing**
   - Select: Free
   - Countries: All

8. **Publish!**

---

## Method 4: Publish to Apple App Store

### Steps:

1. **Go to Apple Developer**
   - URL: https://developer.apple.com
   - Cost: $99/year

2. **Build iOS App**
   - Use the `ios/` project folder
   - Or use cloud service like Codemagic

3. **Upload to App Store Connect**
   - Upload the .ipa file

4. **Submit for Review**

---

## Files Ready for Store:

| File | Location | Use |
|------|---------|-----|
| Release APK | media/apk/IT_Tech_Org_Release.apk | Google Play Store |
| App Icon | media/store-assets/app_icon_512.png | Both stores |
| Feature Graphic | media/store-assets/feature_graphic_1024x500.png | Play Store |
| Screenshots | media/store-assets/screenshot_*.png | Both stores |
| iOS Project | ios/ | App Store |

---

## Quick Start:

To get the app working on any phone right now:

1. Connect phone to same WiFi as this computer
2. Open browser: http://192.168.1.48:8000/
3. Click "Download for Android"
4. Install the APK

Or copy the APK file to your phone and install!