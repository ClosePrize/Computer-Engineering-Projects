package BankProjectNYP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	static HashMap<String,User> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(users);
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
	public Login(HashMap<String,User> users) {
		Login.users = users;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(173, 107, 111, 29);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 147, 111, 31);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Şifre");
		passwordLabel.setBounds(98, 151, 65, 23);
		contentPane.add(passwordLabel);
		
		JLabel lblNewLabel = new JLabel("JavaBank");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(173, 9, 90, 37);
		contentPane.add(lblNewLabel);
		
		JButton logInButton = new JButton("Giriş");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = usernameField.getText();
				String pwd = new String(passwordField.getPassword()); 
				if(users.containsKey(field)) {
					User user = users.get(field);
					String password = user.password;
					if(password.equals(pwd)) {
						if(user.type == 1) {
							CustomerHomePage.user = user;
							CustomerHomePage.users = users;
							CustomerHomePage home = new CustomerHomePage(user, users);
							home.setVisible(true);
							dispose();
						}
						else if(user.type == 2) {
							BankStaff staff = (BankStaff) user;
							if(staff.isWaiting == false && staff.isApproved == true) {
								BankStaffHomePage.user = user;
								BankStaffHomePage.users = users;
								ArrayList<Customer> waitingCreditCardProcess1 = new ArrayList<Customer>();
								ArrayList<Customer> waitingCreditProcess1 = new ArrayList<Customer>();
								
								for(User User : users.values()) {
									if(User.type == 1) {
										Customer customer = (Customer) User;
										if(customer.waitingCreditCardProcess == true)
											waitingCreditCardProcess1.add(customer);
										if(customer.waitingCreditProcess==true) {
											waitingCreditProcess1.add(customer);
										}
									}
								}
								BankStaffHomePage.waitingCreditProcess = waitingCreditProcess1;
								BankStaffHomePage.waitingCreditCardProcess = waitingCreditCardProcess1;
								BankStaffHomePage home = new BankStaffHomePage(user, users, waitingCreditProcess1,waitingCreditCardProcess1);
								home.setVisible(true);
								dispose();
							}
							else if(staff.isWaiting == true && staff.isApproved == false) {
								WarningPage warning = new WarningPage("İşe alım için yönetici onayı bekleniyor!");
								warning.setVisible(true);
							}
							else if(staff.isWaiting == false && staff.isApproved == false) {
								WarningPage warning = new WarningPage("Başvurunuz yönetici tarafından reddedildi!");
								warning.setVisible(true);
							}
						}
						else if(user.type == 3) {
							BankManagerHomePage.user = user;
							BankManagerHomePage.users = users;
							ArrayList<BankStaff> staffs = new ArrayList<BankStaff>();
							for(User User : users.values()) {
								if(User.type == 2) {
									BankStaff staff = (BankStaff) User;
									if(staff.isApproved == false && staff.isWaiting == true) {
										staffs.add(staff);
									}
								}
							}
							BankManagerHomePage.staffs = staffs;
							BankManagerHomePage home = new BankManagerHomePage(user, users, staffs);
							home.setVisible(true);
							dispose();
						}
					}
					else {
						WarningPage warning = new WarningPage("Kullanıcı adı veya şifre yanlış!");
						warning.setVisible(true);
					}
				}
				else {
					WarningPage warning = new WarningPage("Kullanıcı adı veya şifre yanlış!");
					warning.setVisible(true);
				}
			}
		});
		logInButton.setForeground(new Color(0, 0, 0));
		logInButton.setBounds(183, 199, 89, 23);
		contentPane.add(logInButton);
		
		JButton signUpButton = new JButton("Kayıt Ol");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp.users = users;
				SignUp kayıt = new SignUp(users);
				kayıt.setVisible(true);
				dispose();
			}
		});
		signUpButton.setBounds(320, 18, 89, 23);
		contentPane.add(signUpButton);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(98, 110, 65, 23);
		contentPane.add(lblId);
	}
}
