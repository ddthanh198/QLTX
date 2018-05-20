package giaoDienChinh;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import chinhSuaThongTin.ChinhSuaThongTin;
import dangNhap_dangKy.DangNhap;
import thongBao.ChonMotHang;
import database.*;
import khachHang.*;
import nhanVien.*;
import taiKhoan.TaiKhoan;
import thueXe.*;
import xe.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoDienChinh {
	
	TaiKhoan taiKhoan;
	
	GiaoDienChinh giaoDienChinh = this;
	
	Database database;
	XeDatabase xeDatabase;
	KhachHangDatabase khachHangDatabase;
	NhanVienDatabase nhanVienDatabase;
	ThueXeDatabase thueXeDatabase;
	
	public static JFrame frame;
	JPanel xePnl;
	JPanel khachHangPnl;
	JPanel nhanVienPnl;
	JPanel thueXePnl;
	JPanel taiKhoanPnl;
	
	public JTable xeTable = new JTable();
	public JTable khachHangTable = new JTable();
	public JTable nhanVienTable = new JTable();
	public static JTable thueXeTable = new JTable();
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon themIcon = new ImageIcon(iconFolderPath + "them.png");
	ImageIcon suaIcon = new ImageIcon(iconFolderPath + "sua.png");
	ImageIcon xoaIcon = new ImageIcon(iconFolderPath + "xoa.png");
	ImageIcon nhapFileIcon = new ImageIcon(iconFolderPath + "nhapFile.png");
	ImageIcon timKiemIcon = new ImageIcon(iconFolderPath + "timKiem.png");
	ImageIcon thongKeIcon = new ImageIcon(iconFolderPath + "thongKe.png");
	ImageIcon capNhatIcon = new ImageIcon(iconFolderPath + "capNhat.png");
	ImageIcon inPhieuIcon = new ImageIcon(iconFolderPath + "inPhieu.png");
	ImageIcon tienIcon = new ImageIcon(iconFolderPath + "tien.png");
	ImageIcon chiTietIcon = new ImageIcon(iconFolderPath + "chiTiet.png");
	ImageIcon xeIcon = new ImageIcon(iconFolderPath + "xe.png");
	ImageIcon khachHangIcon = new ImageIcon(iconFolderPath + "khachHang.png");
	ImageIcon nhanVienIcon = new ImageIcon(iconFolderPath + "nhanVien.png");
	ImageIcon thueXeIcon = new ImageIcon(iconFolderPath + "thueXe.png");
	ImageIcon taiKhoanIcon = new ImageIcon(iconFolderPath + "taiKhoan.png");
	ImageIcon dangKyIcon = new ImageIcon(iconFolderPath + "dangKy.png");
	ImageIcon dangXuatIcon = new ImageIcon(iconFolderPath + "dangXuat.png");
	
	/**
	 * Create the application.
	 * @param taiKhoanNhapVao 
	 */
	public GiaoDienChinh(TaiKhoan taiKhoan) throws Exception {
		this.taiKhoan = taiKhoan;
		database = new Database();
		xeDatabase = new XeDatabase();
		khachHangDatabase = new KhachHangDatabase();
		nhanVienDatabase = new NhanVienDatabase();
		thueXeDatabase = new ThueXeDatabase();
		
		xeDatabase.connection = database.connection;
		khachHangDatabase.connection = database.connection;
		nhanVienDatabase.connection = database.connection;
		thueXeDatabase.connection = database.connection;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("QUAN LY THUE XE");
		frame.setBounds(30, 30, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.setFont(new Font("Avenir Next", Font.BOLD, 24));
		tabbedPane.setBounds(6, 6, 1288, 666);
		frame.getContentPane().add(tabbedPane);
		
		xePnl = new JPanel();
		tabbedPane.addTab("Xe", xeIcon, xePnl, null);
		xePnl.setLayout(null);
		setUpPanel(xePnl, "Xe", xeTable);
		
		khachHangPnl = new JPanel();
		tabbedPane.addTab("Khach Hang", khachHangIcon, khachHangPnl, null);
		khachHangPnl.setLayout(null);
		setUpPanel(khachHangPnl, "KhachHang", khachHangTable);
		
		nhanVienPnl = new JPanel();
		tabbedPane.addTab("Nhan Vien", nhanVienIcon, nhanVienPnl, null);
		nhanVienPnl.setLayout(null);
		setUpPanel(nhanVienPnl, "NhanVien", nhanVienTable);
		
		thueXePnl = new JPanel();
		tabbedPane.addTab("Thue Xe", thueXeIcon, thueXePnl, null);
		thueXePnl.setLayout(null);
		setUpThueXePanel();
		
		taiKhoanPnl = new JPanel();
		tabbedPane.addTab("Tai Khoan", taiKhoanIcon, taiKhoanPnl, null);
		taiKhoanPnl.setLayout(null);
		setUpTaiKhoanPanel();
	}
	
	void setUpPanel(JPanel panel, String databaseTable, JTable table) throws Exception {
		updateTable(databaseTable, table);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 1251, 412);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton themBtn = new JButton("Them", themIcon);
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					ThemXe window = new ThemXe(giaoDienChinh);
					window.frame.setVisible(true);
				} else if(panel == khachHangPnl) {
					ThemKhachHang window = new ThemKhachHang(giaoDienChinh);
					window.frame.setVisible(true);
				} else if(panel == nhanVienPnl) {
					ThemNhanVien window = new ThemNhanVien(giaoDienChinh);
					window.frame.setVisible(true);
				} 
			}
		});
		themBtn.setHorizontalTextPosition(JButton.CENTER);
		themBtn.setVerticalTextPosition(JButton.BOTTOM);
		themBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		themBtn.setBounds(46, 445, 140, 156);
		panel.add(themBtn);
		
		JButton suaBtn = new JButton("Sua", suaIcon);
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					int row = xeTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maXeCanSua = (String) xeTable.getValueAt(row, 0);
						SuaXe window = new SuaXe(giaoDienChinh, maXeCanSua);
						window.frame.setVisible(true);
					}
				} else if(panel == khachHangPnl) {
					int row = khachHangTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maKhachHangCanSua = (String) khachHangTable.getValueAt(row, 0);
						SuaKhachHang window = new SuaKhachHang(giaoDienChinh, maKhachHangCanSua);
						window.frame.setVisible(true);
					}
				} else if(panel == nhanVienPnl) {
					int row = nhanVienTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maNhanVienCanSua = (String) nhanVienTable.getValueAt(row, 0);
						SuaNhanVien window = new SuaNhanVien(giaoDienChinh, maNhanVienCanSua);
						window.frame.setVisible(true);
					}
				} 
			}
		});
		suaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		suaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		suaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		suaBtn.setBounds(216, 445, 140, 156);
		panel.add(suaBtn);
		
		JButton xoaBtn = new JButton("Xoa", xoaIcon);
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					int row = xeTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maXeCanXoa = (String) xeTable.getValueAt(row, 0);
						XoaXe window = new XoaXe(giaoDienChinh, maXeCanXoa);
						window.frame.setVisible(true);
					}
				} else if(panel == khachHangPnl) {
					int row = khachHangTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maKhachHangCanXoa = (String) khachHangTable.getValueAt(row, 0);
						XoaKhachHang window = new XoaKhachHang(giaoDienChinh, maKhachHangCanXoa);
						window.frame.setVisible(true);
					}
				} else if(panel == nhanVienPnl) {
					int row = nhanVienTable.getSelectedRow();
					if (row < 0) {
						ChonMotHang window = new ChonMotHang();
						window.frame.setVisible(true);
					} else {
						String maNhanVienCanXoa = (String) nhanVienTable.getValueAt(row, 0);
						XoaNhanVien window = new XoaNhanVien(giaoDienChinh, maNhanVienCanXoa);
						window.frame.setVisible(true);
					}
				} 
			}
		});
		xoaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		xoaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xoaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		xoaBtn.setBounds(386, 445, 140, 156);
		panel.add(xoaBtn);
		
		JButton timKiemBtn = new JButton("Tim Kiem", timKiemIcon);
		timKiemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					TimKiemXe window = new TimKiemXe();
					window.frmTimKiemXe.setVisible(true);
				} else if(panel == khachHangPnl) {
					TimKiemKhachHang window = new TimKiemKhachHang();
					window.frmTimKiemKhach.setVisible(true);
				} else if(panel == nhanVienPnl) {
					TimKiemNhanVien window = new TimKiemNhanVien();
					window.frmTimKiemNhan.setVisible(true);
				} 
			}
		});
		timKiemBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		timKiemBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		timKiemBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		timKiemBtn.setBounds(556, 445, 140, 156);
		panel.add(timKiemBtn);
		
		JButton thongKeBtn = new JButton("Thong Ke", thongKeIcon);
		thongKeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					ThongKeXe window = new ThongKeXe();
					window.frmThongKeXe.setVisible(true);
				} else if(panel == khachHangPnl) {
					ThongKeKhachHang window = new ThongKeKhachHang();
					window.frmThongKekhach.setVisible(true);
				} else if(panel == nhanVienPnl) {
					ThongKeNhanVien window = new ThongKeNhanVien();
					window.frmThongKeNhan.setVisible(true);
				}
			}
		});
		thongKeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		thongKeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		thongKeBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		thongKeBtn.setBounds(726, 445, 140, 156);
		panel.add(thongKeBtn);
		
		JButton nhapFileBtn = new JButton("Nhap File", nhapFileIcon);
		nhapFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					new NhapFileXe(giaoDienChinh).nhapFile();
				} else if(panel == khachHangPnl) {
					new NhapFileKhachHang(giaoDienChinh).nhapFile();
				} else if(panel == nhanVienPnl) {
					new NhapFileNhanVien(giaoDienChinh).nhapFile();
				}
			}
		});
		nhapFileBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		nhapFileBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		nhapFileBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		nhapFileBtn.setBounds(896, 445, 140, 156);
		panel.add(nhapFileBtn);
		
		JButton capNhatBtn = new JButton("Cap Nhat", capNhatIcon);
		capNhatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(panel == xePnl) {
					try {
						updateTable("Xe", xeTable);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(panel == khachHangPnl) {
					try {
						updateTable("KhachHang", khachHangTable);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(panel == nhanVienPnl) {
					try {
						updateTable("NhanVien", nhanVienTable);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		capNhatBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		capNhatBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		capNhatBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		capNhatBtn.setBounds(1066, 445, 140, 156);
		panel.add(capNhatBtn);
		
		if(taiKhoan.getCap() != 1) {
			themBtn.setEnabled(false);
			suaBtn.setEnabled(false);
			xoaBtn.setEnabled(false);
			nhapFileBtn.setEnabled(false);
		}
	}
	
	
	public void updateTable(String databaseTable, JTable table) throws Exception {
		xeDatabase.updateXeDatabaseTable();
		DefaultTableModel model = new DefaultTableModel();
		ResultSet rs = database.getData(databaseTable);
		//load column name
		ResultSetMetaData rsMD = rs.getMetaData();
		int colNumber = rsMD.getColumnCount();
		String[] arr = new String[colNumber];
		for(int i=0;i<colNumber;i++) {
			arr[i] = rsMD.getColumnName(i+1);
		}
		model.setColumnIdentifiers(arr);
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 18));
		//load data from database to table
		while(rs.next()) {
			for(int i=0;i<colNumber;i++) {
				arr[i] = rs.getString(i+1);
			}
			model.addRow(arr);
		}
		table.setModel(model);
	}
	
	void setUpThueXePanel() throws Exception {
		updateThueXeTable();
		thueXeTable.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 1251, 412);
		thueXePnl.add(scrollPane);
		scrollPane.setViewportView(thueXeTable);
		
		JButton themBtn = new JButton("Them", themIcon);
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemThueXe window = new ThemThueXe(giaoDienChinh);
				window.frame.setVisible(true);
			}
		});
		themBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		themBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		themBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		themBtn.setBounds(6, 440, 130, 156);
		thueXePnl.add(themBtn);
		
		JButton suaBtn = new JButton("Sua", suaIcon);
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = thueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maThueXeCanSua = (String) thueXeTable.getValueAt(row, 0);
					SuaThueXe window = new SuaThueXe(giaoDienChinh, maThueXeCanSua);
					window.frame.setVisible(true);
				}
			}
		});
		suaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		suaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		suaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		suaBtn.setBounds(146, 440, 130, 156);
		thueXePnl.add(suaBtn);
		
		JButton xoaBtn = new JButton("Xoa", xoaIcon);
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = thueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maThueXeCanXoa = (String) thueXeTable.getValueAt(row, 0);
					XoaThueXe window = new XoaThueXe(giaoDienChinh, maThueXeCanXoa);
					window.frame.setVisible(true);
				}
			}
		});
		xoaBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		xoaBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xoaBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		xoaBtn.setBounds(286, 440, 130, 156);
		thueXePnl.add(xoaBtn);
		
		JButton timKiemBtn = new JButton("Tim Kiem", timKiemIcon);
		timKiemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimKiemThueXe window = new TimKiemThueXe();
				window.frmTimKiemThue.setVisible(true);
			}
		});
		timKiemBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		timKiemBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		timKiemBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		timKiemBtn.setBounds(426, 440, 130, 156);
		thueXePnl.add(timKiemBtn);
		
		JButton thongKeBtn = new JButton("Thong Ke", thongKeIcon);
		thongKeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeThueXe window = new ThongKeThueXe();
				window.frmThongKeThue.setVisible(true);
			}
		});
		thongKeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		thongKeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		thongKeBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		thongKeBtn.setBounds(566, 440, 130, 156);
		thueXePnl.add(thongKeBtn);
		
		JButton inPhieuBtn = new JButton("In Phieu", inPhieuIcon);
		inPhieuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InPhieuThueXe.MaThueXe = String.valueOf(GiaoDienChinh.thueXeTable.getValueAt(GiaoDienChinh.thueXeTable.getSelectedRow(), 0));
					InPhieuThueXe.xuatfile();
				} catch (Exception e1) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				}
			}
		});
		inPhieuBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		inPhieuBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		inPhieuBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		inPhieuBtn.setBounds(706, 440, 130, 156);
		thueXePnl.add(inPhieuBtn);
		
		JButton xemChiTietBtn = new JButton("Xem Chi Tiet", chiTietIcon);
		xemChiTietBtn.setText("<html><center>Xem<br/>Chi Tiet</></html>");
		xemChiTietBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = thueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maThueXe = (String) thueXeTable.getValueAt(row, 0);
					XemChiTietThueXe window = new XemChiTietThueXe(taiKhoan, giaoDienChinh, maThueXe);
					window.frame.setVisible(true);
				}
			}
		});
		xemChiTietBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		xemChiTietBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xemChiTietBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		xemChiTietBtn.setBounds(846, 440, 130, 156);
		thueXePnl.add(xemChiTietBtn);
		
		JButton tienNhanLaiBtn = new JButton("Tien Nhan Lai", tienIcon);
		tienNhanLaiBtn.setText("<html><center>Tien<br/>Nhan Lai</></html>");
		tienNhanLaiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = thueXeTable.getSelectedRow();
				if (row < 0) {
					ChonMotHang window = new ChonMotHang();
					window.frame.setVisible(true);
				} else {
					String maThueXe = (String) thueXeTable.getValueAt(row, 0);
					TienNhanLai window = new TienNhanLai(giaoDienChinh, maThueXe);
					window.frame.setVisible(true);
				}
			}
		});
		tienNhanLaiBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		tienNhanLaiBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		tienNhanLaiBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		tienNhanLaiBtn.setBounds(986, 440, 130, 156);
		thueXePnl.add(tienNhanLaiBtn);
		
		JButton capNhatBtn = new JButton("Cap Nhat", capNhatIcon);
		capNhatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateThueXeTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		capNhatBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		capNhatBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		capNhatBtn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		capNhatBtn.setBounds(1126, 440, 130, 156);
		thueXePnl.add(capNhatBtn);
		
		if(taiKhoan.getCap() != 1) {
			themBtn.setEnabled(false);
			suaBtn.setEnabled(false);
			xoaBtn.setEnabled(false);
		}
	}
	
	public void updateThueXeTable() throws Exception {
		database.updateThueXeDatabaseTable();
		DefaultTableModel model = new DefaultTableModel();
		ResultSet rs = database.getData("ThueXe");
		//load column name
		ResultSetMetaData rsMD = rs.getMetaData();
		String[] arr = new String[10];
		arr[0] = rsMD.getColumnName(1);
		arr[1] = rsMD.getColumnName(2);
		arr[2] = "Ten Khach Hang";
		arr[3] = rsMD.getColumnName(3);
		arr[4] = "Ten Nhan Vien";
		arr[5] = rsMD.getColumnName(4);
		arr[6] = rsMD.getColumnName(5);
		arr[7] = rsMD.getColumnName(6);
		arr[8] = rsMD.getColumnName(7);
		arr[9] = rsMD.getColumnName(8);
		model.setColumnIdentifiers(arr);
		thueXeTable.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 18));
		//load data from database to table
		while(rs.next()) {
			arr[0] = rs.getString(1);
			String maKhachHang = rs.getString(2);
			arr[1] = maKhachHang;
			arr[2] = khachHangDatabase.getTenKhachHang(maKhachHang);
			String maNhanVien = rs.getString(3);
			arr[3] = maNhanVien;
			arr[4] = nhanVienDatabase.getTenNhanVien(maNhanVien);
			arr[5] = rs.getString(4);
			arr[6] = rs.getString(5);
			arr[7] = rs.getString(6);
			arr[8] = rs.getString(7);
			arr[9] = rs.getString(8);
			model.addRow(arr);
		}
		thueXeTable.setModel(model);
	}
	
	JTabbedPane TaiKhoan_Panel;
	JPanel ThongTin_Panel;
	JPanel DangKi_Panel;
	JPanel DoiMatKhau_Panel;
	JPanel DangXuat_Panel;
	
	private JTextField HoTen_TT;
	private JTextField NamSinh_TT;
	private JTextField SoDienThoai_TT;
	private JTextField QuyenHan_TT;
	private JTextField TenDangNhap_DK;
	private JTextField HoTen_DK;
	private JTextField SoDienThoai_DK;
	private JTextField MatKhau_DK;
	private JTextField NamSinh_DK;
	private JTextField Cap_DK;
	private JTextField TenDangNhap_DMK;
	private JTextField MatKhauMoi_DMK;
	private JTextField MatKhauCu_DMK;
	private JTextField NhapLaiMatKhauMoi_DMK;
	
	void setUpTaiKhoanPanel() throws Exception {
		
		TaiKhoan_Panel = new JTabbedPane(JTabbedPane.TOP);
		TaiKhoan_Panel.setFont(new Font("Time new roman ", Font.BOLD, 20));
		TaiKhoan_Panel.setBounds(0, 0, 1263, 610);
		taiKhoanPnl.add(TaiKhoan_Panel);
		
		ThongTin_Panel = new JPanel();
		TaiKhoan_Panel.addTab("Thong Tin Tai Khoan", null, ThongTin_Panel, null);
		ThongTin_Panel.setLayout(null);
		
		JLabel lblHoTen = new JLabel("Ho ten");
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setForeground(Color.BLACK);
		lblHoTen.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblHoTen.setBounds(376, 125, 200, 40);
		ThongTin_Panel.add(lblHoTen);
		
		JLabel lblNamSinh = new JLabel("Nam sinh");
		lblNamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamSinh.setForeground(Color.BLACK);
		lblNamSinh.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblNamSinh.setBounds(376, 188, 200, 40);
		ThongTin_Panel.add(lblNamSinh);
		
		JLabel lblSoDienThoai = new JLabel("So dien thoai");
		lblSoDienThoai.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoDienThoai.setForeground(Color.BLACK);
		lblSoDienThoai.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblSoDienThoai.setBounds(378, 256, 200, 40);
		ThongTin_Panel.add(lblSoDienThoai);
		
		JLabel lblQuynHn = new JLabel("Quyen han");
		lblQuynHn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuynHn.setForeground(Color.BLACK);
		lblQuynHn.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblQuynHn.setBounds(376, 320, 200, 40);
		ThongTin_Panel.add(lblQuynHn);
		
		HoTen_TT = new JTextField();
		HoTen_TT.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		HoTen_TT.setColumns(10);
		HoTen_TT.setBounds(576, 125, 326, 40);
		ThongTin_Panel.add(HoTen_TT);
		HoTen_TT.setEditable(false);
		
		NamSinh_TT = new JTextField();
		NamSinh_TT.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		NamSinh_TT.setColumns(10);
		NamSinh_TT.setBounds(576, 188, 326, 40);
		ThongTin_Panel.add(NamSinh_TT);
		NamSinh_TT.setEditable(false);
		
		SoDienThoai_TT = new JTextField();
		SoDienThoai_TT.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		SoDienThoai_TT.setColumns(10);
		SoDienThoai_TT.setBounds(576, 256, 326, 40);
		ThongTin_Panel.add(SoDienThoai_TT);
		SoDienThoai_TT.setEditable(false);
		
		QuyenHan_TT = new JTextField();
		QuyenHan_TT.setForeground(new Color(0, 0, 0));
		QuyenHan_TT.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		QuyenHan_TT.setColumns(10);
		QuyenHan_TT.setBounds(576, 320, 326, 40);
		ThongTin_Panel.add(QuyenHan_TT);
		QuyenHan_TT.setEditable(false);
		
		DangKi_Panel = new JPanel();
		TaiKhoan_Panel.addTab("Dang ki", null, DangKi_Panel, null);
		DangKi_Panel.setLayout(null);
		
		JLabel lblTenDangNhap = new JLabel("Ten dang nhap");
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenDangNhap.setForeground(Color.BLACK);
		lblTenDangNhap.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblTenDangNhap.setBounds(91, 91, 200, 40);
		DangKi_Panel.add(lblTenDangNhap);
		
		TenDangNhap_DK = new JTextField();
		TenDangNhap_DK.setHorizontalAlignment(SwingConstants.CENTER);
		TenDangNhap_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		TenDangNhap_DK.setColumns(10);
		TenDangNhap_DK.setBounds(291, 91, 326, 40);
		DangKi_Panel.add(TenDangNhap_DK);
		
		JLabel lblMatKhau = new JLabel("Mat khau");
		lblMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblMatKhau.setBounds(617, 91, 200, 40);
		DangKi_Panel.add(lblMatKhau);
		
		JLabel lblHoTen_1 = new JLabel("Ho ten");
		lblHoTen_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen_1.setForeground(Color.BLACK);
		lblHoTen_1.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblHoTen_1.setBounds(91, 183, 200, 40);
		DangKi_Panel.add(lblHoTen_1);
		
		JLabel lblNamSinh_1 = new JLabel("Nam sinh");
		lblNamSinh_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamSinh_1.setForeground(Color.BLACK);
		lblNamSinh_1.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblNamSinh_1.setBounds(617, 183, 200, 40);
		DangKi_Panel.add(lblNamSinh_1);
		
		JLabel lblSoDienThoai_1 = new JLabel("So dien thoai");
		lblSoDienThoai_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoDienThoai_1.setForeground(Color.BLACK);
		lblSoDienThoai_1.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblSoDienThoai_1.setBounds(91, 272, 200, 40);
		DangKi_Panel.add(lblSoDienThoai_1);
		
		JLabel lblCap = new JLabel("Cap");
		lblCap.setHorizontalAlignment(SwingConstants.CENTER);
		lblCap.setForeground(Color.BLACK);
		lblCap.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblCap.setBounds(617, 272, 200, 40);
		DangKi_Panel.add(lblCap);
		
		HoTen_DK = new JTextField();
		HoTen_DK.setHorizontalAlignment(SwingConstants.CENTER);
		HoTen_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		HoTen_DK.setColumns(10);
		HoTen_DK.setBounds(291, 183, 326, 40);
		DangKi_Panel.add(HoTen_DK);
		
		SoDienThoai_DK = new JTextField();
		SoDienThoai_DK.setHorizontalAlignment(SwingConstants.CENTER);
		SoDienThoai_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		SoDienThoai_DK.setColumns(10);
		SoDienThoai_DK.setBounds(291, 272, 326, 40);
		DangKi_Panel.add(SoDienThoai_DK);
		
		MatKhau_DK = new JTextField();
		MatKhau_DK.setHorizontalAlignment(SwingConstants.CENTER);
		MatKhau_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		MatKhau_DK.setColumns(10);
		MatKhau_DK.setBounds(817, 91, 326, 40);
		DangKi_Panel.add(MatKhau_DK);
		
		NamSinh_DK = new JTextField();
		NamSinh_DK.setHorizontalAlignment(SwingConstants.CENTER);
		NamSinh_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		NamSinh_DK.setColumns(10);
		NamSinh_DK.setBounds(817, 183, 326, 40);
		DangKi_Panel.add(NamSinh_DK);
		
		Cap_DK = new JTextField();
		Cap_DK.setHorizontalAlignment(SwingConstants.CENTER);
		Cap_DK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		Cap_DK.setColumns(10);
		Cap_DK.setBounds(817, 272, 326, 40);
		DangKi_Panel.add(Cap_DK);
		
		JButton btnDangKi = new JButton("DANG KY", dangKyIcon);
		btnDangKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TenDangNhap_DK.getText().equals("")||MatKhau_DK.getText().equals("")||HoTen_DK.getText().equals("")||NamSinh_DK.getText().equals("")||SoDienThoai_DK.getText().equals("")||Cap_DK.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin","Loi",0);
				else 
					{
					try{
						if(new TaiKhoanDatabase().taiKhoanDaTonTai(TenDangNhap_DK.getText()))
							JOptionPane.showMessageDialog(null, "Tai Khoan da ton tai\nVui long doi ten tai khoan","Loi",0);
						else {
							new TaiKhoanDatabase().themTaiKhoan(TenDangNhap_DK.getText(),MatKhau_DK.getText(),HoTen_DK.getText(),NamSinh_DK.getText(),SoDienThoai_DK.getText(),Cap_DK.getText());
							JOptionPane.showMessageDialog(null, "Dang ki thanh cong","Thanh cong",1);
						}
						} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Loi","Loi",0);
					}
				}
			}
		});
		btnDangKi.setFont(new Font("Avenir Next", Font.BOLD, 26));
		btnDangKi.setBounds(508, 392, 220, 110);
		DangKi_Panel.add(btnDangKi);
		
