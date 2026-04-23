# Port Forwarding Setup Guide
## Access IT Tech Organisation System from Any Network

---

## What is Port Forwarding?

Port forwarding allows external devices (via your public IP) to access the app running on your computer through your router.

---

## Step 1: Find Your Computer Info

Your internal IP: **192.168.1.48**
Port to forward: **8000**

---

## Step 2: Access Your Router

1. Open browser
2. Go to: **http://192.168.1.1**
3. Login (default usually):
   - Username: **admin** or **admin**
   - Password: **admin** or **password** or check router label

---

## Step 3: Find Port Forwarding

Look for these 메뉴 options:
- "Port Forwarding"
- "Virtual Server"
- "NAT"
- "Applications & Gaming"
- "Advanced" → "NAT"

---

## Step 4: Create Port Forward Rule

Fill in these fields:

| Field | Value |
|-------|-------|
| Name | IT_Org |
| External Port | 8000 |
| Protocol | TCP or Both |
| Internal IP | 192.168.1.48 |
| Internal Port | 8000 |
| Enable | ✓ (checked) |

Save the rule.

---

## Step 5: Find Your Public IP

After setup, find your public IP:
1. Go to: **https://whatismyipaddress.com**
2. Copy the "IPv4" address

This is your public IP - share this to access from any network!

---

## Alternative: Dynamic DNS (Recommended for persistent access)

If your public IP changes often, use free dynamic DNS:

### Option A: No-IP (Free)
1. Go to: https://no-ip.com
2. Create free account
3. Create hostname (e.g., itorg.ddns.net)
4. Download and install their client
5. Your URL stays the same even if IP changes!

### Option B: Cloudflare (Free)
1. Buy domain (~$1/month)
2. Create A record
3. Use Cloudflare tunnel

---

## Quick Test

After port forwarding:
1. Use mobile data (not WiFi) on your phone
2. Go to: http://YOUR_PUBLIC_IP:8000
3. If it loads - success!

---

## Troubleshooting

- Can't access router? Check label or try: 192.168.0.1, 192.168.2.1
- Password not working? Reset router to defaults
- Still not working? Check firewall - may need to allow port 8000

---

## Once Working:

Share this URL with anyone:
**http://YOUR_PUBLIC_IP:8000**

They can:
- Download the app
- Access the website
- Use all features!

---

## For Permanent Access:

Publish to Google Play Store - then users get automatic updates and install from store without needing your IP!

File ready: `media/apk/IT_Tech_Org_Release.apk`