# README

## Project Name: COMP2013JDatabasesGroup7

This project is a Java application that uses MySQL for database operations. It provides functionalities for managing papers, questions, and answers in an exam paper database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java Development Kit (JDK) 8 or later
- MySQL Server 5.7 or later
- Maven 3.6.0 or later
- An IDE such as IntelliJ IDEA

### Installing

1. Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/MolimoWang/COMP2013JDatabasesGroup7.git
```

2. Open the project in your IDE.

3. Update the database connection details in `DatabaseConnection.java` (see the "Database Connection" section below).

4. Build the project using Maven. In IntelliJ IDEA, you can do this by right-clicking on the `pom.xml` file and selecting "Maven" -> "Reimport". This will download all the necessary dependencies.

5. Run the application from your IDE.

## Importing the Sample Database

The project includes a sample database file `exampaperdb.db` located in the `db` directory. To import this database into your MySQL server, follow these steps:

1. Open your MySQL command line tool or MySQL Workbench.

2. Connect to your MySQL server using your MySQL username and password.

3. Create a new database named `exampaperdb`:

```sql
CREATE DATABASE exampaperdb;
```

4. Select the `exampaperdb` database:

```sql
USE exampaperdb;
```

5. Import the `exampaperdb.db` file:

```sql
SOURCE /path/to/your/project/COMP2013JDatabasesGroup7/db/exampaperdb.db;
```

Please replace `/path/to/your/project/` with the actual path where you stored the `COMP2013JDatabasesGroup7` project.

## Database Connection

The `DatabaseConnection` class is responsible for establishing a connection to the MySQL database. You need to provide your database connection details in this class.

Open the `DatabaseConnection.java` file located in `src/main/java/com/example/util/` and update the following line with your MySQL server details:

```java
return DatabaseConnection.getConnection("localhost:3306 ", "exampaperdb", "Your Username Here", "Your Password Here");
```

Replace `"localhost:3306 "` with your MySQL server's hostname and port, `"exampaperdb"` with your database name, `"Your Username Here"` with your MySQL username, and `"Your Password Here"` with your MySQL password.

## Running the Application

You can run the application from your IDE by running the `main` method in the `Application` class.

## Running the Tests

You can run the tests from your IDE by running the `test` methods in the `AnswersDaoImplTest` class.

## Built With

- [Java](https://www.java.com/) - The programming language used
- [Maven](https://maven.apache.org/) - Dependency Management
- [MySQL](https://www.mysql.com/) - The database used

## Authors

- MolimoWang

## License

This project is licensed under the MIT License.

## Acknowledgments

- Thanks to all contributors to this project.