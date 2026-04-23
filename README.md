# IT Tech Organisation Management System

A professional Django-based IT Organization Management System with comprehensive features for managing organizational structure, employees, roles, departments, attendance, work logs, events, and more.

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Project Structure](#project-structure)
5. [Installation](#installation)
6. [Running the Server](#running-the-server)
7. [Android App](#android-app)
8. [Default Login](#default-login)
9. [Web Routes](#web-routes)
10. [API Endpoints](#api-endpoints)
11. [Organization Levels](#organization-levels)
12. [Database Models](#database-models)
13. [Theme Colors](#theme-colors)
14. [Troubleshooting](#troubleshooting)
15. [License](#license)

---

## Overview

**IT Tech Organisation Management System** is a complete enterprise management solution designed for IT organizations. It provides:

- Employee management with hierarchical structure
- Role-based access control (6 levels)
- Attendance tracking with photo capture
- Work logging system
- Event and meeting management
- Interactive dashboard with analytics
- AI ChatBot for organization queries
- Organization chart visualization

---

## Features

### Core Features
- ✅ Django MVT Architecture
- ✅ Role-based authentication (ADMIN, EXECUTIVE, MANAGER, HR, EMPLOYEE)
- ✅ Employee management (CRUD)
- ✅ Department hierarchy with parent-child relationships
- ✅ Role management with 6 organizational levels
- ✅ Manager-subordinate relationships
- ✅ JWT Authentication
- ✅ REST APIs

### Dashboard Features
- ✅ Executive dashboard with stats widgets
- ✅ Orange (#FF6B00) + Dark Blue (#0B1F3A) theme
- ✅ Interactive charts (Chart.js)
- ✅ Recent activity feed
- ✅ Mobile-responsive design

### Additional Features
- ✅ Organization Settings (name, logo, favicon)
- ✅ AI ChatBot with keyword matching
- ✅ Check In/Out with photo capture
- ✅ Work log management
- ✅ Event/Meeting scheduling
- ✅ Leave request management
- ✅ Reports and analytics

---

## Tech Stack

| Technology | Description |
|------------|-------------|
| Django 5.x | Web Framework |
| Django Rest Framework | API Layer |
| SimpleJWT | JWT Authentication |
| Bootstrap 5 | UI Framework |
| Chart.js | Dashboard Charts |
| Python 3.10+ | Programming Language |
| SQLite | Database (development) |

---

## Project Structure

```
IT Tech Organisation Management System/
├── core/                      # Django configuration
│   ├── settings.py            # Django settings
│   ├── urls.py               # URL configuration
│   └── wsgi.py              # WSGI entry point
├── apps/                      # Django applications
│   ├── accounts/            # User authentication
│   ├── employees/          # Employee management
│   ├── roles/             # Role management
│   ├── departments/        # Department management
│   └── dashboard/          # Dashboard & web views
├── templates/                 # HTML templates
├── static/                   # Static files (CSS, JS)
├── media/                    # Uploaded files
│   ├── organization_logos/   # Organization logos
│   └── apk/               # Android APK
├── android/                  # Android app project
├── db.sqlite3              # SQLite database
└── requirements.txt        # Python dependencies
```

---

## Installation

### Prerequisites
- Python 3.10 or higher
- pip

### Step 1: Clone and Setup
```bash
cd "IT Tech Organisation Management System"
python3 -m venv venv
source venv/bin/activate  # Linux/Mac
# or: venv\Scripts\activate  # Windows
pip install -r requirements.txt
```

### Step 2: Run Migrations
```bash
python3 manage.py makemigrations
python3 manage.py migrate
```

### Step 3: Run Server
```bash
python3 manage.py runserver
```

### Access
- **Web UI**: http://127.0.0.1:8000/
- **Admin**: http://127.0.0.1:8000/admin/
- **API**: http://127.0.0.1:8000/api/

---

## Running the Server

### Local Access
```bash
python3 manage.py runserver
```
URL: http://localhost:8000

### Network Access
```bash
python3 manage.py runserver 0.0.0.0:8000
```
URL: http://YOUR_LOCAL_IP:8000

---

## Android App

### APK Location
The pre-built Android APK is available at:
```
media/apk/IT_Tech_Org_Release.apk
```

### App Features
- ✅ Works on any WiFi or mobile network
- ✅ Auto-connects to server
- ✅ Shows organization info when offline
- ✅ Network detection

### Building New APK
```bash
cd android
./gradlew assembleDebug
```
APK location: `android/app/build/outputs/apk/debug/app-debug.apk`

---

## Default Login

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |

**Change password after first login for security.**

---

## Web Routes

| Endpoint | Description |
|----------|-------------|
| `/` | Home/Download page |
| `/login/` | Login page |
| `/logout/` | Logout |
| `/dashboard/` | Main dashboard |
| `/profile/` | User profile |
| `/employees/` | Employee list |
| `/org-chart/` | Organization chart |
| `/worklog/` | Work log & attendance |
| `/schedule/` | Events & leave requests |
| `/reports/` | Reports & analytics |
| `/chatbot/` | Organization ChatBot |
| `/admin/` | Admin panel |
| `/about/` | About page |
| `/contact/` | Contact page |
| `/terms/` | Terms of Service |
| `/privacy/` | Privacy Policy |

---

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts/token/` | Get JWT token |
| POST | `/api/accounts/token/refresh/` | Refresh JWT token |

### Employees
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employees/` | List employees |
| POST | `/api/employees/` | Create employee |
| GET | `/api/employees/{id}/` | Get employee |

### Departments
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/departments/` | List departments |
| POST | `/api/departments/` | Create department |

### Dashboard
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/dashboard/org-chart/` | Organization chart |
| GET | `/api/dashboard/api/` | Dashboard stats |

### ChatBot
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/dashboard/chatbot/` | ChatBot API |

---

## Organization Levels

The system supports 6 organizational levels:

1. **Executive Leadership** - CEO, CTO, CIO, CHRO
2. **Upper Management** - VP, Directors
3. **Middle Management** - Managers
4. **Senior Professionals** - Senior Engineers
5. **Junior Professionals** - Engineers
6. **Support Functions** - HR, Finance, IT Support

---

## Database Models

### CustomUser
- id, username, email
- user_type (ADMIN, EXECUTIVE, MANAGER, HR, EMPLOYEE)
- first_name, last_name

### Department
- name, code, description
- parent_department (self-referential)

### Role
- title, level (1-6)
- department

### Employee
- employee_id, user
- department, role
- reporting_manager
- date_of_joining
- status (ACTIVE, INACTIVE, ON_LEAVE, TERMINATED)
- profile_image

### Attendance
- employee, date
- check_in, check_out
- check_in_photo, check_out_photo
- status (PRESENT, ABSENT, LATE, ON_LEAVE)

### WorkLog
- employee, date
- project, feature, category
- work_description, duration
- work_mode (OFFICE, REMOTE, HYBRID)

---

## Theme Colors

| Color | Hex | Usage |
|-------|-----|-------|
| Primary (Orange) | #FF6B00 | Buttons, highlights |
| Secondary (Dark Blue) | #0B1F3A | Sidebar, backgrounds |

---

## Troubleshooting

### Create Superuser
```bash
python3 manage.py createsuperuser
```

### Reset Database
```bash
rm db.sqlite3
python3 manage.py makemigrations
python3 manage.py migrate
```

### Check Port
```bash
lsof -i :8000
```

---

## License

MIT License

---

## Author

**IT Tech Organisation Management System**

Built with Django + DRF + Bootstrap + Chart.js