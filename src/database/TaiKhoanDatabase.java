package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import chinhSuaThongTin.ChinhSuaThongTin;
import taiKhoan.TaiKhoan;

public class TaiKhoanDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	
	Connection connection;
	Database database;
	
	public TaiKhoanDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void themTaiKhoan(TaiKhoan taiKhoan) throws Exception {
//		String command = "INSERT INTO TaiKhoan (TenTaiKhoan, MatKhau, HoTen, NamSinh, DienThoai) " + "VALUES(?, ?, ?, ?, ?)";
//		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
//		statement.setString(1, taiKhoan.getTenTaiKhoan());
//		statement.setString(2, taiKhoan.getMatKhau());
//		statement.setString(3, taiKhoan.getHoTen());
//		statement.setInt(4, taiKhoan.getNamSinh());
//		statement.setString(5, taiKhoan.getDienThoai());
//		statement.executeUpdate();
//	}
	
	public void themTaiKhoan(String TenTaiKhoan, String MatKhau, String HoTen, String NamSinh, String DienThoai, String Cap) throws Exception {
		String command = "INSERT INTO TaiKhoan (TenTaiKhoan, MatKhau, HoTen, NamSinh, DienThoai, Cap) " + "VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, TenTaiKhoan);
		statement.setString(2, MatKhau);
		statement.setString(3, HoTen);
		statement.setString(4, NamSinh);
		statement.setString(5, DienThoai);
		statement.setString(6, Cap);
		statement.executeUpdate();
	}
	
	public String quenMatKhau(String TenTaiKhoan, String HoTen, String NamSinh, String DienThoai) throws Exception {
		String command = "SELECT MatKhau FROM TaiKhoan WHERE TenTaiKhoan = '" + TenTaiKhoan + "' and HoTen = '"+HoTen+"' and NamSinh = '"+NamSinh+"' and DienThoai = '"+DienThoai+"'";
		ResultSet rs = connection.prepareStatement(command).executeQuery();
		if(rs.next()) {
			if(rs != null) {
				return rs.getString(1);
			}
		}
		return "";
	}
	
	public void suaTaiKhoan(TaiKhoan taiKhoan, String tenTaiKhoanCanSua) throws Exception {
		String command = "UPDATE TaiKhoan SET HoTen = ?, NamSinh = ?, DienThoai = ? WHERE TenTaiKhoan = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, taiKhoan.getHoTen());
		statement.setInt(2, taiKhoan.getNamSinh());
		statement.setString(3, taiKhoan.getDienThoai());
		statement.setString(4, tenTaiKhoanCanSua);
		statement.executeUpdate();
	}
	
	public void doiMatKhau(String tenTaiKhoan, String matKhauMoi) throws Exception {
		String command = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenTaiKhoan = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, matKhauMoi);
		statement.setString(2, tenTaiKhoan);
		statement.executeUpdate();
	}
	
	public String getMatKhau(String tenTaiKhoan) throws Exception {
		String command = "SELECT MatKhau FROM TaiKhoan WHERE TenTaiKhoan = '" + tenTaiKhoan + "'";
		ResultSet rs = connection.prepareStatement(command).executeQuery();
		if(rs.next()) {
			if(rs != null) {
				return rs.getString(1);
			}
		}
		return "";
	}
	
	public boolean taiKhoanDaTonTai(String tenTaiKhoan) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("TaiKhoan", "TenTaiKhoan", tenTaiKhoan);
		if(rs.next()) {
			if(rs.getString(1) != null) {
				return true;
			}
		}
		return false;
	}
	
	public TaiKhoan getTaiKhoan(String tenTaiKhoan) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("TaiKhoan", "TenTaiKhoan", tenTaiKhoan);
		if(rs.next()) {
			String matKhau = rs.getString(2);
			String hoTen = rs.getString(3);
			int namSinh = Integer.parseInt(rs.getString(4));
			String dienThoai = rs.getString(5);
			int cap = Integer.parseInt(rs.getString(6));
			
			TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau, hoTen, namSinh, dienThoai, cap);
			return taiKhoan;
		}
		return null;
	}
	
}