//		JButton btnXoa = new JButton("Xoa");
//		btnXoa.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				TenDangNhap_DK.setText("");
//				MatKhau_DK.setText("");
//				HoTen_DK.setText("");
//				NamSinh_DK.setText("");
//				SoDienThoai_DK.setText("");
//				Cap_DK.setText("");
//			}
//		});
//		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		btnXoa.setBounds(848, 439, 114, 37);
//		DangKi_Panel.add(btnXoa);
		
		DoiMatKhau_Panel = new JPanel();
		TaiKhoan_Panel.addTab("Doi Mat Khau", null, DoiMatKhau_Panel, null);
		DoiMatKhau_Panel.setLayout(null);
		
		JLabel lblTenDangNhap_1 = new JLabel("Ten dang nhap");
		lblTenDangNhap_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenDangNhap_1.setForeground(Color.BLACK);
		lblTenDangNhap_1.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblTenDangNhap_1.setBounds(311, 95, 300, 40);
		DoiMatKhau_Panel.add(lblTenDangNhap_1);
		
		JLabel lblMatKhauCu = new JLabel("Mat khau cu");
		lblMatKhauCu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauCu.setForeground(Color.BLACK);
		lblMatKhauCu.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblMatKhauCu.setBounds(311, 199, 300, 40);
		DoiMatKhau_Panel.add(lblMatKhauCu);
		
		TenDangNhap_DMK = new JTextField();
		TenDangNhap_DMK.setHorizontalAlignment(SwingConstants.CENTER);
		TenDangNhap_DMK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		TenDangNhap_DMK.setColumns(10);
		TenDangNhap_DMK.setBounds(611, 95, 326, 40);
		DoiMatKhau_Panel.add(TenDangNhap_DMK);
		
		JLabel lblMatKhauMi = new JLabel("Mat khau moi");
		lblMatKhauMi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauMi.setForeground(Color.BLACK);
		lblMatKhauMi.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblMatKhauMi.setBounds(311, 147, 300, 40);
		DoiMatKhau_Panel.add(lblMatKhauMi);
		
		JLabel lblNhapLaiMat = new JLabel("Nhap lai mat khau moi");
		lblNhapLaiMat.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapLaiMat.setForeground(Color.BLACK);
		lblNhapLaiMat.setFont(new Font("Avenir Next", Font.BOLD, 24));
		lblNhapLaiMat.setBounds(311, 251, 300, 40);
		DoiMatKhau_Panel.add(lblNhapLaiMat);
		
		MatKhauMoi_DMK = new JTextField();
		MatKhauMoi_DMK.setHorizontalAlignment(SwingConstants.CENTER);
		MatKhauMoi_DMK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		MatKhauMoi_DMK.setColumns(10);
		MatKhauMoi_DMK.setBounds(611, 147, 326, 40);
		DoiMatKhau_Panel.add(MatKhauMoi_DMK);
		
		MatKhauCu_DMK = new JTextField();
		MatKhauCu_DMK.setHorizontalAlignment(SwingConstants.CENTER);
		MatKhauCu_DMK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		MatKhauCu_DMK.setColumns(10);
		MatKhauCu_DMK.setBounds(611, 199, 326, 40);
		DoiMatKhau_Panel.add(MatKhauCu_DMK);
		
		NhapLaiMatKhauMoi_DMK = new JTextField();
		NhapLaiMatKhauMoi_DMK.setHorizontalAlignment(SwingConstants.CENTER);
		NhapLaiMatKhauMoi_DMK.setFont(new Font("Avenir Next", Font.PLAIN, 24));
		NhapLaiMatKhauMoi_DMK.setColumns(10);
		NhapLaiMatKhauMoi_DMK.setBounds(611, 251, 326, 40);
		DoiMatKhau_Panel.add(NhapLaiMatKhauMoi_DMK);
		
		JButton btnThayDoi = new JButton("DOI MAT KHAU", suaIcon);
