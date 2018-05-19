package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import chiTietThueXe.ChiTietThueXe;
import chinhSuaThongTin.ChinhSuaThongTin;
import date.Date;

public class ChiTietThueXeDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	long donGiaPhat = ChinhSuaThongTin.donGiaPhat;
	
	public Connection connection;
	Database database;
	
	public ChiTietThueXeDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void themChiTietThueXe(String maThueXe, String maXe) throws Exception {
		String  command = "INSERT INTO ChiTietThueXe (MaThueXe, MaXe)" + "VALUES(?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maThueXe);
		statement.setString(2, maXe);
		statement.executeUpdate();
	}
	
	public void suaChiTietThueXe_daTra(String maXe, Date ngayTra, String maThueXeCanSua, String maXeCanSua) throws Exception {
		String command = "UPDATE ChiTietThueXe SET MaXe = ?, NgayTra= ? where MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maXe);
		statement.setString(2, ngayTra.getDateString());	
		statement.setString(3, maThueXeCanSua);
		statement.setString(4, maXeCanSua);
		statement.executeUpdate();
	}
	
	public void suaChiTietThueXe_chuaTra(String maXe, String maThueXeCanSua, String maXeCanSua) throws Exception {
		String command = "UPDATE ChiTietThueXe SET MaXe = ? where MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maXe);
		statement.setString(2, maThueXeCanSua);
		statement.setString(3, maXeCanSua);
		statement.executeUpdate();
	}
	
	public void xoaChiTietThueXe(String maThueXe, String maXe) throws Exception {
		String command = "DELETE FROM ChiTietThueXe  WHERE MaThueXe = ? AND MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, maThueXe);
		statement.setString(2, maXe);
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
	
	
	public void setTienThueFor(String maThueXe, String maXe, long tienThue) throws Exception {
		String command = "UPDATE ChiTietThueXe Set TienThue = ? WHERE MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienThue);
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
	
	public void setTienPhatFor(String maThueXe, String maXe, long tienPhat) throws Exception {
		String command = "UPDATE ChiTietThueXe Set TienPhat = ? WHERE MaThueXe = ? and MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setLong(1, tienPhat);
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
	
	public long tinhTienThue(String maXe, Date ngayThue, Date ngayHenTra) throws Exception {
		XeDatabase xeDatabase = new XeDatabase();
		long donGiaThue = xeDatabase.getDonGiaThue(maXe);
		long soNgay = Date.getNumberOfDaysBetween(ngayThue, ngayHenTra);
		long tienThue = donGiaThue * soNgay;
		return tienThue;
	}
	
	public long tinhTienPhat(Date ngayHenTra, Date ngayTra) throws Exception {
		long soNgayTraMuon = Date.getNumberOfDaysBetween(ngayHenTra, ngayTra);
		if(soNgayTraMuon <= 0) {
			return 0;
		}
		return donGiaPhat * soNgayTraMuon;
	}
	
	public void updateTienThueFor(String maThueXe, String maXe) throws Exception {
		ThueXeDatabase thueXeDatabase = new ThueXeDatabase();
		Date ngayThue = thueXeDatabase.getNgayThue(maThueXe);
		Date ngayHenTra = thueXeDatabase.getNgayHenTra(maThueXe);
		long tienThue = tinhTienThue(maXe, ngayThue, ngayHenTra);
		setTienThueFor(maThueXe, maXe, tienThue);
	}
	
	public void updateTienPhatFor(String maThueXe, String maXe) throws Exception {
		ThueXeDatabase thueXeDatabase = new ThueXeDatabase();
		Date ngayHenTra = thueXeDatabase.getNgayHenTra(maThueXe);
		Date ngayTra = getNgayTra(maThueXe, maXe);
		long tienPhat = 0;
		if(ngayTra != null) {
			tienPhat = tinhTienPhat(ngayHenTra, ngayTra);
		}
		setTienPhatFor(maThueXe, maXe, tienPhat);
	}
	
	public void updateTienThueColumnFor(String maThueXe) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			String maXe = rs.getString(2);
			updateTienThueFor(maThueXe, maXe);
		}
	}
	
	public void updateTienPhatColumnFor(String maThueXe) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			String maXe = rs.getString(2);
			updateTienPhatFor(maThueXe, maXe);
		}
	}
	
	public long getTongTienThueOf(String maThueXe) throws Exception {
		updateTienThueColumnFor(maThueXe);
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		long tongTienThue = 0;
		while(rs.next()) {
			String maXe = rs.getString(2);
			long tienThue = getTienThue(maThueXe, maXe);
			tongTienThue += tienThue;
		}
		return tongTienThue;
	}
	
	public long getTongTienPhatOf(String maThueXe) throws Exception {
		updateTienPhatColumnFor(maThueXe);
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		long tongTienPhat = 0;
		while(rs.next()) {
			String maXe = rs.getString(2);
			long tienPhat = getTienPhat(maThueXe, maXe);
			tongTienPhat += tienPhat;
		}
		return tongTienPhat;
	}
	
	public long getSoLuongXeThueOf(String maThueXe) throws Exception {
		long soLuongXeThue = 0;
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		while(rs.next()) {
			soLuongXeThue++;
		}
		return soLuongXeThue; 
	}
	
	public void setNgayTraFor(String maThueXe, String maXe, Date ngayTra) throws Exception {
		String command = "UPDATE ChiTietThueXe SET NgayTra = ? WHERE MaThueXe = ? AND MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, ngayTra.getDateString());
		statement.setString(2, maThueXe);
		statement.setString(3, maXe);
		statement.executeUpdate();
	}
	
	public boolean xeDaDuocTra(String maThueXe, String maXe) throws Exception {
		Date ngayTra = getNgayTra(maThueXe, maXe);
		if(ngayTra != null) {
			return true;
		}
		return false;
	}
}
