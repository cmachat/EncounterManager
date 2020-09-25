package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class DBConnection
{
	public static final String MSSQL_DEFAULTPORT = "1433";
	public static final String MARIADB_DEFAULTPORT = "3306";
	public static final String MSSQL_CLASSFORNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String MARIADB_CLASSFORNAME="org.mariadb.jdbc.Driver";
	public static DatabaseProvider dbProvider  = DatabaseProvider.UNKNOWN;
	
	
	public static enum DatabaseProvider
	{
		UNKNOWN, MARIADB, MSSQL
	}
	
	private static Connection dbConn; 
	private static String connectionString;
	

	public static boolean connectToDatabase(DatabaseProvider dbProvider, String connectionString, String userID, String passWord)
	{
		boolean retValue = false;
		
		if (dbProvider == DatabaseProvider.MARIADB)
			retValue = connectToDatabase(MARIADB_CLASSFORNAME, connectionString, userID, passWord);
		else
			retValue = connectToDatabase(MSSQL_CLASSFORNAME, connectionString, userID, passWord);
		
		DBConnection.dbProvider = dbProvider;
		
		return retValue;
	}
	
	
	public static boolean connectToDatabase(String classForName, String connectionString, String userID, String passWord)
	{
		boolean retValue = false;
		
		try {
			// Dynamisches Laden und registrieren einer Klasse beim JDBC-Treibermanager 
			// (statische Initialisierung).
			Class.forName(classForName).newInstance();
			
			// Die Methode getConnection() liefert eine Verbindung zur Datenbank.
			// Die Verbindung ist ein Objekt vom Typ java.sql.Connection.
			dbConn = DriverManager.getConnection(connectionString, userID, passWord);
			
			DBConnection.connectionString = connectionString;
			
			retValue = true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
					                      "Verbindungsfehler", JOptionPane.ERROR_MESSAGE);
			dbConn = null;
			DBConnection.connectionString = null;
			dbProvider  = DatabaseProvider.UNKNOWN;
		}
		
		return retValue;
	}
	

	public static Connection getConnection()
	{
		return dbConn;
	}




	public static String getConnectionString()
	{
		return connectionString;
	}


	public static String getCatalog()
	{
		String retValue = "";
		
		if (dbConn == null)
			return retValue;
		
		try {
			retValue = dbConn.getCatalog();
		} catch (Exception ex)
		{}
		
		return retValue;
	}
	
	
	public static int executeNonQuery(String SQL)
	{
		Statement stmt;
		
		int retValue = -1;
		
		if (dbConn == null)
			return retValue;
		
		try {
			stmt = dbConn.createStatement();
			retValue = stmt.executeUpdate(SQL);
			stmt.close();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}	
		
		return retValue;
	}
	
	
	public static ResultSet executeQuery(String SQL)
	{
		ResultSet rSet = null;
		Statement stmt;
		
		if (dbConn == null)
			return rSet;
		
		try {
			stmt = dbConn.createStatement();
			rSet = stmt.executeQuery(SQL);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}	
		
		return rSet;
	}
	
	
	public static Object executeScalar(String SQL)
	{
		
		Statement stmt;
		Object retValue = null;
		
		if (dbConn == null)
			return retValue;
		
		try {
			stmt = dbConn.createStatement();
			ResultSet rSet = stmt.executeQuery(SQL);
			
			rSet.next();
			retValue = rSet.getObject(1);
			rSet.close();
			stmt.close();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}	
		
		return retValue;
	}
	
	
	public static Object executePreparedScalarStatement(PreparedStatement prepStatement, Object ... parms)
	{
		Object retValue = null;
		
		try {
			for (int i = 0; i < parms.length; i++)
				prepStatement.setObject(i + 1, parms[i]);
			
			ResultSet rSet = prepStatement.executeQuery();
			rSet.next();
			retValue = rSet.getObject(1);
			rSet.close();
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Einlesen der Datei " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return retValue;
	}

	
	public static PreparedStatement prepareStatement(String SQL)
	{
		PreparedStatement prepStatement = null;
		
		if (dbConn == null)
			return prepStatement;
		
		try {
			prepStatement = dbConn.prepareStatement(SQL);
		}	
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler bei der Vorbereitung der SQL-Anweisung: " + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		return prepStatement;
	}

	
	public static boolean executePreparedStatement(PreparedStatement prepStatement, Object ... parms)
	{
		try {
			for (int i = 0; i < parms.length; i++)
				prepStatement.setObject(i + 1, parms[i]);
			
			prepStatement.executeUpdate();
			return true;
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Einlesen der Datei " + ex.getMessage(), "E/A Fehler", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	
	public static String dbString(String value)
	{
		return "'" + value.replaceAll("'", "''") + "'";
	}
	
	
	public static ResultSet getAllTables()
	{
		ResultSet rSet = null;
		
		if (dbProvider == DatabaseProvider.MARIADB)
			rSet = getAllMariaDBTables();
		else if (dbProvider == DatabaseProvider.MSSQL)
			rSet = getAllMSSQLTables();
		
		return rSet;
	}
	

	private static ResultSet getAllMariaDBTables()
	{
		
		ResultSet rSet = null;
		
		if (dbConn == null)
			return rSet;
		
		String SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES ";
		SQL += "WHERE TABLE_SCHEMA = " + dbString(getCatalog());
		
		return executeQuery(SQL);
		
	}
	
	private static ResultSet getAllMSSQLTables()
	{
		ResultSet rSet = null;
		
		if (dbConn == null)
			return rSet;
		
		String SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES ";
		SQL += "WHERE TABLE_CATALOG = " + dbString(getCatalog());
		
		return executeQuery(SQL);
	}
	
	public static List<String> getAllDatabases(DatabaseProvider dbProvider)
	{
		
		List<String> list = new ArrayList<>();
		
		if (dbProvider == DatabaseProvider.MARIADB)
			list = getAllMariaDBDatabases();
		else if (dbProvider == DatabaseProvider.MSSQL)
			list = getAllMSSQLDatabases();
		
		return list;
		
	}
	
	private static List<String> getAllMSSQLDatabases()
	{
		
		List<String> list = new ArrayList<>();
		ResultSet rSet = null;
		
		if (dbConn == null)
			if (!connectToDatabase(MSSQL_CLASSFORNAME, "jdbc:sqlserver://localhost;integratedSecurity=true;SendStringParametersAsUnicode=false;", null, null))
				return list;
		
		String SQL = "SELECT NAME FROM sys.databases";
		rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return list;
		
		try {
			while(rSet.next())
					list.add(rSet.getString("NAME"));
			
			rSet.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		// Datenbankverbindung schlie�en
		closeConnection();
		
		Collections.sort(list);
		return list;
	}
	
	
	private static List<String> getAllMariaDBDatabases()
	{
		List<String> list = new ArrayList<>();
		ResultSet rSet = null;
		
		if (dbConn == null) {	
			if (!connectToDatabase(MARIADB_CLASSFORNAME, "jdbc:mariadb://localhost:" + MARIADB_DEFAULTPORT, "ich", null))
				return list;
		}	
			
		try {
			rSet = dbConn.getMetaData().getCatalogs();
			if (rSet != null)
				while(rSet.next())
					list.add(rSet.getString("TABLE_CAT"));
					
			rSet.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		// Datenbankverbindung schlie�en
		closeConnection();
		
		Collections.sort(list);
		return list;
	}	
	

	public static void openMariaDB()
	{
		
		String connectionString, classForName;
		String server = "localhost";
		String dataBase = "encounter_manager";
		
		
		classForName = "org.mariadb.jdbc.Driver";
		
		connectionString = "jdbc:mariadb://" + server + ":3306/";
		connectionString += dataBase;
		
		connectToDatabase(classForName, connectionString, "root", null);
	}
	
	
	public static void closeConnection()
	{
		if (dbConn == null)
			return;
		
		try {
			dbConn.close();
		} catch (Exception ex)
		{}
		
		dbConn = null;
		DBConnection.connectionString = null;
	}
	
	
}
