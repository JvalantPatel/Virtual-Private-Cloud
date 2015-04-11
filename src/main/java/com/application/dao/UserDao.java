package com.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.application.model.User;

public class UserDao {
	public static void addUser(String userName, String password) {
		try {
			Connection connection = DBConnection.getInstance().connection; 
			if(userName != null && password != null) {
				PreparedStatement statement = connection.
						prepareStatement(
								"INSERT INTO "
								+ "t_user (user_name, password)"
								+ "VALUES (?,?)"
								);
				
				statement.setString(1, userName);
				statement.setString(2, password);
				statement.executeUpdate();
			} else
				System.out.println("Username or password is null");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static User getUser(String userName) {
		User user = null;
		try {
			Connection connection = DBConnection.getInstance().connection; 
			if(userName != null) {
				PreparedStatement statement = connection.
						prepareStatement(
								"SELECT "
								+ "user_name, password "
								+ "FROM t_user "
								+ "WHERE "
								+ "user_name = ?"
								);
				
				statement.setString(1, userName);
				statement.executeQuery();
				
				ResultSet rs = statement.executeQuery();
				
				if(rs.first()) {
					//rs.next();
					user = new User();
					user.setUserName(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
				}
			} else
				System.out.println("Username is null");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static void addUserVM(String userName, String vmName) {
		try {
			Connection connection = DBConnection.getInstance().connection; 
			if(userName != null && vmName != null) {
				
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "user_id "
								+ "FROM t_user "
								+ "WHERE "
								+ "user_name = ?"
								);
				statement.setString(1, userName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("Invalid username");
					return;
				}
				rs.next();
				String user_id = rs.getString("user_id");
				statement = connection.
						prepareStatement(
								"INSERT INTO "
								+ "t_user_vm (user_id, vm_name)"
								+ "VALUES (?,?)"
								);
				
				statement.setString(1, user_id);
				statement.setString(2, vmName);
				statement.executeUpdate();
			} else
				System.out.println("Username or VMName is null");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static List<String> getUserVMs(String userName) {
		List<String> vmList = new ArrayList<String>();
		try {
			Connection connection = DBConnection.getInstance().connection; 
			if(userName != null) {
				
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "user_id "
								+ "FROM t_user "
								+ "WHERE "
								+ "user_name = ?"
								);
				statement.setString(1, userName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("Invalid username");
					return null;
				}
				rs.next();
				int user_id = rs.getInt("user_id");
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "vm_name "
								+ "FROM t_user_vm "
								+ "WHERE "
								+ "user_id = ?"
								);
				
				statement.setInt(1, user_id);
				rs = statement.executeQuery();
				
				while(rs.next()) {
					vmList.add(rs.getString("vm_name"));
				}
				
			} else
				System.out.println("Username is null");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vmList;
	}
	
	public boolean isVmNameValid(String userName, String vmName) {
		List<String> userVmList = getUserVMs(userName);
		boolean valid = true;
		for(String vm: userVmList){
			if(vm.equals(vmName)) {
				valid = false;
				break;
			}
		}
		return valid;
	}
}
