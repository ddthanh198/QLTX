package thongBao;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongBaoThanhCong {

	public JFrame frame;


	/**
	 * Create the application.
	 */
	public ThongBaoThanhCong() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 200, 377, 167);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("✓ THANH CONG!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GREEN);
		label.setFont(new Font("Avenir Next", Font.BOLD, 30));
		label.setBounds(6, 6, 365, 74);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setForeground(Color.GRAY);
		button.setFont(new Font("Avenir Next", Font.BOLD, 30));
		button.setBounds(52, 73, 273, 48);
		frame.getContentPane().add(button);
	}

}
