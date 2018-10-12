package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ProfilePage extends JFrame {

	private JPanel contentPane;

	///**
	 //* Launch the application.
	 //*/
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				ProfilePage frame = new ProfilePage();
	///				frame.setVisible(true);
	///			} catch (Exception e) {
	//				e.printStackTrace();
		//		}
		//	}
	//	});
	//}

//	/**
//	 * Create the frame.
//	 * @param details 
//	 */
	public ProfilePage(String[] details) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBackground(Color.CYAN);
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(10, 11, 100, 100);
		contentPane.add(lblPhoto);
		
		JLabel lblNewLabel = new JLabel(details[10]);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 25, 360, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(130, 98, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblProfile = new JLabel("Profile :");
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setBounds(10, 152, 102, 24);
		contentPane.add(lblProfile);
		
		JLabel lblIdlabel = new JLabel(details[0]);
		lblIdlabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdlabel.setBounds(187, 98, 114, 14);
		contentPane.add(lblIdlabel);
		

		JLabel TAProf = new JLabel();
		TAProf.setBounds(129, 152, 480, 20);
		contentPane.add(TAProf);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(10, 227, 102, 24);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(10, 259, 102, 24);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(10, 192, 102, 24);
		contentPane.add(lblPhone);
		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setBounds(10, 284, 102, 24);
		contentPane.add(lblAge);
		
		JLabel lblBalance = new JLabel("Balance :");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setBounds(10, 318, 102, 24);
		contentPane.add(lblBalance);
		
		JLabel lblDateRegistered = new JLabel("Date Registered :");
		lblDateRegistered.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateRegistered.setBounds(10, 343, 102, 24);
		contentPane.add(lblDateRegistered);
		
		JLabel phoneno = new JLabel(details[6]);
		phoneno.setHorizontalAlignment(SwingConstants.CENTER);
		phoneno.setBounds(129, 197, 480, 24);
		contentPane.add(phoneno);
		
		JLabel emailadd = new JLabel(details[5]);
		emailadd.setHorizontalAlignment(SwingConstants.CENTER);
		emailadd.setBounds(128, 227, 480, 24);
		contentPane.add(emailadd);
		
		JLabel homeAddress = new JLabel(details[2]);
		homeAddress.setHorizontalAlignment(SwingConstants.CENTER);
		homeAddress.setBounds(128, 259, 480, 24);
		contentPane.add(homeAddress);
		
		JLabel lblEdad = new JLabel(details[1]);
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setBounds(129, 284, 480, 24);
		contentPane.add(lblEdad);
		
		JLabel lblBalan = new JLabel(details[9]);
		lblBalan.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalan.setBounds(129, 318, 480, 24);
		contentPane.add(lblBalan);
		
		JLabel lblDr = new JLabel(details[7]);
		lblDr.setHorizontalAlignment(SwingConstants.CENTER);
		lblDr.setBounds(129, 343, 480, 24);
		contentPane.add(lblDr);
	}
}
