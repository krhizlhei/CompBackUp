package View;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Controller.AccountDA;
import Controller.DepartmentDA;
import Controller.EmployeeDA;
import Controller.ListOfItemsDA;
import Controller.RegisterDA;
import Controller.ReportDA;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.Vector;

import Model.*;
import Controller.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JList;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RegisterUI extends JPanel {

	private JPanel contentPane;
	private JTextField searchTF;
	private JTextField registTF;
	private JTextField accidTF;
	private JTextField empidTF;
	private JTextField empNameTF;
	private JTextField projNoTF;
	private JTextField projNameTF;
	private JTextField projLocTF;
	private JTextField deptTF;
	private JTextField unitidTF;
	private JTextField loisTF;
	private JTextField brandTF;
	private JTextField serialnoTF;
	private JTextField modelTF;
	private JTextField ramInfoTF;
	private JTextField scTF;
	public RegisterDA regDA;
	public EmployeeDA employDA;
	public AccountDA accDA;
	public ListOfItemsDA loiDA;
	public TableColumn colTC;
	String accid = "", unitid = "", status[] = { "On-Going", "Ended" }, cell = "", AvailItems = "",pressButton="";
	int num = 0;

	public Connection connection;
	private JTextField dateTF;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RegisterUI frame = new RegisterUI(connection);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	// /**
	// * Create the frame.
	// */
	public RegisterUI(Connection connection) {
		setSize(924, 588);
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(null);

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
		
		employDA = new EmployeeDA(connection);
		regDA = new RegisterDA(connection);

		ImageIcon ii = new ImageIcon("C:/Users/User/workspace/EESI/src/View/images.jpg");

		JLabel lblRegistry = new JLabel("Registry");
		lblRegistry.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistry.setBounds(12, 11, 1024, 65);
		add(lblRegistry);

		JTableHeader colTH = new JTableHeader();
		colTC = new TableColumn();
		DefaultTableModel tableMod = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(tableMod);
		table.setGridColor(Color.BLUE);
		colTH = null;
		colTH = table.getTableHeader();
		colTH.setBackground(Color.YELLOW);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 454, 900, 122);
		add(scrollPane);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(214, 408, 98, 26);
		add(btnAdd);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(324, 408, 98, 26);
		add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(434, 408, 98, 26);
		add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBounds(541, 408, 98, 26);
		add(btnClear);

		JButton Back = new JButton("<");
		Back.setBounds(375, 377, 98, 26);
		add(Back);

		JButton Last = new JButton(">>");
		Last.setBounds(595, 377, 98, 26);
		add(Last);

		JButton Next = new JButton(">");
		Next.setBounds(485, 377, 98, 26);
		add(Next);

		JButton First = new JButton("<<");
		First.setBounds(265, 377, 98, 26);
		add(First);

		JButton btnRefresh = new JButton("");
		btnRefresh.setEnabled(false);
		btnRefresh.setIcon(ii);
		btnRefresh.setBounds(876, 377, 36, 36);
		add(btnRefresh);

		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransactionUI transUI=new TransactionUI(connection);
				transUI.setVisible(true);
			}
		});
		btnView.setBounds(651, 408, 98, 26);
		add(btnView);

		JButton btnAvailableItem = new JButton("Available Item");
		btnAvailableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnAvailableItem.setBounds(799, 416, 113, 26);
		add(btnAvailableItem);

		JButton btnRegisteredItems = new JButton("Registered Items ");
		btnRegisteredItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnRegisteredItems.setBounds(12, 416, 134, 26);
		add(btnRegisteredItems);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setForeground(Color.BLACK);
		panel.setBounds(12, 111, 519, 244);
		add(panel);
		panel.setLayout(null);

		JLabel lblRegister = new JLabel("Register # :");
		lblRegister.setBounds(12, 26, 74, 16);
		panel.add(lblRegister);

		registTF = new JTextField();
		registTF.setBounds(83, 24, 114, 28);
		panel.add(registTF);
		registTF.setEditable(false);
		registTF.setColumns(10);

		JComboBox statCB = new JComboBox(status);
		statCB.setBounds(305, 22, 114, 25);
		panel.add(statCB);

		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(232, 26, 55, 16);
		panel.add(lblStatus);

		deptTF = new JTextField();
		deptTF.setBounds(322, 53, 114, 28);
		panel.add(deptTF);
		deptTF.setEditable(false);
		deptTF.setColumns(10);

		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setBounds(229, 55, 83, 16);
		panel.add(lblDepartment);

		empidTF = new JTextField();
		empidTF.setBounds(96, 55, 114, 28);
		panel.add(empidTF);
		empidTF.setColumns(10);

		JLabel lblEmployee = new JLabel("Employee :");
		lblEmployee.setBounds(12, 57, 74, 16);
		panel.add(lblEmployee);

		JLabel lblAccountID = new JLabel("Account ID :");
		lblAccountID.setBounds(14, 87, 74, 16);
		panel.add(lblAccountID);

		accidTF = new JTextField();
		accidTF.setBounds(85, 85, 114, 28);
		panel.add(accidTF);
		accidTF.setEditable(false);
		accidTF.setColumns(10);

		empNameTF = new JTextField();
		empNameTF.setBounds(126, 113, 264, 28);
		panel.add(empNameTF);
		empNameTF.setEditable(false);
		empNameTF.setColumns(10);

		projNameTF = new JTextField();
		projNameTF.setBounds(117, 175, 199, 28);
		panel.add(projNameTF);
		projNameTF.setEditable(false);
		projNameTF.setColumns(10);

		projLocTF = new JTextField();
		projLocTF.setBounds(119, 207, 388, 28);
		panel.add(projLocTF);
		projLocTF.setEditable(false);
		projLocTF.setColumns(10);

		JLabel lblProjectName = new JLabel("Project Name :");
		lblProjectName.setBounds(12, 177, 95, 16);
		panel.add(lblProjectName);

		projNoTF = new JTextField();
		projNoTF.setBounds(93, 145, 114, 28);
		panel.add(projNoTF);
		projNoTF.setEditable(false);
		projNoTF.setColumns(10);

		JLabel lblEmployeeName = new JLabel("Employee Name :");
		lblEmployeeName.setBounds(12, 115, 104, 16);
		panel.add(lblEmployeeName);

		JLabel lblProjectNo = new JLabel("Project  No :");
		lblProjectNo.setBounds(12, 147, 74, 16);
		panel.add(lblProjectNo);

		JLabel lblProjectLocation = new JLabel("Project Location :");
		lblProjectLocation.setBounds(12, 209, 102, 16);
		panel.add(lblProjectLocation);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(556, 111, 356, 244);
		add(panel_1);
		panel_1.setLayout(null);

		unitidTF = new JTextField();
		unitidTF.setBounds(100, 16, 114, 28);
		panel_1.add(unitidTF);
		unitidTF.setColumns(10);

		JLabel lblUnitCode = new JLabel("Unit Code :");
		lblUnitCode.setBounds(19, 18, 74, 16);
		panel_1.add(lblUnitCode);

		JLabel lblBranf = new JLabel("Brand :");
		lblBranf.setBounds(23, 48, 55, 16);
		panel_1.add(lblBranf);

		brandTF = new JTextField();
		brandTF.setBounds(81, 46, 154, 28);
		panel_1.add(brandTF);
		brandTF.setEditable(false);
		brandTF.setColumns(10);

		serialnoTF = new JTextField();
		serialnoTF.setBounds(90, 113, 154, 28);
		panel_1.add(serialnoTF);
		serialnoTF.setEditable(false);
		serialnoTF.setColumns(10);

		JLabel lblModel = new JLabel("Model :");
		lblModel.setBounds(19, 85, 55, 16);
		panel_1.add(lblModel);

		modelTF = new JTextField();
		modelTF.setBounds(68, 81, 114, 28);
		panel_1.add(modelTF);
		modelTF.setEditable(false);
		modelTF.setText("");
		modelTF.setColumns(10);

		dateTF = new JTextField();
		dateTF.setBounds(236, 81, 114, 28);
		panel_1.add(dateTF);
		dateTF.setEditable(false);
		dateTF.setColumns(10);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(194, 81, 55, 16);
		panel_1.add(lblDate);

		JLabel lblSerialNo = new JLabel("Serial No. :");
		lblSerialNo.setBounds(19, 115, 74, 16);
		panel_1.add(lblSerialNo);

		JLabel lblRamInfo = new JLabel("RAM Info :");
		lblRamInfo.setBounds(19, 145, 64, 16);
		panel_1.add(lblRamInfo);

		ramInfoTF = new JTextField();
		ramInfoTF.setBounds(101, 143, 248, 28);
		panel_1.add(ramInfoTF);
		ramInfoTF.setEditable(false);
		ramInfoTF.setText("");
		ramInfoTF.setColumns(10);

		JLabel lblListOfItems = new JLabel("List of Items Status :");
		lblListOfItems.setBounds(21, 172, 129, 16);
		panel_1.add(lblListOfItems);

		JLabel lblStorageCapacity = new JLabel("Storage Capacity :");
		lblStorageCapacity.setBounds(19, 200, 114, 16);
		panel_1.add(lblStorageCapacity);

		scTF = new JTextField();
		scTF.setBounds(126, 199, 223, 28);
		panel_1.add(scTF);
		scTF.setEditable(false);
		scTF.setText("");
		scTF.setColumns(10);

		loisTF = new JTextField();
		loisTF.setBounds(150, 170, 154, 28);
		panel_1.add(loisTF);
		loisTF.setEditable(false);
		loisTF.setColumns(10);

		searchTF = new JTextField();
		searchTF.setBounds(724, 88, 188, 28);
		add(searchTF);
		searchTF.setColumns(10);

		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(651, 88, 55, 16);
		add(lblSearch);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regDA = new RegisterDA(connection);
				int rownum = regDA.List("1").size();
				tableMod.setRowCount(rownum);
				
				for (int ins = 0; ins < rownum; ins++) {
					table.setValueAt(regDA.List("1").get(ins).gProcessID(), ins, 0);
					table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getFirstname(), ins, 1);
					table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getDept().getDeptDesc(), ins, 2);
					table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjname(), ins, 3);
					table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjLoc(), ins, 4);
					table.setValueAt(regDA.List("1").get(ins).gLOI().gBrand(), ins, 5);
					table.setValueAt(regDA.List("1").get(ins).gLOI().gModel(), ins, 6);
					table.setValueAt(regDA.List("1").get(ins).gDateReceived(), ins, 7);
					table.setValueAt(regDA.List("1").get(ins).gDateReturned(), ins, 8);
					table.setValueAt(regDA.List("1").get(ins).gStatus(), ins, 9);
				}
			}
		});
		
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				searchTF = (JTextField) arg0.getSource();
				regDA = new RegisterDA(connection);
				Register reg = new Register();
				reg = regDA.searchReg(searchTF.getText());
				num = regDA.getProcessNum(registTF.getText());

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789";

				for (int i = 0; i < userInput.length(); i++) {
					if (!(vowels.contains("" + word[i]))) {
						searchTF.setText(searchTF.getText().substring(0, searchTF.getText().length() - 1));
					}
				}
				if (searchTF.getText().equals("") || searchTF.getText().equals(" ")
						|| searchTF.getText().equals(null)) {
					registTF.setText(null);
					accidTF.setText(null);
					unitidTF.setText(null);
					statCB.setSelectedIndex(0);
					brandTF.setText(null);
					modelTF.setText(null);
					serialnoTF.setText(null);
					scTF.setText(null);
					ramInfoTF.setText(null);
					loisTF.setText(null);
					empidTF.setText(null);
					empNameTF.setText(null);
					deptTF.setText(null);
					projNoTF.setText(null);
					projNameTF.setText(null);
					projLocTF.setText(null);
					dateTF.setText(null);

				} else {
					if (reg.gProcessID() == null) {
						JOptionPane.showMessageDialog(null, "Search Mismatch", "Search Error",
								JOptionPane.ERROR_MESSAGE);

					} else {
						registTF.setText(reg.gProcessID());
						accidTF.setText(reg.gAcc().getAcntNo());
						unitidTF.setText(reg.gLOI().gUnitCode());
						statCB.setSelectedItem(reg.gStatus().replace("_", "-"));
						brandTF.setText(reg.gLOI().gBrand());
						modelTF.setText(reg.gLOI().gModel());
						serialnoTF.setText(reg.gLOI().gSerialNo());
						scTF.setText(reg.gLOI().gSCInfo());
						ramInfoTF.setText(reg.gLOI().gRAMInfo());
						loisTF.setText(reg.gLOI().gLOIStatus());
						empidTF.setText(reg.gAcc().getEmploy().getEmpID());
						empNameTF.setText(reg.gAcc().getEmploy().getFirstname());
						deptTF.setText(reg.gAcc().getEmploy().getDept().getDeptDesc());
						projNoTF.setText(reg.gAcc().getProj().getProjNo());
						projNameTF.setText(reg.gAcc().getProj().getProjname());
						projLocTF.setText(reg.gAcc().getProj().getProjLoc());
						dateTF.setText(reg.gLOI().gDateAdded() + "");
						if (loisTF.getText().equals("Not Available")||loisTF.getText().equals("Use")) {
							btnAdd.setEnabled(false);
						} else {
							btnAdd.setEnabled(true);

						}

					}
				}
			}
		});
		unitidTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				unitidTF = (JTextField) arg0.getSource();
				loiDA = new ListOfItemsDA(connection);
				String add = " ";
				ListofItems loi = new ListofItems();
				loi = loiDA.searchLOI(unitidTF.getText());

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";

				for (int i = 0; i < userInput.length(); i++) {
					if (!(vowels.contains("" + word[i]))) {
						unitidTF.setText(unitidTF.getText().substring(0, unitidTF.getText().length() - 1));
					}
				}
				if (unitidTF.getText().equals("") || unitidTF.getText().equals(" ")
						|| unitidTF.getText().equals(null)) {
					unitid = null;
					brandTF.setText(null);
					modelTF.setText(null);
					serialnoTF.setText(null);
					scTF.setText(null);
					ramInfoTF.setText(null);
					loisTF.setText(null);
					dateTF.setText(null);
				} else {
					if (loi.gUnitCode() == null) {
						JOptionPane.showMessageDialog(null, "No list", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							unitid = loi.gUnitCode();
							brandTF.setText(loi.gBrand());
							modelTF.setText(loi.gModel());
							serialnoTF.setText(loi.gSerialNo());
							scTF.setText(loi.gSCInfo());
							ramInfoTF.setText(loi.gRAMInfo());
							loisTF.setText(loi.gLOIStatus());
							dateTF.setText(loi.gDateAdded() + "");
							if (loisTF.getText().equals("Not Available")) {
								btnAdd.setEnabled(false);
							} else {
								btnAdd.setEnabled(true);

							}

						} else {
							unitid = loi.gUnitCode();
							brandTF.setText(loi.gBrand());
							modelTF.setText(loi.gModel());
							serialnoTF.setText(loi.gSerialNo());
							scTF.setText(loi.gSCInfo());
							ramInfoTF.setText(loi.gRAMInfo());
							loisTF.setText(loi.gLOIStatus());
							dateTF.setText(loi.gDateAdded() + "");
							if (loisTF.getText().equals("Not Available")||loisTF.getText().equals("Not Use")) {
								btnAdd.setEnabled(false);
							} else {
								btnAdd.setEnabled(true);

							}

						}
					}
				}
			}
		});

		empidTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				empidTF = (JTextField) arg0.getSource();
				employDA = new EmployeeDA(connection);
				accDA = new AccountDA(connection);
				Account acc = new Account();
				DepartmentDA dep = new DepartmentDA(connection);
				acc = accDA.searchAccount(empidTF.getText());

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";

				for (int i = 0; i < userInput.length(); i++) {
					if (!(vowels.contains("" + word[i]))) {
						empidTF.setText(empidTF.getText().substring(0, empidTF.getText().length() - 1));
					}
				}
				if (empidTF.getText().equals("") || empidTF.getText().equals(" ") || empidTF.getText().equals(null)) {
					accidTF.setText(null);
					empidTF.setText(null);
					empNameTF.setText(null);
					deptTF.setText(null);
					projNoTF.setText(null);
					projNameTF.setText(null);
					projLocTF.setText(null);
				} else {
					if (acc.getAcntNo() == null) {
						JOptionPane.showMessageDialog(null, "Search Mismatch", "Mismatch Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						accidTF.setText(acc.getAcntNo());
						empNameTF.setText(acc.getEmploy().getFirstname());
						deptTF.setText(dep.getDepartment(acc.getEmploy().getDept().getDeptcode()));
						projNoTF.setText(acc.getProj().getProjNo());
						projNameTF.setText(acc.getProj().getProjname());
						projLocTF.setText(acc.getProj().getProjLoc());

					}
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register reg = new Register();
				Account acc = new Account();
				ListofItems loi = new ListofItems();
				regDA = new RegisterDA(connection);

				reg.sStatus(statCB.getSelectedItem().toString());
				loi.sUnitCode(unitid);
				acc.setAcntNo(accidTF.getText());
				reg.sAcc(acc);
				reg.sLOI(loi);
				regDA.addReg(reg);

			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				Account acc = new Account();
				ListofItems loi = new ListofItems();
				regDA = new RegisterDA(connection);
				String datereturned = "";

				reg.sProcessID(registTF.getText());
				if (statCB.getSelectedIndex() == 0) {
					datereturned = " ";
				} else {
					datereturned = ",datereturned=current date ";
				}
				reg.sStatus(statCB.getSelectedItem().toString());
				loi.sUnitCode(unitidTF.getText());
				acc.setAcntNo(accidTF.getText());
				reg.sAcc(acc);
				reg.sLOI(loi);
				regDA.editReg(reg, datereturned);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regDA = new RegisterDA(connection);
				regDA.deleteReg(registTF.getText());

			}
		});
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regDA = new RegisterDA(connection);
				num--;

				if (num == -1) {
					num = 0;
				} else if (num == regDA.List("1").size()) {
					num = regDA.List("1").size() - 1;
				} else {
					registTF.setText(regDA.List("1").get(num).gProcessID());
					accidTF.setText(regDA.List("1").get(num).gAcc().getAcntNo());
					unitidTF.setText(regDA.List("1").get(num).gLOI().gUnitCode());
					statCB.setSelectedItem(regDA.List("1").get(num).gStatus());
					brandTF.setText(regDA.List("1").get(num).gLOI().gBrand());
					modelTF.setText(regDA.List("1").get(num).gLOI().gModel());
					serialnoTF.setText(regDA.List("1").get(num).gLOI().gSerialNo());
					scTF.setText(regDA.List("1").get(num).gLOI().gSCInfo());
					ramInfoTF.setText(regDA.List("1").get(num).gLOI().gRAMInfo());
					loisTF.setText(regDA.List("1").get(num).gLOI().gLOIStatus());
					empidTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getEmpID());
					empNameTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getFirstname());
					deptTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getDept().getDeptDesc());
					projNoTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjNo());
					projNameTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjname());
					projLocTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjLoc());
					dateTF.setText(regDA.List("1").get(num).gLOI().gDateAdded() + "");
					if (loisTF.getText().equals("Not Available")) {
						btnAdd.setEnabled(false);
					} else {
						btnAdd.setEnabled(true);

					}

				}
			}
		});
		Last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regDA = new RegisterDA(connection);
				num = regDA.List("1").size() - 1;

				registTF.setText(regDA.List("1").get(num).gProcessID());
				accidTF.setText(regDA.List("1").get(num).gAcc().getAcntNo());
				unitidTF.setText(regDA.List("1").get(num).gLOI().gUnitCode());
				statCB.setSelectedItem(regDA.List("1").get(num).gStatus());
				brandTF.setText(regDA.List("1").get(num).gLOI().gBrand());
				modelTF.setText(regDA.List("1").get(num).gLOI().gModel());
				serialnoTF.setText(regDA.List("1").get(num).gLOI().gSerialNo());
				scTF.setText(regDA.List("1").get(num).gLOI().gSCInfo());
				ramInfoTF.setText(regDA.List("1").get(num).gLOI().gRAMInfo());
				loisTF.setText(regDA.List("1").get(num).gLOI().gLOIStatus());
				empidTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getEmpID());
				empNameTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getFirstname());
				deptTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getDept().getDeptDesc());
				projNoTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjNo());
				projNameTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjname());
				projLocTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjLoc());
				dateTF.setText(regDA.List("1").get(num).gLOI().gDateAdded() + "");
				if (loisTF.getText().equals("Not Available")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);

				}

			}
		});
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regDA = new RegisterDA(connection);
				num++;

				if (num >= regDA.List("1").size()) {
					num = regDA.List("1").size() - 1;
				} else {
					registTF.setText(regDA.List("1").get(num).gProcessID());
					accidTF.setText(regDA.List("1").get(num).gAcc().getAcntNo());
					unitidTF.setText(regDA.List("1").get(num).gLOI().gUnitCode());
					statCB.setSelectedItem(regDA.List("1").get(num).gStatus());
					brandTF.setText(regDA.List("1").get(num).gLOI().gBrand());
					modelTF.setText(regDA.List("1").get(num).gLOI().gModel());
					serialnoTF.setText(regDA.List("1").get(num).gLOI().gSerialNo());
					scTF.setText(regDA.List("1").get(num).gLOI().gSCInfo());
					ramInfoTF.setText(regDA.List("1").get(num).gLOI().gRAMInfo());
					loisTF.setText(regDA.List("1").get(num).gLOI().gLOIStatus());
					empidTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getEmpID());
					empNameTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getFirstname());
					deptTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getDept().getDeptDesc());
					projNoTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjNo());
					projNameTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjname());
					projLocTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjLoc());
					dateTF.setText(regDA.List("1").get(num).gLOI().gDateAdded() + "");
					if (loisTF.getText().equals("Not Available")) {
						btnAdd.setEnabled(false);
					} else {
						btnAdd.setEnabled(true);

					}

				}
			}
		});
		First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regDA = new RegisterDA(connection);
				num = 0;

				registTF.setText(regDA.List("1").get(num).gProcessID());
				accidTF.setText(regDA.List("1").get(num).gAcc().getAcntNo());
				unitidTF.setText(regDA.List("1").get(num).gLOI().gUnitCode());
				statCB.setSelectedItem(regDA.List("1").get(num).gStatus());
				brandTF.setText(regDA.List("1").get(num).gLOI().gBrand());
				modelTF.setText(regDA.List("1").get(num).gLOI().gModel());
				serialnoTF.setText(regDA.List("1").get(num).gLOI().gSerialNo());
				scTF.setText(regDA.List("1").get(num).gLOI().gSCInfo());
				ramInfoTF.setText(regDA.List("1").get(num).gLOI().gRAMInfo());
				loisTF.setText(regDA.List("1").get(num).gLOI().gLOIStatus());
				empidTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getEmpID());
				empNameTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getFirstname());
				deptTF.setText(regDA.List("1").get(num).gAcc().getEmploy().getDept().getDeptDesc());
				projNoTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjNo());
				projNameTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjname());
				projLocTF.setText(regDA.List("1").get(num).gAcc().getProj().getProjLoc());
				if (loisTF.getText().equals("Not Available")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);

				}

				dateTF.setText(regDA.List("1").get(num).gLOI().gDateAdded() + "");

			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registTF.setText(null);
				accidTF.setText(null);
				unitidTF.setText(null);
				statCB.setSelectedIndex(0);
				dateTF.setText(null);
				brandTF.setText(null);
				modelTF.setText(null);
				serialnoTF.setText(null);
				scTF.setText(null);
				ramInfoTF.setText(null);
				loisTF.setText(null);
				empidTF.setText(null);
				empNameTF.setText(null);
				deptTF.setText(null);
				projNoTF.setText(null);
				projNameTF.setText(null);
				projLocTF.setText(null);
			}
		});
		btnAvailableItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loiDA = new ListOfItemsDA(connection);
				ListofItems loi = new ListofItems();

				Vector<String> colName = new Vector();
				colName.add("Unitcode");
				colName.add("Brand");
				colName.add("Model");
				tableMod.setColumnIdentifiers(colName);

				for (int i = 0; i < colName.size(); i++) {
					colTC = table.getColumnModel().getColumn(i);
					colTC.setPreferredWidth(100);
				}

				int rownum = loiDA.AvailableItems().size();
				
				
				if (rownum == 0) {
					rownum=1;
					tableMod.setRowCount(rownum);
					for (int ins = 0; ins < rownum; ins++) {

					table.setValueAt(null, 0, 0);
					table.setValueAt(null, 0, 1);
					table.setValueAt(null, 0, 2);
					}
				} else {
					tableMod.setRowCount(rownum);
					for (int ins = 0; ins < rownum; ins++) {
						
						table.setValueAt(loiDA.AvailableItems().get(ins).gUnitCode(), ins, 0);
						table.setValueAt(loiDA.AvailableItems().get(ins).gBrand(), ins, 1);
						table.setValueAt(loiDA.AvailableItems().get(ins).gModel(), ins, 2);

					}
				}
				AvailItems = "Pressed";
				btnRefresh.setEnabled(false);
			}
		});
		btnRegisteredItems.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnRefresh.setEnabled(true);
				Vector<String> colName = new Vector();
				colName.add("Process ID");
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
				table.setValueAt(null, 0, 0);
				for (int i = 0; i < colName.size(); i++) {
					colTC = table.getColumnModel().getColumn(i);
					colTC.setPreferredWidth(100);
				}
				
					for (int ins = 0; ins < rownum; ins++) {
					
						table.setValueAt(regDA.List("1").get(ins).gProcessID(), ins, 0);
						table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getFirstname(), ins, 1);
						table.setValueAt(regDA.List("1").get(ins).gAcc().getEmploy().getDept().getDeptDesc(), ins, 2);
						table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjname(), ins, 3);
						table.setValueAt(regDA.List("1").get(ins).gAcc().getProj().getProjLoc(), ins, 4);
						table.setValueAt(regDA.List("1").get(ins).gLOI().gBrand(), ins, 5);
						table.setValueAt(regDA.List("1").get(ins).gLOI().gModel(), ins, 6);
						table.setValueAt(regDA.List("1").get(ins).gDateReceived(), ins, 7);
						table.setValueAt(regDA.List("1").get(ins).gDateReturned(), ins, 8);
						table.setValueAt(regDA.List("1").get(ins).gStatus(), ins, 9);

					
				}
				AvailItems = "Unpressed";
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Register reg = new Register();
				ListofItems loi = new ListofItems();
				int x = table.rowAtPoint(e.getPoint());
				int y = table.columnAtPoint(e.getPoint());
				String selectedCell = table.getValueAt(x, 0).toString();

				if (AvailItems.equals("Unpressed")) {
					reg = regDA.gSelectedCell(selectedCell);
					registTF.setText(reg.gProcessID());
					accidTF.setText(reg.gAcc().getAcntNo());
					empidTF.setText(reg.gAcc().getEmploy().getEmpID());
					empNameTF.setText(reg.gAcc().getEmploy().getFirstname());
					deptTF.setText(reg.gAcc().getEmploy().getDept().getDeptDesc());
					projNoTF.setText(reg.gAcc().getProj().getProjNo());
					projNameTF.setText(reg.gAcc().getProj().getProjname());
					projLocTF.setText(reg.gAcc().getProj().getProjLoc());
					unitidTF.setText(reg.gLOI().gUnitCode());
					statCB.setSelectedItem(reg.gStatus().replace("_", "-"));
					brandTF.setText(reg.gLOI().gBrand());
					modelTF.setText(reg.gLOI().gModel());
					serialnoTF.setText(reg.gLOI().gSerialNo());
					scTF.setText(reg.gLOI().gSCInfo());
					ramInfoTF.setText(reg.gLOI().gRAMInfo());
					loisTF.setText(reg.gLOI().gLOIStatus());
					dateTF.setText(reg.gLOI().gDateAdded() + "");
					if (loisTF.getText().equals("Not Available")) {
						btnAdd.setEnabled(false);
					} else {
						btnAdd.setEnabled(true);

					}
				} else if (AvailItems.equals("Pressed")) {
					loi = loiDA.getSelectedItem(selectedCell);
					unitidTF.setText(loi.gUnitCode());
					unitid = unitidTF.getText();
					brandTF.setText(loi.gBrand());
					modelTF.setText(loi.gModel());
					serialnoTF.setText(loi.gSerialNo());
					scTF.setText(loi.gSCInfo());
					ramInfoTF.setText(loi.gRAMInfo());
					loisTF.setText(loi.gLOIStatus());
					dateTF.setText(loi.gDateAdded() + "");
					if (loisTF.getText().equals("Not Available")) {
						btnAdd.setEnabled(false);
					} else {
						btnAdd.setEnabled(true);

					}
				}

			}
		});

	}
}
