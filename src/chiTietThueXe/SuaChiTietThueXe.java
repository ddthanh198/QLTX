package chiTietThueXe;

import javax.swing.JFrame;

import giaoDienChinh.GiaoDienChinh;
import thongBao.*;
import thueXe.XemChiTietThueXe;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.KhachHangDatabase;
import database.NhanVienDatabase;
import database.XeDatabase;
import database.ChiTietThueXeDatabase;
import date.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class SuaChiTietThueXe {

	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon suaIcon = new ImageIcon(iconFolderPath + "sua.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");

	GiaoDienChinh giaoDienChinh;	
	XemChiTietThueXe xemChiTietThueXe;
	
	XeDatabase xeDatabase;
	ChiTietThueXeDatabase chiTietThueXeDatabase;
	
	public JFrame frame;
	String maThueXeCanSua;
	String maXeCanSua;
	
	DefaultComboBoxModel maXeModel;
	private JComboBox maXe_comboBox;

	private JTextField maThueXe_tf;
	private JTextField tenXe_tf;
	private JTextField loaiXe_tf;
	private JTextField namSanXuat_tf;
	private JTextField ngayTra_tf;


	/**
	 * Create the application.
	 */
	public SuaChiTietThueXe(GiaoDienChinh giaoDienChinh, XemChiTietThueXe xemChiTietThueXe, String maThueXeCanSua, String maXeCanSua) {
		this.giaoDienChinh = giaoDienChinh;
		this.xemChiTietThueXe = xemChiTietThueXe;
		this.maThueXeCanSua = maThueXeCanSua;
		this.maXeCanSua = maXeCanSua;
		xeDatabase = new XeDatabase();
		chiTietThueXeDatabase = new ChiTietThueXeDatabase();
		chiTietThueXeDatabase.connection = xeDatabase.connection;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sua Thue Xe");
		frame.setBounds(300, 100, 555, 515);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		try {
			setUpComboBox();
			setSelectedItemForComboBox();
			setTextForTextFields();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUpButtons();
	}
	
	void setUpLabels() {

		JLabel lblMaChiTietThueXe = new JLabel("1. Ma Thue Xe");
		lblMaChiTietThueXe.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaChiTietThueXe.setBounds(29, 28, 174, 41);
		frame.getContentPane().add(lblMaChiTietThueXe);
		
		JLabel lblMaKhach = new JLabel("2. Ma Xe");
		lblMaKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaKhach.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(lblMaKhach);
		
		JLabel lblTenKhach = new JLabel("3. Ten Xe");
		lblTenKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenKhach.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(lblTenKhach);
		
		JLabel lblMaNhan = new JLabel("4. Loai Xe");
		lblMaNhan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaNhan.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(lblMaNhan);
		
		JLabel lblTenNhan = new JLabel("5. Nam San Xuat ");
		lblTenNhan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenNhan.setBounds(29, 242, 174, 41);
		frame.getContentPane().add(lblTenNhan);
		
		JLabel lblNgayThue = new JLabel("6. Ngay Tra");
		lblNgayThue.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblNgayThue.setBounds(29, 295, 174, 41);
		frame.getContentPane().add(lblNgayThue);
	}
	
	void setUpTextFields() {
		maThueXe_tf = new JTextField();
		maThueXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maThueXe_tf.setEditable(false);
		maThueXe_tf.setColumns(10);
		maThueXe_tf.setBackground(new Color(176, 196, 222));
		maThueXe_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maThueXe_tf);
		
		tenXe_tf = new JTextField();
		tenXe_tf.setBackground(new Color(176, 196, 222));
		tenXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenXe_tf.setColumns(10);
		tenXe_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(tenXe_tf);
		tenXe_tf.setEditable(false);
		
		loaiXe_tf = new JTextField();
		loaiXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		loaiXe_tf.setEditable(false);
		loaiXe_tf.setColumns(10);
		loaiXe_tf.setBackground(new Color(176, 196, 222));
		loaiXe_tf.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(loaiXe_tf);
		
		namSanXuat_tf = new JTextField();
		namSanXuat_tf.setBackground(new Color(176, 196, 222));
		namSanXuat_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		namSanXuat_tf.setColumns(10);
		namSanXuat_tf.setBounds(215, 242, 317, 41);
		frame.getContentPane().add(namSanXuat_tf);
		namSanXuat_tf.setEditable(false);
		
		ngayTra_tf = new JTextField();
		ngayTra_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayTra_tf.setColumns(10);
		ngayTra_tf.setBounds(215, 295, 317, 41);
		frame.getContentPane().add(ngayTra_tf);
		
	}
	
	void setUpComboBox() throws Exception {
		createMaXeModel();
		maXe_comboBox = new JComboBox(maXeModel);
		maXe_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maXe = maXe_comboBox.getSelectedItem().toString();
				try {
					tenXe_tf.setText(xeDatabase.getTenXe(maXe));
					loaiXe_tf.setText(xeDatabase.getLoaiXe(maXe));
					namSanXuat_tf.setText(String.valueOf(xeDatabase.getNamSanXuat(maXe)));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		maXe_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maXe_comboBox.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(maXe_comboBox);
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
		suaBtn.setBounds(312, 366, 220, 100);
		frame.getContentPane().add(suaBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(29, 366, 220, 100);
		frame.getContentPane().add(huyBtn);
	}
	
	void suaBtnPressed() throws Exception {
		suaChiTietThueXe();
		giaoDienChinh.updateTable("Xe", giaoDienChinh.xeTable);
		giaoDienChinh.updateThueXeTable();
		xemChiTietThueXe.updateXemChiTietThueXeView();
		frame.dispose();
	}
	
	void suaChiTietThueXe() throws Exception {
		String maXeMoi = maXe_comboBox.getSelectedItem().toString();
		if(chiTietThueXeDatabase.xeDaDuocTra(maThueXeCanSua, maXeCanSua)) {
			Date ngayTra = new Date(ngayTra_tf.getText());
			chiTietThueXeDatabase.suaChiTietThueXe_daTra(maXeMoi, ngayTra, maThueXeCanSua, maXeCanSua);
		} else {
			chiTietThueXeDatabase.suaChiTietThueXe_chuaTra(maXeMoi, maThueXeCanSua, maXeCanSua);
		}
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
	
	
	void setTextForTextFields() throws Exception {
		maThueXe_tf.setText(maThueXeCanSua);
		String maXe = maXe_comboBox.getSelectedItem().toString();
		try {
			tenXe_tf.setText(xeDatabase.getTenXe(maXe));
			loaiXe_tf.setText(xeDatabase.getLoaiXe(maXe));
			namSanXuat_tf.setText(String.valueOf(xeDatabase.getNamSanXuat(maXe)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(chiTietThueXeDatabase.xeDaDuocTra(maThueXeCanSua, maXeCanSua)) {
			ngayTra_tf.setText(chiTietThueXeDatabase.getNgayTra(maThueXeCanSua, maXe).getDateString());
		} else {
			ngayTra_tf.setBackground(new Color(176, 196, 222));
			ngayTra_tf.setEditable(false);
		}
	}
	
	void createMaXeModel() throws Exception {
		maXeModel = new DefaultComboBoxModel();
		ResultSet maXeRS = xeDatabase.getMaXeResultSetWithTrangThai("Co");
		while(maXeRS.next()) {
			maXeModel.addElement(maXeRS.getString(1));
		}
	}
	
	void setSelectedItemForComboBox() {
		maXeModel.setSelectedItem(maXeCanSua);
	}
	
}
