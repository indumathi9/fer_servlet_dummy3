
package com.rs.fer.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.DButil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {
		boolean isRegister = false;

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		try {
			connection = DButil.getConnection();

			String inputSQL = "insert into user(firstname, middlename, lastname, email, username, password, mobile) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(inputSQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getMobile());

			int numOfRecAffected = preparedStatement.executeUpdate();

			isRegister = numOfRecAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DButil.closeConnection(connection);

		}
		return isRegister;
	}

	@Override
	public int login(String username, String password) {

		Connection connection = null;

		PreparedStatement preparedStatement = null;
		try {
			connection = DButil.getConnection();

			String inputSQL = "SELECT * FROM  user WHERE username=? AND password=? ";
			preparedStatement = connection.prepareStatement(inputSQL);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return resultset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DButil.closeConnection(connection);

		}
		return 0;
	}

	@Override
	public boolean addExpense(Expense expense) {
		boolean addExpense = false;
		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// 1. To register driver ..creating the driver object

		try {

			connection = DButil.getConnection();
			// 3. create statement object

			String inputSQL = "INSERT INTO expense (type, date, price, numberofitems, total, byWhom, userid) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setString(1, expense.getType());
			preparedstatement.setString(2, expense.getDate());
			preparedstatement.setFloat(3, expense.getPrice());
			preparedstatement.setInt(4, expense.getNumOfItems());
			preparedstatement.setFloat(5, expense.getTotal());
			preparedstatement.setString(6, expense.getByWhom());
			preparedstatement.setInt(7, expense.getUserId());
			// 4. Excute the statement

			int numOfRecAffected = preparedstatement.executeUpdate();
			addExpense = numOfRecAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DButil.closeConnection(connection);

		}
		return addExpense;

	}

	@Override
	public boolean editExpense(Expense expense) {
		boolean editExpense = false;
		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// 1. To register driver ..creating the driver object

		try {

			connection = DButil.getConnection();
			// 3. create statement object

			String inputSQL = "UPDATE  expense SET type=?, date=?, price=?, numberofitems=?, total=?, byWhom=? WHERE id=?";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setString(1, expense.getType());
			preparedstatement.setString(2, expense.getDate());
			preparedstatement.setFloat(3, expense.getPrice());
			preparedstatement.setInt(4, expense.getNumOfItems());
			preparedstatement.setFloat(5, expense.getTotal());
			preparedstatement.setString(6, expense.getByWhom());
			preparedstatement.setInt(7, expense.getId());
			// 4. Excute the statement

			int numOfRecAffected = preparedstatement.executeUpdate();
			editExpense = numOfRecAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DButil.closeConnection(connection);

		}
		return editExpense;

	}

	@Override
	public boolean resetPassword(int userId, String currentPassword, String newPassword) {
		boolean resetPassword = false;
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {

			connection = DButil.getConnection();
			// 3. create statement object

			String inputSQL = "UPDATE user SET password=? WHERE id=? AND password=? ";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setString(1, newPassword);
			preparedstatement.setInt(2, userId);
			preparedstatement.setString(3, currentPassword);

			// 4. Excute the statement
			int numOfRecordAffected = preparedstatement.executeUpdate();
			resetPassword = numOfRecordAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DButil.closeConnection(connection);

		}

		return resetPassword;
	}

	@Override
	public boolean deleteExpense(int expenseid) {

		boolean deleteExpense = false;
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {

			connection = DButil.getConnection();
			// 3. create statement object

			String inputSQL = "DELETE FROM expense WHERE id=?";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, expenseid);

			// 4. Excute the statement

			int numOfRecAffected = preparedstatement.executeUpdate();
			deleteExpense = numOfRecAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DButil.closeConnection(connection);

		}
		return deleteExpense;

	}

	@Override
	public Expense getExpense(int expenseId) {
		Expense expense = null;
		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// To register driver

		try {
			connection = DButil.getConnection();
			// create statement object

			String inputSQL = "select * from expense WHERE id =?";
			preparedstatement = connection.prepareStatement(inputSQL);
			// preparedstatement.setString(1,username);
			preparedstatement.setInt(1, expenseId);

			// Excute the statement

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				int userid = resultset.getInt(8);
				expense = new Expense();

				expense.setId(id);

				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(userid);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DButil.closeConnection(connection);

		}

		return expense;
	}

	@Override
	public List<Expense> getExpenses(int userId) {

		List<Expense> expenses = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// To register driver

		try {
			connection = DButil.getConnection();
			// create statement object

			String inputSQL = "select * from expense WHERE userid=? ";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, userId);

			// Excute the statement

			ResultSet resultset = preparedstatement.executeQuery();
			Expense expense = null;
			while (resultset.next()) {

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				int userid = resultset.getInt(8);

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(userid);

				expenses.add(expense);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DButil.closeConnection(connection);

		}

		return expenses;
	}

	@Override
	public List<Expense> expenseReport(int userId, String Type, String fromDate, String toDate) {

		List<Expense> expenseReport = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// To register driver

		try {
			connection = DButil.getConnection();
			// create statement object

			String inputSQL = "SELECT * FROM expense WHERE userid=? AND type=? AND date BETWEEN ? AND ? ";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, userId);
			preparedstatement.setString(2, Type);
			preparedstatement.setString(3, fromDate);
			preparedstatement.setString(4, toDate);

			// Excute the statement

			ResultSet resultset = preparedstatement.executeQuery();
			Expense expense = null;
			while (resultset.next()) {

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int numberOfItems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String byWhom = resultset.getString(7);
				// int userid = resultset.getInt(8);

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				// expense.setUserId(userid);

				expenseReport.add(expense);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DButil.closeConnection(connection);

		}

		return expenseReport;
	}

	@Override
	public User getUser(int userId) {

		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DButil.getConnection();
			String inputSQL = "select u.* , a.* from user u left join address a on u.id=a.userid where u.id=? ";
			preparedStatement = connection.prepareStatement(inputSQL);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstname = resultSet.getString(2);
				String middlename = resultSet.getString(3);
				String lastname = resultSet.getString(4);
				String email = resultSet.getString(5);
				String username = resultSet.getString(6);
				String password = resultSet.getString(7);
				String mobile = resultSet.getString(8);

				user = new User();

				user.setId(id);
				user.setFirstName(firstname);
				user.setMiddleName(middlename);
				user.setLastName(lastname);
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				user.setMobile(mobile);

				int addressId = resultSet.getInt(9);
				String line1 = resultSet.getString(10);
				String line2 = resultSet.getString(11);
				String city = resultSet.getString(12);
				String state = resultSet.getString(13);
				String pinCode = resultSet.getString(14);
				String country = resultSet.getString(15);
				int userid = resultSet.getInt(16);

				Address address = new Address();

				address.setId(userid);
				address.setLineOne(line1);
				address.setLineTwo(line2);
				address.setCity(city);
				address.setState(state);
				address.setPinCode(pinCode);
				address.setCountry(country);
				address.setUserId(userid);

				user.setAddress(address);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DButil.closeConnection(connection);

		}

		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean isUpdate = false;

		Connection connection = null;
		PreparedStatement preparedstatement = null;

		// 1. To register driver ..creating the driver object

		try {
			connection = DButil.getConnection();

			// 3. create statement object

			connection.setAutoCommit(false);
			String inputSQL = "UPDATE USER SET firstname=?, middlename=?, lastname=?, email=?, mobile=? WHERE  id=?";
			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setString(1, user.getFirstName());
			preparedstatement.setString(2, user.getMiddleName());
			preparedstatement.setString(3, user.getLastName());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.setString(5, user.getMobile());
			preparedstatement.setInt(6, user.getId());
			// 4. Excute the statement

			int numOfRecAffected = preparedstatement.executeUpdate();
			if (numOfRecAffected <= 0) {
				System.out.println("User update is failed");
			} else {
				Address address = user.getAddress();
				int addressId = address.getId();
				if (addressId <= 0) {
					String inputSQL1 = "INSERT INTO address (line1, line2, city,state, pincode, country, userid) VALUES (?, ?, ?, ?, ?, ?, ?)";
					preparedstatement = connection.prepareStatement(inputSQL1);
					preparedstatement.setString(1, address.getLineOne());
					preparedstatement.setString(2, address.getLineTwo());
					preparedstatement.setString(3, address.getCity());
					preparedstatement.setString(4, address.getState());
					preparedstatement.setString(5, address.getPinCode());
					preparedstatement.setString(6, address.getCountry());
					preparedstatement.setInt(7, address.getId());

					int numOfRecAffected1 = preparedstatement.executeUpdate();
					if (numOfRecAffected1 >= 1) {
						isUpdate = true;
						connection.commit();
						System.out.println("Address inserted successfully");
					}
				} else {
					String inputSQL11 = "UPDATE address SET line1=?, line2=?, city=?,state=?, pincode=?, country=? where id=?";
					preparedstatement = connection.prepareStatement(inputSQL11);
					preparedstatement.setString(1, address.getLineOne());
					preparedstatement.setString(2, address.getLineTwo());
					preparedstatement.setString(3, address.getCity());
					preparedstatement.setString(4, address.getState());
					preparedstatement.setString(5, address.getPinCode());
					preparedstatement.setString(6, address.getCountry());
					preparedstatement.setInt(7, address.getId());

					int numOfRecAffected11 = preparedstatement.executeUpdate();
					if (numOfRecAffected11 >= 1) {
						isUpdate = true;

						connection.commit();
						System.out.println("Address updated successfully");
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

			// 5. close the connection
		} finally {

			DButil.closeConnection(connection);

		}

		return isUpdate;
	}

}
