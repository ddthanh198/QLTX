package thueXe;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import chiTietThueXe.*;
import chinhSuaThongTin.ChinhSuaThongTin;
import database.*;
import giaoDienChinh.GiaoDienChinh;
import taiKhoan.TaiKhoan;
import thongBao.ChonMotHang;
import thongBao.XeDaDuocTra;
import xe.SuaXe;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class XemChiTietThueXe {

	TaiKhoan taiKhoan;
	GiaoDienChinh giaoDienChinh;
	XemChiTietThueXe xemChiTietThueXe = this;
	
	Database database;
	XeDatabase xeDatabase;
	KhachHangDatabase khachHangDatabase;
	NhanVienDatabase nhanVienDatabase;
	ThueXeDatabase thueXeDatabase;
	ChiTietThueXeDatabase chiTietThueXeDatabase;
	
	public JFrame frame;
	public String maThueXe;
	
	JPanel thongTinPanel;
	JTable chiTietThueXeTable = new JTable();
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon themIcon = new ImageIcon(iconFolderPath + "them.png");
	ImageIcon suaIcon = new ImageIcon(iconFolderPath + "sua.png");
	ImageIcon xoaIcon = new ImageIcon(iconFolderPath + "xoa.png");
	ImageIcon traXeIcon = new ImageIcon(iconFolderPath + "traXe.png");
	ImageIcon timKiemIcon = new ImageIcon(iconFolderPath + "timKiem.png");
	ImageIcon thongKeIcon = new ImageIcon(iconFolderPath + "thongKe.png");
	ImageIcon capNhatIcon = new ImageIcon(iconFolderPath + "capNhat.png");

	private JTextField maThueXe_tf;
	private JTextField tenKhachHang_tf;
	private JTextField tenNhanVien_tf;
	private JTextField ngayThue_tf;
	private JTextField ngayHenTra_tf;
	private JTextField tienCoc_tf;
	private JTextField thanhTien_tf;
	private JTextField tongTienPhat_tf;
	private JTextField soLuong_tf;

	/**
	 * Create the application.
	 */
	public XemChiTietThueXe(TaiKhoan taiKhoan, GiaoDienChinh giaoDienChinh, String maThueXe) {
		this.taiKhoan = taiKhoan;
		database = new Database();
		xeDatabase = new XeDatabase();
		khachHangDatabase = new KhachHangDatabase();
		nhanVienDatabase = new NhanVienDatabase();
		thueXeDatabase = new ThueXeDatabase();
		chiTietThueXeDatabase = new ChiTietThueXeDatabase();
		
		xeDatabase.connection = database.connection;
		khachHangDatabase.connection = database.connection;
		nhanVienDatabase.connection = database.connection;
		thueXeDatabase.connection = database.connection;
		chiTietThueXeDatabase.connection = database.connection;
		
		this.giaoDienChinh = giaoDienChinh;
		this.maThueXe = maThueXe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Chi Tiet Thue Xe");
		frame.setBounds(120, 30, 1129, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpThongTinPanel();
		
		try {
			updateCTTXTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chiTietThueXeTable.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 182, 1117, 290);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(chiTietThueXeTable);
		
		setUpButtons();
	}
	
	void setUpLabels() {
		JLabel lblMaThue = new JLabel("1. Ma Thue Xe");
		lblMaThue.setBounds(6, 6, 160, 41);
		thongTinPanel.add(lblMaThue);
		lblMaThue.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblKhachHang = new JLabel("2. Khach Hang");
		lblKhachHang.setBounds(379, 6, 160, 41);
		thongTinPanel.add(lblKhachHang);
		lblKhachHang.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblNhanVien = new JLabel("3. Nhan Vien");
		lblNhanVien.setBounds(751, 6, 160, 41);
		thongTinPanel.add(lblNhanVien);
		lblNhanVien.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblNgayThue = new JLabel("4. Ngay Thue");
		lblNgayThue.setBounds(6, 59, 160, 41);
		thongTinPanel.add(lblNgayThue);
		lblNgayThue.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblNgayHen = new JLabel("5. Ngay Hen Tra");
		lblNgayHen.setBounds(379, 59, 160, 41);
		thongTinPanel.add(lblNgayHen);
		lblNgayHen.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblTienCoc = new JLabel("6. Tien Coc");
		lblTienCoc.setBounds(751, 59, 160, 41);
		thongTinPanel.add(lblTienCoc);
		lblTienCoc.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblThanhTien = new JLabel("7. Thanh Tien");
		lblThanhTien.setBounds(6, 112, 160, 41);
		thongTinPanel.add(lblThanhTien);
		lblThanhTien.setFont(new Font("Avenir Next", Font.BOLD, 18));

		JLabel lblTongTien = new JLabel("8. Tong Tien Phat");
		lblTongTien.setBounds(378, 112, 160, 41);
		thongTinPanel.add(lblTongTien);
		lblTongTien.setFont(new Font("Avenir Next", Font.BOLD, 18));
		
		JLabel lblSoLuong = new JLabel("9. So luong xe");
		lblSoLuong.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblSoLuong.setBounds(751, 112, 160, 41);
		thongTinPanel.add(lblSoLuong);
	}
	
	void setUpTextFields() {
		maThueXe_tf = new JTextField();
		maThueXe_tf.setBounds(166, 6, 200, 41);
		thongTinPanel.add(maThueXe_tf);
		maThueXe_tf.setBackground(new Color(176, 196, 222));
		maThueXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maThueXe_tf.setColumns(10);
		maThueXe_tf.setEditable(false);
		
		tenKhachHang_tf = new JTextField();
		tenKhachHang_tf.setBounds(539, 6, 200, 41);
		thongTinPanel.add(tenKhachHang_tf);
		tenKhachHang_tf.setBackground(new Color(176, 196, 222));
		tenKhachHang_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenKhachHang_tf.setColumns(10);
		tenKhachHang_tf.setEditable(false);
		
		tenNhanVien_tf = new JTextField();
		tenNhanVien_tf.setBounds(911, 6, 200, 41);
		thongTinPanel.add(tenNhanVien_tf);
		tenNhanVien_tf.setBackground(new Color(176, 196, 222));
		tenNhanVien_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenNhanVien_tf.setColumns(10);
		tenNhanVien_tf.setEditable(false);

		ngayThue_tf = new JTextField();
		ngayThue_tf.setBounds(167, 59, 200, 41);
		thongTinPanel.add(ngayThue_tf);
		ngayThue_tf.setBackground(new Color(176, 196, 222));
		ngayThue_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayThue_tf.setColumns(10);
		ngayThue_tf.setEditable(false);
		
		ngayHenTra_tf = new JTextField();
		ngayHenTra_tf.setBounds(539, 59, 200, 41);
		thongTinPanel.add(ngayHenTra_tf);
		ngayHenTra_tf.setBackground(new Color(176, 196, 222));
		ngayHenTra_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayHenTra_tf.setColumns(10);
		ngayHenTra_tf.setEditable(false);
		
		tienCoc_tf = new JTextField();
		tienCoc_tf.setBounds(912, 59, 200, 41);
		thongTinPanel.add(tienCoc_tf);
		tienCoc_tf.setBackground(new Color(176, 196, 222));
		tienCoc_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tienCoc_tf.setColumns(10);
		tienCoc_tf.setEditable(false);
		
		thanhTien_tf = new JTextField();
		thanhTien_tf.setBounds(166, 112, 200, 41);
		thongTinPanel.add(thanhTien_tf);
		thanhTien_tf.setBackground(new Color(176, 196, 222));
		thanhTien_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		thanhTien_tf.setColumns(10);
		thanhTien_tf.setEditable(false);
		
		tongTienPhat_tf = new JTextField();
		tongTienPhat_tf.setBounds(538, 112, 200, 41);
		thongTinPanel.add(tongTienPhat_tf);
		tongTienPhat_tf.setBackground(new Color(176, 196, 222));
		tongTienPhat_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tongTienPhat_tf.setColumns(10);
		tongTienPhat_tf.setEditable(false);
		
		soLuong_tf = new JTextField();
		soLuong_tf.setText("0");
		soLuong_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		soLuong_tf.setEditable(false);
		soLuong_tf.setColumns(10);
		soLuong_tf.setBackground(new Color(176, 196, 222));
		soLuong_tf.setBounds(911, 112, 200, 41);
		thongTinPanel.add(soLuong_tf);
	}
	
	void setUpButtons() {
		JButton themBtn = new JButton("Them", themIcon);
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemChiTietThueXe window = new ThemChiTietThueXe(giaoDienChinh, xemChiTietThueXe);
				window.frame.setVisible(true);
			}
		});
		themBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		themBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		themBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		themBtn.setBounds(31, 502, 135, 156);
		frame.getContentPane().add(themBtn);
		
		JButton suaBtn = new JButton("Sua", suaIcon);
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = chiTietThueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maXeCanSua = (String) chiTietThueXeTable.getValueAt(row, 0);
					SuaChiTietThueXe window = new SuaChiTietThueXe(giaoDienChinh, xemChiTietThueXe, maThueXe, maXeCanSua);
					window.frame.setVisible(true);
				}
			}
		});
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		suaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		suaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		suaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		suaBtn.setBounds(186, 502, 135, 156);
		frame.getContentPane().add(suaBtn);
		
		JButton xoaBtn = new JButton("Xoa", xoaIcon);
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = chiTietThueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maXeCanXoa = (String) chiTietThueXeTable.getValueAt(row, 0);
					XoaChiTietThueXe window = new XoaChiTietThueXe(giaoDienChinh, xemChiTietThueXe, maThueXe, maXeCanXoa);
					window.frame.setVisible(true);
				}
			}
		});
		xoaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		xoaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xoaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		xoaBtn.setBounds(341, 502, 135, 156);
		frame.getContentPane().add(xoaBtn);

		JButton traXeBtn = new JButton("Tra Xe", traXeIcon);
		traXeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = chiTietThueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maXe = (String) chiTietThueXeTable.getValueAt(row, 0);
					try {
						if(chiTietThueXeDatabase.xeDaDuocTra(maThueXe, maXe)) {
							XeDaDuocTra window = new XeDaDuocTra();
							window.frame.setVisible(true);
						} else {
							TraXe window = new TraXe(giaoDienChinh, xemChiTietThueXe, maThueXe, maXe);
							window.frame.setVisible(true);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		traXeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		traXeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		traXeBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		traXeBtn.setBounds(496, 502, 135, 156);
		frame.getContentPane().add(traXeBtn);
		
		JButton timKiemBtn = new JButton("Tim Kiem", timKiemIcon);
		timKiemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimKiemChiTietThueXe window= new TimKiemChiTietThueXe();
				window.frmTimKiemChi.setVisible(true);
			}
		});
		timKiemBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		timKiemBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		timKiemBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		timKiemBtn.setBounds(651, 502, 135, 156);
		frame.getContentPane().add(timKiemBtn);
		
		JButton thongKeBtn = new JButton("Thong Ke", thongKeIcon);
		thongKeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeChiTietThueXe window = new ThongKeChiTietThueXe();
				window.frmThongKeChi.setVisible(true);
			}
		});
		thongKeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		thongKeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		thongKeBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		thongKeBtn.setBounds(806, 502, 135, 156);
		frame.getContentPane().add(thongKeBtn);
		
		JButton capNhatBtn = new JButton("Cap Nhat", capNhatIcon);
		capNhatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateXemChiTietThueXeView();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		capNhatBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		capNhatBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		capNhatBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		capNhatBtn.setBounds(961, 502, 135, 156);
		frame.getContentPane().add(capNhatBtn);
		
		if(taiKhoan.getCap() != 1) {
			themBtn.setEnabled(false);
			suaBtn.setEnabled(false);
			xoaBtn.setEnabled(false);
			traXeBtn.setEnabled(false);
		}
	}
	
	void setUpThongTinPanel() {
		thongTinPanel = new JPanel();
		thongTinPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		thongTinPanel.setBounds(6, 6, 1117, 164);
		frame.getContentPane().add(thongTinPanel);
		thongTinPanel.setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		try {
			setTextForTextFields();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setTextForTextFields() throws Exception {
		ThueXe thueXe = thueXeDatabase.getThueXe(maThueXe);
		maThueXe_tf.setText(thueXe.getMaThueXe());
		tenKhachHang_tf.setText(khachHangDatabase.getTenKhachHang(thueXe.getMaKhachHang()));
		tenNhanVien_tf.setText(nhanVienDatabase.getTenNhanVien(thueXe.getMaNhanVien()));
		ngayThue_tf.setText(thueXe.getNgayThue().getDateString());
		ngayHenTra_tf.setText(thueXe.getNgayHenTra().getDateString());
		tienCoc_tf.setText(String.valueOf(thueXe.getTienCoc()));
		thanhTien_tf.setText(String.valueOf(thueXe.getThanhTien()));
		tongTienPhat_tf.setText(String.valueOf(thueXe.getTongTienPhat()));
		soLuong_tf.setText(String.valueOf(chiTietThueXeDatabase.getSoLuongXeThueOf(maThueXe)));
	}
	
	public void updateCTTXTable() throws Exception {
		DefaultTableModel model = new DefaultTableModel();
		ResultSet rs = database.getDataWithExactCondition("ChiTietThueXe", "MaThueXe", maThueXe);
		//load column name
		String[] arr = new String[7];
		arr[0] = "Ma Xe";
		arr[1] = "Ten Xe";
		arr[2] = "Loai Xe";
		arr[3] = "Ngay Tra";
		arr[4] = "Tien Thue";
		arr[5] = "Tien Phat";
		arr[6] = "Ghi Chu";
		model.setColumnIdentifiers(arr);
		chiTietThueXeTable.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 18));
		//load data from database to table
		while(rs.next()) {
			String maXe = rs.getString(2);
			arr[0] = maXe;
			arr[1] = xeDatabase.getTenXe(maXe);
			arr[2] = xeDatabase.getLoaiXe(maXe);
			arr[3] = rs.getString(3);
			arr[4] = rs.getString(4);
			arr[5] = rs.getString(5);
			arr[6] = rs.getString(6);
			model.addRow(arr);
		}
		chiTietThueXeTable.setModel(model);
	}
	
	public void updateXemChiTietThueXeView() throws Exception {
		updateCTTXTable();
		setTextForTextFields();
	}
}
