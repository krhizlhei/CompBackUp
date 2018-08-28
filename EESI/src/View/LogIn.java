package View;

import java.awt.BorderLayout;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;
import Controller.UserDA;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class LogIn extends JFrame {
	public Connection connection;
	private JPanel contentPane;
	private JTextField userTF;
	private JPasswordField passwordPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.POPUP);
		setTitle("EESI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		
		JLabel lblEesi = new JLabel("EESI");
		lblEesi.setHorizontalAlignment(SwingConstants.CENTER);
		lblEesi.setFont(new Font("Gill Sans MT", Font.BOLD, 35));
		lblEesi.setBounds(524, 135, 424, 77);
		contentPane.add(lblEesi);
		
		userTF = new JTextField();
		userTF.setBounds(666, 233, 174, 23);
		contentPane.add(userTF);
		userTF.setColumns(10);
		
		passwordPF = new JPasswordField();
		passwordPF.setBounds(666, 285, 174, 23);
		contentPane.add(passwordPF);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(560, 239, 73, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(564, 291, 69, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Log-in");
		btnLogin.setBounds(678, 335, 89, 23);
		contentPane.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				User user = new User();
				user.sUsername(userTF.getText());
				user.sPassword(passwordPF.getText());
				
				String lbl=lblUsername.getText();
				UserDA userDA = new UserDA(getConnection());
				User validUser= new User();
				
					validUser.sUsername(userDA.userlist().get(0).gUsername());
					validUser.sPassword(userDA.userlist().get(0).gPassword());
			
				if(validUser.gUsername().equals(user.gUsername())&&validUser.gPassword().equals(user.gPassword()))
				{
					MainMenu mm=new MainMenu(connection);
					mm.setVisible(true);
					setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Invalid User","Invalid User",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		});
		contentPane.getRootPane().setDefaultButton(btnLogin);
		
	}

	public Connection getConnection() 
	{
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection=DriverManager.getConnection("jdbc:db2://localhost:50000/EESI",
					"user","mylove24");
		}
		catch (ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		

		return connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
