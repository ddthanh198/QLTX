package xe;

import date.Date;

public class Xe {
	
	String maXe;
	String tenXe;
	String loaiXe;
	int namSanXuat;
	Date ngayNhap;
	String tinhTrangXe;
	long donGiaThue;
	String trangThai;
	
	public Xe(String maXe, String tenXe, String loaiXe, int namSanXuat, Date ngayNhap, String tinhTrangXe, long donGiaThue, String trangThai) {
		this.maXe = maXe;
		this.tenXe = tenXe;
		this.loaiXe = loaiXe;
		this.namSanXuat = namSanXuat;
		this.ngayNhap = ngayNhap;
		this.tinhTrangXe = tinhTrangXe;
		this.donGiaThue = donGiaThue;
		this.trangThai = trangThai;
	}
	
	public String getMaXe() {
		return maXe;
	}
	
	public String getTenXe() {
		return tenXe;
	}

	public String getLoaiXe() {
		return loaiXe;
	}
	
	public int getNamSanXuat() {
		return namSanXuat;
	}
	
	public Date getNgayNhap() {
		return ngayNhap;
	}
	
	public String getTinhTrangXe() {
		return tinhTrangXe;
	}
	
	public long getDonGiaThue() {
		return donGiaThue;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
}
