package thueXe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.Database;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ThongKeThueXe {

	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	public static JFrame frmThongKeThue;
	private static JTable tbl;
	static JComboBox cb = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeThueXe window = new ThongKeThueXe();
					window.frmThongKeThue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThongKeThueXe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThongKeThue = new JFrame();
		frmThongKeThue.setTitle("Thong ke thue xe - nhom 2");
		frmThongKeThue.setBounds(100, 100, 800, 321);
		frmThongKeThue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmThongKeThue.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 757, 134);
		frmThongKeThue.getContentPane().add(scrollPane);
		
		tbl = new JTable();
		scrollPane.setViewportView(tbl);
		
		
		cb.setModel(new DefaultComboBoxModel(new String[] {"", "Thong Ke Ngay Thue", "Thong Ke Ngay Hen Tra", "Thong Ke Theo Quy", "Thong Ke Theo Thang", "Thong Ke Theo Nam", "Thong Ke Khach Hang", "Thong Ke Nhan Vien", "Thong Ke Thanh Tien", "Thong Ke Tong tien Phat"}));
		cb.setBounds(387, 157, 350, 31);
		cb.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		frmThongKeThue.getContentPane().add(cb);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setIcon(new ImageIcon(iconFolderPath + "huy.png"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmThongKeThue.dispose();
			}
		});
		btnCancel.setFont(new Font("Avenir Next", Font.BOLD, 20));
		btnCancel.setBounds(57, 212, 200, 70);
		frmThongKeThue.getContentPane().add(btnCancel);
		
		JButton btnTimKiem = new JButton("THONG KE");
		btnTimKiem.setIcon(new ImageIcon(iconFolderPath + "thongKe.png"));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				txtien.setText("");
				if(cb.getSelectedItem()=="Thong Ke Ngay Thue")
					countNgayThue();
				if(cb.getSelectedItem()=="Thong Ke Ngay Hen Tra")
					countNgayHenTra();
				if(cb.getSelectedItem()=="Thong Ke Khach Hang")
					countKhachHang();
				if(cb.getSelectedItem()=="Thong Ke Nhan Vien")
					countNhanVien();
				if(cb.getSelectedItem()=="Thong Ke Thanh Tien")
					countThanhTien();
				if(cb.getSelectedItem()=="Thong Ke Tong tien Phat")
					countTongTienPhat();
				if(cb.getSelectedItem()=="Thong Ke Theo Quy")
					countQuy();
				if(cb.getSelectedItem()=="Thong Ke Theo Thang")
					countThang();
				if(cb.getSelectedItem()=="Thong Ke Theo Nam")
					countNam();
				
				
				
				
			}
		});
		btnTimKiem.setBounds(537, 212, 200, 70);
		btnTimKiem.setFont(new Font("Dialog", Font.BOLD, 18));

		frmThongKeThue.getContentPane().add(btnTimKiem);
		
		JButton btnXutFile = new JButton("Xuất file");
		btnXutFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatfile();
			}
		});
		btnXutFile.setIcon(new ImageIcon(iconFolderPath + "inPhieu.png"));
		btnXutFile.setFont(new Font("Avenir Next", Font.BOLD, 20));
		btnXutFile.setBounds(297, 212, 200, 70);
		frmThongKeThue.getContentPane().add(btnXutFile);
		
		txtien = new JTextField();
		txtien.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		txtien.setBounds(157, 157, 150, 31);
		frmThongKeThue.getContentPane().add(txtien);
		txtien.setColumns(10);
		
		JLabel lblTinThuV = new JLabel("Tổng tiền");
		lblTinThuV.setFont(new Font("Avenir Next", Font.BOLD, 18));
		lblTinThuV.setBounds(57, 157, 100, 31);
		frmThongKeThue.getContentPane().add(lblTinThuV);
	}
	
	 //Function
	
	static Database db = new Database();
	private static JTextField txtien;
	
	public static void countNgayThue() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select NgayThue,count(MaThueXe) `So luong don`, sum(TienCoc) TienCoc, sum(ThanhTien) ThanhTien, sum(TongTienPhat) TongTienPhat from thuexe group by NgayThue" ;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find makh error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
	
	public static void countQuy() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select quarter(NgayThue ) as Quý, year(NgayThue) as Năm, count(MaKhachHang) as `Số khách thuê`, sum(TienCoc) as `Tổng tiền cọc`, sum(TongTienPhat) as`Tổng tiền phạt` from thuexe group by quarter(NgayThue ), year(NgayThue)\n" + 
				""  ;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find kh error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}	
	
	
	public static void countThang() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select month(NgayThue ) as Tháng, year(NgayThue) as Năm, count(MaKhachHang) as `Số khách thuê`, sum(TienCoc) as `Tổng tiền cọc`, sum(TongTienPhat) as`Tổng tiền phạt` from thuexe group by month(NgayThue ), year(NgayThue)\n" + 
				""  ;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find kh error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}

	
	public static void countNam() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select year(NgayThue) as Năm, count(MaKhachHang) as `Số khách thuê`, sum(TienCoc) as `Tổng tiền cọc`, sum(TongTienPhat) as`Tổng tiền phạt` from thuexe group by year(NgayThue)"  ;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find kh error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
	
	public static void countNgayHenTra() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select NgayHenTra,count(MaThueXe) `So luong don`, sum(TienCoc) TienCoc, sum(ThanhTien) ThanhTien, sum(TongTienPhat) TongTienPhat from thuexe group by NgayHenTra"  ;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find kh error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
	
	
	
	public static void countKhachHang() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select thuexe.MaKhachHang MaKhachHang, khachhang.TenKhachHang TenKhachHang, count(thuexe.MaThueXe) `So luong don`, sum(thuexe.TienCoc) TienCoc, sum(thuexe.ThanhTien) ThanhTien, sum(thuexe.TongTienPhat) TongTienPhat from khachhang, thuexe where khachhang.MaKhachHang = thuexe.MaKhachHang group by thuexe.MaKhachHang";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find nv error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
	
	
	
	
	public static void countNhanVien() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select thuexe.MaNhanVien MaNhanVien, nhanvien.TenNhanVien TenNhanVien, count(thuexe.MaThueXe) `So luong don`, sum(thuexe.TienCoc) TienCoc, sum(thuexe.ThanhTien) ThanhTien, sum(thuexe.TongTienPhat) TongTienPhat from nhanvien, thuexe where nhanvien.MaNhanVien = thuexe.MaNhanVien group by thuexe.MaNhanVien";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find ngaythue error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		}
	
	public static void countThanhTien() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select ThanhTien, count(MaThueXe) `So luong don` from thuexe group by ThanhTien";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find ngaythue error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		int l=0;
		for(int h=0;h<tbl.getRowCount();h++)
		{
			String g = (String) tbl.getValueAt(h, 0);
			String gg = (String) tbl.getValueAt(h, 1);
			
			l=l+ Integer.parseInt(g)*Integer.parseInt(gg);
		}
		txtien.setText(String.valueOf(l));
		}
	
	public static void countTongTienPhat() {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select TongTienPhat, count(MaThueXe) `So luong don` from thuexe group by TongTienPhat";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find tinh trang error \n" + e.toString());
		}
		
		try {
			//load column name
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for(int j=0;j<colNumber;j++)
			{
				arr[j] = rsMD.getColumnName(j+1);
			}
			
			model.setColumnIdentifiers(arr);
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD,16));
			//load data from database to table
			while(rs.next()) {
				for(int j=0;j<colNumber;j++)
				{
					arr[j] = rs.getString(j+1);
				}
				model.addRow(arr);
			}		
		} catch (SQLException e) {
			
		}	
		tbl.setModel(model);
		tbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		int l=0;
		for(int h=0;h<tbl.getRowCount();h++)
		{
			String g = (String) tbl.getValueAt(h, 0);
			String gg = (String) tbl.getValueAt(h, 1);
			
			l=l+ Integer.parseInt(g)*Integer.parseInt(gg);
		}
		txtien.setText(String.valueOf(l));
		}
	
	
	
	
	
	
	
	
	public static void xuatfile() {
		JFileChooser fileChooser = new JFileChooser();
		//indicates whether the user still wants to export the settings
		boolean doExport = true;
		File destinationFile = null;
		String s;
		// let the user choose the destination file
		if (fileChooser.showSaveDialog(frmThongKeThue) == JFileChooser.APPROVE_OPTION) {
			
			
		
			// indicates whether to override an already existing file
			boolean overrideExistingFile = false;
		
			// get destination file
			destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			// check if file already exists
			while (doExport && destinationFile.exists() && !overrideExistingFile) {
				// let the user decide whether to override the existing file
				overrideExistingFile = (JOptionPane.showConfirmDialog(frmThongKeThue, "Replace file?", "Export Settings", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
				// let the user choose another file if the existing file shall not be overridden
				if (!overrideExistingFile) {
					if (fileChooser.showSaveDialog(frmThongKeThue) == JFileChooser.APPROVE_OPTION) 
						// get new destination file
						destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
					} else {
						// seems like the user does not want to export the settings any longer
						doExport = false;
					}
				}
		}
			// perform the actual export
			if(doExport) {
				s = ""+destinationFile+".pdf";
				
					taopdf(s);
				
			}
		}

	public static void taopdf(String a) {
		Document document = new Document();
		

		
		//font
		com.itextpdf.text.Font f = null;
		try {
			f = new com.itextpdf.text.Font(BaseFont.createFont("font/vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}    	
		f.setSize(12);
		f.setStyle(com.itextpdf.text.Font.NORMAL);
		
		com.itextpdf.text.Font f_B = null;
		try {
			f_B = new com.itextpdf.text.Font(BaseFont.createFont("font/vuArialBold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}    	
		f_B.setSize(12);
		f_B.setStyle(com.itextpdf.text.Font.BOLD);
		
		com.itextpdf.text.Font f_I = null;
		try {
			f_I = new com.itextpdf.text.Font(BaseFont.createFont("font/vuArialItalic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}    	
		f_I.setSize(12);
		f_I.setStyle(com.itextpdf.text.Font.BOLDITALIC);
		


		 Image image1 = null;
		 try {
			  image1 = Image.getInstance("iconImages/hust.png");
		} catch (BadElementException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 image1.setAbsolutePosition(36, 640);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(a));
			document.open();
			Paragraph pa1= new Paragraph("  TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI         Đề tài: Chương trình quản lý cho thuê xe",f_B) ;
			Paragraph pa2= new Paragraph("                                 ĐỒ ÁN I                                                    Nhóm 2: Bùi Hữu Quyết",f_B) ;
			Paragraph pa22= new Paragraph("                                                                                                                  Đoàn Văn Phú",f_B) ;
			Paragraph pa222= new Paragraph("                                                                                                                  Lý Bảo Long",f_B) ;
			
			Paragraph pa3= new Paragraph("		 				                                                                                 	----------------------------------",f) ;
			Paragraph pa4= new Paragraph(" ",f) ;
			Calendar cal = Calendar.getInstance();
			   String day = String.valueOf(cal.get(Calendar.DATE));
			   String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			   String year = String.valueOf(cal.get(Calendar.YEAR));
			
			Paragraph pa5= new Paragraph("                                                                                                             "+"Ngày "+ day+" Tháng "+month+" Năm "+year,f) ;
			Paragraph pa6= new Paragraph(" ",f) ;
			Paragraph pa7 = new Paragraph();
			Paragraph pa8 = new Paragraph(" ");
			Paragraph pa9 = new Paragraph();
			
			//table
			
			
			
			
			
			
			
				if(cb.getSelectedItem()=="Thong Ke Ngay Thue")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO NGÀY THUÊ ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Ngay Hen Tra")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO NGÀY HẸN TRẢ ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Khach Hang")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO KHÁCH HÀNG ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Nhan Vien")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO NHÂN VIÊN ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Thanh Tien")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE TEO THÀNH TIỀN ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Tong tien Phat")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO TỔNG TIỀN PHẠT ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Theo Quy")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO QUÝ ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Theo Thang")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO THÁNG ",f_B) ;
				if(cb.getSelectedItem()=="Thong Ke Theo Nam")
					pa7= new Paragraph("THỐNG KÊ THUÊ XE THEO NĂM ",f_B) ;
				
				
				
				
			
			pa7.setAlignment(Element.ALIGN_CENTER);
			Paragraph pa10 = new Paragraph("                         Người lập                                                   Xác nhận của quản lý",f);
			Paragraph pa11 = new Paragraph("                    (Ký và ghi rõ họ tên)                                           (Ký và ghi rõ họ tên)",f);
			Paragraph pa12 = new Paragraph();
			pa12= new Paragraph("Tổng tiền: "+txtien.getText(),f) ;
			//table
			
			 int col = tbl.getColumnCount();
			 int row = tbl.getRowCount();
				PdfPTable table = new PdfPTable(col);
			int h,g;
			
			
			
			
			// set độ rộng của table, mặc định là 80% 
			table.setWidthPercentage(100);
			// set độ rộng cho từng cột 
			

			
			
			//get col name
			for(h=0;h<col;h++)
			{
				table.addCell(new Phrase(tbl.getColumnName(h),f));
			}
			//get cell
			for(h=0;h<row;h++)
				for(g=0;g<col;g++)
				{	String ss = (String) tbl.getValueAt(h, g);
					table.addCell(new Phrase(ss,f));
				}
			
			//ADD TEXT
			document.add(pa1);		
			document.add(pa2);
			document.add(pa22);
			document.add(pa222);
			document.add(pa3);
			document.add(pa4);
			document.add(pa5);	
			document.add(pa6);	

			document.add(pa8);
			document.add(pa8);
			document.add(pa7);
			document.add(pa8);
			document.add(pa8);
			document.add(pa9);
			document.add(pa8);
			document.add(pa8);
			document.add(table);
			document.add(pa8);
			document.add(pa12);
			document.add(pa8);
			document.add(pa10);
			document.add(pa11);
			document.add(image1);
			document.close();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
