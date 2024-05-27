package BankProjectNYP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class SettingsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField_1;
	static User user;
	static HashMap<String,User> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsPage frame = new SettingsPage(user,users);
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
	public SettingsPage(User user,HashMap<String,User> users) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullanıcı Adı Değiştir");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 23, 174, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginPage = new Login(users);
				loginPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(294, 208, 107, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(152, 66, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Yeni Kullanıcı Adı");
		lblNewLabel_1.setBounds(27, 68, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Değiştir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				if(!username.equals(""))
					user.setUsername(username);
					WarningPage warning = new WarningPage("Kullanıcı adı başarıyla değiştirildi!");
					warning.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(280, 65, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Yeni Şifre");
		lblNewLabel_1_1.setBounds(27, 142, 107, 14);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		passwordField_1.setBounds(152, 139, 86, 20);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton_1_1 = new JButton("Değiştir");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField_1.getPassword());
				if(!password.equals(""))
					user.changePassword(password);
					WarningPage warning = new WarningPage("Şifre başarıyla değiştirildi!");
					warning.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(280, 138, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Şifre Değiştir");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(27, 96, 107, 31);
		contentPane.add(lblNewLabel_2);
		
		JButton homeButton = new JButton("Ana Menü");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.type == 1) {
					CustomerHomePage.user = user;
					CustomerHomePage.users = users;
					CustomerHomePage home = new CustomerHomePage(user, users);
					home.setVisible(true);
					dispose();					
				}
				else if(user.type == 2){
					
				}
			}
		});
		homeButton.setBounds(27, 208, 107, 23);
		contentPane.add(homeButton);
	}
}
