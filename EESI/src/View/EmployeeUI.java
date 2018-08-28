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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Controller.DepartmentDA;
import Controller.EmployeeDA;
import Controller.JobDA;
import Controller.ProjectDA;
import Model.*;
import Controller.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EmployeeUI extends JPanel {
	public Connection connection;
	private JPanel contentPane;
	public EmployeeDA employDA;
	public DepartmentDA deptDA;
	public ProjectDA projDA;
	public JobDA jobDA;
	public Employee employ;
	public Department dept;
	public Job job;
	public JTextField searchText;
	public JTextField firstnametf;
	public JTextField lastnametf;
	public JTextField midinitTF;
	public JTextField contactTF;
	public JTextField empidTF;
	String sex[] = { "", "M", "F" }, pcode = "", dcode = "", Ncode = "", deptcode[], jobcode[], projCode[];
	int num;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// EmployeeUI frame = new EmployeeUI(connection);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/// **
	// * Create the frame.
	// */
	public EmployeeUI(Connection connection) {

		setSize(924, 588);
		setBackground(Color.GRAY);
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
		
		deptDA = new DepartmentDA(connection);
		jobDA = new JobDA(connection);
		employDA = new EmployeeDA(connection);
		projDA = new ProjectDA(connection);

		dcode += " _";
		Ncode += " _";
		pcode += " _";
		for (int depNum = 0; depNum < deptDA.deptlist().size(); depNum++) {
			dcode += deptDA.deptlist().get(depNum).getDeptDesc() + "_";

		}
		for (int jobNum = 0; jobNum < jobDA.joblist().size(); jobNum++) {
			Ncode += jobDA.joblist().get(jobNum).getJobDesc() + "_";

		}
		for (int projNum = 0; projNum < projDA.projlist().size(); projNum++) {
			pcode += projDA.projlist().get(projNum).getProjname() + "_";
		}
		jobcode = Ncode.split("_");
		deptcode = dcode.split("_");
		projCode = pcode.split("_");

		Vector<String> colName = new Vector();
		colName.add("Employee ID");
		colName.add("Name");
		colName.add("Gender");
		colName.add("Contact #");
		colName.add("Department");
		colName.add("Position");
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
		scrollPane.setBounds(12, 346, 900, 230);
		add(scrollPane);

		int rownum = employDA.employlist().size();
		String name = "";
		tableMod.setRowCount(rownum);

		for (int ins = 0; ins < rownum; ins++) {
			table.setValueAt(employDA.employlist().get(ins).getEmpID(), ins, 0);
			name = employDA.employlist().get(ins).getFirstname() + " " + employDA.employlist().get(ins).getMidInit()
					+ ". " + employDA.employlist().get(ins).getLastname();
			table.setValueAt(name, ins, 1);
			table.setValueAt(employDA.employlist().get(ins).getGender(), ins, 2);
			table.setValueAt(employDA.employlist().get(ins).getPhoneNo(), ins, 3);
			table.setValueAt(employDA.employlist().get(ins).getDept().getDeptcode(), ins, 4);
			table.setValueAt(employDA.employlist().get(ins).getJob().getJobcode(), ins, 5);

		}

		searchText = new JTextField();
		searchText.setBounds(76, 108, 145, 28);
		add(searchText);
		searchText.setColumns(10);

		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(12, 111, 61, 14);
		add(lblSearch);

		firstnametf = new JTextField();
		firstnametf.setBounds(230, 193, 174, 28);
		add(firstnametf);
		firstnametf.setColumns(10);

		lastnametf = new JTextField();
		lastnametf.setBounds(230, 224, 174, 28);
		add(lastnametf);
		lastnametf.setColumns(10);

		midinitTF = new JTextField();
		midinitTF.setBounds(230, 255, 68, 28);
		add(midinitTF);
		midinitTF.setColumns(10);

		JLabel lblFirstname = new JLabel("Firstname :");
		lblFirstname.setBounds(146, 196, 74, 14);
		add(lblFirstname);

		JLabel lblLastname = new JLabel("Lastname :");
		lblLastname.setBounds(146, 227, 68, 14);
		add(lblLastname);

		JLabel lblMiddleInitial = new JLabel("Middle Initial :");
		lblMiddleInitial.setBounds(135, 258, 85, 14);
		add(lblMiddleInitial);

		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(351, 256, 59, 14);
		add(lblGender);

		JComboBox genderCB = new JComboBox(sex);
		genderCB.setBounds(428, 255, 58, 28);
		add(genderCB);

		contactTF = new JTextField("09");
		contactTF.setBounds(613, 255, 174, 28);
		add(contactTF);
		contactTF.setColumns(10);

		JLabel lblContactNo = new JLabel("Contact No :");
		lblContactNo.setBounds(529, 258, 68, 14);
		add(lblContactNo);

		JComboBox deptCB = new JComboBox(deptcode);
		deptCB.setBounds(629, 156, 193, 28);
		add(deptCB);

		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setBounds(549, 159, 84, 14);
		add(lblDepartment);

		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setBounds(562, 190, 55, 14);
		add(lblPosition);

		JComboBox positionCB = new JComboBox(jobcode);
		positionCB.setBounds(629, 187, 193, 28);
		add(positionCB);

		JButton First = new JButton("<<");
		First.setBounds(312, 287, 89, 23);
		add(First);

		JButton Back = new JButton("<");
		Back.setBounds(401, 287, 89, 23);
		add(Back);

		JButton Next = new JButton(">");
		Next.setBounds(490, 287, 89, 23);
		add(Next);

		JButton Last = new JButton(">>");
		Last.setBounds(579, 287, 89, 23);
		add(Last);

		JButton Add = new JButton("ADD");
		Add.setBounds(312, 311, 89, 23);
		add(Add);

		JButton Edit = new JButton("EDIT");
		Edit.setBounds(401, 311, 89, 23);
		add(Edit);

		JButton Delete = new JButton("DELETE");
		Delete.setBounds(579, 311, 89, 23);
		add(Delete);

		JButton Retrieve = new JButton("RETRIEVE");
		Retrieve.setBounds(491, 311, 89, 23);
		add(Retrieve);

		empidTF = new JTextField();
		empidTF.setEditable(false);
		empidTF.setBounds(230, 156, 114, 28);
		add(empidTF);
		empidTF.setColumns(10);

		JLabel lblEmployeeId = new JLabel("Employee ID :");
		lblEmployeeId.setBounds(135, 158, 85, 16);
		add(lblEmployeeId);

		JLabel label = new JLabel("");
		label.setBounds(343, 12, 55, 16);
		add(label);

		JComboBox deptFilt = new JComboBox(deptcode);
		deptFilt.setBounds(379, 108, 220, 28);
		add(deptFilt);

		JComboBox projFilt = new JComboBox(projCode);
		projFilt.setBounds(686, 108, 220, 28);
		add(projFilt);

		JLabel lblDepartment_1 = new JLabel("Department :");
		lblDepartment_1.setBounds(295, 108, 89, 16);
		add(lblDepartment_1);

		JLabel lblProject = new JLabel("Project :");
		lblProject.setBounds(625, 112, 55, 16);
		add(lblProject);

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setEnabled(false);
		btnRefresh.setFont(new Font("Arial", Font.BOLD, 12));
		btnRefresh.setBounds(823, 308, 89, 26);
		add(btnRefresh);
		
		JLabel lblEmployee = new JLabel("EMPLOYEE");
		lblEmployee.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee.setBounds(12, 12, 900, 84);
		add(lblEmployee);

	
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				searchText = (JTextField) arg0.getSource();
				EmployeeDA employDA=new EmployeeDA(connection);
				
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÒ— ";
				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						searchText.setText(searchText.getText().substring(0, searchText.getText().length() - 1));
					}
				if(searchText.getText().equals("")||searchText.getText().equals(" ")||searchText.getText().equals(null)){
					empidTF.setText(null);
					firstnametf.setText(null);
					lastnametf.setText(null);
					midinitTF.setText(null);
					genderCB.setSelectedIndex(0);
					contactTF.setText(null);
					deptCB.setSelectedIndex(0);
					positionCB.setSelectedIndex(0);
				}
				else{
				DepartmentDA dept = new DepartmentDA(connection);
				JobDA jobDA = new JobDA(connection);
				Employee emp = new Employee();
				if (deptFilt.getSelectedIndex() == 0 && projFilt.getSelectedIndex() == 0) {
					emp = searchEmployee(userInput);
					if (emp.getEmpID() == null) {
						JOptionPane.showMessageDialog(null, "No Employee Listed", "Form Incomplete",
								JOptionPane.ERROR_MESSAGE);
						num=0;
					} else {
						num = employDA.getEmployeeNumber(empidTF.getText());
						empidTF.setText(emp.getEmpID());
						firstnametf.setText(emp.getFirstname());
						lastnametf.setText(emp.getLastname());
						midinitTF.setText(emp.getMidInit());
						for (int sexNo = 1; sexNo < 3; sexNo++) {
							if (sex[sexNo].equals(emp.getGender())) {
								genderCB.setSelectedIndex(sexNo);
							}
						}
						contactTF.setText(employDA.employlist().get(0).getPhoneNo());
						deptCB.setSelectedItem(deptDA.getDepartment(emp.getDept().getDeptcode()));
						positionCB.setSelectedItem(jobDA.getJob(emp.getJob().getJobcode()));
					}
				} else if (projFilt.getSelectedIndex() == 0) {
					emp = searchEmployeeByDept(userInput,
							dept.getDepartmentCode(deptFilt.getSelectedItem().toString()));
					if (emp.getEmpID() == null) {
						JOptionPane.showMessageDialog(null, "No Employee Listed", "Form Incomplete",
								JOptionPane.ERROR_MESSAGE);
						num=0;
						
					} else {
						num = employDA.getEmployeeNumber(empidTF.getText());
						empidTF.setText(emp.getEmpID());
						firstnametf.setText(emp.getFirstname());
						lastnametf.setText(emp.getLastname());
						midinitTF.setText(emp.getMidInit());
						for (int sexNo = 1; sexNo < 3; sexNo++) {
							if (sex[sexNo].equals(emp.getGender())) {
								genderCB.setSelectedIndex(sexNo);
							}
						}
						contactTF.setText(employDA.employlist().get(0).getPhoneNo());
						deptCB.setSelectedItem(deptDA.getDepartment(emp.getDept().getDeptcode()));
						positionCB.setSelectedItem(jobDA.getJob(emp.getJob().getJobcode()));
					}
				} else {
					num = employDA.getEmployeeNumber(empidTF.getText());
					emp = searchEmployeeByDeptProj(userInput, deptFilt.getSelectedItem().toString(),
							projFilt.getSelectedItem().toString());
					if (emp.getEmpID() == null) {
						JOptionPane.showMessageDialog(null, "No Employee Listed", "Form Incomplete",
								JOptionPane.ERROR_MESSAGE);
						num=0;
					} else {
						num = employDA.getEmployeeNumber(empidTF.getText());
						empidTF.setText(emp.getEmpID());
						firstnametf.setText(emp.getFirstname());
						lastnametf.setText(emp.getLastname());
						midinitTF.setText(emp.getMidInit());
						for (int sexNo = 1; sexNo < 3; sexNo++) {
							if (sex[sexNo].equals(emp.getGender())) {
								genderCB.setSelectedIndex(sexNo);
							}
						}
						contactTF.setText(employDA.employlist().get(0).getPhoneNo());
						deptCB.setSelectedItem(emp.getDept().getDeptcode());
						positionCB.setSelectedItem(emp.getJob().getJobcode());
					}
				}
				}
			}
		});

		contactTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				searchText = (JTextField) arg0.getSource();
				Employee emp = new Employee();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "1234567890";
				if (userInput.length() < 12) {
					if (userInput.startsWith("09")) {
						for (int i = 0; i < userInput.length(); i++)

							if (!(vowels.contains("" + word[i]))) {
								searchText
										.setText(searchText.getText().substring(0, searchText.getText().length() - 1));
							}
					} else {
						JOptionPane.showMessageDialog(null,
								"Your Contact Number should start in 09.\nPlease fill up correctly.",
								"Contact Number Error", JOptionPane.ERROR_MESSAGE);
						searchText.setText("09");
					}
				} else {

					searchText.setText(searchText.getText().substring(0, searchText.getText().length() - 1));
					searchText.setText("09");
					JOptionPane.showMessageDialog(null,
							"The number of your contact number is has exceeded.\nPlease fill up correctly.",
							"Contact Number Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		firstnametf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				firstnametf = (JTextField) arg0.getSource();
				Employee emp = new Employee();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÒ— ";

				for (int i = 0; i < userInput.length(); i++){
					if (!(vowels.contains("" + word[i]))) {
						firstnametf.setText(firstnametf.getText().substring(0, firstnametf.getText().length() - 1));
					}
				}
				if(firstnametf.getText().length()>50)
				{
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only","Input Error",JOptionPane.ERROR_MESSAGE);
					firstnametf.setText(null);
				}
			}

		});
		lastnametf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				lastnametf = (JTextField) arg0.getSource();
				Employee emp = new Employee();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÒ— ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						lastnametf.setText(lastnametf.getText().substring(0, lastnametf.getText().length() - 1));
					}
				if(lastnametf.getText().length()>50)
				{
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only","Input Error",JOptionPane.ERROR_MESSAGE);
					lastnametf.setText(null);
				}
			}

		});
		midinitTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				midinitTF = (JTextField) arg0.getSource();
				Employee emp = new Employee();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ— ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						midinitTF.setText(midinitTF.getText().substring(0, midinitTF.getText().length() - 1));
					}
			}

		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeDA employDA = new EmployeeDA(connection);

				int rowno = employDA.EmployeeList().size();
				String name = "";
				tableMod.setRowCount(rowno);

				for (int ins = 0; ins < rownum; ins++) {
					table.setValueAt(employDA.employlist().get(ins).getEmpID(), ins, 0);
					name = employDA.employlist().get(ins).getFirstname() + " "
							+ employDA.employlist().get(ins).getMidInit() + ". "
							+ employDA.employlist().get(ins).getLastname();
					table.setValueAt(name, ins, 1);
					table.setValueAt(employDA.employlist().get(ins).getGender(), ins, 2);
					table.setValueAt(employDA.employlist().get(ins).getPhoneNo(), ins, 3);
					table.setValueAt(employDA.employlist().get(ins).getDept().getDeptcode(), ins, 4);
					table.setValueAt(employDA.employlist().get(ins).getJob().getJobcode(), ins, 5);
				}
				tableMod.fireTableDataChanged();
			}
		});
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employD = new EmployeeDA(connection);
				employ = new Employee();
				dept = new Department();
				job = new Job();
				String firstname[] = firstnametf.getText().split(" "), properName = "",
						lastname[] = lastnametf.getText().split(" "), lastName = "";
				char fn, ln;
				for (int fnno = 0; fnno < firstname.length; fnno++) {
					for (int charNum = 0; charNum < firstname[fnno].length(); charNum++) {
						if (charNum == 0) {
							fn = firstname[fnno].toUpperCase().charAt(charNum);
							properName += fn;
						} else {
							fn = firstname[fnno].toLowerCase().charAt(charNum);
							properName += fn;
						}
					}
					properName += " ";
				}
				for (int lnno = 0; lnno < firstname.length; lnno++) {
					for (int charNum = 0; charNum < lastname[lnno].length(); charNum++) {
						if (charNum == 0) {
							ln = lastname[lnno].toUpperCase().charAt(charNum);
							lastName += ln;
						} else {
							ln = lastname[lnno].toLowerCase().charAt(charNum);
							lastName += ln;
						}
					}
					lastName += " ";
				}

				lastName = lastName.substring(0, lastName.length() - 1);

				if (firstnametf.getText().equals(null) || lastnametf.getText().equals(null)
						|| contactTF.getText().equals(null) || lastnametf.getText().equals(null)
						|| genderCB.getSelectedItem().toString().equals("") || positionCB.getSelectedIndex() == 0
						|| deptCB.getSelectedIndex() == 0)

				{
					JOptionPane.showMessageDialog(null, "Please Complete the Form", "Form Incomplete",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String code[] = getJobDeptCode(positionCB.getSelectedItem().toString(),
							deptCB.getSelectedItem().toString()).split(" ");
					employ.setFirstname(properName);
					employ.setLastname(lastName);
					employ.setMidInit(midinitTF.getText());
					employ.setGender(genderCB.getSelectedItem().toString());
					employ.setPhoneNo(contactTF.getText());
					dept.setDeptcode(code[1]);
					job.setJobcode(code[0]);
					employD.addEmploy(employ, job.getJobcode(), dept.getDeptcode());
				}
			}
		});
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employDA = new EmployeeDA(connection);
				Employee employ = new Employee();
				Department dept = new Department();
				Job job = new Job();
				String code[] = getJobDeptCode(positionCB.getSelectedItem().toString(),
						deptCB.getSelectedItem().toString()).split(" ");
				employ.setEmpID(empidTF.getText());
				employ.setFirstname(firstnametf.getText());
				employ.setLastname(lastnametf.getText());
				employ.setMidInit(midinitTF.getText());
				employ.setGender(genderCB.getSelectedItem().toString());
				employ.setPhoneNo(contactTF.getText());
				dept.setDeptDesc(code[1]);
				job.setJobDesc(code[0]);
				employDA.editEmploy(employ, job.getJobDesc(), dept.getDeptDesc());
			}
		});
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employeDA = new EmployeeDA(connection);
				employeDA.deleteEmploy(empidTF.getText());
			}
		});
		Retrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee employ = new Employee();
				EmployeeDA employDA = new EmployeeDA(connection);
				employ.setEmpID(empidTF.getText());
				employDA.retrieveEmploy(employ);
			}
		});
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employDA = new EmployeeDA(connection);
				Employee employ = new Employee();
				DepartmentDA dep = new DepartmentDA(connection);
				JobDA work = new JobDA(connection);
				num++;
				if (num == employDA.employlist().size()) {
					num = employDA.employlist().size() - 1;
				} else {
					empidTF.setText(employDA.employlist().get(num).getEmpID());
					firstnametf.setText(employDA.employlist().get(num).getFirstname());
					lastnametf.setText(employDA.employlist().get(num).getLastname());
					midinitTF.setText(employDA.employlist().get(num).getMidInit());
					for (int sexNo = 1; sexNo < 3; sexNo++) {
						if (sex[sexNo].equals(employDA.employlist().get(num).getGender())) {
							genderCB.setSelectedIndex(sexNo);
						}
					}
					for (int depno = 0; depno < dep.deptlist().size(); depno++) {
						if (dep.deptlist().get(depno).getDeptcode()
								.equals(employDA.employlist().get(num).getDept().getDeptcode())) {
							deptCB.setSelectedIndex(depno + 1);
						}
					}
					for (int jobno = 0; jobno < work.joblist().size(); jobno++) {
						if (work.joblist().get(jobno).getJobcode()
								.equals(employDA.employlist().get(num).getJob().getJobcode())) {
							positionCB.setSelectedIndex(jobno + 1);
						}
					}
					contactTF.setText(employDA.employlist().get(num).getPhoneNo());
				}
			}
		});
		First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeDA employDA = new EmployeeDA(connection);
				Employee employ = new Employee();
				DepartmentDA dep = new DepartmentDA(connection);
				JobDA work = new JobDA(connection);
				num = 0;
				empidTF.setText(employDA.employlist().get(num).getEmpID());
				firstnametf.setText(employDA.employlist().get(num).getFirstname());
				lastnametf.setText(employDA.employlist().get(num).getLastname());
				midinitTF.setText(employDA.employlist().get(num).getMidInit());
				for (int sexNo = 1; sexNo < 3; sexNo++) {
					if (sex[sexNo].equals(employDA.employlist().get(num).getGender())) {
						genderCB.setSelectedIndex(sexNo);
					}
				}
				for (int depno = 0; depno < dep.deptlist().size(); depno++) {
					if (dep.deptlist().get(depno).getDeptcode()
							.equals(employDA.employlist().get(num).getDept().getDeptcode())) {
						deptCB.setSelectedIndex(depno + 1);

					}
				}
				for (int jobno = 0; jobno < work.joblist().size(); jobno++) {
					if (work.joblist().get(jobno).getJobcode()
							.equals(employDA.employlist().get(num).getJob().getJobcode())) {
						positionCB.setSelectedIndex(jobno + 1);
					}
				}
				contactTF.setText(employDA.employlist().get(0).getPhoneNo());

			}
		});
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employDA = new EmployeeDA(connection);
				Employee employ = new Employee();
				DepartmentDA dep = new DepartmentDA(connection);
				JobDA work = new JobDA(connection);
				num--;
				if (num == -1) {
					num = 0;
				} else {
					empidTF.setText(employDA.employlist().get(num).getEmpID());
					firstnametf.setText(employDA.employlist().get(num).getFirstname());
					lastnametf.setText(employDA.employlist().get(num).getLastname());
					midinitTF.setText(employDA.employlist().get(num).getMidInit());
					for (int sexNo = 1; sexNo < 3; sexNo++) {
						if (sex[sexNo].equals(employDA.employlist().get(num).getGender())) {
							genderCB.setSelectedIndex(sexNo);
						}
					}
					for (int depno = 0; depno < dep.deptlist().size(); depno++) {
						if (dep.deptlist().get(depno).getDeptcode()
								.equals(employDA.employlist().get(num).getDept().getDeptcode())) {
							deptCB.setSelectedIndex(depno + 1);
						}
					}
					for (int jobno = 0; jobno < work.joblist().size(); jobno++) {
						if (work.joblist().get(jobno).getJobcode()
								.equals(employDA.employlist().get(num).getJob().getJobcode())) {
							positionCB.setSelectedIndex(jobno + 1);
						}
					}
					contactTF.setText(employDA.employlist().get(num).getPhoneNo());
				}
			}
		});
		Last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDA employDA = new EmployeeDA(connection);
				Employee employ = new Employee();
				DepartmentDA dep = new DepartmentDA(connection);
				JobDA work = new JobDA(connection);
				num = employDA.employlist().size() - 1;
				empidTF.setText(employDA.employlist().get(num).getEmpID());
				firstnametf.setText(employDA.employlist().get(num).getFirstname());
				lastnametf.setText(employDA.employlist().get(num).getLastname());
				midinitTF.setText(employDA.employlist().get(num).getMidInit());
				for (int sexNo = 1; sexNo < 3; sexNo++) {
					if (sex[sexNo].equals(employDA.employlist().get(num).getGender())) {
						genderCB.setSelectedIndex(sexNo);
					}
				}
				for (int depno = 0; depno < dep.deptlist().size(); depno++) {
					if (dep.deptlist().get(depno).getDeptcode()
							.equals(employDA.employlist().get(num).getDept().getDeptcode())) {
						deptCB.setSelectedIndex(depno + 1);
					}
				}
				for (int jobno = 0; jobno < work.joblist().size(); jobno++) {
					if (work.joblist().get(jobno).getJobcode()
							.equals(employDA.employlist().get(num).getJob().getJobcode())) {
						positionCB.setSelectedIndex(jobno + 1);
					}
				}
				contactTF.setText(employDA.employlist().get(num).getPhoneNo());
			}
		});
	}

	public Employee searchEmployee(String searchText) {
		Employee employ = new Employee();
		for (Employee emp : employDA.employlist()) {
			if (emp.getFirstname().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getLastname().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getMidInit().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getEmpID().toLowerCase().contains(searchText.toLowerCase())) {
				employ.setEmpID(emp.getEmpID());
				employ.setFirstname(emp.getFirstname());
				employ.setLastname(emp.getLastname());
				employ.setMidInit(emp.getMidInit());
				employ.setGender(emp.getGender());
				employ.setJob(emp.getJob());
				employ.setDept(emp.getDept());
				employ.setPhoneNo(emp.getPhoneNo());
				break;
			}
		}
		return employ;
	}

	public Employee searchEmployeeByDeptProj(String searchText, String dept, String pos) {
		Employee employ = new Employee();
		for (Employee emp : employDA.EmployeeByDeptProj(dept, pos)) {
			if (emp.getFirstname().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getLastname().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getMidInit().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getEmpID().toLowerCase().contains(searchText.toLowerCase())) {

				employ.setEmpID(emp.getEmpID());
				employ.setFirstname(emp.getFirstname());
				employ.setLastname(emp.getLastname());
				employ.setMidInit(emp.getMidInit());
				employ.setGender(emp.getGender());
				employ.setJob(emp.getJob());
				employ.setDept(emp.getDept());
				employ.setPhoneNo(emp.getPhoneNo());
				break;
			}
		}
		return employ;
	}

	public Employee searchEmployeeByDept(String searchText, String dept) {
		Employee employ = new Employee();
		
		String name[];
		for (Employee emp : employDA.EmployeeByDept(dept)) {
			if (emp.getFirstname().toLowerCase().contains(searchText.toLowerCase())
					|| emp.getEmpID().toLowerCase().contains(searchText.toLowerCase())) {
				name = emp.getFirstname().split("_");
				employ.setEmpID(emp.getEmpID());
				employ.setFirstname(name[0]);
				employ.setLastname(name[2]);
				employ.setMidInit(name[1]);
				employ.setGender(emp.getGender());
				employ.setJob(emp.getJob());
				employ.setDept(emp.getDept());
				employ.setPhoneNo(emp.getPhoneNo());
				break;
			}
		}
		return employ;
	}

	public String getJobDeptCode(String job, String dept) {

		String dc = "", jcode = "";
		for (Department dep : deptDA.deptlist()) {
			if (dep.getDeptDesc().equals(dept)) {
				dc = dep.getDeptcode();
			}

		}
		for (Job dep : jobDA.joblist()) {
			if (dep.getJobDesc().equals(job)) {
				jcode = dep.getJobcode();

			}

		}
		return jcode + " " + dc;
	}
}
