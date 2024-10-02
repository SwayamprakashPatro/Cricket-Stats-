### GitHub README.md:

# Cricket Stats Application 🏏

Welcome to the **Cricket Stats Application**, a Java-based program designed to manage and display cricket player statistics such as runs and wickets. This project leverages **Java Swing** for the user interface and **MySQL** for backend data storage, offering a dynamic way to track and analyze player performance.

## Features:
- **Add Player Stats**: Input player name, runs scored, and wickets taken directly through the interface, with data stored in a MySQL database.
- **View and Load Stats**: Displays player data in a neatly organized JTable. You can easily load data from the database with a single click.
- **Total Summary**: Automatically calculates and updates the total runs and wickets at the bottom of the app after each player is added.
- **Clean UI**: Designed with a straightforward interface to make interaction simple and efficient, including tooltips for user guidance.

## Technologies Used:
- **Java Swing**: To create a smooth and interactive graphical user interface.
- **MySQL**: To store and retrieve player data securely.
- **JDBC**: Used to handle database operations between Java and MySQL.

## Setup and Installation:

### Prerequisites:
- Java Development Kit (JDK) version 8 or above.
- MySQL server installed on your system.
- MySQL JDBC Driver for database connectivity.

### Database Setup:
1. **Create the Database**:
   First, ensure you have a MySQL database named `Cricket`:
   ```sql
   CREATE DATABASE Cricket;
   ```
2. **Create the Players Table**:
   Use the following SQL script to create the necessary table:
   ```sql
   CREATE TABLE Players (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       runs INT NOT NULL,
       wickets INT NOT NULL
   );
   ```
3. **Configure JDBC Connection**:
   Update the connection details in the Java code:
   ```java
   String jdbcUrl = "jdbc:mysql://localhost:3306/Cricket";
   String username = "root";
   String password = "your_password";
   ```

### Running the Application:
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/cricket-stats-app.git
   ```
2. **Compile the Application**:
   Navigate to the project folder and compile the Java files:
   ```bash
   javac CricketStats.java
   ```
3. **Run the Application**:
   Launch the app:
   ```bash
   java CricketStats
   ```

## How It Works:
- Enter the player's name, runs, and wickets into the text fields.
- Click "Add Player" to store the data in the MySQL database.
- Use "Load Stats" to view all the stored player stats in the table, along with the **total runs** and **wickets** summary at the bottom.

## Screenshots:

- **Main Interface**: Input fields for player stats, buttons for adding and loading players, and a summary of total runs and wickets.
- **JTable Display**: Shows a list of all players, their runs, and wickets, retrieved from the database.

## Future Enhancements:
- **Player Search**: Add functionality to search for specific players by name.
- **Additional Stats**: Extend the app to track more statistics like batting averages and strike rates.
- **Improved UI**: Further refine the user interface for a more modern look and feel.

## Contributions:
Contributions are welcome! Feel free to fork the repository, make changes, and submit a pull request. Let’s collaborate to make this project even better.

## Contact:
If you have any questions or feedback, feel free to reach out!

Enjoy tracking cricket stats with this simple and effective application!
