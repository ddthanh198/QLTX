package chiTietThueXe;

import javax.swing.JFrame;

import thongBao.*;
import thueXe.XemChiTietThueXe;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.ChiTietThueXeDatabase;
import database.XeDatabase;
import date.Date;
import giaoDienChinh.GiaoDienChinh;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class TraXe {

	GiaoDienChinh giaoDienChinh;
	XemChiTietThueXe xemChiTietThueXe;
	public JFrame frame;
	String maThueXe;
	String maXe;
	
	XeDatabase xeDatabase;
	ChiTietThueXeDatabase chiTietThueXeDatabase;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon traXeIcon = new ImageIcon(iconFolderPath + "traXe.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");

	private JTextField maXe_tf;
	private JTextField tenXe_tf;
	private JTextField ngayTra_tf;


	/**
	 * Create the application.
	 */
	public TraXe(GiaoDienChinh giaoDienChinh, XemChiTietThueXe xemChiTietThueXe, String maThueXe, String maXe) {
		this.giaoDienChinh = giaoDienChinh;
		this.xemChiTietThueXe = xemChiTietThueXe;
		this.maThueXe = maThueXe;
		this.maXe = maXe;
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
		frame.setTitle("Tra Xe");
		frame.setBounds(350, 150, 555, 347);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
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
		
		JLabel lblTenKhach = new JLabel(">> Nhap Ngay Tra");
		lblTenKhach.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTenKhach.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(lblTenKhach);
	}
	
	void setUpTextFields() {
		maXe_tf = new JTextField();
		maXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maXe_tf.setEditable(false);
		maXe_tf.setColumns(10);
		maXe_tf.setBackground(new Color(176, 196, 222));
		maXe_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maXe_tf);
		
		tenXe_tf = new JTextField();
		tenXe_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenXe_tf.setEditable(false);
		tenXe_tf.setColumns(10);
		tenXe_tf.setBackground(new Color(176, 196, 222));
		tenXe_tf.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(tenXe_tf);
		
		ngayTra_tf = new JTextField();
		ngayTra_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		ngayTra_tf.setColumns(10);
		ngayTra_tf.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(ngayTra_tf);
	}
	
	void setTextForTextFields() {
		maXe_tf.setText(maXe);
		try {
			tenXe_tf.setText(xeDatabase.getTenXe(maXe));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setUpButtons() {
		JButton traXeBtn = new JButton("TRA XE", traXeIcon);
		traXeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					traXeBtnPressed();
					showThongBaoThanhCong();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showThongBaoThatBai();
				}
			}
		});
		traXeBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		traXeBtn.setBounds(312, 197, 220, 100);
		frame.getContentPane().add(traXeBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(29, 197, 220, 100);
		frame.getContentPane().add(huyBtn);
	}
	
	void traXeBtnPressed() throws Exception {
		traXe();
		giaoDienChinh.updateTable("Xe", giaoDienChinh.xeTable);
		giaoDienChinh.updateThueXeTable();
		xemChiTietThueXe.updateXemChiTietThueXeView();
		frame.dispose();
	}
	
	void traXe() throws Exception {
		Date ngayTra = new Date(ngayTra_tf.getText());
		chiTietThueXeDatabase.setNgayTraFor(maThueXe, maXe, ngayTra);
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
