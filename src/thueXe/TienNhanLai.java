package thueXe;

import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.*;
import giaoDienChinh.GiaoDienChinh;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class TienNhanLai {

	GiaoDienChinh giaoDienChinh;

	ThueXeDatabase thueXeDatabase;
	KhachHangDatabase khachHangDatabase; 
	NhanVienDatabase nhanVienDatabase;
	
	public JFrame frame;
	String maThueXe;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon okIcon = new ImageIcon(iconFolderPath + "ok.png");

	private JTextField maThueXe_tf;
	private JTextField tenKhachHang_tf;
	private JTextField tenNhanVien_tf;
	private JTextField tienCoc_tf;
	private JTextField tongTienPhat_tf;
	private JTextField message_tf;


	/**
	 * Create the application.
	 */
	public TienNhanLai(GiaoDienChinh giaoDienChinh, String maThueXe) {
		this.giaoDienChinh = giaoDienChinh;
		this.maThueXe = maThueXe;
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
		frame.setBounds(350, 150, 555, 502);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		try {
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
		
		JLabel lblMaKhach = new JLabel("2. Ten Khach Hang");
		lblMaKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaKhach.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(lblMaKhach);
		
		JLabel lblTenNhan = new JLabel("3. Ten Nhan Vien");
		lblTenNhan.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenNhan.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(lblTenNhan);
		
		JLabel lblTienCoc = new JLabel("4. Tien Coc");
		lblTienCoc.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTienCoc.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(lblTienCoc);
		
		JLabel lblTongTien = new JLabel("5. Tong Tien Phat");
		lblTongTien.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTongTien.setBounds(29, 240, 174, 41);
		frame.getContentPane().add(lblTongTien);
		
		JLabel label = new JLabel(">>");
		label.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label.setBounds(29, 293, 25, 41);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("<<");
		label_1.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_1.setBounds(507, 293, 25, 41);
		frame.getContentPane().add(label_1);
	}
	
	void setUpTextFields() {
		maThueXe_tf = new JTextField();
		maThueXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maThueXe_tf.setEditable(false);
		maThueXe_tf.setColumns(10);
		maThueXe_tf.setBackground(new Color(176, 196, 222));
		maThueXe_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maThueXe_tf);
		
		tenKhachHang_tf = new JTextField();
		tenKhachHang_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenKhachHang_tf.setEditable(false);
		tenKhachHang_tf.setColumns(10);
		tenKhachHang_tf.setBackground(new Color(176, 196, 222));
		tenKhachHang_tf.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(tenKhachHang_tf);
		
		tenNhanVien_tf = new JTextField();
		tenNhanVien_tf.setText((String) null);
		tenNhanVien_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenNhanVien_tf.setEditable(false);
		tenNhanVien_tf.setColumns(10);
		tenNhanVien_tf.setBackground(new Color(176, 196, 222));
		tenNhanVien_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(tenNhanVien_tf);
		
		tienCoc_tf = new JTextField();
		tienCoc_tf.setText((String) null);
		tienCoc_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tienCoc_tf.setEditable(false);
		tienCoc_tf.setColumns(10);
		tienCoc_tf.setBackground(new Color(176, 196, 222));
		tienCoc_tf.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(tienCoc_tf);
		
		tongTienPhat_tf = new JTextField();
		tongTienPhat_tf.setText((String) null);
		tongTienPhat_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tongTienPhat_tf.setEditable(false);
		tongTienPhat_tf.setColumns(10);
		tongTienPhat_tf.setBackground(new Color(176, 196, 222));
		tongTienPhat_tf.setBounds(215, 240, 317, 41);
		frame.getContentPane().add(tongTienPhat_tf);
		
		message_tf = new JTextField();
		message_tf.setHorizontalAlignment(SwingConstants.CENTER);
		message_tf.setText((String) null);
		message_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		message_tf.setEditable(false);
		message_tf.setColumns(10);
		message_tf.setBackground(new Color(176, 196, 222));
		message_tf.setBounds(66, 293, 429, 41);
		frame.getContentPane().add(message_tf);
	}
	
	void setTextForTextFields() throws Exception {
		maThueXe_tf.setText(maThueXe);
		tenKhachHang_tf.setText(khachHangDatabase.getTenKhachHang(thueXeDatabase.getMaKhachHang(maThueXe)));
		tenNhanVien_tf.setText(nhanVienDatabase.getTenNhanVien(thueXeDatabase.getMaNhanVien(maThueXe)));
		long tienCoc = thueXeDatabase.getTienCoc(maThueXe);
		tienCoc_tf.setText(String.valueOf(tienCoc));
		long tongTienPhat = thueXeDatabase.getTongTienPhat(maThueXe);
		tongTienPhat_tf.setText(String.valueOf(tongTienPhat));
		if(thueXeDatabase.daTraHetXe(maThueXe)) {
			long tienNhanLai = tienCoc - tongTienPhat;
			if(tienNhanLai >= 0) {
				message_tf.setText("Khach hang duoc nhan lai " + tienNhanLai + " vnd.");
			} else {
				tienNhanLai = -tienNhanLai;
				message_tf.setText("Khach hang phai nop them " + tienNhanLai + " vnd.");
			}
		} else {
			message_tf.setText("Khach hang chua tra het xe.");
		}
	}
	
	void setUpButtons() {
		JButton okBtn = new JButton("OK", okIcon);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		okBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		okBtn.setBounds(29, 358, 501, 100);
		frame.getContentPane().add(okBtn);
		
	}
}