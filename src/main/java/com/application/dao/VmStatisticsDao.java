package com.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VmStatisticsDao {
	public static Map<String, Long> getVMs() {
		Map<String, Long> vmNames = new HashMap<String, Long>();
		try {
			Connection connection = DBConnection.getInstance().connection; 

				
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "id"
								+ ", vm_name "
								+ "FROM "
								+ "t_user_vm"
								);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : getVMs : No result");
					return vmNames;
				}
				while(rs.next()){
					vmNames.put(rs.getString("vm_name"), rs.getLong("id"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vmNames;
	}
	
	public static Map<String, Long> getVMPropertyThresholdValues(String vmName) {
		Map<String, Long> vmPropThresholdMap = new HashMap<String, Long>();
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "property.name"
								+ ", threshold.threshold_value "
								+ "FROM "
								+ "t_vm_property_threshold threshold"
								+ ", t_properties property"
								+ ", t_user_vm vm "
								+ "WHERE "
								+ "threshold.property_id = property.id "
								+ "AND vm.id = threshold.vm_id "
								+ "AND vm.vm_name = ?"
								);
				statement.setString(1, vmName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : getVMPropertyThresholdValues : No result");
					return vmPropThresholdMap;
				}
				while(rs.next()){
					vmPropThresholdMap.put(rs.getString("name"), rs.getLong("threshold_value"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return vmPropThresholdMap;
	}
	
	public static boolean isVmPropertyLimitExceeded(long vmId, long propertyId) {
		boolean result = false;
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT "
								+ "limit_exceed "
								+ "FROM "
								+ "t_vm_property_threshold "
								+ "WHERE "
								+ "vm_id = ? "
								+ "AND property_id = ?"
								);
				statement.setLong(1, vmId);
				statement.setLong(2, propertyId);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : isVMPropertyLimitExceeded : No result");
					return result;
				}
				rs.next();
				if(rs.getInt("limit_exceed") == 1)
					result = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static void setVmPropertyLimitExceeded(long vmId, long propertyId, boolean value) {
		int val;
		if(value) val = 1; 
		else val = 0;
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"UPDATE t_vm_property_threshold "
								+ "SET "
								+ "limit_exceed = ? "
								+ "WHERE "
								+ "vm_id = ? "
								+ "AND property_id = ?;"
								);
				statement.setLong(1, val);
				statement.setLong(2, vmId);
				statement.setLong(3, propertyId);
				int rs = statement.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void setVmPropertyThreshold(String vmName, String property, int value) {
		int vmId = getVmIdFromName(vmName);
		int propId = getPropertyIdFromName(property);
		if(vmId != -1 && propId != -1) {
			try {
				Connection connection = DBConnection.getInstance().connection; 
					PreparedStatement statement = null;
					
					statement = connection.
							prepareStatement(
									"INSERT INTO "
									+ "t_vm_property_threshold ("
									+ "vm_id"
									+ ", property_id"
									+ ", threshold_value"
									+ ", limit_exceed"
									+ ") VALUES ("
									+ "?, ?, ?, ?"
									+ ")"
									);
					statement.setInt(1, vmId);
					statement.setInt(2, propId);
					statement.setInt(3, value);
					statement.setInt(4, 0);
					int rs = statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("VmStatisticsDao : setVmPropertyThreshold : Invalid vmName or property");
		}
		
	}
	
	private static int getVmIdFromName(String vmName) {
		int result = -1;
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT id FROM t_user_vm WHERE vm_name = ?"
								);
				statement.setString(1, vmName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : getVmIdFromName : No result");
					return result;
				}
				rs.next();
				result = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	private static int getPropertyIdFromName(String propName) {
		int result = -1;
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT id FROM t_properties WHERE name = ?"
								);
				statement.setString(1, propName);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : getPropertyIdFromName : No result");
					return result;
				}
				rs.next();
				result = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static Map<String, Long> getVmStatProperties() {
		Map<String, Long> result = new HashMap<String, Long>();
		try {
			Connection connection = DBConnection.getInstance().connection; 
				PreparedStatement statement = null;
				
				statement = connection.
						prepareStatement(
								"SELECT id, name FROM t_properties"
								);
				ResultSet rs = statement.executeQuery();
				if(rs == null) {
					System.out.println("VmStatisticsDao : getPropertyIdFromName : No result");
					return result;
				}
				while(rs.next()){
					result.put(rs.getString("name"), rs.getLong("id"));
					//System.out.println(rs.getString("name") + "---"+ rs.getLong("id"));
				}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
