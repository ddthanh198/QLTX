package khachHang;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import giaoDienChinh.GiaoDienChinh;
import readExcel.read;
import thongBao.ThongBaoThanhCong;
import thongBao.ThongBaoThatBai;

public class NhapFileKhachHang {

	GiaoDienChinh giaoDienChinh;
	public NhapFileKhachHang(GiaoDienChinh giaoDienChinh) {
		this.giaoDienChinh = giaoDienChinh;
	}
	public void nhapFile()
	{
		JFileChooser chooser = new JFileChooser();
	       chooser.setDialogTitle("Open File");
	           int value = chooser.showOpenDialog(chooser);
	           if(value == JFileChooser.OPEN_DIALOG)
	           {
	               File file = chooser.getSelectedFile();
	               String s =""+file;
	               int click = JOptionPane.showConfirmDialog(chooser,"Ban co muon nhap file  "+file+ " nay khong?");
	               if(click==JOptionPane.YES_OPTION)
	               {
	                   try
	                   {
	                       boolean giatri = new read().ReadDataFromExcelFile(s,"KhachHang");
	                       if(giatri==true)
	                       {
	                       	ThongBaoThanhCong thongBaoThanhCong = new ThongBaoThanhCong();
	                   		thongBaoThanhCong.frame.setVisible(true);
	                   		giaoDienChinh.updateTable("KhachHang", giaoDienChinh.khachHangTable);
	                       }
	                       else 
	                       {
	                       	ThongBaoThatBai thongBaoThatBai = new ThongBaoThatBai();
	                   		thongBaoThatBai.frame.setVisible(true);
	                       }
	                   }
	                   catch (Exception e1)
	                   {
	                       e1.printStackTrace();
	                   }
	               }
	           }
	}

}
