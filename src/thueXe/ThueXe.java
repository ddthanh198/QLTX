package thueXe;

import date.Date;

public class ThueXe {
	String maThueXe;
	String maKhachHang;
	String maNhanVien;
	Date ngayThue;
	Date ngayHenTra;
	long tienCoc;
	long thanhTien;
	long tongTienPhat;
	
	public ThueXe(String maThueXe, String maKhachHang, String maNhanVien, Date ngayThue, Date ngayHenTra, long tienCoc, long thanhTien, long tongTienPhat) {
		
		this.maThueXe = maThueXe;
		this.maKhachHang = maKhachHang;
		this.maNhanVien = maNhanVien;
		this.ngayThue = ngayThue;
		this.ngayHenTra = ngayHenTra;
		this.tienCoc = tienCoc;
		this.thanhTien = thanhTien;
		this.tongTienPhat = tongTienPhat;
	}
	
	public String getMaThueXe() {
		return maThueXe;
	}
	
	public String getMaKhachHang() {
		return maKhachHang;
	}
	
	public String getMaNhanVien() {
		return maNhanVien;
	}
	
	public Date getNgayThue() {
		return ngayThue;
	}
	
	public Date getNgayHenTra() {
		return ngayHenTra;
	}
	
	public long getTienCoc() {
		return tienCoc;
	}
	
	public long getThanhTien() {
		return thanhTien;
	}
	
	public long getTongTienPhat() {
		return tongTienPhat;
	}
}
