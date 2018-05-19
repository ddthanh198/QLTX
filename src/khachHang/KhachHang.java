package khachHang;

public class KhachHang {
	String maKhachHang;
	String tenKhachHang;
	String gioiTinh;
	int namSinh;
	String CMND;
	String DT;
	String email;
	String diaChi;
	
	public KhachHang(String maKhachHang, String tenKhachHang, String gioiTinh, int namSinh, String CMND, String DT, String email, String diaChi) {
		
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.namSinh = namSinh;
		this.CMND = CMND;
		this.DT = DT;
		this.email = email;
		this.diaChi = diaChi;
	}
	
	public String getMaKhachHang() {
		return maKhachHang;
	}
	
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	
	public String getGioiTinh() {
		return gioiTinh;
	}
	
	public int getNamSinh() {
		return namSinh;
	}
	
	public String getCMND() {
		return CMND;
	}
	
	public String getDT() {
		return DT;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDiaChi() {
		return diaChi;
	}
}
