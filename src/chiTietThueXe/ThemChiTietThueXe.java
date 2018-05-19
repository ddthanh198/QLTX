package chiTietThueXe;

import javax.swing.JFrame;

import thongBao.*;
import thueXe.XemChiTietThueXe;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.*;
import giaoDienChinh.GiaoDienChinh;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class ThemChiTietThueXe {

	GiaoDienChinh giaoDienChinh;
	XemChiTietThueXe xemChiTietThueXe;

	XeDatabase xeDatabase;
	ChiTietThueXeDatabase chiTietThueXeDatabase;
	
	public JFrame frame;
	
	private JComboBox maXe_comboBox;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon themIcon = new ImageIcon(iconFolderPath + "them.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");

	private JTextField tenXe_tf;
	private JTextField loaiXe_tf;
	private JTextField namSanXuat_tf;


	/**
	 * Create the application.
	 */
	public ThemChiTietThueXe(GiaoDienChinh giaoDienChinh, XemChiTietThueXe xemChiTietThueXe) {
		this.giaoDienChinh = giaoDienChinh;
		this.xemChiTietThueXe = xemChiTietThueXe;
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
		frame.setTitle("Thue Xe");
		frame.setBounds(300, 100, 555, 412);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		try {
			setUpComboBox();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTextForTextFields();
		setUpButtons();
	}
	
	void setUpLabels() {
		JLabel lblMaChiTietThueXe = new JLabel("1. Ma Xe");
		lblMaChiTietThueXe.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaChiTietThueXe.setBounds(29, 28, 174, 41);
		frame.getContentPane().add(lblMaChiTietThueXe);
		
		JLabel lblMaKhach = new JLabel("2. Ten Xe");
		lblMaKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaKhach.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(lblMaKhach);
		
		JLabel lblTenKhach = new JLabel("3. Loai Xe");
		lblTenKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenKhach.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(lblTenKhach);
		
		JLabel lblNamSan = new JLabel("4. Nam San Xuat");
		lblNamSan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblNamSan.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(lblNamSan);
	}
	
	void setUpTextFields() {
		tenXe_tf = new JTextField();
		tenXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenXe_tf.setEditable(false);
		tenXe_tf.setColumns(10);
		tenXe_tf.setBackground(new Color(176, 196, 222));
		tenXe_tf.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(tenXe_tf);
		
		loaiXe_tf = new JTextField();
		loaiXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		loaiXe_tf.setEditable(false);
		loaiXe_tf.setColumns(10);
		loaiXe_tf.setBackground(new Color(176, 196, 222));
		loaiXe_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(loaiXe_tf);
		
		namSanXuat_tf = new JTextField();
		namSanXuat_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		namSanXuat_tf.setEditable(false);
		namSanXuat_tf.setColumns(10);
		namSanXuat_tf.setBackground(new Color(176, 196, 222));
		namSanXuat_tf.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(namSanXuat_tf);
	}
	
	void setTextForTextFields() {
		String maXe = maXe_comboBox.getSelectedItem().toString();
		String tenXe = "";
		String loaiXe = "";
		String namSanXuat = "";
		try {
			tenXe = xeDatabase.getTenXe(maXe);
			loaiXe = xeDatabase.getLoaiXe(maXe);
			namSanXuat = String.valueOf(xeDatabase.getNamSanXuat(maXe));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tenXe_tf.setText(tenXe);
		loaiXe_tf.setText(loaiXe);
		namSanXuat_tf.setText(namSanXuat);
	}
	
	void setUpComboBox() throws Exception {
		maXe_comboBox = new JComboBox(createMaXeModel());
		maXe_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maXe = maXe_comboBox.getSelectedItem().toString();
				String tenXe = "";
				String loaiXe = "";
				String namSanXuat = "";
				try {
					tenXe = xeDatabase.getTenXe(maXe);
					loaiXe = xeDatabase.getLoaiXe(maXe);
					namSanXuat = String.valueOf(xeDatabase.getNamSanXuat(maXe));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tenXe_tf.setText(tenXe);
				loaiXe_tf.setText(loaiXe);
				namSanXuat_tf.setText(namSanXuat);
			}
		});
		maXe_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maXe_comboBox.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maXe_comboBox);
	}
	
	void setUpButtons() {
		JButton themBtn = new JButton("THEM", themIcon);
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					themBtnPressed();
					showThongBaoThanhCong();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showThongBaoThatBai();
				}
			}
		});
		themBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		themBtn.setBounds(312, 256, 220, 100);
		frame.getContentPane().add(themBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(29, 256, 220, 100);
		frame.getContentPane().add(huyBtn);
	}
	
	void themBtnPressed() throws Exception {
		themChiTietThueXe();
		giaoDienChinh.updateTable("Xe", giaoDienChinh.xeTable);
		giaoDienChinh.updateThueXeTable();
		xemChiTietThueXe.updateXemChiTietThueXeView();
		updateMaXeComboBox();
	}
	
	void themChiTietThueXe() throws Exception {
		String maThueXe = xemChiTietThueXe.maThueXe;
		String maXe = maXe_comboBox.getSelectedItem().toString();
		chiTietThueXeDatabase.themChiTietThueXe(maThueXe, maXe);
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
	
	DefaultComboBoxModel createMaXeModel() throws Exception {
		DefaultComboBoxModel maXeModel = new DefaultComboBoxModel();
		ResultSet maXeRS = xeDatabase.getMaXeResultSetWithTrangThai("Co");
		while(maXeRS.next()) {
			maXeModel.addElement(maXeRS.getString(1));
		}
		return maXeModel;
	}
	
	void updateMaXeComboBox() throws Exception {
		maXe_comboBox.setModel(createMaXeModel());
		setTextForTextFields();
	}
	
}
