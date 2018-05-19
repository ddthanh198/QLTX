package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import chinhSuaThongTin.ChinhSuaThongTin;
import date.Date;

public class Database {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	long donGiaCoc = ChinhSuaThongTin.donGiaCoc;
	long donGiaPhat = ChinhSuaThongTin.donGiaPhat;
	
	public Connection connection;
	
	public Database() {
		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public ResultSet getData(String tableName) throws ClassNotFoundException, SQLException {
		String command = "SELECT * FROM " + tableName;
		return connection.prepareStatement(command).executeQuery();
	}
	
	public ResultSet getDataWithCondition(String tableName, String conditionalColumn, String conditionalValue) throws ClassNotFoundException, SQLException {
		String command = "SELECT * FROM " + tableName + " WHERE " + conditionalColumn + " LIKE '%" + conditionalValue + "%'";
		return connection.prepareStatement(command).executeQuery();
	}
	
	public ResultSet getDataWithExactCondition(String tableName, String conditionalColumn, String conditionalValue) throws ClassNotFoundException, SQLException {
		String command = "SELECT * FROM " + tableName + " WHERE " + conditionalColumn + " = '" + conditionalValue + "'";
		return connection.prepareStatement(command).executeQuery();
	}
	
	
	
	/**
	 * 
	 * 			//////////////////////////////////////
	 * 			//////////////////////////////////////
	 * 			//////////////////////////////////////
	 * 			//////////////////////////////////////
	 * 
	 */
	public void updateDatabaseTables() throws Exception {
		updateXeDatabaseTable();
		updateThueXeDatabaseTable();
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void updateXeDatabaseTable() throws Exception {
		updateTrangThaiColumn();
	}
	
	public void setTrangThai(String maXe, String trangThai) throws Exception {
		String command = "UPDATE Xe Set TrangThai = '" + trangThai + "' WHERE MaXe = '" + maXe + "'";
		connection.prepareStatement(command).executeUpdate();
	}
	
	public boolean xeDaDuocMuon(String maXe) throws Exception {
		String command = "SELECT MaThueXe, NgayTra FROM ChiTietThueXe WHERE MaXe = '" + maXe + "'";
		ResultSet rs = connection.prepareStatement(command).executeQuery();
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			String ngayTra = rs.getString(2);
			if(maThueXe != null && ngayTra == null) {
				return true;
			} 
		}
		return false;
	}
	
	public void updateTrangThaiColumn() throws Exception {
		ResultSet rs = getData("Xe");
		while(rs.next()) {
			String maXe = rs.getString(1);
			if(xeDaDuocMuon(maXe)) {
				setTrangThai(maXe, "Khong");
			} else {
				setTrangThai(maXe, "Co");
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void updateThueXeDatabaseTable() throws Exception {
		updateTienCocColumn();
		updateThanhTienColumn();
		updateTongTienPhatColumn();
	}
	
	public void updateTienCocColumn() throws Exception {
		ResultSet rs = getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateTienCocFor(maThueXe);
		}
	}
	
	public void updateTienCocFor(String maThueXe) throws Exception {
		long tienCoc = donGiaCoc * getSoLuongXeThueOf(maThueXe);
		setTienCocFor(maThueXe, tienCoc);
	}
	
	public long getSoLuongXeThueOf(String maThueXe) throws Exception {
		long soLuongXeThue = 0;
		ResultSet rs = getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			soLuongXeThue++;
		}
		return soLuongXeThue; 
	}

	
	public void setTienCocFor(String maThueXe, long tienCoc) throws Exception {
		String command = "UPDATE ThueXe Set TienCoc = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienCoc);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	//////////////////////////////////////////////////////////
	public void updateThanhTienColumn() throws Exception {
		ResultSet rs = getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateThanhTienOf(maThueXe);
		}
	}
	
	public void updateThanhTienOf(String maThueXe) throws Exception {
		long tongTienThue = getTongTienThueOf(maThueXe);
		setThanhTienFor(maThueXe, tongTienThue);
	}
	
	public void setThanhTienFor(String maThueXe, long thanhTien) throws Exception {
		String command = "UPDATE ThueXe Set ThanhTien = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, thanhTien);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	public long getTongTienThueOf(String maThueXe) throws Exception {
		updateTienThueColumnFor(maThueXe);
		ResultSet rs = getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		long tongTienThue = 0;
		while(rs.next()) {
			String maXe = rs.getString(2);
			long tienThue = getTienThue(maThueXe, maXe);
			tongTienThue += tienThue;
		}
		return tongTienThue;
	}
	
	public void updateTienThueColumnFor(String maThueXe) throws Exception {
		ResultSet rs = getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			String maXe = rs.getString(2);
			updateTienThueFor(maThueXe, maXe);
		}
	}
	
	public void updateTienThueFor(String maThueXe, String maXe) throws Exception {
		ThueXeDatabase thueXeDatabase = new ThueXeDatabase();
		Date ngayThue = thueXeDatabase.getNgayThue(maThueXe);
		Date ngayHenTra = thueXeDatabase.getNgayHenTra(maThueXe);
		long tienThue = tinhTienThue(maXe, ngayThue, ngayHenTra);
		setTienThueFor(maThueXe, maXe, tienThue);
	}
	
	public long tinhTienThue(String maXe, Date ngayThue, Date ngayHenTra) throws Exception {
		XeDatabase xeDatabase = new XeDatabase();
		long donGiaThue = xeDatabase.getDonGiaThue(maXe);
		long soNgay = Date.getNumberOfDaysBetween(ngayThue, ngayHenTra);
		long tienThue = donGiaThue * soNgay;
		return tienThue;
	}
	
	public void setTienThueFor(String maThueXe, String maXe, long tienThue) throws Exception {
		String command = "UPDATE ChiTietThueXe Set TienThue = ? WHERE MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienThue);
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
	
	public long getTienThue(String maThueXe, String maXe) throws Exception {
		String command = "SELECT TienThue FROM ChiTietThueXe WHERE MaThueXe = ? AND MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maThueXe);
		statement.setString(2, maXe);
		ResultSet rs =  statement.executeQuery();
		if(rs.next()) {
			return Long.parseLong(rs.getString(1));
		}
		return 0;
	}
	
	
	
	//////////////////////////////////////////////////////////
	public void updateTongTienPhatColumn() throws Exception {
		ResultSet rs = getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateTongTienPhatFor(maThueXe);
		}
	}
	
	public void updateTongTienPhatFor(String maThueXe) throws Exception {
		long tongTienPhat = getTongTienPhatOf(maThueXe);
		setTongTienPhatFor(maThueXe, tongTienPhat);
	}
	
	public void setTongTienPhatFor(String maThueXe, long tongTienPhat) throws Exception {
		String command = "UPDATE ThueXe Set TongTienPhat = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tongTienPhat);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	public long getTongTienPhatOf(String maThueXe) throws Exception {
		updateTienPhatColumnFor(maThueXe);
		ResultSet rs = getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		long tongTienPhat = 0;
		while(rs.next()) {
			String maXe = rs.getString(2);
			long tienPhat = getTienPhat(maThueXe, maXe);
			tongTienPhat += tienPhat;
		}
		return tongTienPhat;
	}
	
	public void updateTienPhatColumnFor(String maThueXe) throws Exception {
		ResultSet rs = getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			String maXe = rs.getString(2);
			updateTienPhatFor(maThueXe, maXe);
		}
	}
	
