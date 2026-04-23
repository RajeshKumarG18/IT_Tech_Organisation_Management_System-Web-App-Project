# IT Tech Organisation Management System

A professional Django-based IT Organization Management System with comprehensive features for managing organizational structure, employees, roles, departments, attendance, work logs, events, and more.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Project Structure](#project-structure)
5. [Installation](#installation)
6. [Running the Server](#running-the-server)
7. [Android App](#android-app)
8. [Default Credentials](#default-credentials)
9. [API Endpoints](#api-endpoints)
10. [Web Routes](#web-routes)
11. [Organization Levels](#organization-levels)
12. [Database Models](#database-models)
13. [Environment Variables](#environment-variables)
14. [Troubleshooting](#troubleshooting)
15. [License](#license)

---

## Project Overview

**IT Tech Organisation Management System** is a complete enterprise management solution designed for IT organizations. It provides:

- Complete employee management with hierarchical structure
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
- ✅ Employee management (CRUD operations)
- ✅ Department hierarchy with parent-child relationships
- ✅ Role management with 6 organizational levels
- ✅ Manager-subordinate relationships
- ✅ JWT Authentication via DRF
- ✅ REST APIs with Django Rest Framework

### Dashboard Features
- ✅ Executive dashboard with stats widgets
- ✅ Orange (#FF6B00) + Dark Blue (#0B1F3A) theme
- ✅ Interactive charts (Chart.js)
- ✅ Recent activity feed
- ✅ Notifications & alerts
- ✅ Mobile-responsive design

### Organization Management
- ✅ Organization Settings (name, logo, favicon)
- ✅ Dynamic organization name updates
- ✅ Custom admin panel

### ChatBot Features
- ✅ Organization AI ChatBot
- ✅ Advanced keyword matching
- ✅ Security features (blocks sensitive info)
- ✅ LLM Integration support (OpenAI GPT)

### Attendance & Work Log
- ✅ Check In with face capture
- ✅ Check Out with face capture
- ✅ Camera access for photo capture
- ✅ 3-step work log workflow
- ✅ Attendance photo display

### Schedule & Reports
- ✅ Schedule page with upcoming events
- ✅ Leave request management
- ✅ Reports page with analytics
- ✅ Employee statistics
- ✅ Meeting records tracking

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
| SQLite | Development Database |
| PostgreSQL | Production Database |

---

## Project Structure

```
IT Tech Organisation Management System/
├── core/                          # Django core configuration
│   ├── settings.py               # Django settings
│   ├── urls.py                  # Root URL configuration
│   └── wsgi.py                 # WSGI entry point
├── apps/                         # Django applications
│   ├── accounts/               # User authentication & profiles
│   ├── employees/             # Employee management
│   ├── roles/                # Role management
│   ├── departments/           # Department management
│   └── dashboard/            # Dashboard & web views
├── templates/                   # HTML templates
│   ├── base.html
│   ├── dashboard/
│   ├── employees/
│   └── registration/
├── static/                     # Static files (CSS, JS)
├── media/                     # Uploaded files
│   ├── organization_logos/     # Organization logos
│   └── apk/                  # Android APK files
├── android/                   # Android app project
├── ios/                       # iOS app project
├── db.sqlite3               # SQLite database
├── requirements.txt           # Python dependencies
└── manage.py                # Django management script
```

---

## Installation

### Prerequisites
- Python 3.10 or higher
- pip (Python package manager)

### Step 1: Clone the Project
```bash
git clone <repository-url>
cd "IT Tech Organisation Management System"
```

### Step 2: Create Virtual Environment
```bash
python3 -m venv venv
```

### Step 3: Activate Virtual Environment

**Linux/Mac:**
```bash
source venv/bin/activate
```

**Windows:**
```bash
venv\Scripts\activate
```

### Step 4: Install Dependencies
```bash
pip install -r requirements.txt
```

### Step 5: Run Migrations
```bash
python3 manage.py makemigrations
python3 manage.py migrate
```

### Step 6: Seed Initial Data (Optional)
```bash
python3 manage.py seed_data
```

---

## Running the Server

### Development Server
```bash
python3 manage.py runserver
```

### Access the Application
| Interface | URL |
|----------|-----|
| Web UI | http://127.0.0.1:8000/ |
| Admin Panel | http://127.0.0.1:8000/admin/ |
| API | http://127.0.0.1:8000/api/ |

### Running on Network
To make accessible on local network:
```bash
python3 manage.py runserver 0.0.0.0:8000
```

Access via: `http://YOUR_LOCAL_IP:8000`

---

## Android App

### APK Location
The pre-built Android APK is available at:
```
media/apk/IT_Tech_Org_Release.apk
```

### Android App Features
- ✅ Works on any WiFi or mobile network
- ✅ Downloads and installs APK locally
- ✅ Shows organization info when offline
- ✅ Connect to server automatically when available
- ✅ Network detection (WiFi/Mobile data)

### Building New APK
If you modify the Android code, rebuild using:
```bash
cd android
./gradlew assembleDebug
```

The APK will be at: `android/app/build/outputs/apk/debug/app-debug.apk`

---

## Default Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |

**Note:** Change the admin password after first login for security.

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
| PUT | `/api/employees/{id}/` | Update employee |
| DELETE | `/api/employees/{id}/` | Delete employee |

### Departments
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/departments/` | List departments |
| POST | `/api/departments/` | Create department |

### Roles
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/roles/` | List roles |
| POST | `/api/roles/` | Create role |

### Dashboard
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/dashboard/org-chart/` | Organization chart data |
| GET | `/api/dashboard/api/` | Dashboard statistics |

### ChatBot
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/dashboard/chatbot/` | ChatBot API |

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

### 1. CustomUser
- id (UUID)
- username
- email
- user_type (ADMIN, EXECUTIVE, MANAGER, HR, EMPLOYEE)
- first_name, last_name

### 2. Department
- id (UUID)
- name, code
- description
- parent_department (self-referential)

### 3. Role
- id (UUID)
- title
- level (1-6)
- department

### 4. Employee
- id (UUID)
- employee_id
- user (OneToOne)
- department
- role
- reporting_manager
- date_of_joining
- status (ACTIVE, INACTIVE, ON_LEAVE, TERMINATED)
- profile_image

### 5. Attendance
- employee
- date
- check_in, check_out
- check_in_photo, check_out_photo
- status (PRESENT, ABSENT, LATE, ON_LEAVE)

### 6. WorkLog
- employee
- date
- project, feature
- category
- work_description
- duration
- work_mode (OFFICE, REMOTE, HYBRID)

### 7. Event
- title, description
- event_type (MEETING, TRAINING, HOLIDAY)
- location
- meeting_link
- start_datetime, end_datetime
- status

---

## Environment Variables

Create a `.env` file in the root directory:

```env
SECRET_KEY=your-secret-key-here
DEBUG=True
ALLOWED_HOSTS=localhost,127.0.0.1
OPENAI_API_KEY=your-openai-api-key  # Optional - for LLM ChatBot
```

---

## Troubleshooting

### Database Issues
If you encounter database errors:
```bash
python3 manage.py migrate --run-syncdb
```

### Static Files Not Loading
```bash
python3 manage.py collectstatic
```

### Create Superuser
```bash
python3 manage.py createsuperuser
```

### Check Server Status
Make sure no other app is using port 8000:
```bash
lsof -i :8000
```

---

## Theme Colors

| Color | Hex | Usage |
|-------|-----|-------|
| Primary (Orange) | #FF6B00 | Buttons, highlights, gradients |
| Secondary (Dark Blue) | #0B1F3A | Sidebar, headers, backgrounds |

---

## License

MIT License

---

## Author

**IT Tech Organisation Management System**

Built with Django + DRF + Bootstrap + Chart.js

---

## Support

For issues and questions:
- Check the documentation in `COMPLETE_SETUP.md`
- Review the API documentation at `/api/`
- Contact your system administrator