package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Controller.ListOfItemsDA;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemListUI extends JFrame {

	private JPanel contentPane;
	private Connection connection;
//	/**
///	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ItemListUI frame = new ItemListUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public ItemListUI(Connection connection) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
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
		
		Vector<String> colName = new Vector();
		colName.add("Unit ID");
		colName.add("Item Type");
		colName.add("Item ID");
		colName.add("Brand");
		colName.add("Model");
		colName.add("Status");
		JTableHeader colTH = new JTableHeader();
		TableColumn colTC = new TableColumn();
		DefaultTableModel tableMod = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMod.setColumnIdentifiers(colName);
		JTable table = new JTable(tableMod);
		table.setGridColor(Color.BLUE);
		colTH = null;
		for (int i = 0; i < colName.size(); i++) {
			colTC = table.getColumnModel().getColumn(i);
			colTC.setPreferredWidth(100);
		}
		colTH = table.getTableHeader();
		colTH.setBackground(Color.YELLOW);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 11, 568, 395);
		getContentPane().add(scrollPane);
		
		ListOfItemsDA listDA=new ListOfItemsDA(connection);
		
		int rownum = listDA.loiList().size();
		String name = "";
		tableMod.setRowCount(rownum);

		for (int ins = 0; ins < rownum; ins++) {
			table.setValueAt(listDA.loiList().get(ins).gUnitCode(), ins, 0);
			table.setValueAt(listDA.loiList().get(ins).gCom().gPCID(), ins, 1);
			table.setValueAt(listDA.loiList().get(ins).gDev().gItemID(), ins, 2);
			table.setValueAt(listDA.loiList().get(ins).gBrand(), ins, 3);
			table.setValueAt(listDA.loiList().get(ins).gModel(), ins, 4);
			table.setValueAt(listDA.loiList().get(ins).gLOIStatus(), ins, 5);
		}
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(10, 427, 89, 23);
		contentPane.add(btnBack);
		
		
	}
}
