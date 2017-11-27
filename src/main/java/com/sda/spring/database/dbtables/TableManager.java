package com.sda.spring.database.dbtables;

import com.sda.spring.database.dbsettings.ConnectionFactory;
import com.sda.spring.database.dbsettings.DBSettings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    private static final Connection connection = ConnectionFactory.getConnection(
            DBSettings.DB_CONNECTION,
            DBSettings.DB_USER,
            DBSettings.DB_PASSWORD);

    private static void createStatement(String StatementQuery){
        try {
            Statement statement = connection.createStatement();
            statement.execute(StatementQuery);
            statement.close();
            // statement.executeUpdate() //insert, update, delete (DDF)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropAllTables()  {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP SCHEMA bank CASCADE");
            createStatement("CREATE SCHEMA bank;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createAllTables(){
        createStatement(StatementBuilider.getCreateCustomerTableStatement());
        createStatement(StatementBuilider.getCreateAccountTableStatement());
        createStatement(StatementBuilider.getCreateTransferHistoryTableStatement());
        createStatement(StatementBuilider.getConstraintsToAccount());
        createStatement(StatementBuilider.getConstraintsToTransferHistoryDeparting());
    }
}
