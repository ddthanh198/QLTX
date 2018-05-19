package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import chinhSuaThongTin.ChinhSuaThongTin;
import date.Date;
import thueXe.ThueXe;

public class ThueXeDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	long donGiaCoc = ChinhSuaThongTin.donGiaCoc;
	
	public static Connection connection;
	Database database;
	
	public ThueXeDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void themThueXe(ThueXe thueXe) throws Exception {
		String command = "INSERT INTO ThueXe (MaThueXe, MaKhachHang, MaNhanVien, NgayThue, NgayHenTra, TienCoc, ThanhTien, TongTienPhat)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, thueXe.getMaThueXe());
		statement.setString(2, thueXe.getMaKhachHang());
		statement.setString(3, thueXe.getMaNhanVien());
		statement.setString(4, thueXe.getNgayThue().getDateString());
		statement.setString(5, thueXe.getNgayHenTra().getDateString());
		statement.setString(6, String.valueOf(thueXe.getTienCoc()));
		statement.setString(7, String.valueOf(thueXe.getThanhTien()));
		statement.setString(8, String.valueOf(thueXe.getTongTienPhat()));
		statement.executeUpdate();
	}
	
	public void suaThueXe(ThueXe thueXe, String maThueXeCanSua) throws Exception {
		String command = "UPDATE ThueXe SET MaThueXe = ?, MaKhachHang  = ?, MaNhanVien = ?, NgayThue = ?, NgayHenTra = ? where MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, thueXe.getMaThueXe());
		statement.setString(2, thueXe.getMaKhachHang());
		statement.setString(3, thueXe.getMaNhanVien());
		statement.setString(4, thueXe.getNgayThue().getDateString());
		statement.setString(5, thueXe.getNgayHenTra().getDateString());
		statement.setString(6, maThueXeCanSua);
		statement.executeUpdate();
		
		command = "UPDATE ChiTietThueXe SET MaThueXe = ? where MaThueXe = ?";
		statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, thueXe.getMaThueXe());
		statement.setString(2, maThueXeCanSua);
		statement.executeUpdate();
	}
	
	public void xoaThueXe(String maThueXe) throws Exception {
		String command = "DELETE FROM ThueXe WHERE MaThueXe = '" + maThueXe + "'";
		connection.prepareStatement(command).executeUpdate();
		command = "DELETE FROM ChiTietThueXe WHERE MaThueXe = '" + maThueXe + "'";
		connection.prepareStatement(command).executeUpdate();
	}
	
	public ThueXe getThueXe(String maThueXe) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("ThueXe	", "MaThueXe", maThueXe);
		if(rs.next()) {
			String maKhachHang = rs.getString(2);
			String maNhanVien = rs.getString(3);
			String ngayThue = rs.getString(4);
			String ngayHenTra = rs.getString(5);
			long tienCoc = Long.parseLong(rs.getString(6));
			long thanhTien = Long.parseLong(rs.getString(7));
			long tongTienPhat = Long.parseLong(rs.getString(8));
			
			ThueXe thueXe = new ThueXe(maThueXe, maKhachHang, maNhanVien, new Date(ngayThue), new Date(ngayHenTra), tienCoc, thanhTien, tongTienPhat);
			return thueXe;
		}
		return null;
	}
	
	public void setThanhTienFor(String maThueXe, long thanhTien) throws Exception {
		String command = "UPDATE ThueXe Set ThanhTien = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, thanhTien);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	public void setTienCocFor(String maThueXe, long tienCoc) throws Exception {
		String command = "UPDATE ThueXe Set TienCoc = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienCoc);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	public void setTongTienPhatFor(String maThueXe, long tongTienPhat) throws Exception {
		String command = "UPDATE ThueXe Set TongTienPhat = ? WHERE MaThueXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tongTienPhat);
		statement.setString(2, maThueXe);
		statement.executeUpdate();
	}
	
	public String getMaKhachHang(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getMaKhachHang();
		}
		return "";
	}
	
	public String getMaNhanVien(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getMaNhanVien();
		}
		return null;
	}
	
	public Date getNgayThue(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getNgayThue();
		}
		return null;
	}
	
	public Date getNgayHenTra(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getNgayHenTra();
		}
		return null;
	}
	
	public long getTienCoc(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getTienCoc();
		}
		return 0;
	}
	
	public long getTongTienPhat(String maThueXe) throws Exception {
		ThueXe thueXe = getThueXe(maThueXe);
		if(thueXe != null) {
			return thueXe.getTongTienPhat();
		}
		return 0;
	}
	
	public void updateThanhTienOf(String maThueXe) throws Exception {
		ChiTietThueXeDatabase chiTietThueXeDatabase = new ChiTietThueXeDatabase();
		long tongTienThue = chiTietThueXeDatabase.getTongTienThueOf(maThueXe);
		setThanhTienFor(maThueXe, tongTienThue);
	}
	
	public void updateThanhTienColumn() throws Exception {
		ResultSet rs = database.getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateThanhTienOf(maThueXe);
		}
	}
	
	public void updateTienCocColumn() throws Exception {
		ResultSet rs = database.getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateTienCocFor(maThueXe);
		}
	}
	
	public void updateTienCocFor(String maThueXe) throws Exception {
		ChiTietThueXeDatabase chiTietThueXeDatabase = new ChiTietThueXeDatabase();
		long tienCoc = donGiaCoc * chiTietThueXeDatabase.getSoLuongXeThueOf(maThueXe);
		setTienCocFor(maThueXe, tienCoc);
	}
	
	public void updateTongTienPhatColumn() throws Exception {
		ResultSet rs = database.getData("ThueXe");
		while(rs.next()) {
			String maThueXe = rs.getString(1);
			updateTongTienPhatFor(maThueXe);
		}
	}
	
	public void updateTongTienPhatFor(String maThueXe) throws Exception {
		ChiTietThueXeDatabase chiTietThueXeDatabase = new ChiTietThueXeDatabase();
		long tongTienPhat = chiTietThueXeDatabase.getTongTienPhatOf(maThueXe);
		setTongTienPhatFor(maThueXe, tongTienPhat);
	}
	
	public void updateThueXeDatabaseTable() throws Exception {
		updateTienCocColumn();
		updateThanhTienColumn();
		updateTongTienPhatColumn();
	}
	
	public static boolean daTraHetXe(String maThueXe) throws Exception {
		String command = "SELECT MaXe, NgayTra FROM ChiTietThueXe WHERE MaThueXe = '" + maThueXe + "'";
		ResultSet rs = connection.prepareStatement(command).executeQuery();
		while(rs.next()) {
			String maXe = rs.getString(1);
			String ngayTra = rs.getString(2);
			if(maXe != null && ngayTra == null) {
				return false;
			} 
		}
		return true;
	}
}
