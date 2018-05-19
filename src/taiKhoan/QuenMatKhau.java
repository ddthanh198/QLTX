package taiKhoan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dangNhap_dangKy.DangNhap;
import database.TaiKhoanDatabase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuenMatKhau extends JFrame {

	private JPanel contentPane;
	private JTextField TaiKhoan;
	private JTextField HoTen;
	private JTextField NamSinh;
	private JTextField SDT;


	/**
	 * Create the frame.
	 */
	public QuenMatKhau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 668, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhanMemQuan = new JLabel("Phan mem quan ly thue xe");
		lblPhanMemQuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhanMemQuan.setForeground(Color.WHITE);
		lblPhanMemQuan.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblPhanMemQuan.setBounds(135, 29, 383, 51);
		contentPane.add(lblPhanMemQuan);
		
		JLabel lblQuenMatKhau = new JLabel("Quen Mat Khau");
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMatKhau.setForeground(Color.WHITE);
		lblQuenMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblQuenMatKhau.setBounds(204, 91, 253, 51);
		contentPane.add(lblQuenMatKhau);
		
		JLabel lblTaiKhoan = new JLabel("Tai khoan");
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaiKhoan.setForeground(Color.WHITE);
		lblTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblTaiKhoan.setBackground(Color.WHITE);
		lblTaiKhoan.setBounds(10, 174, 111, 33);
		contentPane.add(lblTaiKhoan);
		
		TaiKhoan = new JTextField();
		TaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		TaiKhoan.setForeground(Color.BLACK);
		TaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		TaiKhoan.setColumns(10);
		TaiKhoan.setBounds(135, 174, 162, 32);
		contentPane.add(TaiKhoan);
		
		HoTen = new JTextField();
		HoTen.setHorizontalAlignment(SwingConstants.CENTER);
		HoTen.setForeground(Color.BLACK);
		HoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		HoTen.setColumns(10);
		HoTen.setBounds(135, 259, 162, 32);
		contentPane.add(HoTen);
		
		NamSinh = new JTextField();
		NamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		NamSinh.setForeground(Color.BLACK);
		NamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		NamSinh.setColumns(10);
		NamSinh.setBounds(484, 174, 162, 32);
		contentPane.add(NamSinh);
		
		JLabel lblHoTen = new JLabel("Ho Ten");
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setForeground(Color.WHITE);
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblHoTen.setBackground(Color.WHITE);
		lblHoTen.setBounds(10, 259, 111, 33);
		contentPane.add(lblHoTen);
		
		JLabel lblNamSinh = new JLabel("Nam Sinh");
		lblNamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamSinh.setForeground(Color.WHITE);
		lblNamSinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNamSinh.setBackground(Color.WHITE);
		lblNamSinh.setBounds(363, 174, 111, 33);
		contentPane.add(lblNamSinh);
		
		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblSdt.setBackground(Color.WHITE);
		lblSdt.setBounds(363, 259, 111, 33);
		contentPane.add(lblSdt);
		
		SDT = new JTextField();
		SDT.setHorizontalAlignment(SwingConstants.CENTER);
		SDT.setForeground(Color.BLACK);
		SDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		SDT.setColumns(10);
		SDT.setBounds(484, 259, 162, 32);
		contentPane.add(SDT);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TaiKhoan.getText().equals("")||HoTen.getText().equals("")||NamSinh.getText().equals("")||SDT.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin", "Loi", 0);
				else
				{
					try {
						String MatKhau = new TaiKhoanDatabase().quenMatKhau(TaiKhoan.getText(),HoTen.getText(),NamSinh.getText(),SDT.getText());
						if(MatKhau.equals("")) JOptionPane.showMessageDialog(null, "Thong tin khong chinh xac !!!\nVui long nhap lai !!!", "Loi", 0);
						else JOptionPane.showMessageDialog(null, "Mat Khau cua ban la \n"+MatKhau, "Thanh cong", 1);
					}catch(Exception e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Loi", "Loi", 0);
					}
				}
			}
		});
		btnOk.setToolTipText("Ckick de dang nhap");
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnOk.setBackground(new Color(0, 0, 128));
		btnOk.setBounds(156, 338, 111, 41);
		contentPane.add(btnOk);
		
		JButton btnXoa = new JButton("Xoa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TaiKhoan.setText("");
				HoTen.setText("");
				NamSinh.setText("");
				SDT.setText("");
			}
		});
		btnXoa.setToolTipText("click de huy");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setBackground(new Color(0, 0, 128));
		btnXoa.setBounds(386, 338, 111, 41);
		contentPane.add(btnXoa);
		
		JButton btnQuayLai = new JButton("Quay lai");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DangNhap dangnhap = new DangNhap();
				dangnhap.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnQuayLai.setToolTipText("Ckick de dang nhap");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnQuayLai.setBackground(new Color(0, 0, 128));
		btnQuayLai.setBounds(10, 401, 111, 41);
		contentPane.add(btnQuayLai);
		
		JButton btnDong = new JButton("Dong");
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		btnDong.setToolTipText("Ckick de dang nhap");
		btnDong.setForeground(Color.WHITE);
		btnDong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDong.setBackground(new Color(0, 0, 128));
		btnDong.setBounds(535, 401, 111, 41);
		contentPane.add(btnDong);
	}

}
