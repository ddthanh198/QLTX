package dangNhap_dangKy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import chinhSuaThongTin.ChinhSuaThongTin;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class DangKy {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKy window = new DangKy();
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
	public DangKy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 150, 668, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDangKi = new JLabel("Dang ki");
		lblDangKi.setForeground(Color.BLUE);
		lblDangKi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangKi.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblDangKi.setBounds(263, 125, 144, 55);
		frame.getContentPane().add(lblDangKi);
		
		JLabel lblTenDangNhap = new JLabel("Ten dang nhap");
		lblTenDangNhap.setForeground(Color.BLUE);
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenDangNhap.setBounds(58, 191, 108, 24);
		frame.getContentPane().add(lblTenDangNhap);
		
		JLabel lblMatKhau = new JLabel("Mat khau");
		lblMatKhau.setForeground(Color.BLUE);
		lblMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMatKhau.setBounds(367, 191, 86, 24);
		frame.getContentPane().add(lblMatKhau);
		
		JLabel lblHoTen = new JLabel("Ho ten");
		lblHoTen.setForeground(Color.BLUE);
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setBounds(58, 249, 92, 24);
		frame.getContentPane().add(lblHoTen);
		
		JLabel lblNamSinh = new JLabel("Nam sinh");
		lblNamSinh.setForeground(Color.BLUE);
		lblNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamSinh.setBounds(377, 249, 76, 24);
		frame.getContentPane().add(lblNamSinh);
		
		JLabel lblSoDienThoai = new JLabel("So dien thoai");
		lblSoDienThoai.setForeground(Color.BLUE);
		lblSoDienThoai.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoDienThoai.setBounds(58, 306, 108, 24);
		frame.getContentPane().add(lblSoDienThoai);
		
		JLabel lblChucVu = new JLabel("Chuc vu");
		lblChucVu.setForeground(Color.BLUE);
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblChucVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucVu.setBounds(377, 308, 76, 21);
		frame.getContentPane().add(lblChucVu);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField.setBounds(176, 191, 120, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_1.setBounds(176, 251, 120, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_2.setBounds(176, 308, 120, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setBounds(463, 190, 120, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_4.setBounds(463, 251, 119, 24);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_5.setBounds(463, 308, 120, 24);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnOk.setBounds(135, 373, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnHuy = new JButton("Huy");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setBounds(432, 373, 89, 23);
		frame.getContentPane().add(btnHuy);
		
		JLabel lblNewLabel = new JLabel("");
		String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
		lblNewLabel.setIcon(new ImageIcon(iconFolderPath+"dang_ki.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 656, 481);
		frame.getContentPane().add(lblNewLabel);
	}
}
