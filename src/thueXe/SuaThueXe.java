package thueXe;

import javax.swing.JFrame;

import giaoDienChinh.GiaoDienChinh;
import thongBao.*;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.KhachHangDatabase;
import database.NhanVienDatabase;
import database.ThueXeDatabase;
import date.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class SuaThueXe {

	GiaoDienChinh giaoDienChinh;	
	
	KhachHangDatabase khachHangDatabase;
	NhanVienDatabase nhanVienDatabase;
	ThueXeDatabase thueXeDatabase;
	
	public JFrame frame;
	String maThueXeCanSua;
	
	private JTextField maThueXe_tf;
	private JTextField ngayThue_tf;
	private JTextField ngayHenTra_tf;
	
	DefaultComboBoxModel maKhachHangModel;
	DefaultComboBoxModel maNhanVienModel;
	private JComboBox maKhachHang_comboBox;
	private JComboBox maNhanVien_comboBox;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon suaIcon = new ImageIcon(iconFolderPath + "sua.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");
	private JTextField tenKhachHang_tf;
	private JTextField tenNhanVien_tf;


	/**
	 * Create the application.
	 */
	public SuaThueXe(GiaoDienChinh giaoDienChinh, String maThueXeCanSua) {
		this.giaoDienChinh = giaoDienChinh;
		this.maThueXeCanSua = maThueXeCanSua;
		khachHangDatabase = new KhachHangDatabase();
		nhanVienDatabase = new NhanVienDatabase();
		thueXeDatabase = new ThueXeDatabase();
		
		khachHangDatabase.connection = thueXeDatabase.connection;
		nhanVienDatabase.connection = thueXeDatabase.connection;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sua Thue Xe");
		frame.setBounds(300, 100, 555, 562);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		try {
			setUpComboBox();
			setUpTextFields();
			setSelectedItemForComboBox();
			setTextForTextFields();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUpButtons();
	}
	
	void setUpLabels() {

		JLabel lblMaThueXe = new JLabel("1. Ma Thue Xe");
		lblMaThueXe.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaThueXe.setBounds(29, 28, 174, 41);
		frame.getContentPane().add(lblMaThueXe);
		
		JLabel lblMaKhach = new JLabel("2. Ma Khach Hang");
		lblMaKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaKhach.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(lblMaKhach);
		
		JLabel lblTenKhach = new JLabel("3. Ten Khach Hang");
		lblTenKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenKhach.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(lblTenKhach);
		
		JLabel lblMaNhan = new JLabel("4. Ma Nhan Vien");
		lblMaNhan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaNhan.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(lblMaNhan);
		
		JLabel lblTenNhan = new JLabel("5. Ten Nhan Vien");
		lblTenNhan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenNhan.setBounds(29, 242, 174, 41);
		frame.getContentPane().add(lblTenNhan);
		
		JLabel lblNgayThue = new JLabel("6. Ngay Thue");
		lblNgayThue.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblNgayThue.setBounds(29, 295, 174, 41);
		frame.getContentPane().add(lblNgayThue);
		
		JLabel lblNgayHen = new JLabel("7. Ngay Hen Tra");
		lblNgayHen.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblNgayHen.setBounds(29, 348, 174, 41);
		frame.getContentPane().add(lblNgayHen);
		
	}
	
	void setUpTextFields() {
		maThueXe_tf = new JTextField();
		maThueXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maThueXe_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maThueXe_tf);
		maThueXe_tf.setColumns(10);
		
		tenKhachHang_tf = new JTextField();
		tenKhachHang_tf.setBackground(new Color(176, 196, 222));
		tenKhachHang_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenKhachHang_tf.setColumns(10);
		tenKhachHang_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(tenKhachHang_tf);
		tenKhachHang_tf.setEditable(false);
		
		
		tenNhanVien_tf = new JTextField();
		tenNhanVien_tf.setBackground(new Color(176, 196, 222));
		tenNhanVien_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenNhanVien_tf.setColumns(10);
		tenNhanVien_tf.setBounds(215, 242, 317, 41);
		frame.getContentPane().add(tenNhanVien_tf);
		tenNhanVien_tf.setEditable(false);
		
		ngayThue_tf = new JTextField();
		ngayThue_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayThue_tf.setColumns(10);
		ngayThue_tf.setBounds(215, 295, 317, 41);
		frame.getContentPane().add(ngayThue_tf);
		
		ngayHenTra_tf = new JTextField();
		ngayHenTra_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayHenTra_tf.setColumns(10);
		ngayHenTra_tf.setBounds(215, 348, 317, 41);
		frame.getContentPane().add(ngayHenTra_tf);
		
	}
	
	void setUpComboBox() throws Exception {
		maKhachHangModel = new DefaultComboBoxModel();
		createMaKhachHangModel();
		
		maNhanVienModel = new DefaultComboBoxModel();
		createMaNhanVienModel();
		
		maKhachHang_comboBox = new JComboBox(maKhachHangModel);
		maKhachHang_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKhachHang = maKhachHang_comboBox.getSelectedItem().toString();
				String tenKhachHang = "";
				try {
					tenKhachHang = khachHangDatabase.getTenKhachHang(maKhachHang);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tenKhachHang_tf.setText(tenKhachHang);
			}
		});
		maKhachHang_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maKhachHang_comboBox.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(maKhachHang_comboBox);
		
		maNhanVien_comboBox = new JComboBox(maNhanVienModel);
		maNhanVien_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNhanVien = maNhanVien_comboBox.getSelectedItem().toString();
				String tenNhanVien = "";
				try {
					tenNhanVien = nhanVienDatabase.getTenNhanVien(maNhanVien);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tenNhanVien_tf.setText(tenNhanVien);
			}
		});
		maNhanVien_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maNhanVien_comboBox.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(maNhanVien_comboBox);
	}
	
	void setUpButtons() {
		JButton suaBtn = new JButton("SUA", suaIcon);
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					suaBtnPressed();
					showThongBaoThanhCong();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showThongBaoThatBai();
				}
			}
		});
		suaBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		suaBtn.setBounds(312, 418, 220, 100);
		frame.getContentPane().add(suaBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(29, 418, 220, 100);
		frame.getContentPane().add(huyBtn);
	}
	
	void suaBtnPressed() throws Exception {
		suaThueXe();
		giaoDienChinh.updateThueXeTable();
		frame.dispose();
	}
	
	void suaThueXe() throws Exception {
		String maThueXe = maThueXe_tf.getText();
		String maKhachHang = maKhachHang_comboBox.getSelectedItem().toString();
		String maNhanVien = maNhanVien_comboBox.getSelectedItem().toString();
		String ngayThue = ngayThue_tf.getText();
		String ngayHenTra = ngayHenTra_tf.getText();
		long tienCoc = 0;
		long thanhTien = 0;
		long tongTienPhat = 0;
		
		ThueXe thueXeMoi = new ThueXe(maThueXe, maKhachHang, maNhanVien, new Date(ngayThue), new Date(ngayHenTra), tienCoc, thanhTien, tongTienPhat);
		thueXeDatabase.suaThueXe(thueXeMoi, maThueXeCanSua);
	}
	
	void huyBtnPressed() {
		frame.dispose();
	}
	
	void showThongBaoThanhCong() {
		ThongBaoThanhCong thongBaoThanhCong = new ThongBaoThanhCong();
		thongBaoThanhCong.frame.setVisible(true);
	}
	
	void showThongBaoThatBai() {
		ThongBaoThatBai thongBaoThatBai = new ThongBaoThatBai();
		thongBaoThatBai.frame.setVisible(true);
	}
	
	void createMaKhachHangModel() throws Exception {
		maKhachHangModel.removeAllElements();
		ResultSet maKhachHangRS = khachHangDatabase.getMaKhachHangResultSet();
		while(maKhachHangRS.next()) {
			maKhachHangModel.addElement(maKhachHangRS.getString(1));
		}
	}
	
	void createMaNhanVienModel() throws Exception {
		maNhanVienModel.removeAllElements();
		ResultSet maNhanVienRS = nhanVienDatabase.getMaNhanVienResultSet();
		while(maNhanVienRS.next()) {
			maNhanVienModel.addElement(maNhanVienRS.getString(1));
		}
	}
	
	void setTextForTextFields() throws Exception {
		ThueXe thueXe = thueXeDatabase.getThueXe(maThueXeCanSua);
		maThueXe_tf.setText(thueXe.getMaThueXe());
		ngayThue_tf.setText(thueXe.getNgayThue().getDateString());
		ngayHenTra_tf.setText(thueXe.getNgayHenTra().getDateString());
		
		String maKhachHang = maKhachHang_comboBox.getSelectedItem().toString();
		String tenKhachHang = "";
		try {
			tenKhachHang = khachHangDatabase.getTenKhachHang(maKhachHang);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tenKhachHang_tf.setText(tenKhachHang);

		String maNhanVien = maNhanVien_comboBox.getSelectedItem().toString();
		String tenNhanVien = "";
		try {
			tenNhanVien = nhanVienDatabase.getTenNhanVien(maNhanVien);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tenNhanVien_tf.setText(tenNhanVien);
	}
	
	void setSelectedItemForComboBox() throws Exception {
		ThueXe thueXe = thueXeDatabase.getThueXe(maThueXeCanSua);
		String maKhachHang = thueXe.getMaKhachHang();
		maKhachHangModel.setSelectedItem(maKhachHang);
		String maNhanVien = thueXe.getMaNhanVien();
		maNhanVienModel.setSelectedItem(maNhanVien);
	}
}
