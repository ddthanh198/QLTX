package chiTietThueXe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.ChiTietThueXeDatabase;
import database.XeDatabase;
import giaoDienChinh.GiaoDienChinh;
import thueXe.XemChiTietThueXe;

public class XoaChiTietThueXe {
	
	GiaoDienChinh giaoDienChinh;	
	XemChiTietThueXe xemChiTietThueXe;
	
	XeDatabase xeDatabase;
	ChiTietThueXeDatabase chiTietThueXeDatabase; 
	
	String maThueXeCanXoa;
	String maXeCanXoa;
	public JFrame frame;
	
	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	ImageIcon xoaIcon = new ImageIcon(iconFolderPath + "xoa.png");
	ImageIcon huyIcon = new ImageIcon(iconFolderPath + "huy.png");
	
	/**
	 * Create the application.
	 */
	public XoaChiTietThueXe(GiaoDienChinh giaoDienChinh, XemChiTietThueXe xemChiTietThueXe, String maThueXeCanXoa, String maXeCanXoa) {
		this.giaoDienChinh = giaoDienChinh;
		this.xemChiTietThueXe = xemChiTietThueXe;
		this.maThueXeCanXoa = maThueXeCanXoa;
		this.maXeCanXoa = maXeCanXoa;
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
		frame.setBounds(400, 200, 563, 281);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ban co chac chan muon XOA?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Avenir Next", Font.BOLD, 30));
		label.setBounds(6, 6, 551, 94);
		frame.getContentPane().add(label);
		
		JButton xoaBtn = new JButton("XOA", xoaIcon);
		xoaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xoaBtnPressed();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		xoaBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		xoaBtn.setBounds(313, 112, 220, 100);
		frame.getContentPane().add(xoaBtn);
		
		JButton huyBtn = new JButton("HUY", huyIcon);
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyBtnPressed();
			}
		});
		huyBtn.setFont(new Font("Avenir Next", Font.BOLD, 26));
		huyBtn.setBounds(30, 112, 220, 100);
		frame.getContentPane().add(huyBtn);
	
	}
	
	void xoaChiTietThueXe() throws Exception {
		chiTietThueXeDatabase.xoaChiTietThueXe(maThueXeCanXoa, maXeCanXoa);
	}
	
	void xoaBtnPressed() throws Exception {
		xoaChiTietThueXe();
		giaoDienChinh.updateTable("Xe", giaoDienChinh.xeTable);
		giaoDienChinh.updateThueXeTable();
		xemChiTietThueXe.updateXemChiTietThueXeView();
		frame.dispose();
	}
	
	void huyBtnPressed() {
		frame.dispose();
	}

}
