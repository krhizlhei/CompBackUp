package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import controller.GetJSON;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("HOME PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(10, 11, 185, 34);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(197, 11, 89, 34);
		contentPane.add(btnSearch);
		
		Vector<String> colName = new Vector();
		colName.add("Name");
		colName.add("Age");
		colName.add("Active");
		colName.add("Block");
		JTableHeader colTH = new JTableHeader();
		TableColumn colTC = new TableColumn();
		DefaultTableModel tableMod = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableMod.setColumnIdentifiers(colName);
		
		table = new JTable(tableMod);
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
		scrollPane.setBounds(10, 56, 599, 378);
		getContentPane().add(scrollPane);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetJSON json=new GetJSON();
				int rowNum=json.searchList(searchField.getText()).size();
				tableMod.setRowCount(rowNum);
				for (int ins = 0; ins < rowNum; ins++) {
					String insVal[]=json.searchList(searchField.getText()).get(ins).split("&");
					table.setValueAt(insVal[0], ins, 0);
					table.setValueAt(insVal[1], ins, 1);
					table.setValueAt(insVal[2], ins, 2);
					table.setValueAt(insVal[3], ins, 3);
					
				}
			}
		});
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				GetJSON json=new GetJSON();
				String details[]={},detail="";
				int rowNum=json.searchList(searchField.getText()).size();
				tableMod.setRowCount(rowNum);
				String namelist="",name[];
				for (int ins = 0; ins < rowNum; ins++) {
					String insVal[]=json.searchList(searchField.getText()).get(ins).split("&");
					namelist+=insVal[0]+"&";
				}
				namelist=namelist.substring(0,namelist.length()-1);
				name=namelist.split("&");
				for(String det:json.gDetails(name[table.getSelectedRow()])){
					detail+=det+"&&";
				}
				System.out.println(detail);
				details=detail.split("&&");

				ProfilePage pp=new ProfilePage(details);
				pp.setVisible(true);
			}
		});
	}
}
