package xe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
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
import khachHang.TimKiemKhachHang;


public class TimKiemXe {

	String iconFolderPath = ChinhSuaThongTin.iconFolderPath;
	public static JFrame frmTimKiemXe;
	private static JTable tbl;
	private static JTextField tx;
	static JComboBox cb = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemXe window = new TimKiemXe();
					window.frmTimKiemXe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimKiemXe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTimKiemXe = new JFrame();
		frmTimKiemXe.setTitle("Tim kiem xe - Nhom 2");
		frmTimKiemXe.setBounds(100, 100, 999, 340);
		frmTimKiemXe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTimKiemXe.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 957, 152);
		frmTimKiemXe.getContentPane().add(scrollPane);
		
		tbl = new JTable();
		scrollPane.setViewportView(tbl);
		cb.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		
		
		cb.setModel(new DefaultComboBoxModel(new String[] {"", "Ma Xe", "Ten Xe", "Loai Xe", "Nam San Xuat", "Ngay Nhap", "Tinh Trang Xe", "Don Gia Thue", "Trang Thai"}));
		cb.setBounds(142, 181, 350, 31);
		frmTimKiemXe.getContentPane().add(cb);
		
		tx = new JTextField();
		tx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb.getSelectedItem() == "Ma Xe")
				{
					findMaXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Ten Xe")
				{
					findTenXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Loai Xe")
				{
					findLoaiXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Nam San Xuat")
				{
					findNamSanXuat(Integer.parseInt(tx.getText()));
				}
				if(cb.getSelectedItem() == "Ngay Nhap")
				{
					Date date = Date.valueOf(tx.getText());					
					findNgayNhap(date);
				}
				if(cb.getSelectedItem() == "Tinh Trang Xe")
				{
					findTinhTrang(tx.getText());
				}
				if(cb.getSelectedItem() == "Don Gia Thue")
				{
					findDonGia(tx.getText());
				}
				if(cb.getSelectedItem() == "Trang Thai")
				{
					findTrangThai(tx.getText());
				}
			}
		});
		tx.setBounds(516, 175, 350, 41);
		tx.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		frmTimKiemXe.getContentPane().add(tx);
		tx.setColumns(10);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setIcon(new ImageIcon(iconFolderPath + "huy.png"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTimKiemXe.dispose();
			}
		});
		btnCancel.setFont(new Font("Avenir Next", Font.BOLD, 26));
		btnCancel.setBounds(82, 228, 240, 70);
		frmTimKiemXe.getContentPane().add(btnCancel);
		
		JButton btnTimKiem = new JButton("TIM KIEM");
		btnTimKiem.setIcon(new ImageIcon(iconFolderPath + "timKiem.png"));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(cb.getSelectedItem() == "Ma Xe")
				{
					findMaXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Ten Xe")
				{
					findTenXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Loai Xe")
				{
					findLoaiXe(tx.getText());
				}
				if(cb.getSelectedItem() == "Nam San Xuat")
				{
					findNamSanXuat(Integer.parseInt(tx.getText()));
				}
				if(cb.getSelectedItem() == "Ngay Nhap")
				{
					Date date = Date.valueOf(tx.getText());					
					findNgayNhap(date);
				}
				if(cb.getSelectedItem() == "Tinh Trang Xe")
				{
					findTinhTrang(tx.getText());
				}
				if(cb.getSelectedItem() == "Don Gia Thue")
				{
					findDonGia(tx.getText());
				}
				if(cb.getSelectedItem() == "Trang Thai")
				{
					findTrangThai(tx.getText());
				}
			}
		});
		btnTimKiem.setBounds(682, 228, 240, 70);
		btnTimKiem.setFont(new Font("Avenir Next", Font.BOLD, 26));
		frmTimKiemXe.getContentPane().add(btnTimKiem);
		
		JButton btnXutFile = new JButton("Xuất file");
		btnXutFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatfile();
			}
		});
		btnXutFile.setIcon(new ImageIcon(iconFolderPath + "xuatFile.png"));
		btnXutFile.setFont(new Font("Avenir Next", Font.BOLD, 26));
		btnXutFile.setBounds(382, 228, 240, 70);
		frmTimKiemXe.getContentPane().add(btnXutFile);
	}
	
	 //Function
	
	static Database db = new Database();
	
	public static void findMaXe(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where MaXe like '%"+s+"%'";
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findTenXe(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe where TenXe like '%"+s+"%'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find ten error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findLoaiXe(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe where LoaiXe like '%"+s+"%'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find loai error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findNamSanXuat(int i) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where NamSanXuat =" +i;
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find namsx error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	
	public static void findNgayNhap(Date s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where NgayNhap like '%"+s+"%'";
		ResultSet rs = null;
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find ngay error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findTinhTrang(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where TinhTrangXe like '%"+s+"%'";
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findDonGia(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where DonGiaThue like '%"+s+"%'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find don gia error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	public static void findTrangThai(String s) {
		DefaultTableModel model = new DefaultTableModel();
		String sqlCommand = "select * from xe  where TrangThai like '%"+s+"%'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find dia chi error \n" + e.toString());
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
			tbl.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 16));
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
	
	
	

	public static void xuatfile() {
		JFileChooser fileChooser = new JFileChooser();
		//indicates whether the user still wants to export the settings
		boolean doExport = true;
		File destinationFile = null;
		String s;
		// let the user choose the destination file
		if (fileChooser.showSaveDialog(frmTimKiemXe) == JFileChooser.APPROVE_OPTION) {
			
			
		
			// indicates whether to override an already existing file
			boolean overrideExistingFile = false;
		
			// get destination file
			destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			// check if file already exists
			while (doExport && destinationFile.exists() && !overrideExistingFile) {
				// let the user decide whether to override the existing file
				overrideExistingFile = (JOptionPane.showConfirmDialog(frmTimKiemXe, "Replace file?", "Export Settings", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
				// let the user choose another file if the existing file shall not be overridden
				if (!overrideExistingFile) {
					if (fileChooser.showSaveDialog(frmTimKiemXe) == JFileChooser.APPROVE_OPTION) 
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
			Paragraph pa2= new Paragraph("                                 ĐỒ ÁN I                                                    Nhóm 2",f_B) ;
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
			
			if(cb.getSelectedItem()=="Ma Xe") 
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO MÃ XE ",f_B) ;
				pa9= new Paragraph("MÃ XE: "+tx.getText(),f) ;
			}
				
			if(cb.getSelectedItem()=="Ten Xe")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO TÊN XE ",f_B) ;
				pa9= new Paragraph("TÊN XE: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Loai Xe")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO LOẠI XE ",f_B) ;
				pa9= new Paragraph("LOẠI XE: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Nam San Xuat")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO NĂM SẢN XUẤT ",f_B) ;
				pa9= new Paragraph("NĂM: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Ngay Nhap")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO NGÀY NHẬP ",f_B) ;
				pa9= new Paragraph("NGÀY NHẬP: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Tinh Trang Xe")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO TÌNH TRẠNG XE ",f_B) ;
				pa9= new Paragraph("TÌNH TRẠNG XE: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Don Gia Thue")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO ĐƠN GIÁ THUÊ ",f_B) ;
				pa9= new Paragraph("ĐƠN GIÁ THUÊ: "+tx.getText(),f) ;
			}
			if(cb.getSelectedItem()=="Trang Thai")
			{
				pa7= new Paragraph("TÌM KIẾM THÔNG TIN XE THEO TRẠNG THÁI XE ",f_B) ;
				pa9= new Paragraph("TRẠNG THÁI: "+tx.getText(),f) ;
			}
				
				
				
				
			
			pa7.setAlignment(Element.ALIGN_CENTER);
			Paragraph pa10 = new Paragraph("                         Người lập                                                   Xác nhận của quản lý",f);
			Paragraph pa11 = new Paragraph("                    (Ký và ghi rõ họ tên)                                           (Ký và ghi rõ họ tên)",f);
			
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
			document.add(pa3);
			document.add(pa4);
			document.add(pa5);	
			document.add(pa6);	
			document.add(pa8);
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
