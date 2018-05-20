package taiKhoan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chinhSuaThongTin.ChinhSuaThongTin;
import dangNhap_dangKy.DangNhap;
import database.TaiKhoanDatabase;
import thongBao.ThongBaoThatBai;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class QuenMatKhau extends JFrame {
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon okIcon = new ImageIcon(iconFolderPath + "ok.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");

	private JPanel contentPane;
	private JTextField TaiKhoan;
	private JTextField HoTen;
	private JTextField NamSinh;
	private JTextField SDT;


	/**
	 * Create the frame.
	 */
	public QuenMatKhau() {
		setTitle("Quen Mat Khau");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 546, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaiKhoan = new JLabel("Tai khoan");
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaiKhoan.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblTaiKhoan.setBounds(30, 27, 180, 40);
		contentPane.add(lblTaiKhoan);
		
		TaiKhoan = new JTextField();
		TaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		TaiKhoan.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		TaiKhoan.setColumns(10);
		TaiKhoan.setBounds(210, 27, 300, 40);
		contentPane.add(TaiKhoan);
		
		HoTen = new JTextField();
		HoTen.setHorizontalAlignment(SwingConstants.CENTER);
		HoTen.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		HoTen.setColumns(10);
		HoTen.setBounds(210, 79, 300, 40);
		contentPane.add(HoTen);
		
		NamSinh = new JTextField();
		NamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		NamSinh.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		NamSinh.setColumns(10);
		NamSinh.setBounds(210, 131, 300, 40);
		contentPane.add(NamSinh);
		
		SDT = new JTextField();
		SDT.setHorizontalAlignment(SwingConstants.CENTER);
		SDT.setFont(new Font("Avenir Next", Font.PLAIN, 28));
		SDT.setColumns(10);
		SDT.setBounds(210, 183, 300, 40);
		contentPane.add(SDT);
		
		JLabel lblHoTen = new JLabel("Ho Ten");
		lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoTen.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblHoTen.setBounds(30, 79, 180, 40);
		contentPane.add(lblHoTen);
		
		JLabel lblNamSinh = new JLabel("Nam Sinh");
		lblNamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNamSinh.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblNamSinh.setBounds(30, 131, 180, 40);
		contentPane.add(lblNamSinh);
		
		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSdt.setFont(new Font("Avenir Next", Font.BOLD, 28));
		lblSdt.setBounds(30, 183, 180, 40);
		contentPane.add(lblSdt);
		
		
		JButton btnOk = new JButton("OK", okIcon);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(TaiKhoan.getText().equals("")&&HoTen.getText().equals("")&&NamSinh.getText().equals("")&&SDT.getText().equals("")))	
				{
					try {
						String MatKhau = new TaiKhoanDatabase().quenMatKhau(TaiKhoan.getText(),HoTen.getText(),NamSinh.getText(),SDT.getText());
						if(MatKhau.equals("")) JOptionPane.showMessageDialog(null, "Thong tin khong chinh xac !!!\nVui long nhap lai !!!", "Loi", 0);
						else {
							JOptionPane.showMessageDialog(null, "Mat Khau cua ban la \n"+MatKhau, "Thanh cong", 1);
							DangNhap dangnhap = new DangNhap();
							dangnhap.frame.setVisible(true);
							dispose();
						}
					}
					catch(Exception e) {
						ThongBaoThatBai window = new ThongBaoThatBai();
						window.frame.setVisible(true);
					}
				} else {
					ThongBaoThatBai window = new ThongBaoThatBai();
					window.frame.setVisible(true);
				}
			}
		});
		btnOk.setFont(new Font("Dialog", Font.BOLD, 28));
		btnOk.setBounds(67, 247, 180, 78);
		contentPane.add(btnOk);
		
		JButton btnQuayLai = new JButton("HUY", huyIcon);
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DangNhap dangnhap = new DangNhap();
				dangnhap.frame.setVisible(true);
				dispose();
			}
		});
		btnQuayLai.setFont(new Font("Dialog", Font.BOLD, 28));
		btnQuayLai.setBounds(303, 247, 180, 78);
		contentPane.add(btnQuayLai);
	}

}
