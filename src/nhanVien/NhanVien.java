package nhanVien;

public class NhanVien {
	String maNhanVien;
	String tenNhanVien;
	String gioiTinh;
	int namSinh;
	String viTri;
	String DT;
	String email;
	String diaChi;
	
	public NhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, int namSinh, String viTri, String DT, String email, String diaChi) {
		
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.namSinh = namSinh;
		this.viTri = viTri;
		this.DT = DT;
		this.email = email;
		this.diaChi = diaChi;
	}
	
	public String getMaNhanVien() {
		return maNhanVien;
	}
	
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	
	public String getGioiTinh() {
		return gioiTinh;
	}
	
	public int getNamSinh() {
		return namSinh;
	}
	
	public String getViTri() {
		return viTri;
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
