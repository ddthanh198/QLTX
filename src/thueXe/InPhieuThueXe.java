package thueXe;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import database.Database;
import database.ThueXeDatabase;
import giaoDienChinh.GiaoDienChinh;

public class InPhieuThueXe {

	public static String MaThueXe;
	static String MaKhachHang;
	static String MaNhanVien;
	static String NgayThue;
	static String TienCoc;
	static String TenKhachHang;
	static String TenNhanVien;
	static String NgayHenTra;
	static String TongTienPhat;
	static String TienNhanLai;
	static String ThanhTien;
	
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	

	/**
	 * Initialize the contents of the frame.
	 */
	static Database db = new Database();
	
	public static void MaKhachHang() {
		
		String sqlCommand = "select MaKhachHang from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find dia chi error \n" + e.toString());
		}
		
		
			//load data from database to table
			try {
				while(rs.next()) {
					MaKhachHang = rs.getString(1);				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
		
		}
	
public static void MaNhanVien() {
		
		String sqlCommand = "select MaNhanVien from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
		ResultSet rs = null;
		
		Statement st;
		try {
			st = db.connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("find dia chi error \n" + e.toString());
		}
		
		
			//load data from database to table
			try {
				while(rs.next()) {
					MaNhanVien = rs.getString(1);				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
		
		}
	
public static void NgayThue() {
	
	String sqlCommand = "select NgayThue from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				NgayThue = String.valueOf(rs.getDate(1));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


public static void TienCoc() {
	
	String sqlCommand = "select TienCoc from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				TienCoc = rs.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


public static void TenKhachHang() {
	
	String sqlCommand = "select TenKhachHang from khachhang where MaKhachHang = " +"'"+MaKhachHang+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				TenKhachHang = rs.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


public static void TenNhanVien() {
	
	String sqlCommand = "select TenNhanVien from nhanvien where MaNhanVien = " +"'"+MaNhanVien+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				TenNhanVien = rs.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


public static void NgayHenTra() {
	
	String sqlCommand = "select NgayHenTra from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				NgayHenTra = String.valueOf(rs.getDate(1));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


	public static void TongTienPhat() {
	
	String sqlCommand = "select TongTienPhat from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				TongTienPhat = rs.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}


	public static void ThanhTien() {
	
	String sqlCommand = "select ThanhTien from thuexe where MaThueXe = " +"'"+MaThueXe+"'";
	ResultSet rs = null;
	
	Statement st;
	try {
		st = db.connection.createStatement();
		rs = st.executeQuery(sqlCommand);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("find dia chi error \n" + e.toString());
	}
	
	
		//load data from database to table
		try {
			while(rs.next()) {
				ThanhTien = rs.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	
	}





	
	
	public static void xuatfile() {
		// declare JFileChooser
		JFileChooser fileChooser = new JFileChooser();
		//indicates whether the user still wants to export the settings
		boolean doExport = true;
		File destinationFile = null;
		String s;
		// let the user choose the destination file
		if (fileChooser.showSaveDialog(GiaoDienChinh.frame) == JFileChooser.APPROVE_OPTION) {
			
			
		
			// indicates whether to override an already existing file
			boolean overrideExistingFile = false;
		
			// get destination file
			destinationFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			// check if file already exists
			while (doExport && destinationFile.exists() && !overrideExistingFile) {
				// let the user decide whether to override the existing file
				overrideExistingFile = (JOptionPane.showConfirmDialog(GiaoDienChinh.frame, "Replace file?", "Export Settings", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
				// let the user choose another file if the existing file shall not be overridden
				if (!overrideExistingFile) {
					if (fileChooser.showSaveDialog(GiaoDienChinh.frame) == JFileChooser.APPROVE_OPTION) 
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
		
		MaKhachHang();
		MaNhanVien();
		NgayThue();
		TienCoc();
		TenKhachHang();
		TenNhanVien();
		NgayHenTra();
		TongTienPhat();
		ThanhTien();
		
		try {
			if(ThueXeDatabase.daTraHetXe(MaThueXe))
			{
				int i = Integer.parseInt(TienCoc) - Integer.parseInt(TongTienPhat);
				TienNhanLai = String.valueOf(i);
			}
			else
			{
				TienNhanLai = "Chưa xác định";
			}
		} catch (NumberFormatException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
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
		f_B.setStyle(Font.BOLD);
		
		

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
			
			Paragraph pa5= new Paragraph("                                                                                         "+"Ngày "+ day+" Tháng "+month+" Năm "+year,f) ;
			Paragraph pa6= new Paragraph(" ",f) ;
			Paragraph pa7 = new Paragraph("PHIẾU THÔNG TIN THUÊ XE",f_B);
			pa7.setAlignment(Element.ALIGN_CENTER);
			Paragraph pa8 = new Paragraph(" ");
			
			PdfPTable line = new PdfPTable(2);
			
			PdfPCell cell1 = new PdfPCell(new Phrase("Mã thuê xe: "+ MaThueXe,f));
			PdfPCell cell2 = new PdfPCell(new Phrase("Tiền cọc: "+ TienCoc,f));
			PdfPCell cell3 = new PdfPCell(new Phrase("Mã khách hàng: "+ MaKhachHang,f));
			PdfPCell cell4 = new PdfPCell(new Phrase("Tên khách hàng: "+ TenKhachHang,f));
			PdfPCell cell5 = new PdfPCell(new Phrase("Mã nhân viên: "+  MaNhanVien,f));
			PdfPCell cell6 = new PdfPCell(new Phrase("Tên nhân viên: "+ TenNhanVien,f));
			PdfPCell cell7 = new PdfPCell(new Phrase("Ngày thuê: "+ NgayThue,f));
			PdfPCell cell8 = new PdfPCell(new Phrase("Ngày hẹn trả: "+  NgayHenTra,f));
			PdfPCell cell9 = new PdfPCell(new Phrase("Tổng tiền phạt: "+ TongTienPhat,f));
			PdfPCell cell10 = new PdfPCell(new Phrase("Tiền nhận lại: "+  TienNhanLai,f));
			PdfPCell cell11 = new PdfPCell(new Phrase("Thành tiền: "+  ThanhTien,f));
			PdfPCell cell12 = new PdfPCell(new Phrase(""));

			cell1.setBorder(Rectangle.NO_BORDER);
			cell2.setBorder(Rectangle.NO_BORDER);
			cell3.setBorder(Rectangle.NO_BORDER);
			cell4.setBorder(Rectangle.NO_BORDER);
			cell5.setBorder(Rectangle.NO_BORDER);
			cell6.setBorder(Rectangle.NO_BORDER);
			cell7.setBorder(Rectangle.NO_BORDER);
			cell8.setBorder(Rectangle.NO_BORDER);
			cell9.setBorder(Rectangle.NO_BORDER);
			cell10.setBorder(Rectangle.NO_BORDER);
			cell11.setBorder(Rectangle.NO_BORDER);
			cell12.setBorder(Rectangle.NO_BORDER);
			
			line.addCell(cell1);
			line.addCell(cell2);
			line.addCell(cell3);
			line.addCell(cell4);
			line.addCell(cell5);
			line.addCell(cell6);
			line.addCell(cell7);
			line.addCell(cell8);
			line.addCell(cell9);
			line.addCell(cell10);
			line.addCell(cell11);
			line.addCell(cell12);
			
			
			Paragraph pa10 = new Paragraph("                         Người lập                                                   Xác nhận của quản lý",f);
			Paragraph pa11 = new Paragraph("                    (Ký và ghi rõ họ tên)                                           (Ký và ghi rõ họ tên)",f);
			
			//table
			
			String sqlCommand = "select xe.TenXe `Tên xe`, chitietthuexe.NgayTra `Ngày trả`, chitietthuexe.TienThue `Tiền thuê`, chitietthuexe.TienPhat `Tiền phạt`, chitietthuexe.GhiChu `Ghi chú` from chitietthuexe, xe where chitietthuexe.MaXe = xe.MaXe and MaThueXe = " +"'"+MaThueXe+"'";
			ResultSet rs = null;
			ResultSetMetaData rsMD = null;
			Statement st;
			int colNumber = 0;
			
			//SQL
			try {
				st = db.connection.createStatement();
				rs = st.executeQuery(sqlCommand);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error \n" + e.toString());
			}
			
			//load column number
			try {
				
				rsMD = rs.getMetaData();
				colNumber = rsMD.getColumnCount();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//create table
			PdfPTable tablep = new PdfPTable(colNumber);
			
			//load column name		
			
			tablep.addCell(new Phrase("Tên xe",f_B));
			tablep.addCell(new Phrase("Ngày trả",f_B));
			tablep.addCell(new Phrase("Tiền thuê",f_B));
			tablep.addCell(new Phrase("Tiền phạt",f_B));
			tablep.addCell(new Phrase("Ghi chú",f_B));
			//load data from database to table
			try {
				while(rs.next()) {
					for(int j=0;j<colNumber;j++)
					{
						tablep.addCell(new Phrase(rs.getString(j+1),f));
					}					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			
			
			
			
			
			
			
			
			
			// set độ rộng của table, mặc định là 80% 
			tablep.setWidthPercentage(100);
			// set độ rộng cho từng cột 
			
			
			//ADD TEXT
			document.add(pa1);		
			document.add(pa2);

			document.add(pa22);
			document.add(pa222);
			document.add(pa3);
			document.add(pa4);
				
			document.add(pa6);	
			document.add(pa7);
			document.add(pa8);
			document.add(pa8);			
			document.add(pa8);
			document.add(pa8);			
			document.add(line);		
			document.add(pa8);
			document.add(tablep);
			document.add(pa8);
			document.add(pa5);
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