//		btnThayDoi.setText("<html><center>DOI<br/>MAT<br/>KHAU</center></html>");
		btnThayDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TenDangNhap_DMK.getText().equals("")||MatKhauCu_DMK.getText().equals("")||MatKhauMoi_DMK.getText().equals("")||NhapLaiMatKhauMoi_DMK.getText().equals("")) 
					JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin","Loi",0);
				else if (!MatKhauMoi_DMK.getText().equals(NhapLaiMatKhauMoi_DMK.getText())) 
					JOptionPane.showMessageDialog(null, "Mat khau moi khong trung khop","Loi",0);
				else {
					try {
						String MatKhauCu = new TaiKhoanDatabase().getMatKhau(TenDangNhap_DMK.getText());
						if(MatKhauCu.equals(MatKhauCu_DMK.getText()))
						{
							new TaiKhoanDatabase().doiMatKhau(TenDangNhap_DMK.getText(),MatKhauMoi_DMK.getText());
							JOptionPane.showMessageDialog(null, "Doi mat khau thanh cong","Thanh cong",1);
						}
						else
							JOptionPane.showMessageDialog(null, "Mat khau khong dung ","Loi",0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnThayDoi.setFont(new Font("Avenir Next", Font.BOLD, 26));
		btnThayDoi.setBounds(468, 371, 312, 80);
		DoiMatKhau_Panel.add(btnThayDoi);
		
//		JButton btnXoa_1 = new JButton("Xoa");
//		btnXoa_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				TenDangNhap_DMK.setText("");
//				MatKhauCu_DMK.setText("");
//				MatKhauMoi_DMK.setText("");
//				NhapLaiMatKhauMoi_DMK.setText("");
//			}
//		});
//		btnXoa_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		btnXoa_1.setBounds(854, 376, 114, 37);
//		DoiMatKhau_Panel.add(btnXoa_1);
		
		DangXuat_Panel = new JPanel();
		TaiKhoan_Panel.addTab("Dang Xuat", null, DangXuat_Panel, null);
		DangXuat_Panel.setLayout(null);
		
		JLabel lblTamBietVa = new JLabel("Tam biet va hen gap lai");
		lblTamBietVa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamBietVa.setForeground(Color.BLACK);
		lblTamBietVa.setFont(new Font("Avenir Next", Font.BOLD, 40));
		lblTamBietVa.setBounds(324, 132, 599, 55);
		DangXuat_Panel.add(lblTamBietVa);
		
		JButton btnDangXuat = new JButton("DANG XUAT", dangXuatIcon);
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DangNhap window = new DangNhap();
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnDangXuat.setFont(new Font("Avenir Next", Font.BOLD, 40));
		btnDangXuat.setBounds(438, 279, 370, 80);
		DangXuat_Panel.add(btnDangXuat);
	}
	
	
	public void thongtintaikhoan(TaiKhoan taikhoan)
	{
		HoTen_TT.setText(taikhoan.getHoTen());
		NamSinh_TT.setText(""+taikhoan.getNamSinh());
		SoDienThoai_TT.setText(taikhoan.getDienThoai());
		if(taikhoan.getCap()==1) QuyenHan_TT.setText("Admin");
		else 
			{
				QuyenHan_TT.setText("user");
				TaiKhoan_Panel.remove(DangKi_Panel);
			}
		
	}
	
}
