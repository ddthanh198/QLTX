package dangNhap_dangKy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.TaiKhoanDatabase;
import giaoDienChinh.GiaoDienChinh;
import taiKhoan.QuenMatKhau;
import taiKhoan.TaiKhoan;
import thongBao.DangNhapThatBai;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Icon;

public class DangNhap {

	TaiKhoanDatabase taiKhoanDatabase;
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon bachKhoaLogoIcon = new ImageIcon(iconFolderPath + "bachKhoaLogo.png");
	ImageIcon dangNhapIcon = new ImageIcon(iconFolderPath + "dangNhap.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");
	
	public JFrame frame;
	private JTextField tenTaiKhoan_tf;
	private JPasswordField matKhau_pf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap window = new DangNhap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DangNhap() {
		taiKhoanDatabase = new TaiKhoanDatabase();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 150, 756, 489);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setUpLabels();
		setUpTextFields();
		setUpButtons();
	}
	
	void setUpLabels() {
		
		JLabel lblTaiKhoan = new JLabel("Tai khoan");
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaiKhoan.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblTaiKhoan.setBounds(308, 186, 150, 40);
		frame.getContentPane().add(lblTaiKhoan);
		
		JLabel lblMatKhau = new JLabel("Mat khau");
		lblMatKhau.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhau.setBounds(308, 254, 150, 40);
		frame.getContentPane().add(lblMatKhau);
	}
	
	void setUpTextFields() {
		tenTaiKhoan_tf = new JTextField();
		tenTaiKhoan_tf.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		tenTaiKhoan_tf.setHorizontalAlignment(SwingConstants.CENTER);
		tenTaiKhoan_tf.setBounds(458, 186, 250, 40);
		frame.getContentPane().add(tenTaiKhoan_tf);
		tenTaiKhoan_tf.setColumns(10);
		
		matKhau_pf = new JPasswordField();
		matKhau_pf.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		matKhau_pf.setHorizontalAlignment(SwingConstants.CENTER);
		matKhau_pf.setBounds(458, 254, 250, 40);
		frame.getContentPane().add(matKhau_pf);
	}
	
	void setUpButtons() {
		JButton btnDangNhap = new JButton("DANG NHAP", dangNhapIcon);
		btnDangNhap.setText("<html><center>DANG<br/>NHAP</></html>");
		btnDangNhap.setToolTipText("Click de dang nhap");
		btnDangNhap.setBackground(new Color(0, 0, 128));
		btnDangNhap.setFont(new Font("Avenir Next", Font.BOLD, 28));
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhapBtnPressed();
			}
		});
		btnDangNhap.setBounds(308, 328, 180, 110);
		frame.getContentPane().add(btnDangNhap);
		
		JButton btnHuy = new JButton("Huy", huyIcon);
		btnHuy.setToolTipText("Click de huy");
		btnHuy.setFont(new Font("Avenir Next", Font.BOLD, 28));
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		btnHuy.setBounds(528, 328, 180, 110);
		frame.getContentPane().add(btnHuy);
		
		JLabel lblPhanMemQuan = new JLabel("PHAN MEM QUAN LY THUE XE");
		lblPhanMemQuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhanMemQuan.setFont(new Font("Avenir Next", Font.BOLD, 40));
		lblPhanMemQuan.setBounds(16, 9, 722, 90);
		frame.getContentPane().add(lblPhanMemQuan);
		
		JLabel lblNewLabel = new JLabel("Quen mat khau ?");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				QuenMatKhau window = new QuenMatKhau();
				window.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		lblNewLabel.setBounds(362, 537, 129, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel(bachKhoaLogoIcon);
		label.setBounds(16, 111, 250, 327);
		frame.getContentPane().add(label);
		
		JLabel lblNhom = new JLabel("- NHOM 2 -");
		lblNhom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhom.setFont(new Font("Avenir Next", Font.BOLD, 32));
		lblNhom.setBounds(308, 111, 397, 40);
		frame.getContentPane().add(lblNhom);
		
	}
	
	void dangNhapBtnPressed() {
		try {
			dangNhap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void dangNhap() throws Exception {
		String tenTaiKhoanNhapVao = tenTaiKhoan_tf.getText();
		String matKhauNhapVao = matKhau_pf.getText();
		if(tenTaiKhoanNhapVao.isEmpty() || matKhauNhapVao.isEmpty() || !matKhauNhapVao.equals(taiKhoanDatabase.getMatKhau(tenTaiKhoanNhapVao))) {
			DangNhapThatBai window = new DangNhapThatBai();
			window.frame.setVisible(true);
		} else {
			TaiKhoan taiKhoan = taiKhoanDatabase.getTaiKhoan(tenTaiKhoanNhapVao);
			GiaoDienChinh window = new GiaoDienChinh(taiKhoan);
			window.frame.setVisible(true);
			window.thongtintaikhoan(taiKhoan);
			frame.dispose();
		}
	}
	
	void huyBtnPressed() {
		frame.dispose();
	}
}
