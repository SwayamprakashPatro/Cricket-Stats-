import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class CricketStats extends JFrame
{
    String jdbcUrl = "jdbc:mysql://localhost:3306/Cricket";
    String username = "root";
    String password = "admin1234";

    JLabel nameLabel = new JLabel("Player Name:");
    JLabel runsLabel = new JLabel("Player Runs:");
    JLabel wicketsLabel = new JLabel("Player Wickets:");
    JLabel summaryLabel = new JLabel("Total Runs: 0, Total Wickets: 0");

    JTextField playerNameField = new JTextField(15);
    JTextField playerRunsField = new JTextField(15);
    JTextField playerWicketsField = new JTextField(15);

    JButton addButton = new JButton("Add Player");
    JButton loadButton = new JButton("Load Stats");

    String[] columnNames = {"Player Name", "Runs", "Wickets"};
    Object[][] data = new Object[0][3];
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

    JTable playerTable = new JTable(tableModel);

    String insertStats = "INSERT INTO Players (name, runs, wickets) VALUES (?, ?, ?)";
    String retrieveStats = "SELECT name, runs, wickets FROM Players";

    String playerName = playerNameField.getText();
    int runs, wickets;

    public CricketStats()
    {
        // Setting bounds for labels
        nameLabel.setBounds(50, 20, 100, 25);
        runsLabel.setBounds(50, 60, 100, 25);
        wicketsLabel.setBounds(50, 100, 100, 25);

        // Setting bounds for the input fields and buttons
        playerNameField.setBounds(160, 20, 150, 25);
        playerRunsField.setBounds(160, 60, 150, 25);
        playerWicketsField.setBounds(160, 100, 150, 25);
        addButton.setBounds(50, 140, 100, 30);
        loadButton.setBounds(160, 140, 100, 30);

        // Adding components to the frame
        add(nameLabel);
        add(runsLabel);
        add(wicketsLabel);
        add(playerNameField);
        add(playerRunsField);
        add(playerWicketsField);
        add(addButton);
        add(loadButton);

        // Setting bounds for the player stats table
        playerTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.setBounds(50, 180, 500, 200);
        add(scrollPane);

        // Summary label at the bottom
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        summaryLabel.setBounds(50, 400, 300, 25);
        add(summaryLabel);

        // Tooltips for guidance
        playerNameField.setToolTipText("Enter the name of the player");
        playerRunsField.setToolTipText("Enter the runs scored by the player");
        playerWicketsField.setToolTipText("Enter the wickets taken by the player");
        addButton.setToolTipText("Click to add player stats");
        loadButton.setToolTipText("Click to load player stats from the database");

        // Button actions
        addButton.addActionListener(e -> addPlayer());
        loadButton.addActionListener(e -> loadPlayers());

        setTitle("Cricket Stats Application");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    void addPlayer()
    {
        try
        {
            runs = Integer.parseInt(playerRunsField.getText());
            wickets = Integer.parseInt(playerWicketsField.getText());
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Please enter Valid Numbers.","Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(insertStats);
            statement.setString(1, playerName);
            statement.setInt(2, runs);
            statement.setInt(3, wickets);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Player added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            playerNameField.setText("");
            playerRunsField.setText("");
            playerWicketsField.setText("");
            loadPlayers();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Error adding player: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void loadPlayers()
    {
        try
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(retrieveStats);
            ResultSet resultSet = statement.executeQuery();

            tableModel.setRowCount(0); // Clear table
            int totalRuns = 0;
            int totalWickets = 0;

            while (resultSet.next())
            {
                String playerName = resultSet.getString("name");
                int runs = resultSet.getInt("runs");
                int wickets = resultSet.getInt("wickets");

                // Add row to table
                tableModel.addRow(new Object[]{playerName, runs, wickets});
                totalRuns += runs;
                totalWickets += wickets;
            }
            summaryLabel.setText("Total Runs: " + totalRuns + ", Total Wickets: " + totalWickets);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Error loading players: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            CricketStats app = new CricketStats();
            app.setVisible(true);
        });
    }
}