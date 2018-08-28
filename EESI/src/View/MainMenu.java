package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.sql.Connection;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
	public Connection connection;
	public JPanel contentPane;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainMenu frame = new MainMenu(connection);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	//
	// /**
	// * Create the frame.
	// */
	public MainMenu(Connection connection) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 1437);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

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
		
		EmployeeUI employ = new EmployeeUI(connection);
		AccountUI acc = new AccountUI(connection);
		ListOfItemsUI loi = new ListOfItemsUI(connection);
		RegisterUI rui = new RegisterUI(connection);

		contentPane = new JPanel();
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel windowPanel = new JPanel();
		windowPanel.setBackground(Color.WHITE);
		windowPanel.setBorder(new LineBorder(Color.WHITE, 3, true));
		windowPanel.setBounds(422, 88, 936, 598);
		contentPane.add(windowPanel);
		windowPanel.setLayout(null);

		JLabel lblEesi = new JLabel("EESI");
		lblEesi.setBackground(Color.DARK_GRAY);
		lblEesi.setForeground(Color.DARK_GRAY);
		lblEesi.setFont(new Font("STCaiyun", Font.BOLD, 45));
		lblEesi.setHorizontalAlignment(SwingConstants.CENTER);
		lblEesi.setBounds(10, 11, 1348, 65);
		contentPane.add(lblEesi);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBorder(new LineBorder(Color.BLUE));
		panel.setBounds(12, 80, 398, 606);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnEmploy = new JButton("Employee");
		btnEmploy.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnEmploy.setForeground(Color.WHITE);
		btnEmploy.setBackground(Color.BLUE);
		btnEmploy.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnEmploy.setBounds(12, 128, 374, 94);
		panel.add(btnEmploy);

		JButton btnLOI = new JButton("Item");
		btnLOI.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnLOI.setForeground(Color.WHITE);
		btnLOI.setBackground(Color.BLUE);
		btnLOI.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnLOI.setBounds(12, 340, 374, 94);
		panel.add(btnLOI);

		JButton btnAcc = new JButton("Accounts");
		btnAcc.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnAcc.setBackground(Color.BLUE);
		btnAcc.setForeground(Color.WHITE);
		btnAcc.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnAcc.setBounds(12, 234, 374, 94);
		panel.add(btnAcc);

		JButton btnLogout = new JButton("Log-out");
		btnLogout.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnLogout.setBackground(Color.BLUE);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				LogIn log = new LogIn();
				log.setVisible(true);
			}
		});
		btnLogout.setBounds(12, 492, 374, 94);
		panel.add(btnLogout);

		JButton btnItemRegistry = new JButton("Item Registry");
		btnItemRegistry.setBorder(new EmptyBorder(1, 1, 1, 1));
		btnItemRegistry.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnItemRegistry.setForeground(Color.WHITE);
		btnItemRegistry.setBackground(Color.BLUE);
		btnItemRegistry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnItemRegistry.setBorder(new LineBorder(new Color(0, 0, 0), 3));
				btnItemRegistry.setBackground(Color.red);
				btnEmploy.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnAcc.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnLOI.setBorder(new EmptyBorder(1, 1, 1, 1));
				windowPanel.add(rui);
				acc.setVisible(false);
				employ.setVisible(false);
				loi.setVisible(false);
				rui.setVisible(true);
				rui.setBounds(5, 5, 926, 588);
			}
		});
		btnItemRegistry.setBounds(12, 22, 374, 94);
		panel.add(btnItemRegistry);
		
		btnAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAcc.setBorder(new LineBorder(new Color(0, 0, 0),3));
				btnItemRegistry.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnEmploy.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnLOI.setBorder(new EmptyBorder(1, 1, 1, 1));
				windowPanel.add(acc);
				employ.setVisible(false);
				loi.setVisible(false);
				rui.setVisible(false);
				acc.setVisible(true);
				acc.setBounds(5, 5, 926, 588);
			}
		});
		
		btnLOI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLOI.setBorder(new LineBorder(new Color(0, 0, 0), 3));
				btnItemRegistry.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnAcc.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnEmploy.setBorder(new EmptyBorder(1, 1, 1, 1));
				windowPanel.add(loi);
				employ.setVisible(false);
				acc.setVisible(false);
				rui.setVisible(false);
				loi.setVisible(true);
				loi.setBounds(5, 5, 926, 588);
			}
		});
		
		btnEmploy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEmploy.setBorder(new LineBorder(new Color(0, 0, 0), 3));
				btnItemRegistry.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnAcc.setBorder(new EmptyBorder(1, 1, 1, 1));
				btnLOI.setBorder(new EmptyBorder(1, 1, 1, 1));
				windowPanel.add(employ);
				loi.setVisible(false);
				acc.setVisible(false);
				rui.setVisible(false);
				employ.setVisible(true);
				employ.setBounds(5, 5, 926, 588);

			}
		});
		
		btnEmploy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEmploy.setBackground(Color.RED);
				btnItemRegistry.setBackground(Color.BLUE);
				btnLOI.setBackground(Color.BLUE);
				btnAcc.setBackground(Color.BLUE);
			}
		});
		btnItemRegistry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnItemRegistry.setBackground(Color.RED);
				btnEmploy.setBackground(Color.BLUE);
				btnLOI.setBackground(Color.BLUE);
				btnAcc.setBackground(Color.BLUE);
			}
		});
		btnLOI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnLOI.setBackground(Color.RED);
				btnItemRegistry.setBackground(Color.BLUE);
				btnEmploy.setBackground(Color.BLUE);
				btnAcc.setBackground(Color.BLUE);
			}
		});
		btnAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAcc.setBackground(Color.RED);
				btnItemRegistry.setBackground(Color.BLUE);
				btnLOI.setBackground(Color.BLUE);
				btnEmploy.setBackground(Color.BLUE);
			}
		});

	}
}