	public void updateTienPhatFor(String maThueXe, String maXe) throws Exception {
		ThueXeDatabase thueXeDatabase = new ThueXeDatabase();
		Date ngayHenTra = thueXeDatabase.getNgayHenTra(maThueXe);
		Date ngayTra = getNgayTra(maThueXe, maXe);
		long tienPhat = 0;
		if(ngayTra != null) {
			tienPhat = tinhTienPhat(ngayHenTra, ngayTra);
			updateGhiChuFor(maThueXe, maXe, ngayHenTra, ngayTra);
		}
		setTienPhatFor(maThueXe, maXe, tienPhat);
	}
	
	public long tinhTienPhat(Date ngayHenTra, Date ngayTra) throws Exception {
		long soNgayTraMuon = Date.getNumberOfDaysBetween(ngayHenTra, ngayTra);
		if(soNgayTraMuon <= 0) {
			return 0;
		}
		return donGiaPhat * soNgayTraMuon;
	}
	
	public void setTienPhatFor(String maThueXe, String maXe, long tienPhat) throws Exception {
		String command = "UPDATE ChiTietThueXe Set TienPhat = ? WHERE MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienPhat);
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
	
	public Date getNgayTra(String maThueXe, String maXe) throws Exception {
		String command = "SELECT NgayTra FROM ChiTietThueXe WHERE MaThueXe = ? AND MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maThueXe);
		statement.setString(2, maXe);
		ResultSet rs =  statement.executeQuery();
		if(rs.next()) {
			String ngayTraString = rs.getString(1);
			if(ngayTraString == null) {
				return null;
			}
			Date ngayTra = new Date(ngayTraString);
			return ngayTra;
		}
		return null;
	}
	
	public long getTienPhat(String maThueXe, String maXe) throws Exception {
		String command = "SELECT TienPhat FROM ChiTietThueXe WHERE MaThueXe = ? AND MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maThueXe);
		statement.setString(2, maXe);
		ResultSet rs =  statement.executeQuery();
		if(rs.next()) {
			return Long.parseLong(rs.getString(1));
		}
		return 0;
	}
	
	public void updateGhiChuFor(String maThueXe, String maXe, Date ngayHenTra, Date ngayTra) throws Exception {
		long soNgayTraMuon = Date.getNumberOfDaysBetween(ngayHenTra, ngayTra);
		String ghiChu = "Tra xe dung han.";
		if(soNgayTraMuon > 0) {
			ghiChu = "Tra xe muon " + soNgayTraMuon + " ngay.";
		}
		setGhiChuFor(maThueXe, maXe, ghiChu);
	}
	
	public void setGhiChuFor(String maThueXe, String maXe, String ghiChu) throws Exception {
		String command = "UPDATE ChiTietThueXe Set GhiChu = ? WHERE MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, ghiChu);
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
}