package xe;

import javax.swing.JFrame;

import giaoDienChinh.GiaoDienChinh;
import thongBao.*;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.XeDatabase;
import date.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SuaXe {

	GiaoDienChinh giaoDienChinh;	
	
	XeDatabase xeDatabase;
	
	public JFrame frame;
	String maXeCanSua;
	
	private JTextField maXe_tf;
	private JTextField tenXe_tf;
	private JTextField loaiXe_tf;
	private JTextField namSanXuat_tf;
	private JTextField ngayNhap_tf;
	private JTextField tinhTrangXe_tf;
	private JTextField donGiaThue_tf;
	
	private JComboBox trangThai_comboBox;
	private DefaultComboBoxModel trangThaiModel;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon suaIcon = new ImageIcon(iconFolderPath + "sua.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");


	/**
	 * Create the application.
	 */
	public SuaXe(GiaoDienChinh giaoDienChinh, String maXeCanSua) {
		this.maXeCanSua = maXeCanSua;
		this.giaoDienChinh = giaoDienChinh;
		xeDatabase = new XeDatabase();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sua Xe");
		frame.setBounds(300, 100, 555, 617);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		setUpComboBox();
		setUpButtons();
		try {
			setTextForTextFields();
			setSelectedItemForComboBox();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpLabels() {
		JLabel lblMaXe = new JLabel("1. Ma Xe");
		lblMaXe.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaXe.setBounds(29, 28, 174, 41);
		frame.getContentPane().add(lblMaXe);
		
		JLabel label = new JLabel("2. Ten Xe");
		label.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("3. Loai Xe");
		label_1.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_1.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("4. Nam San Xuat");
		label_2.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_2.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("5. Ngay Nhap");
		label_3.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_3.setBounds(29, 240, 174, 41);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("6. Tinh Trang Xe");
		label_4.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_4.setBounds(29, 293, 174, 41);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("7. Don Gia Thue");
		label_5.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_5.setBounds(29, 346, 174, 41);
		frame.getContentPane().add(label_5);
		
		JLabel label_7 = new JLabel("8. Trang Thai");
		label_7.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_7.setBounds(29, 399, 174, 41);
		frame.getContentPane().add(label_7);
	}
	
	void setUpTextFields() {
		maXe_tf = new JTextField();
		maXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maXe_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maXe_tf);
		maXe_tf.setColumns(10);
		
		tenXe_tf = new JTextField();
		tenXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenXe_tf.setColumns(10);
		tenXe_tf.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(tenXe_tf);
		
		loaiXe_tf = new JTextField();
		loaiXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		loaiXe_tf.setColumns(10);
		loaiXe_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(loaiXe_tf);

		namSanXuat_tf = new JTextField();
		namSanXuat_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		namSanXuat_tf.setColumns(10);
		namSanXuat_tf.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(namSanXuat_tf);
		
		ngayNhap_tf = new JTextField();
		ngayNhap_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayNhap_tf.setColumns(10);
		ngayNhap_tf.setBounds(215, 240, 317, 41);
		frame.getContentPane().add(ngayNhap_tf);
		
		tinhTrangXe_tf = new JTextField();
		tinhTrangXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tinhTrangXe_tf.setColumns(10);
		tinhTrangXe_tf.setBounds(215, 293, 317, 41);
		frame.getContentPane().add(tinhTrangXe_tf);
		
		donGiaThue_tf = new JTextField();
		donGiaThue_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		donGiaThue_tf.setColumns(10);
		donGiaThue_tf.setBounds(215, 346, 317, 41);
		frame.getContentPane().add(donGiaThue_tf);
		
	}
	
	void setTextForTextFields() throws Exception {
		Xe xe = xeDatabase.getXe(maXeCanSua);
		maXe_tf.setText(maXeCanSua);
		tenXe_tf.setText(xe.getTenXe());
		loaiXe_tf.setText(xe.getLoaiXe());
		namSanXuat_tf.setText(String.valueOf(xe.getNamSanXuat()));
		ngayNhap_tf.setText(xe.getNgayNhap().getDateString());
		tinhTrangXe_tf.setText(xe.getTinhTrangXe());
		donGiaThue_tf.setText(String.valueOf(xe.getDonGiaThue()));
	}
	
	void setUpComboBox() {
		trangThaiModel = new DefaultComboBoxModel();
		trangThaiModel.addElement("Co");
		trangThaiModel.addElement("Khong");
		
		trangThai_comboBox = new JComboBox(trangThaiModel);
		trangThai_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		trangThai_comboBox.setBounds(215, 399, 317, 41);
		frame.getContentPane().add(trangThai_comboBox);
	}
	
	void setSelectedItemForComboBox() throws Exception {
		Xe xe = xeDatabase.getXe(maXeCanSua);
		String trangThai = xe.getTrangThai();
		trangThaiModel.setSelectedItem(trangThai);
	}
	
	void setUpButtons() {
		JButton suaBtn = new JButton("SUA", suaIcon);
		suaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					suaBtnPressed();
					showThongBaoThanhCong();
					giaoDienChinh.updateTable("Xe", giaoDienChinh.xeTable);
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showThongBaoThatBai();
				}
			}
		});
		suaBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		suaBtn.setBounds(312, 468, 220, 100);
		frame.getContentPane().add(suaBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(29, 468, 220, 100);
		frame.getContentPane().add(huyBtn);
		
	}
	
	void suaBtnPressed() throws Exception {
		String maXe = maXe_tf.getText();
		String tenXe = tenXe_tf.getText();
		String loaiXe = loaiXe_tf.getText();
		String namSanXuat = namSanXuat_tf.getText();
		String ngayNhap = ngayNhap_tf.getText();
		String tinhTrangXe = tinhTrangXe_tf.getText();
		String donGiaThue = donGiaThue_tf.getText();
		String trangThai = trangThai_comboBox.getSelectedItem().toString();
		
		Xe xeMoi = new Xe(maXe, tenXe, loaiXe, Integer.parseInt(namSanXuat), new Date(ngayNhap), tinhTrangXe, Long.parseLong(donGiaThue), trangThai);
		xeDatabase.suaXe(xeMoi, maXeCanSua);
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
}
