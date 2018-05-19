package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import chinhSuaThongTin.ChinhSuaThongTin;
import khachHang.KhachHang;

public class KhachHangDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	
	public Connection connection;
	Database database;
	
	public KhachHangDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void themKhachHang(KhachHang khachHang) throws Exception {
		String command = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, NamSinh, CMND, DT, Email, DiaChi)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, khachHang.getMaKhachHang());
		statement.setString(2, khachHang.getTenKhachHang());
		statement.setString(3, khachHang.getGioiTinh());
		statement.setInt(4, khachHang.getNamSinh());
		statement.setString(5, khachHang.getCMND());
		statement.setString(6, khachHang.getDT());
		statement.setString(7, khachHang.getEmail());
		statement.setString(8, khachHang.getDiaChi());
		statement.executeUpdate();
	}
	
	public void suaKhachHang(KhachHang khachHang, String maKhachHangCanSua) throws Exception {
		String command = "UPDATE KhachHang SET MaKhachHang = ?, TenKhachHang = ?, GioiTinh = ?, NamSinh = ?, CMND = ?, DT = ?, Email = ?, DiaChi = ? WHERE MaKhachHang = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, khachHang.getMaKhachHang());
		statement.setString(2, khachHang.getTenKhachHang());
		statement.setString(3, khachHang.getGioiTinh());
		statement.setInt(4, khachHang.getNamSinh());
		statement.setString(5, khachHang.getCMND());
		statement.setString(6, khachHang.getDT());
		statement.setString(7, khachHang.getEmail());
		statement.setString(8, khachHang.getDiaChi());
		statement.setString(9, maKhachHangCanSua);
		statement.executeUpdate();
		
		command = "UPDATE ThueXe SET MaKhachHang = ? WHERE MaKhachHang = ?";
		statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, khachHang.getMaKhachHang());
		statement.setString(2, maKhachHangCanSua);
		statement.executeUpdate();
	}
	
	public void xoaKhachHang(String maKhachHang) throws Exception {
		String command = "DELETE FROM KhachHang WHERE MaKhachHang = '" + maKhachHang + "'";
		connection.prepareStatement(command).executeUpdate();
		command = "DELETE FROM ThueXe WHERE MaKhachHang = '" + maKhachHang + "'";
		connection.prepareStatement(command).executeUpdate();
	}
	
	public KhachHang getKhachHang(String maKhachHang) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("KhachHang", "MaKhachHang", maKhachHang);
		if(rs.next()) {
			String tenKhachHang = rs.getString(2);
			String gioiTinh = rs.getString(3);
			String namSinh = rs.getString(4);
			String CMND = rs.getString(5);
			String DT = rs.getString(6);
			String email = rs.getString(7);
			String diaChi = rs.getString(8);
			
			KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, Integer.parseInt(namSinh), CMND, DT, email, diaChi);
			return khachHang;
		}
		return null;
	}
	
	public ResultSet getMaKhachHangResultSet() throws Exception {
		String command = "SELECT MaKhachHang FROM KhachHang";
		return connection.prepareStatement(command).executeQuery();
	}
	
	public String getTenKhachHang(String maKhachHang) throws Exception {
		KhachHang khachHang = getKhachHang(maKhachHang);
		if(khachHang != null) {
			return khachHang.getTenKhachHang();
		} 
		return "";
	}
}
