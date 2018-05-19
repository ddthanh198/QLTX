package khachHang;

import javax.swing.JFrame;

import giaoDienChinh.GiaoDienChinh;
import thongBao.*;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.KhachHangDatabase;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ThemKhachHang {

	GiaoDienChinh giaoDienChinh;	
	
	KhachHangDatabase khachHangDatabase;
	
	public JFrame frame;
	
	private JTextField maKhachHang_tf;
	private JTextField tenKhachHang_tf;
	private JTextField namSinh_tf;
	private JTextField CMND_tf;
	private JTextField DT_tf;
	private JTextField email_tf;
	private JTextField diaChi_tf;
	
	private JComboBox gioiTinh_comboBox;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon themIcon = new ImageIcon(iconFolderPath + "them.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");


	/**
	 * Create the application.
	 */
	public ThemKhachHang(GiaoDienChinh giaoDienChinh) {
		khachHangDatabase = new KhachHangDatabase();
		this.giaoDienChinh = giaoDienChinh;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Them Khach Hang");
		frame.setBounds(300, 100, 555, 617);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		setUpComboBox();
		setUpButtons();
	}
	
	void setUpLabels() {

		JLabel lblMaKhachHang = new JLabel("1. Ma Khach Hang");
		lblMaKhachHang.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblMaKhachHang.setBounds(29, 28, 174, 41);
		frame.getContentPane().add(lblMaKhachHang);
		
		JLabel label = new JLabel("2. Ten Khach Hang");
		label.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label.setBounds(29, 81, 174, 41);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("3. Gioi Tinh");
		label_1.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_1.setBounds(29, 134, 174, 41);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("4. Nam Sinh");
		label_2.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_2.setBounds(29, 187, 174, 41);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("5. CMND");
		label_3.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_3.setBounds(29, 240, 174, 41);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("6. DT");
		label_4.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_4.setBounds(29, 293, 174, 41);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("7. Email");
		label_5.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_5.setBounds(29, 346, 174, 41);
		frame.getContentPane().add(label_5);
		
		JLabel label_7 = new JLabel("8. Dia Chi");
		label_7.setFont(new Font("Avenir Next", Font.BOLD, 18));
		label_7.setBounds(29, 399, 174, 41);
		frame.getContentPane().add(label_7);
		
	}
	
	void setUpTextFields() {
		maKhachHang_tf = new JTextField();
		maKhachHang_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		maKhachHang_tf.setBounds(215, 28, 317, 41);
		frame.getContentPane().add(maKhachHang_tf);
		maKhachHang_tf.setColumns(10);
		
		tenKhachHang_tf = new JTextField();
		tenKhachHang_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		tenKhachHang_tf.setColumns(10);
		tenKhachHang_tf.setBounds(215, 81, 317, 41);
		frame.getContentPane().add(tenKhachHang_tf);
		
		namSinh_tf = new JTextField();
		namSinh_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		namSinh_tf.setColumns(10);
		namSinh_tf.setBounds(215, 187, 317, 41);
		frame.getContentPane().add(namSinh_tf);
		
		CMND_tf = new JTextField();
		CMND_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		CMND_tf.setColumns(10);
		CMND_tf.setBounds(215, 240, 317, 41);
		frame.getContentPane().add(CMND_tf);
		
		DT_tf = new JTextField();
		DT_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		DT_tf.setColumns(10);
		DT_tf.setBounds(215, 293, 317, 41);
		frame.getContentPane().add(DT_tf);
		
		email_tf = new JTextField();
		email_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		email_tf.setColumns(10);
		email_tf.setBounds(215, 346, 317, 41);
		frame.getContentPane().add(email_tf);
		
		diaChi_tf = new JTextField();
		diaChi_tf.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		diaChi_tf.setColumns(10);
		diaChi_tf.setBounds(215, 399, 317, 41);
		frame.getContentPane().add(diaChi_tf);
		
	}
	
	void setUpComboBox() {
		DefaultComboBoxModel gioiTinhModel = new DefaultComboBoxModel();
		gioiTinhModel.addElement("Nam");
		gioiTinhModel.addElement("Nu");
		
		gioiTinh_comboBox = new JComboBox(gioiTinhModel);
		gioiTinh_comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		gioiTinh_comboBox.setBounds(215, 134, 317, 41);
		frame.getContentPane().add(gioiTinh_comboBox);
	}
	
	void setUpButtons() {
		JButton themBtn = new JButton("THEM", themIcon);
		themBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					themBtnPressed();
					showThongBaoThanhCong();
					giaoDienChinh.updateTable("KhachHang", giaoDienChinh.khachHangTable);
					clearTextFields();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showThongBaoThatBai();
				}
			}
		});
		themBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		themBtn.setBounds(312, 468, 220, 100);
		frame.getContentPane().add(themBtn);
		
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
	
	void themBtnPressed() throws Exception {
		String maKhachHang = maKhachHang_tf.getText();
		String tenKhachHang = tenKhachHang_tf.getText();
		String gioiTinh = gioiTinh_comboBox.getSelectedItem().toString();
		String namSinh = namSinh_tf.getText();
		String CMND = CMND_tf.getText();
		String DT = DT_tf.getText();
		String email = email_tf.getText();
		String diaChi = diaChi_tf.getText();
		
		KhachHang khachHangMoi = new KhachHang(maKhachHang, tenKhachHang, gioiTinh, Integer.parseInt(namSinh), CMND, DT, email, diaChi);
		khachHangDatabase.themKhachHang(khachHangMoi);
	}
	
	void huyBtnPressed() {
		frame.dispose();
	}
	
	void clearTextFields() {
		maKhachHang_tf.setText("");
		tenKhachHang_tf.setText("");
		namSinh_tf.setText("");
		CMND_tf.setText("");
		DT_tf.setText("");
		email_tf.setText("");
		diaChi_tf.setText("");
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
