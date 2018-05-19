package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import chinhSuaThongTin.ChinhSuaThongTin;
import date.Date;
import xe.Xe;

public class XeDatabase {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	
	public Connection connection;
	Database database;
	
	public XeDatabase() {
		try {
//			Class.forName(driver);
//			connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
			this.connection = database.connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void themXe(Xe xe) throws Exception {
		String command = "INSERT INTO Xe (MaXe, TenXe, LoaiXe, NamSanXuat, NgayNhap, TinhTrangXe, DonGiaThue, TrangThai) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, xe.getMaXe());
		statement.setString(2, xe.getTenXe());
		statement.setString(3, xe.getLoaiXe());
		statement.setInt(4, xe.getNamSanXuat());
		statement.setString(5, xe.getNgayNhap().getDateString());
		statement.setString(6, xe.getTinhTrangXe());
		statement.setString(7, String.valueOf(xe.getDonGiaThue()));
		statement.setString(8, xe.getTrangThai());
		statement.executeUpdate();
	}
	
	public void suaXe(Xe xe, String maXeCanSua) throws Exception {
		String command = "UPDATE Xe SET MaXe = ?, TenXe = ?, LoaiXe = ?, NamSanXuat = ?, NgayNhap = ?, TinhTrangXe = ?, DonGiaThue = ?, TrangThai = ? WHERE MaXe = ?";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, xe.getMaXe());
		statement.setString(2, xe.getTenXe());
		statement.setString(3, xe.getLoaiXe());
		statement.setInt(4, xe.getNamSanXuat());
		statement.setString(5, xe.getNgayNhap().getDateString());
		statement.setString(6, xe.getTinhTrangXe());
		statement.setString(7, String.valueOf(xe.getDonGiaThue()));
		statement.setString(8, xe.getTrangThai());
		statement.setString(9, maXeCanSua);
		statement.executeUpdate();
		
		command = "UPDATE ChiTietThueXe Set MaXe = ? WHERE MaXe = ?";
		statement = (PreparedStatement) connection.prepareStatement(command);
		statement.setString(1, xe.getMaXe());
		statement.setString(2, maXeCanSua);
		statement.executeUpdate();
	}
	
	public void xoaXe(String maXe) throws Exception {
		String command = "DELETE FROM Xe WHERE MaXe = '" + maXe + "'";
		connection.prepareStatement(command).executeUpdate();
		command = "DELETE FROM ChiTietThueXe WHERE MaXe = '" + maXe + "'";
		connection.prepareStatement(command).executeUpdate();
	}
	
	public Xe getXe(String maXe) throws Exception {
		ResultSet rs = database.getDataWithExactCondition("Xe", "MaXe", maXe);
		if(rs.next()) {
			String tenXe = rs.getString(2);
			String loaiXe = rs.getString(3);
			String namSanXuat = rs.getString(4);
			String ngayNhap = rs.getString(5);
			String tinhTrangXe = rs.getString(6);
			String donGiaThue = rs.getString(7);
			String trangThai = rs.getString(8);
			
			Xe xe = new Xe(maXe, tenXe, loaiXe, Integer.parseInt(namSanXuat), new Date(ngayNhap), tinhTrangXe, Long.parseLong(donGiaThue), trangThai);
			return xe;
		}
		return null;
	}
	
	public ResultSet getMaXeResultSet() throws Exception {
		String command = "SELECT MaXe FROM Xe";
		return connection.prepareStatement(command).executeQuery();
	}
	
	public ResultSet getMaXeResultSetWithTrangThai(String trangThai) throws Exception {
		String command = "SELECT MaXe FROM Xe WHERE TrangThai = '" + trangThai + "'";
		return connection.prepareStatement(command).executeQuery();
	}
	
	public String getTenXe(String maXe) throws Exception {
		Xe xe = getXe(maXe);
		if(xe != null) {
			return xe.getTenXe();
		} 
		return "";
	}
	
	public String getLoaiXe(String maXe) throws Exception {
		Xe xe = getXe(maXe);
		if(xe != null) {
			return xe.getLoaiXe();
		}
		return "";
	}
	
	public int getNamSanXuat(String maXe) throws Exception {
		Xe xe = getXe(maXe);
		if(xe != null) {
			return xe.getNamSanXuat();
		}
		return 0;
	}
	
	public long getDonGiaThue(String maXe) throws Exception {
		Xe xe = getXe(maXe);
		if(xe != null) {
			return xe.getDonGiaThue();
		}
		return 0;
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
		ResultSet rs = database.getData("Xe");
		while(rs.next()) {
			String maXe = rs.getString(1);
			if(xeDaDuocMuon(maXe)) {
				setTrangThai(maXe, "Khong");
			} else {
				setTrangThai(maXe, "Co");
			}
		}
	}
	
	public void updateXeDatabaseTable() throws Exception {
		updateTrangThaiColumn();
	}
	
}