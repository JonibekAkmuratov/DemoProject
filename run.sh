#!/bin/bash

# Demo Project Docker Runner Script

set -e  # Exit on any error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if Docker is installed
check_docker() {
    if ! command -v docker &> /dev/null; then
        print_error "Docker is not installed. Please install Docker first."
        exit 1
    fi

    if ! command -v docker-compose &> /dev/null; then
        print_error "Docker Compose is not installed. Please install Docker Compose first."
        exit 1
    fi
}

# Clean up function
cleanup() {
    print_info "Cleaning up old containers and images..."
    docker-compose down --remove-orphans
    docker system prune -f
    print_success "Cleanup completed"
}

# Build and run
build_and_run() {
    print_info "Building and starting the application..."

    # Copy environment file if it doesn't exist
    if [ ! -f .env ]; then
        print_warning ".env file not found. Creating from template..."
        cp .env.example .env 2>/dev/null || true
    fi

    # Build and start services
    docker-compose up --build -d

    print_success "Application is starting..."
    print_info "Waiting for services to be ready..."

    # Wait for application to be ready
    timeout=120
    counter=0
    while [ $counter -lt $timeout ]; do
        if curl -f http://localhost:8080/actuator/health >/dev/null 2>&1; then
            break
        fi
        echo -n "."
        sleep 2
        counter=$((counter + 2))
    done

    if [ $counter -ge $timeout ]; then
        print_error "Application failed to start within $timeout seconds"
        docker-compose logs demo-app
        exit 1
    fi

    echo ""
    print_success "Application is ready!"
    print_info "🌍 Application: http://localhost:8080"
    print_info "📚 Swagger UI: http://localhost:8080/swagger-ui.html"
    print_info "❤️  Health Check: http://localhost:8080/actuator/health"
    print_info "🗄️  Database: localhost:5432 (demo/postgres/root123)"
}

# Stop services
stop_services() {
    print_info "Stopping services..."
    docker-compose down
    print_success "Services stopped"
}

# Show logs
show_logs() {
    if [ -n "$1" ]; then
        docker-compose logs -f "$1"
    else
        docker-compose logs -f
    fi
}

# Show status
show_status() {
    print_info "Service Status:"
    docker-compose ps

    print_info "\nContainer Resources:"
    docker stats --no-stream --format "table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}"
}

# Main menu
show_menu() {
    echo ""
    echo "==================================="
    echo "    Demo Project Docker Manager"
    echo "==================================="
    echo "1. Build and Run"
    echo "2. Stop Services"
    echo "3. Restart Services"
    echo "4. Show Logs"
    echo "5. Show Status"
    echo "6. Clean Up"
    echo "7. Enter Container Shell"
    echo "8. Database Shell"
    echo "9. Exit"
    echo ""
}

# Enter container shell
enter_shell() {
    print_info "Available containers:"
    docker-compose ps --format "table {{.Service}}\t{{.State}}"
    echo ""
    read -p "Enter container name (demo-app/postgres/redis): " container

    case $container in
        demo-app)
            docker-compose exec demo-app /bin/bash
            ;;
        postgres)
            docker-compose exec postgres psql -U postgres -d demo
            ;;
        redis)
            docker-compose exec redis redis-cli -a redis123
            ;;
        *)
            print_error "Invalid container name"
            ;;
    esac
}

# Database shell
db_shell() {
    print_info "Connecting to PostgreSQL database..."
    docker-compose exec postgres psql -U postgres -d demo
}

# Check Docker installation
check_docker

# Handle command line arguments
case "${1:-menu}" in
    "build"|"start"|"up")
        build_and_run
        ;;
    "stop"|"down")
        stop_services
        ;;
    "restart")
        stop_services
        sleep 2
        build_and_run
        ;;
    "logs")
        show_logs "$2"
        ;;
    "status")
        show_status
        ;;
    "clean")
        cleanup
        ;;
    "shell")
        enter_shell
        ;;
    "db")
        db_shell
        ;;
    "menu")
        while true; do
            show_menu
            read -p "Choose an option [1-9]: " choice

            case $choice in
                1)
                    build_and_run
                    ;;
                2)
                    stop_services
                    ;;
                3)
                    stop_services
                    sleep 2
                    build_and_run
                    ;;
                4)
                    echo "Enter service name (or press Enter for all):"
                    read service
                    show_logs "$service"
                    ;;
                5)
                    show_status
                    ;;
                6)
                    cleanup
                    ;;
                7)
                    enter_shell
                    ;;
                8)
                    db_shell
                    ;;
                9)
                    print_info "Goodbye!"
                    exit 0
                    ;;
                *)
                    print_error "Invalid option. Please choose 1-9."
                    ;;
            esac

            echo ""
            read -p "Press Enter to continue..."
        done
        ;;
    *)
        echo "Usage: $0 {build|stop|restart|logs|status|clean|shell|db|menu}"
        echo ""
        echo "Commands:"
        echo "  build    - Build and start all services"
        echo "  stop     - Stop all services"
        echo "  restart  - Restart all services"
        echo "  logs     - Show service logs"
        echo "  status   - Show service status"
        echo "  clean    - Clean up containers and images"
        echo "  shell    - Enter container shell"
        echo "  db       - Connect to database"
        echo "  menu     - Show interactive menu (default)"
        exit 1
        ;;
esac