package taiKhoan;

public class TaiKhoan {
	String tenTaiKhoan;
	String matKhau;
	String hoTen;
	int namSinh;
	String dienThoai;
	int cap;
	
	public TaiKhoan(String tenTaiKhoan, String matKhau, String hoTen, int namSinh, String dienThoai, int cap) {
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.namSinh = namSinh;
		this.dienThoai = dienThoai;
		this.cap = cap;
	}
	
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	
	public String getHoTen() {
		return hoTen;
	}
	
	public int getNamSinh() {
		return namSinh;
	}
	
	public String getDienThoai() {
		return dienThoai;
	}
	
	public int getCap() {
		return cap;
	}
}
