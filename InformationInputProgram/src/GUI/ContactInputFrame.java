package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import logic.Contact;
import logic.ContactDAO;
import logic.ContactFactory;
import logic.Util;

public class ContactInputFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtAge;
	private JTextField txtCellPhone;
	private JTable tblOutput;
	private DefaultTableModel model;
	private ContactDAO list;
	private static int counter = 1;
	
	/**
	 * Create the frame.
	 */
	public ContactInputFrame() {
		setTitle("Enter Contacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 497);
		list = new ContactDAO();
		model = Util.CreateTableHeader(new String[] { "ID", "First Name",
				"Last Name", "Email", "Age", "Cell Phone" });
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSave = new JMenu("Save");
		menuBar.add(mnSave);

		JMenuItem mntmSvToFile = new JMenuItem("To File");
		mntmSvToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.saveDataToFile("contacts.txt");
			}
		});
		mnSave.add(mntmSvToFile);

		JMenu mnLoad = new JMenu("Load");
		menuBar.add(mnLoad);

		JMenuItem mntmLdFromFile = new JMenuItem("From File");
		mntmLdFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				list.readDataFromFile("contacts.txt");
				for (Contact obj : list.GetAll()) {
					model.addRow(new Object[] { obj.getiD(),
							obj.getFirstName(), obj.getLastName(),
							obj.getEmailAddress(), obj.getAge(),
							obj.getCellPhone()

					});
				}
			}
		});
		mnLoad.add(mntmLdFromFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 22, 76, 14);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 100, 76, 14);
		contentPane.add(lblLastName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 256, 76, 14);
		contentPane.add(lblAge);

		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(10, 178, 140, 14);
		contentPane.add(lblEmailAddress);

		JLabel lblCellPhone = new JLabel("Cell Phone");
		lblCellPhone.setBounds(10, 334, 107, 14);
		contentPane.add(lblCellPhone);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(10, 58, 154, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(10, 136, 154, 20);
		contentPane.add(txtLastName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 214, 154, 20);
		contentPane.add(txtEmail);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(10, 292, 154, 20);
		contentPane.add(txtAge);

		txtCellPhone = new JTextField();
		txtCellPhone.setColumns(10);
		txtCellPhone.setBounds(10, 370, 154, 20);
		contentPane.add(txtCellPhone);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 28, 600, 396);
		contentPane.add(scrollPane);

		tblOutput = new JTable(model);
		tblOutput.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblOutput);

		TableColumnModel tcm = tblOutput.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(5); // sets width of first name
		tcm.getColumn(1).setPreferredWidth(35); // sets width of first name
		tcm.getColumn(2).setPreferredWidth(35); // sets width of last name
		tcm.getColumn(3).setPreferredWidth(100); // sets width of email
		tcm.getColumn(4).setPreferredWidth(8); // sets width of age column
		tcm.getColumn(5).setPreferredWidth(35); // sets width of phone number
												// column

		JButton btnAddRecord = new JButton("Add Contact");
		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String fName = txtFirstName.getText();
				String lName = txtLastName.getText();
				String email = txtEmail.getText();
				String stAge = txtAge.getText();
				String cPhone = txtCellPhone.getText();

				int age = Integer.parseInt(stAge);

				if (!Util.isValidInt(age, 0, 120)) { // exclamation means not
														// true
					JOptionPane.showMessageDialog(null,
							"Please Enter a valid age between 0 and 120",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Contact newObj = ContactFactory.Create(counter++, fName, lName,
						email, age, cPhone);
				list.AddContact(newObj);
				model.setRowCount(0); // resets row count

				for (Contact obj : list.GetAll()) {
					model.addRow(new Object[] { obj.getiD(),
							obj.getFirstName(), obj.getLastName(),
							obj.getEmailAddress(), obj.getAge(),
							obj.getCellPhone()

					});
				}
			}
		});
		btnAddRecord.setBounds(10, 401, 140, 23);
		contentPane.add(btnAddRecord);
	}
}
