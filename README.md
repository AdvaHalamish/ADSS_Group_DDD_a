## README

### Developers:
- Adva Halamish 206594012
- Shani Zilberberg 207106824
- Ido Badash 206917510

# Store Management System

## Introduction
This project is a simple store management system implemented in Java using SQLite as the database engine. The system allows storekeepers to insert new products, update item statuses, and maintain product and item information.

## Requirements
- Java JDK 8 or higher
- SQLite JDBC driver (included in the `lib` directory)

## Setup Instructions
1. Clone the repository to your local machine.
2. Ensure you have Java JDK installed.
3. Navigate to the project directory.
4. Compile the Java code using the following command:
   ```sh
   javac -cp .:lib/sqlite-jdbc-3.36.0.3.jar $(find . -name "*.java")
