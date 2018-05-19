package thongBao;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangNhapThatBai {

	public JFrame frame;


	/**
	 * Create the application.
	 */
	public DangNhapThatBai() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 200, 546, 167);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("✘ Tai khoan hoac mat khau khong dung!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Avenir Next", Font.BOLD, 26));
		label.setBounds(6, 6, 534, 74);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setForeground(Color.GRAY);
		button.setFont(new Font("Avenir Next", Font.BOLD, 30));
		button.setBounds(133, 80, 273, 48);
		frame.getContentPane().add(button);
	}

}
