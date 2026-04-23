import os
import sys
from django.core.handlers.wsgi import WSGIApplication

# Add project root to Python path
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

# Set Django settings
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'core.settings')

# Create WSGI application
application = WSGIApplication()

def handler(request, context):
    """Vercel Python handler"""
    if request.method == 'GET' and request.path == '/':
        return {'statusCode': 200, 'body': 'IT Tech Organisation Management System - Server is running'}
    
    # For all other requests, use Django
    from django.core.handlers.wsgi import get_wsgi_application
    app = get_wsgi_application()
    return app(request, context)