package chiTietThueXe;

import date.Date;

public class ChiTietThueXe {
	String maThueXe;
	String maXe;
	Date ngayTra;
	long tienThue;
	long tienPhat;
	String ghiChu;
	
	public ChiTietThueXe(String maThueXe, String maXe, Date ngayTra, long tienThue, long tienPhat, String ghiChu) {
		this.maThueXe = maThueXe;
		this.maXe = maXe;
		this.ngayTra = ngayTra;
		this.tienThue = tienThue;
		this.tienPhat = tienPhat;
		this.ghiChu = ghiChu;
	}
	
	public String getMaThueXe() {
		return maThueXe;
	}
	
	public String getMaXe() {
		return maXe;
	}
	
	public Date getNgayTra() {
		return ngayTra;
	}
	
	public long getTienThue() {
		return tienThue;
	}
	
	public long getTienPhat() {
		return tienPhat;
	}
	
	public String getGhiChu() {
		return ghiChu;
	}
}
