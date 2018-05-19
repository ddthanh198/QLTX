package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import chinhSuaThongTin.ChinhSuaThongTin;
import nhanVien.NhanVien;

public class NhanVienDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	
	public Connection connection;
	Database database;
	
	public NhanVienDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void themNhanVien(NhanVien nhanVien) throws Exception {
		String command = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, GioiTinh, NamSinh, ViTri, DT, Email, DiaChi)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, nhanVien.getMaNhanVien());
		statement.setString(2, nhanVien.getTenNhanVien());
		statement.setString(3, nhanVien.getGioiTinh());
		statement.setInt(4, nhanVien.getNamSinh());
		statement.setString(5, nhanVien.getViTri());
		statement.setString(6, nhanVien.getDT());
		statement.setString(7, nhanVien.getEmail());
		statement.setString(8, nhanVien.getDiaChi());
		statement.executeUpdate();
	}
	
	public void suaNhanVien(NhanVien nhanVien, String maNhanVienCanSua) throws Exception {
		String command = "UPDATE NhanVien SET MaNhanVien = ?, TenNhanVien  = ?, GioiTinh = ?, NamSinh = ?, ViTri = ?, DT = ?, Email = ?, DiaChi = ? where MaNhanVien = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, nhanVien.getMaNhanVien());
		statement.setString(2, nhanVien.getTenNhanVien());
		statement.setString(3, nhanVien.getGioiTinh());
		statement.setInt(4, nhanVien.getNamSinh());
		statement.setString(5, nhanVien.getViTri());
		statement.setString(6, nhanVien.getDT());
		statement.setString(7, nhanVien.getEmail());
		statement.setString(8, nhanVien.getDiaChi());
		statement.setString(9, maNhanVienCanSua);
		statement.executeUpdate();
		
		command = "UPDATE ThueXe SET MaNhanVien = ? where MaNhanVien = ?";
		statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, nhanVien.getMaNhanVien());
		statement.setString(2, maNhanVienCanSua);
		statement.executeUpdate();
	}
	
	public void xoaNhanVien(String maNhanVien) throws Exception {
		String command = "DELETE FROM NhanVien WHERE MaNhanVien = '" + maNhanVien + "'";
		connection.prepareStatement(command).executeUpdate();
		command = "DELETE FROM ThueXe WHERE MaNhanVien = '" + maNhanVien + "'";
		connection.prepareStatement(command).executeUpdate();
	}
	
	public NhanVien getNhanVien(String maNhanVien) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("NhanVien", "MaNhanVien", maNhanVien);
		if(rs.next()) {
			String tenNhanVien = rs.getString(2);
			String gioiTinh = rs.getString(3);
			String namSinh = rs.getString(4);
			String viTri = rs.getString(5);
			String DT = rs.getString(6);
			String email = rs.getString(7);
			String diaChi = rs.getString(8);
			
			NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, Integer.parseInt(namSinh), viTri, DT, email, diaChi);
			return nhanVien;
		}
		return null;
	}
	
	public ResultSet getMaNhanVienResultSet() throws Exception {
		String command = "SELECT MaNhanVien FROM NhanVien";
		return connection.prepareStatement(command).executeQuery();
	}
	
	public String getTenNhanVien(String maNhanVien) throws Exception {
		NhanVien nhanVien = getNhanVien(maNhanVien);
		if(nhanVien != null) {
			return nhanVien.getTenNhanVien();
		} 
		return "";
	}
}
