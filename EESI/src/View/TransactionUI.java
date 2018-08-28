package View;

import java.awt.AWTException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.hadoop.hive.ql.parse.HiveParser.sysFuncNames_return;

import Controller.RegisterDA;
import Controller.ReportDA;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TransactionUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchTF;
	private Connection connection;
	private DefaultListModel<String> dfl;
	private JList list,viewList;
	private List<String> arrList;
	private RegisterDA regDA;

	String filter[] = { "Name", "Department", "Project Name", "Project Location", "Brand", "Model", "Issued",
			"Returned", "Status"};
	Object[] listValue={};
	int intValue[];

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TransactionUI frame = new TransactionUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
///					e.printStackTrace();
//				}
//			}
//		});
//	}/
//
//	/**
//	 * Create the frame.
//	 */
	public TransactionUI(Connection connection) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

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
		
		dfl = new DefaultListModel <String>();
		arrList=new ArrayList<String>();
		DefaultTableModel tableMod = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(tableMod);
		table.setAutoCreateRowSorter(true);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(5, 109, 769, 347);
		contentPane.add(scrollPane);
		
		RegisterDA regDA=new RegisterDA(connection);
		Vector<String> colName = new Vector();
		colName.add("Name");
		colName.add("Department");
		colName.add("Project Name");
		colName.add("Project Location");
		colName.add("Brand");
		colName.add("Model");
		colName.add("Issued");
		colName.add("Returned");
		colName.add("Status");

		tableMod.setColumnIdentifiers(colName);
		int rownum = regDA.List("1").size();
		tableMod.setRowCount(rownum);
		
		
			for (int ins = 0; ins < rownum; ins++) {
			
				table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getFirstname(), ins, 0);
				table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getDept().getDeptDesc(), ins, 1);
				table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjname(), ins, 2);
				table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjLoc(), ins, 3);
				table.setValueAt(regDA.List("1").get(ins).gLOI().gBrand(), ins, 4);
				table.setValueAt(regDA.List("1").get(ins).gLOI().gModel(), ins, 5);
				table.setValueAt(regDA.List("1").get(ins).gDateReceived(), ins, 6);
				table.setValueAt(regDA.List("1").get(ins).gDateReturned(),ins, 7);
				table.setValueAt(regDA.List("1").get(ins).gStatus(), ins, 8);
		}
		
		searchTF = new JTextField();
		searchTF.setBounds(59, 12, 182, 28);
		contentPane.add(searchTF);
		searchTF.setColumns(10);

		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(5, 15, 53, 14);
		contentPane.add(lblSearch);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReportDA report = new ReportDA(connection);
				report.viewTransaction(searchTF.getText());
			}
		});
		btnPrint.setBounds(676, 9, 98, 26);
		contentPane.add(btnPrint);
		
		JList list = new JList(filter);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setBounds(59, 57, 475, 40);
		list.setVisibleRowCount(2);
		
		contentPane.add(list);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filtered="",value="",filt="";
				intValue=list.getSelectedIndices();
				listValue=list.getSelectedValues();
				for(int valList=0;valList<listValue.length;valList++)
				{
					filtered+=listValue[valList]+"\n";	
					value+=listValue[valList]+", ";
				
				}
				
				int press=JOptionPane.showConfirmDialog(null,"Are you sure to filter by?\n" + filtered,"Filter",JOptionPane.YES_NO_OPTION);
				if(press==JOptionPane.YES_OPTION){
					int rownum;
					
					if(value.equals(""))
					{
						value="1";
						rownum = regDA.List(value).size();
						tableMod.setRowCount(rownum);
						
					}
					else{
					value=regDA.filter(value.split(", "));
					rownum = regDA.List(value).size();
					tableMod.setRowCount(rownum);
					
					}
					
						for (int ins = 0; ins < rownum; ins++) {
						
							table.setValueAt(regDA.List(value).get(ins).gAcc().getEmploy().getFirstname(), ins, 0);
							table.setValueAt(regDA.List(value).get(ins).gAcc().getEmploy().getDept().getDeptDesc(), ins, 1);
							table.setValueAt(regDA.List(value).get(ins).gAcc().getProj().getProjname(), ins, 2);
							table.setValueAt(regDA.List(value).get(ins).gAcc().getProj().getProjLoc(), ins, 3);
							table.setValueAt(regDA.List(value).get(ins).gLOI().gBrand(), ins, 4);
							table.setValueAt(regDA.List(value).get(ins).gLOI().gModel(), ins, 5);
							table.setValueAt(regDA.List(value).get(ins).gDateReceived(), ins, 6);
							table.setValueAt(regDA.List(value).get(ins).gDateReturned(),ins, 7);
							table.setValueAt(regDA.List(value).get(ins).gStatus(), ins, 8);
					}
					
				}
				
			}
		});
		btnFilter.setBounds(250, 9, 98, 26);
		contentPane.add(btnFilter);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				RegisterUI reg=new RegisterUI(connection);
				reg.setVisible(true);
				
			}
		});
		btnBack.setBounds(676, 53, 98, 26);
		contentPane.add(btnBack);
		
		setLocationRelativeTo(null);
		

	}
}
