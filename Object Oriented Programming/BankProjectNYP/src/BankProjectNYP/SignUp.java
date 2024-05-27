package BankProjectNYP;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField idField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static HashMap<String,User> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp(users);
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
	public SignUp(HashMap<String,User> users) {
		SignUp.users = users;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblNewLabel = new JLabel("JavaBank");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(173, 9, 90, 37);
		contentPane.add(lblNewLabel);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(187, 108, 86, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setBounds(187, 139, 86, 20);
		contentPane.add(surnameField);
		surnameField.setColumns(10);
		
		JLabel nameLabel = new JLabel("İsim");
		nameLabel.setBounds(78, 111, 46, 14);
		contentPane.add(nameLabel);
		
		JLabel lblSurname = new JLabel("Soyisim");
		lblSurname.setBounds(78, 142, 46, 14);
		contentPane.add(lblSurname);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(187, 170, 86, 20);
		contentPane.add(idField);
		
		JLabel lblId = new JLabel("İd");
		lblId.setBounds(78, 173, 46, 14);
		contentPane.add(lblId);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(187, 201, 86, 20);
		contentPane.add(usernameField);
		
		JLabel lblSurname_1_1 = new JLabel("Kullanıcı Adı");
		lblSurname_1_1.setBounds(78, 204, 99, 14);
		contentPane.add(lblSurname_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(187, 232, 86, 20);
		contentPane.add(passwordField);
		
		JLabel lblSurname_1_1_1_1 = new JLabel("Şifre");
		lblSurname_1_1_1_1.setBounds(78, 235, 46, 14);
		contentPane.add(lblSurname_1_1_1_1);
		
		JRadioButton customerRadio = new JRadioButton("Müşteri");
		buttonGroup.add(customerRadio);
		customerRadio.setBounds(102, 69, 90, 23);
		contentPane.add(customerRadio);
		
		JRadioButton bankStaffRadio = new JRadioButton("Banka Personeli");
		buttonGroup.add(bankStaffRadio);
		bankStaffRadio.setBounds(232, 69, 121, 23);
		contentPane.add(bankStaffRadio);
		
		Button signUpButton = new Button("Kayıt Ol");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customerRadio.isSelected()) {
					String name = nameField.getText();
					String surname = surnameField.getText();
					String id = idField.getText();
					String username = usernameField.getText();
					String password = new String(passwordField.getPassword());
					if(!password.isEmpty()&&!username.isEmpty()&&!name.isEmpty()&&!surname.isEmpty()&&!id.isEmpty()) {
						if(!users.containsKey(id)) {
							Customer user = new Customer(name,surname,id,username,password);
							users.put(id, user);
							try {
								FileOutputStream f=new FileOutputStream("users.txt",false) ;
						        PrintStream yaz=new PrintStream(f);
						        for(User User : users.values()) {
						        	if(User.type == 1 || User.type == 3)
						        		yaz.println(User.type + ", " + User.name + ", " + User.surname + ", " + User.id + ", " + User.surname + ", " + User.password + ", " + User.balance + ", " + User.dolarBalance + ", " + User.euroBalance + ", " + User.sterlinBalance + ", " + User.waitingCreditCardProcess + ", " + User.waitingCreditProcess + ", " + User.desiredCreditCardLimit + ", " + User.desiredCredit);
						        	else if(User.type == 2) {
						        		BankStaff staff = (BankStaff) User;
						        		yaz.println(staff.type + ", " + staff.name + ", " + staff.surname + ", " + staff.id + ", " + staff.username + ", " + staff.password + ", " + staff.isApproved + ", " + staff.isWaiting);
						        	}						       
					        	}
						        yaz.close();
							} catch (Exception e1) {
							}
							Login.users = users;
							Login login = new Login(users);
							login.setVisible(true);
							dispose();
							WarningPage warning1 = new WarningPage("Kullanıcı başarıyla oluşturuldu!");
							warning1.setVisible(true);
						}
						else {
							WarningPage warning1 = new WarningPage("Farklı bir id numarası seçiniz!");
							warning1.setVisible(true);
						}
					}
					else {
						WarningPage warning1 = new WarningPage("Bütün satırlar doldurulmalıdır!");
						warning1.setVisible(true);
					}
				}
				else if(bankStaffRadio.isSelected()) {
					String name = nameField.getText();
					String surname = surnameField.getText();
					String id = idField.getText();
					String username = usernameField.getText();
					String password = new String(passwordField.getPassword());
					if(!password.isEmpty()&&!username.isEmpty()&&!name.isEmpty()&&!surname.isEmpty()&&!id.isEmpty()) {
						if(!users.containsKey(id)) {
							BankStaff user = new BankStaff(name,surname,id,username,password);
							users.put(id, user);
							try {
								FileOutputStream f=new FileOutputStream("users.txt",false) ;
						        PrintStream yaz=new PrintStream(f);
						        for(User User : users.values()) {
						        	if(User.type == 1 || User.type == 3)
						        		yaz.println(User.type + ", " + User.name + ", " + User.surname + ", " + User.id + ", " + User.surname + ", " + User.password + ", " + User.balance + ", " + User.dolarBalance + ", " + User.euroBalance + ", " + User.sterlinBalance + ", " + User.waitingCreditCardProcess + ", " + User.waitingCreditProcess + ", " + User.desiredCreditCardLimit + ", " + User.desiredCredit);
						        	else if(User.type == 2) {
						        		BankStaff staff = (BankStaff) User;
						        		yaz.println(staff.type + ", " + staff.name + ", " + staff.surname + ", " + staff.id + ", " + staff.username + ", " + staff.password + ", " + staff.isApproved + ", " + staff.isWaiting);
						        	}
						        	
						        }
						        yaz.close();
							} catch (Exception e1) {
							}
							Login.users = users;
							Login login = new Login(users);
							login.setVisible(true);
							dispose();
							WarningPage warning1 = new WarningPage("Başvurunuz başarıyla alındı!");
							warning1.setVisible(true);
						}
						else {
							WarningPage warning1 = new WarningPage("Farklı bir id numarası seçiniz!");
							warning1.setVisible(true);
						}
					}
					else {
						WarningPage warning1 = new WarningPage("Bütün satırlar doldurulmalıdır!");
						warning1.setVisible(true);
					}
				}
				else{
					WarningPage warning = new WarningPage("Lütfen kayıt olmak istediğiniz türü belirtiniz!");
					warning.setVisible(true);
				}
			}
		});
		signUpButton.setBounds(193, 301, 70, 22);
		contentPane.add(signUpButton);
		
		Button signUpButton_1 = new Button("Giriş Sayfası");
		signUpButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.users = users;
				Login login = new Login(users);
				login.setVisible(true);
				dispose();
			}
		});
		signUpButton_1.setBounds(334, 349, 90, 22);
		contentPane.add(signUpButton_1);
	}
}
