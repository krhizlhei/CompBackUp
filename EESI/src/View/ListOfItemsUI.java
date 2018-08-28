package View;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import Model.*;
import Controller.*;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import Controller.ComputerDA;
import Controller.DevicesDA;
import Controller.ListOfItemsDA;

import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ListOfItemsUI extends JPanel {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField searchTF;
	private JTextField unitidTF;
	private JTextField brandTF;
	private JTextField modelTF;
	private JTextField serialnoTF;
	private JTextField scTF;
	private JTextField hddTF;
	private JTextField romTF;
	private JTextField vramTF;
	private JTextField ramTF;
	private JTextField processorTF;
	String[] TypeFilter = { "All", "Desktop", "Laptop" }, LOIS = { "Available", "Not Available" },
			options = { "Yes", "No", "Cancel" }, ctype = { "", "Desktop", "Laptop" };
	int loiNum;
	private JTextField itemidTF;
	private JTextField displayTF;
	private JTextField ramInfoTF;
	public Connection connection;
	private JTextField pcidTF;
	private JTextField capacityTF;
	public ListOfItemsDA loiDA;
	public DevicesDA devDA;
	public ComputerDA comDA;
	private JTextField itemTypeTF;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ListOfItemsUI frame = new ListOfItemsUI(connection);
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
	public ListOfItemsUI(Connection connection) {
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

		JLabel lblUnit = new JLabel("Unit  :");
		lblUnit.setBounds(92, 189, 35, 14);
		add(lblUnit);

		JLabel lblBrand = new JLabel("Brand :");
		lblBrand.setBounds(85, 218, 46, 24);
		add(lblBrand);

		JLabel lblModel = new JLabel("Model :");
		lblModel.setBounds(658, 257, 46, 25);
		add(lblModel);

		JLabel lblSerialNo = new JLabel("Serial No. :");
		lblSerialNo.setBounds(354, 263, 70, 14);
		add(lblSerialNo);

		JLabel lblLoiStatus = new JLabel("LOI Status :");
		lblLoiStatus.setBounds(64, 376, 67, 24);
		add(lblLoiStatus);

		JLabel lblStorageCapacity = new JLabel("Storage Capacity :");
		lblStorageCapacity.setBounds(318, 294, 103, 14);
		add(lblStorageCapacity);

		JLabel lblRam = new JLabel("RAM :");
		lblRam.setBounds(96, 294, 35, 14);
		add(lblRam);

		JLabel lblDescription = new JLabel("Item Description :");
		lblDescription.setBounds(75, 440, 114, 14);
		add(lblDescription);

		JLabel lblVram = new JLabel("VRAM :");
		lblVram.setBounds(658, 326, 46, 14);
		add(lblVram);

		JLabel lblProcessor = new JLabel("Processor :");
		lblProcessor.setBounds(64, 326, 67, 14);
		add(lblProcessor);

		JLabel lblHdd = new JLabel("HDD : ");
		lblHdd.setBounds(388, 381, 55, 14);
		add(lblHdd);

		JLabel lblRom = new JLabel("ROM :");
		lblRom.setBounds(388, 329, 55, 14);
		add(lblRom);

		JLabel lblListOfItems = new JLabel("ITEM");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 58));
		lblListOfItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfItems.setBounds(12, 12, 900, 47);
		add(lblListOfItems);

		JLabel lblSearch = new JLabel("Search :");
		lblSearch.setBounds(64, 134, 55, 16);
		add(lblSearch);

		searchTF = new JTextField();
		searchTF.setBounds(129, 134, 114, 28);
		add(searchTF);
		searchTF.setColumns(10);

		unitidTF = new JTextField();
		unitidTF.setEditable(false);
		unitidTF.setBounds(145, 186, 114, 28);
		add(unitidTF);
		unitidTF.setColumns(10);

		brandTF = new JTextField();
		brandTF.setBounds(145, 220, 114, 28);
		add(brandTF);
		brandTF.setColumns(10);

		modelTF = new JTextField();
		modelTF.setBounds(722, 257, 114, 28);
		add(modelTF);
		modelTF.setColumns(10);

		serialnoTF = new JTextField();
		serialnoTF.setBounds(438, 255, 114, 28);
		add(serialnoTF);
		serialnoTF.setColumns(10);

		scTF = new JTextField();
		scTF.setBounds(438, 291, 114, 28);
		add(scTF);
		scTF.setColumns(10);

		hddTF = new JTextField();
		hddTF.setBounds(438, 378, 114, 28);
		add(hddTF);
		hddTF.setColumns(10);

		romTF = new JTextField();
		romTF.setBounds(438, 323, 114, 28);
		add(romTF);
		romTF.setColumns(10);

		vramTF = new JTextField();
		vramTF.setBounds(722, 320, 114, 28);
		add(vramTF);
		vramTF.setColumns(10);

		ramTF = new JTextField();
		ramTF.setBounds(145, 294, 114, 28);
		add(ramTF);
		ramTF.setColumns(10);

		processorTF = new JTextField();
		processorTF.setBounds(145, 323, 114, 28);
		add(processorTF);
		processorTF.setColumns(10);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(206, 551, 98, 26);
		add(btnAdd);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(316, 551, 98, 26);
		add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(426, 551, 98, 26);
		add(btnDelete);

		JButton First = new JButton("<<");
		First.setBounds(258, 512, 98, 26);
		add(First);

		JButton Back = new JButton("<");
		Back.setBounds(368, 512, 98, 26);
		add(Back);

		JButton Next = new JButton(">");
		Next.setBounds(478, 512, 98, 26);
		add(Next);

		JButton Last = new JButton(">>");
		Last.setBounds(587, 512, 98, 26);
		add(Last);

		JRadioButton textAreaRB = new JRadioButton("");
		textAreaRB.setBackground(Color.GRAY);
		textAreaRB.setBounds(195, 440, 20, 28);
		add(textAreaRB);

		JList searchlist = new JList();
		searchTF.add(searchlist);

		JScrollPane spList = new JScrollPane(searchlist);
		
		JTextArea descTA = new JTextArea();
		descTA.setBounds(227, 440, 458, 60);
		JScrollPane spTextArea = new JScrollPane(descTA);
		spTextArea.setBounds(227, 440, 458, 60);
		add(spList);
		add(spTextArea);
		
		JLabel lblItem = new JLabel("Item :");
		lblItem.setBounds(669, 192, 35, 16);
		add(lblItem);

		itemidTF = new JTextField();
		itemidTF.setEditable(false);
		itemidTF.setBounds(722, 190, 114, 28);
		add(itemidTF);
		itemidTF.setColumns(10);

		JComboBox LOIStatCB = new JComboBox(LOIS);
		LOIStatCB.setBounds(152, 378, 130, 24);
		add(LOIStatCB);

		JLabel lblDisplay = new JLabel("Display :");
		lblDisplay.setBounds(82, 259, 49, 21);
		add(lblDisplay);

		displayTF = new JTextField();
		displayTF.setBounds(145, 261, 114, 28);
		add(displayTF);
		displayTF.setColumns(10);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		add(desktopPane);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBounds(535, 551, 98, 26);
		add(btnClear);

		ramInfoTF = new JTextField();
		ramInfoTF.setBounds(722, 291, 114, 28);
		add(ramInfoTF);
		ramInfoTF.setColumns(10);

		JLabel lblRamInfo = new JLabel("RAM Info :");
		lblRamInfo.setBounds(643, 290, 61, 16);
		add(lblRamInfo);

		pcidTF = new JTextField();
		pcidTF.setEditable(false);
		pcidTF.setBounds(438, 188, 114, 28);
		add(pcidTF);
		pcidTF.setColumns(10);

		capacityTF = new JTextField();
		capacityTF.setBounds(722, 380, 114, 28);
		add(capacityTF);
		capacityTF.setColumns(10);

		JLabel lblCapacity = new JLabel("Capacity :");
		lblCapacity.setBounds(649, 382, 55, 16);
		add(lblCapacity);

		JLabel lblItemType = new JLabel("Item Type :");
		lblItemType.setBounds(637, 220, 67, 16);
		add(lblItemType);

		JLabel lblComputer = new JLabel("Computer :");
		lblComputer.setBounds(354, 188, 67, 16);
		add(lblComputer);

		JLabel lblComputerType = new JLabel("Computer Type :");
		lblComputerType.setBounds(329, 222, 92, 16);
		add(lblComputerType);

		JComboBox comTypeCB = new JComboBox(ctype);
		comTypeCB.setBounds(438, 218, 114, 25);
		add(comTypeCB);

		itemTypeTF = new JTextField();
		itemTypeTF.setBounds(722, 218, 114, 28);
		add(itemTypeTF);
		itemTypeTF.setColumns(10);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemListUI itemList=new ItemListUI(connection);
				itemList.setVisible(true);
			}
		});
		btnView.setBounds(645, 550, 90, 28);
		add(btnView);

		searchTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				searchTF = (JTextField) arg0.getSource();
				loiDA = new ListOfItemsDA(connection);
				String add = "";
				ListofItems loi = new ListofItems();
				loi = loiDA.searchLOI(searchTF.getText());

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";

				if (searchTF.getText().equals("") || searchTF.getText().equals(" ")) {
					unitidTF.setText(null);
					brandTF.setText(null);
					modelTF.setText(null);
					serialnoTF.setText(null);
					hddTF.setText(null);
					romTF.setText(null);
					vramTF.setText(null);
					ramTF.setText(null);
					processorTF.setText(null);
					displayTF.setText(null);
					scTF.setText(null);
					ramInfoTF.setText(null);
					itemTypeTF.setText(null);
					capacityTF.setText(null);
					itemidTF.setText(null);
					descTA.setText(null);
					pcidTF.setText(null);
					comTypeCB.setSelectedIndex(0);
					LOIStatCB.setSelectedIndex(0);
				} else {
					for (int i = 0; i < userInput.length(); i++) {
						if (!(vowels.contains("" + word[i]))) {
							searchTF.setText(searchTF.getText().substring(0, searchTF.getText().length() - 1));
						} else {
							continue;
						}
					}

					if (loi.gUnitCode() == null) {
						JOptionPane.showMessageDialog(null, "No list", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							unitidTF.setText(loi.gUnitCode());
							brandTF.setText(loi.gBrand());
							modelTF.setText(loi.gModel());
							serialnoTF.setText(loi.gSerialNo());
							hddTF.setText(loi.gCom().gHDD());
							romTF.setText(loi.gCom().gROM());
							vramTF.setText(loi.gCom().gVRAM());
							ramTF.setText(loi.gCom().gRAM());
							processorTF.setText(loi.gCom().gProcessor());
							displayTF.setText(loi.gCom().gDisplay());
							scTF.setText(loi.gSCInfo());
							ramInfoTF.setText(loi.gRAMInfo());
							itemTypeTF.setText(loi.gDev().gItemType());
							capacityTF.setText(loi.gDev().gCapacity());
							itemidTF.setText(loi.gDev().gItemID());
							pcidTF.setText(loi.gCom().gPCID());
							comTypeCB.setSelectedItem(loi.gCom().gCType());
							descTA.setText(loi.gDev().gDescription());
							LOIStatCB.setSelectedItem(loi.gLOIStatus());
						} else {
							unitidTF.setText(loi.gUnitCode());
							brandTF.setText(loi.gBrand());
							modelTF.setText(loi.gModel());
							serialnoTF.setText(loi.gSerialNo());
							hddTF.setText(loi.gCom().gHDD());
							romTF.setText(loi.gCom().gROM());
							vramTF.setText(loi.gCom().gVRAM());
							ramTF.setText(loi.gCom().gRAM());
							processorTF.setText(loi.gCom().gProcessor());
							displayTF.setText(loi.gCom().gDisplay());
							scTF.setText(loi.gSCInfo());
							ramInfoTF.setText(loi.gRAMInfo());
							itemTypeTF.setText(loi.gDev().gItemType());
							capacityTF.setText(loi.gDev().gCapacity());
							itemidTF.setText(loi.gDev().gItemID());
							pcidTF.setText(loi.gCom().gPCID());
							comTypeCB.setSelectedItem(loi.gCom().gCType());
							descTA.setText(loi.gDev().gDescription());
							LOIStatCB.setSelectedItem(loi.gLOIStatus());
						}
					}
				}
			}
		});

		comTypeCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JComboBox comTypeCB = new JComboBox();
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					int comType = ((JComboBox) arg0.getSource()).getSelectedIndex();
					if (comType == 1 || comType == 2) {
						itemTypeTF.setEditable(false);
						capacityTF.setEditable(false);
						descTA.setEditable(false);
					} else {
						itemTypeTF.setEditable(true);
						capacityTF.setEditable(true);
						descTA.setEditable(true);
					}
				}

			}
		});
		textAreaRB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (textAreaRB.isSelected()) {
					descTA.setEditable(true);
				} else {
					descTA.setEditable(false);
				}
			}
		});
		itemTypeTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				itemTypeTF = (JTextField) arg0.getSource();

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

				if (itemTypeTF.getText().equals("") || itemTypeTF.getText().equals(null)) {
					hddTF.setEditable(true);
					romTF.setEditable(true);
					vramTF.setEditable(true);
					ramTF.setEditable(true);
					processorTF.setEditable(true);
					displayTF.setEditable(true);
					comTypeCB.setEnabled(true);

				} else {
					for (int i = 0; i < userInput.length(); i++) {
						if (vowels.contains("" + word[i])) {
							hddTF.setEditable(false);
							romTF.setEditable(false);
							vramTF.setEditable(false);
							ramTF.setEditable(false);
							processorTF.setEditable(false);
							displayTF.setEditable(false);
							comTypeCB.setEnabled(false);
						}
					}
				}
			}
		});

		First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loiDA = new ListOfItemsDA(connection);
				loiNum = 0;
				String add = "";

				unitidTF.setText(loiDA.ListOfItems().get(loiNum).gUnitCode());
				brandTF.setText(loiDA.ListOfItems().get(loiNum).gBrand());
				modelTF.setText(loiDA.ListOfItems().get(loiNum).gModel());
				serialnoTF.setText(loiDA.ListOfItems().get(loiNum).gSerialNo());
				hddTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gHDD());
				romTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gROM());
				vramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gVRAM());
				ramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gRAM());
				processorTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gProcessor());
				displayTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gDisplay());
				scTF.setText(loiDA.ListOfItems().get(loiNum).gSCInfo());
				ramInfoTF.setText(loiDA.ListOfItems().get(loiNum).gRAMInfo());
				itemTypeTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemType());
				capacityTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gCapacity());
				itemidTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemID());
				pcidTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gPCID());
				comTypeCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gCom().gCType());
				descTA.setText(loiDA.ListOfItems().get(loiNum).gDev().gDescription());
				LOIStatCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gLOIStatus());
			}
		});
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loiDA = new ListOfItemsDA(connection);
				String add = "";
				loiNum--;
				if (loiNum == -1) {
					loiNum = 0;
				} else {

					unitidTF.setText(loiDA.ListOfItems().get(loiNum).gUnitCode());
					brandTF.setText(loiDA.ListOfItems().get(loiNum).gBrand());
					modelTF.setText(loiDA.ListOfItems().get(loiNum).gModel());
					serialnoTF.setText(loiDA.ListOfItems().get(loiNum).gSerialNo());
					hddTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gHDD());
					romTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gROM());
					vramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gVRAM());
					ramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gRAM());
					processorTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gProcessor());
					displayTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gDisplay());
					scTF.setText(loiDA.ListOfItems().get(loiNum).gSCInfo());
					ramInfoTF.setText(loiDA.ListOfItems().get(loiNum).gRAMInfo());
					itemTypeTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemType());
					capacityTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gCapacity());
					itemidTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemID());
					pcidTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gPCID());
					comTypeCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gCom().gCType());
					descTA.setText(loiDA.ListOfItems().get(loiNum).gDev().gDescription());
					LOIStatCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gLOIStatus());
				}

			}
		});
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loiDA = new ListOfItemsDA(connection);
				String add = "";
				loiNum++;

				if (loiNum == loiDA.ListOfItems().size()) {
					loiNum = loiDA.ListOfItems().size() - 1;
				} else {

					unitidTF.setText(loiDA.ListOfItems().get(loiNum).gUnitCode());
					brandTF.setText(loiDA.ListOfItems().get(loiNum).gBrand());
					modelTF.setText(loiDA.ListOfItems().get(loiNum).gModel());
					serialnoTF.setText(loiDA.ListOfItems().get(loiNum).gSerialNo());
					hddTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gHDD());
					romTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gROM());
					vramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gVRAM());
					ramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gRAM());
					processorTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gProcessor());
					displayTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gDisplay());
					scTF.setText(loiDA.ListOfItems().get(loiNum).gSCInfo());
					ramInfoTF.setText(loiDA.ListOfItems().get(loiNum).gRAMInfo());
					itemTypeTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemType());
					capacityTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gCapacity());
					itemidTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemID());
					pcidTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gPCID());
					comTypeCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gCom().gCType());
					descTA.setText(loiDA.ListOfItems().get(loiNum).gDev().gDescription());
					LOIStatCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gLOIStatus());
				}
			}
		});
		Last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loiDA = new ListOfItemsDA(connection);
				String add = "";

				loiNum = loiDA.ListOfItems().size() - 1;
				unitidTF.setText(loiDA.ListOfItems().get(loiNum).gUnitCode());
				brandTF.setText(loiDA.ListOfItems().get(loiNum).gBrand());
				modelTF.setText(loiDA.ListOfItems().get(loiNum).gModel());
				serialnoTF.setText(loiDA.ListOfItems().get(loiNum).gSerialNo());
				hddTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gHDD());
				romTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gROM());
				vramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gVRAM());
				ramTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gRAM());
				processorTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gProcessor());
				displayTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gDisplay());
				scTF.setText(loiDA.ListOfItems().get(loiNum).gSCInfo());
				ramInfoTF.setText(loiDA.ListOfItems().get(loiNum).gRAMInfo());
				itemTypeTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemType());
				capacityTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gCapacity());
				itemidTF.setText(loiDA.ListOfItems().get(loiNum).gDev().gItemID());
				pcidTF.setText(loiDA.ListOfItems().get(loiNum).gCom().gPCID());
				comTypeCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gCom().gCType());
				descTA.setText(loiDA.ListOfItems().get(loiNum).gDev().gDescription());
				LOIStatCB.setSelectedItem(loiDA.ListOfItems().get(loiNum).gLOIStatus());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListofItems loi = new ListofItems();
				Devices dev = new Devices();
				Computer com = new Computer();
				loiDA = new ListOfItemsDA(connection);
				comDA = new ComputerDA(connection);
				devDA = new DevicesDA(connection);

				if (brandTF.getText().equals(null) || modelTF.getText().equals(null) || ramInfoTF.getText().equals(null)
						|| scTF.getText().equals(null) || serialnoTF.getText().equals(null)
						|| capacityTF.getText().equals(null) || itemTypeTF.getText().equals(null)
						|| ramTF.getText().equals(null) || displayTF.getText().equals(null)
						|| hddTF.getText().equals(null) || romTF.getText().equals(null) || vramTF.getText().equals(null)
						|| comTypeCB.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Fill-Up all!!!", "Fill Up", JOptionPane.ERROR_MESSAGE);
				}else{
					if (comTypeCB.getSelectedIndex() == 1 || comTypeCB.getSelectedIndex() == 2) {
						com.sDisplay(displayTF.getText());
						com.sHDD(hddTF.getText());
						com.sProcessor(processorTF.getText());
						com.sRAM(ramTF.getText());
						com.sROM(romTF.getText());
						com.sVRAM(vramTF.getText());
						com.sCType(comTypeCB.getSelectedItem().toString());
						comDA.addComp(com);
						com.sPCID(comDA.getPCID());
						loi.sUnitCode(comTypeCB.getSelectedItem().toString().substring(0, 1));
					} else {
						dev.sCapacity(capacityTF.getText());
						dev.sItemType(itemTypeTF.getText());
						dev.sDescription(descTA.getText());
						devDA.addDevice(dev);
						dev.sItemID(devDA.getItemID());

						loi.sUnitCode(itemTypeTF.getText().substring(0, 3));
					}
					loi.sBrand(brandTF.getText());
					loi.sLOIStatus(LOIStatCB.getSelectedItem().toString());
					loi.sModel(modelTF.getText());
					loi.sRAMInfo(ramInfoTF.getText());
					loi.sSCInfo(scTF.getText());
					loi.sSerialNo(serialnoTF.getText());
					loi.sCom(com);
					loi.sDev(dev);
					loiDA.addLOI(loi);
					JOptionPane.showMessageDialog(null, "New Item has been Added!!!", "New Item", JOptionPane.PLAIN_MESSAGE);
					
				}

			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListofItems loi = new ListofItems();
				Devices dev = new Devices();
				Computer com = new Computer();
				loiDA = new ListOfItemsDA(connection);
				devDA = new DevicesDA(connection);
				comDA = new ComputerDA(connection);
				String item = "";

				loi.sBrand(brandTF.getText());
				loi.sLOIStatus(LOIStatCB.getSelectedItem().toString());
				loi.sModel(modelTF.getText());
				loi.sRAMInfo(ramInfoTF.getText());
				loi.sSCInfo(scTF.getText());
				loi.sSerialNo(serialnoTF.getText());
				loi.sUnitCode(unitidTF.getText());
				if (comTypeCB.getSelectedIndex() == 1 || comTypeCB.getSelectedIndex() == 2) {
					com.sCType(comTypeCB.getSelectedItem().toString());
					com.sDisplay(displayTF.getText());
					com.sHDD(hddTF.getText());
					com.sPCID(pcidTF.getText());
					com.sProcessor(processorTF.getText());
					com.sRAM(ramTF.getText());
					com.sROM(romTF.getText());
					com.sVRAM(vramTF.getText());
					comDA.editComp(com);
					item = " pcid='" + com.gPCID() + "'";
				} else {
					dev.sItemType(itemTypeTF.getText());
					dev.sItemID(itemidTF.getText());
					dev.sCapacity(capacityTF.getText());
					dev.sDescription(descTA.getText());
					devDA.editDevice(dev);
					item = " itemid='" + dev.gItemID() + "'";
				}
				loi.sCom(com);
				loi.sDev(dev);
				loiDA.editLOI(loi, item);
				JOptionPane.showInternalMessageDialog(null, "Item has been Updated!!!", "Updated Item", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListofItems loi = new ListofItems();
				Devices dev = new Devices();
				Computer com = new Computer();
				loiDA = new ListOfItemsDA(connection);
				devDA = new DevicesDA(connection);
				comDA = new ComputerDA(connection);

				if (itemidTF.getText().equals(null)) {
					com.sPCID(pcidTF.getText());
					comDA.deleteComp(com);
				} else {
					dev.sItemID(itemidTF.getText());
					devDA.deleteDevice(dev);
				}
				loi.sUnitCode(unitidTF.getText());
				loiDA.deleteLOI(loi);
				JOptionPane.showInternalMessageDialog(null, "Item has been Deleted!!!", "Delete Item", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchTF.setText(null);
				unitidTF.setText(null);
				itemidTF.setText(null);
				brandTF.setText(null);
				modelTF.setText(null);
				serialnoTF.setText(null);
				hddTF.setText(null);
				romTF.setText(null);
				vramTF.setText(null);
				ramTF.setText(null);
				processorTF.setText(null);
				displayTF.setText(null);
				scTF.setText(null);
				ramInfoTF.setText(null);
				itemTypeTF.setText(null);
				capacityTF.setText(null);
				descTA.setText(null);
				pcidTF.setText(null);
				comTypeCB.setSelectedIndex(0);
			}
		});

		brandTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						brandTF.setText(brandTF.getText().substring(0, brandTF.getText().length() - 1));
					}
				if (brandTF.getText().length() > 20) {
					JOptionPane.showMessageDialog(null, "Number of input must be 20 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					brandTF.setText(brandTF.getText().substring(0, 20));
				}

			}

		});
		modelTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						modelTF.setText(modelTF.getText().substring(0, modelTF.getText().length() - 1));
					}
				if (modelTF.getText().length() > 40) {
					JOptionPane.showMessageDialog(null, "Number of input must be 40 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					modelTF.setText(modelTF.getText().substring(0, 40));
				}
			}

		});
		serialnoTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {

				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						serialnoTF.setText(serialnoTF.getText().substring(0, serialnoTF.getText().length() - 1));
					}
				if (serialnoTF.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "Number of input must be 15 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					serialnoTF.setText(serialnoTF.getText().substring(0, 15));
				}
			}

		});
		scTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						scTF.setText(scTF.getText().substring(0, scTF.getText().length() - 1));
					}
				if (scTF.getText().length() > 30) {
					JOptionPane.showMessageDialog(null, "Number of input must be 30 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					scTF.setText(scTF.getText().substring(0, 30));
				}
			}

		});
		capacityTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						capacityTF.setText(capacityTF.getText().substring(0, capacityTF.getText().length() - 1));
					}
				if (capacityTF.getText().length() > 10) {
					JOptionPane.showMessageDialog(null, "Number of input must be 10 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					capacityTF.setText(capacityTF.getText().substring(0, 10));
				}
			}

		});
		ramTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						ramTF.setText(ramTF.getText().substring(0, ramTF.getText().length() - 1));
					}
				if (ramTF.getText().length() > 10) {
					JOptionPane.showMessageDialog(null, "Number of input must be 10 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					ramTF.setText(ramTF.getText().substring(0, 10));
				}
			}

		});
		hddTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						hddTF.setText(hddTF.getText().substring(0, hddTF.getText().length() - 1));
					}
				if (hddTF.getText().length() > 10) {
					JOptionPane.showMessageDialog(null, "Number of input must be 10 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					hddTF.setText(hddTF.getText().substring(0, 10));
				}

			}

		});
		romTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						romTF.setText(romTF.getText().substring(0, romTF.getText().length() - 1));
					}
				if (romTF.getText().length() > 10) {
					JOptionPane.showMessageDialog(null, "Number of input must be 10 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					romTF.setText(romTF.getText().substring(0, 10));
				}
			}

		});

		displayTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						displayTF.setText(displayTF.getText().substring(0, displayTF.getText().length() - 1));
					}
				if (displayTF.getText().length() > 50) {
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					displayTF.setText(displayTF.getText().substring(0, 20));
				}

			}

		});
		ramInfoTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						ramInfoTF.setText(ramInfoTF.getText().substring(0, ramInfoTF.getText().length() - 1));
					}
				if (ramInfoTF.getText().length() > 30) {
					JOptionPane.showMessageDialog(null, "Number of input must be 30 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					ramInfoTF.setText(ramInfoTF.getText().substring(0, 20));
				}

			}

		});
		vramTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						vramTF.setText(vramTF.getText().substring(0, vramTF.getText().length() - 1));
					}
				if (vramTF.getText().length() > 20) {
					JOptionPane.showMessageDialog(null, "Number of input must be 20 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					vramTF.setText(vramTF.getText().substring(0, 15));
				}

			}

		});
		processorTF.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						processorTF.setText(processorTF.getText().substring(0, processorTF.getText().length() - 1));
					}
				if (processorTF.getText().length() > 50) {
					JOptionPane.showMessageDialog(null, "Number of input must be 50 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					processorTF.setText(processorTF.getText().substring(0, 15));
				}

			}

		});
		itemTypeTF.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				String userInput = ((JTextField) arg0.getSource()).getText();
				char[] word = userInput.toCharArray();
				String vowels = "QWERTYUIOPLKJHGFDSAMNBVCXZqwertyuioplkjhgfdsamnbvcxz ";

				for (int i = 0; i < userInput.length(); i++)

					if (!(vowels.contains("" + word[i]))) {
						itemTypeTF.setText(itemTypeTF.getText().substring(0, itemTypeTF.getText().length() - 1));
					}
				if (itemTypeTF.getText().length() > 20) {
					JOptionPane.showMessageDialog(null, "Number of input must be 20 only", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					itemTypeTF.setText(itemTypeTF.getText().substring(0, 15));
				}
			}

		});

	}
}
