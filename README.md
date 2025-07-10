# LibraX - Smart Library Management System

A comprehensive, object-oriented library management system built with Java, demonstrating advanced OOP principles, SOLID design patterns, and clean architecture.

## Features

### **Authentication System**
- **Admin Login**: Secure access with username/password
- **Student Login**: Simple ID-based authentication
- **User Registration**: New student enrollment

### **Role-Based Access Control**
- **Students**: Search, borrow, return books, view history, check fines
- **Admins**: Full system management, reports, user oversight

### **Multi-Media Library Support**
- **Books**: Title, author, genre
- **Magazines**: Issue number, publisher
- **DVDs**: Duration, language
- **EBooks**: File size, format

### **Smart Borrowing System**
- **14-day borrowing period**
- **₹2 per day fine** for overdue items
- **Maximum 3 items** per student
- **Automatic fine calculation**
- **Real-time availability tracking**

### **Comprehensive Reporting**
- Overdue items tracking
- Fine summary and collection
- Library statistics
- User activity reports

## Architecture

### **Design Principles**
- **SOLID Principles**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **DRY (Don't Repeat Yourself)**: Reusable components and methods
- **KISS (Keep It Simple, Stupid)**: Clean, readable, and maintainable code

### **Design Patterns**
- **Abstract Factory**: For creating different library items
- **Strategy Pattern**: For fine calculation strategies
- **Observer Pattern**: For system notifications
- **Template Method**: For common operations

### **Key Components**
```
Core Architecture
├── Interfaces (FinesApplicable, ExamEnrollable)
├── 🏛Abstract Classes (Member, LibraryItem)
├── User Models (Student, Admin)
├── Item Models (Book, Magazine, DVD, EBook)
├── Services (LibrarySystem)
├── 🖥UI Layer (MenuHandler)
└── Models (BorrowRecord)
```

## Quick Start

### **Prerequisites**
- Java 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)
- Terminal/Command Prompt

### **Installation**
1. **Clone the repository**
   ```bash
   git clone https://github.com/viswadarshan-024/LibraX.git
   cd LibraX
   ```

2. **Compile the project**
   ```bash
   # Using scripts
   chmod +x scripts/compile.sh
   ./scripts/compile.sh
   
   # Or manually
   javac -d build/classes src/main/java/com/library/*.java src/main/java/com/library/*/*.java src/main/java/com/library/*/*/*.java
   ```

3. **Run the application**
   ```bash
   # Using scripts
   chmod +x scripts/run.sh
   ./scripts/run.sh
   
   # Or manually
   java -cp build/classes com.library.LibraryManagementSystem
   ```

### **Using Maven**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.library.LibraryManagementSystem"
```

### **Using Gradle**
```bash
gradle build
gradle run
```

## Usage Guide

### **Login Credentials**
- **Admin**: Username: `admin`, Password: `admin123`
- **Student**: Use any registered student ID (no password required)

### **Sample Student IDs**
The system comes pre-loaded with sample data. You can register new students or use the admin interface to manage users.

### **Main Menu Flow**
```
Login Menu
├── Student Login
│   ├── Search Books
│   ├── View Available Items
│   ├── Borrow Item
│   ├── Return Item
│   ├── View History
│   └── Check Fines
└── Admin Login
    ├── Add Library Item
    ├── Remove Library Item
    ├── View All Items
    ├── View All Users
    ├── Generate Reports
    └── Search Items
```

## Project Structure

```
LibraX/
├── src/
│   ├── main/java/com/library/
│   │   ├── interfaces/          # FinesApplicable, ExamEnrollable
│   │   ├── models/
│   │   │   ├── abstract/        # Member, LibraryItem
│   │   │   ├── users/           # Student, Admin
│   │   │   ├── items/           # Book, Magazine, DVD, EBook
│   │   │   └── BorrowRecord.java
│   │   ├── services/            # LibrarySystem
│   │   ├── ui/                  # MenuHandler
│   │   └── LibraryManagementSystem.java
└── README.md
```

## Configuration

### **System Settings** (`src/main/resources/config.properties`)
```properties
borrowing.period.days=14
fine.per.day=2.0
max.borrowed.items=3
admin.username=admin
admin.password=admin123
```

### **Customization Options**
- **Fine rates**: Modify daily fine amount
- **Borrowing limits**: Change maximum items per student
- **Borrowing period**: Adjust loan duration
- **User roles**: Add new member types

## Sample Data

The system includes pre-loaded sample data:
- **Books**: Java Programming, Data Structures, Web Development
- **Magazines**: Tech Today, Science Monthly
- **DVDs**: Programming tutorials, Database design
- **EBooks**: Python Basics, Machine Learning

## Advanced Features

### **Search Functionality**
- **Title-based search**: Find items by title
- **Author-based search**: Search by author name
- **Fuzzy matching**: Partial keyword matching
- **Case-insensitive**: User-friendly search

### **Fine Management**
- **Automatic calculation**: Real-time fine computation
- **Grace periods**: Configurable due date handling
- **Payment tracking**: Fine payment history
- **Overdue notifications**: System alerts

### **Data Persistence**
- **Serialization**: Save/load system state
- **File-based storage**: Persistent data storage
- **Backup mechanisms**: Data recovery options

## Development

### **Contributing**
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

### **Code Style**
- **Java conventions**: Standard naming and formatting
- **Documentation**: Comprehensive JavaDoc comments
- **Testing**: Unit tests for all new features
- **SOLID principles**: Maintain clean architecture

### **Adding New Features**
1. **New Item Types**: Extend `LibraryItem` abstract class
2. **User Roles**: Implement `Member` abstract class
3. **Interfaces**: Add behavior contracts
4. **Services**: Extend `LibrarySystem` functionality

## Performance

### **Optimization Features**
- **HashMap lookups**: O(1) average time complexity
- **Memory management**: Efficient object lifecycle
- **Stream processing**: Modern Java features
- **Lazy loading**: On-demand data loading

### **Scalability**
- **Modular design**: Easy to extend and maintain
- **Database ready**: Can integrate with SQL databases
- **Multi-threading**: Concurrent user support potential
- **REST API ready**: Web service integration possible

### **Error Handling**
- **Graceful degradation**: System continues on errors
- **User-friendly messages**: Clear error descriptions
- **Logging**: Detailed error tracking
- **Recovery mechanisms**: Automatic state recovery

## Learning Outcomes

This project demonstrates:
- **Advanced OOP concepts**: Inheritance, polymorphism, abstraction
- **Design patterns**: Factory, Strategy, Observer, Template Method
- **SOLID principles**: Clean, maintainable, extensible code
- **Java best practices**: Modern language features and idioms
- **Software architecture**: Layered, modular design
- **Testing strategies**: Unit testing and quality assurance

## License

This project is licensed under the MIT License.

---

**Built with ❤️ using Java and following industry best practices**
