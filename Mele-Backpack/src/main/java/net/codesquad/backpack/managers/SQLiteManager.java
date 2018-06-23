/**
 * 
 */
package net.codesquad.backpack.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.codesquad.backpack.BackPackPlugin;

/** @author nano3. */
public class SQLiteManager
{
	private static SQLiteManager instance;

	private Connection connection;

	private Statement statement;

	public SQLiteManager() {
		openConnection();
		checkTables();
	}

	private boolean checkTables() {
		executeUpdate("CREATE TABLE IF NOT EXISTS backpacks (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `inventory` TEXT)");
		return true;
	}

	public Connection openConnection() {
		try {
			if (isConnection()) {
				return connection;
			}

			try {
				Class.forName("org.sqlite.JDBC");
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				connection = DriverManager.getConnection("jdbc:sqlite:" + BackPackPlugin.getInstance().getDataFolder() + File.separator + "backpacks.db");
				statement = getConnection().createStatement();
				return connection;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Statement getStatement() {
		return statement;
	}

	public Connection getConnection() throws SQLException {
		if (isConnection()) {
			return this.connection;
		}
		try {
			return openConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isConnection() {
		try {
			return this.connection != null && !this.connection.isClosed();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection != null;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			this.connection = null;
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			if (!isConnection()) {
				openConnection();
			}
			if (connection == null) {
				return null;
			}
			return statement.executeQuery(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdate(String query) {
		try {
			if (!isConnection()) {
				openConnection();
			}
			if (statement == null) {
				return 0;
			}
			return statement.executeUpdate(query);
		}
		catch (SQLException e) {

			e.printStackTrace();

		}
		return 0;
	}

	public static SQLiteManager getInstance() {
		if (instance == null) instance = new SQLiteManager();
		return instance;
	}
}
