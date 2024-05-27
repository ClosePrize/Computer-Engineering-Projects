package BankProjectNYP;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class BankManagerHomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	static ArrayList<BankStaff> staffs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankManagerHomePage frame = new BankManagerHomePage(user,users,staffs);
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
	public BankManagerHomePage(User user,HashMap<String,User> users,ArrayList<BankStaff> staffs) {
		String message = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 383);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblNewLabel = new JLabel("JavaBank");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(199, 15, 90, 37);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Ayarlar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsPageForManager.user = user;
				SettingsPageForManager.users = users;
				SettingsPageForManager ayarlarPage = new SettingsPageForManager(user,users,staffs);
				ayarlarPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(371, 18, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel nameLabel = new JLabel(String.format("Hoşgeldin, %s %s", user.name, user.surname));
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(28, 53, 389, 31);
		contentPane.add(nameLabel);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIeAlnacakalanlar = new JLabel("İşe Alınacak Çalışanlar");
		lblIeAlnacakalanlar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIeAlnacakalanlar.setBounds(28, 105, 193, 31);
		contentPane.add(lblIeAlnacakalanlar);
		
		if(staffs.size() == 0) {
			message = "<html>İşe alınmayı bekleyen kullanıcı yok</html>";
		}
		else {
			message = String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s</html>",staffs.get(staffs.size()-1).name,staffs.get(staffs.size()-1).surname,staffs.get(staffs.size()-1).id);
		}
		
		JLabel userInf = new JLabel(message);
		userInf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userInf.setBounds(44, 158, 140, 68);
		contentPane.add(userInf);
		
		JButton btnNewButton = new JButton("Onayla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(staffs.size() != 0) {
					staffs.get(staffs.size()-1).isApproved = true;
					staffs.get(staffs.size()-1).isWaiting = false;				
					staffs.remove(staffs.size()-1);
					try {
						FileOutputStream f=new FileOutputStream("users.txt",false) ;
				        PrintStream yaz=new PrintStream(f);
				        for(User User : users.values()) {
				        	if(User.type == 1 || User.type == 3)
				        		yaz.println(User.type + ", " + User.name + ", " + User.surname + ", " + User.id + ", " + User.surname + ", " + User.password + ", " + User.balance + ", " + User.dolarBalance + ", " + User.euroBalance + ", " + User.sterlinBalance + ", " + User.waitingCreditCardProcess + ", " + User.waitingCreditProcess + ", " + User.desiredCreditCardLimit + ", " + User.desiredCredit);
				        	else if(User.type == 2) {
				        		BankStaff staff = (BankStaff) User;
				        		yaz.println(staff.type + ", " + staff.name + ", " + staff.surname + ", " + staff.id + ", " + staff.surname + ", " + staff.password + ", " + staff.isApproved + ", " + staff.isWaiting);
				        	}
				        	
				        }
				        yaz.close();
					} catch (Exception e1) {
					}
				}
				if(staffs.size() == 0) {
					userInf.setText(String.format("<html>İşe alınmayı bekleyen kullanıcı yok</html>"));
				}
				else {
					userInf.setText(String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s</html>",staffs.get(staffs.size()-1).name,staffs.get(staffs.size()-1).surname,staffs.get(staffs.size()-1).id));
				}
			}
		});
		btnNewButton.setBounds(269, 203, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Reddet");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(staffs.size() != 0) {
					staffs.get(staffs.size()-1).isApproved = false;
					staffs.get(staffs.size()-1).isWaiting = false;	
					staffs.remove(staffs.size()-1);
					try {
						FileOutputStream f=new FileOutputStream("users.txt",false) ;
				        PrintStream yaz=new PrintStream(f);
				        for(User User : users.values()) {
				        	if(User.type == 1 || User.type == 3)
				        		yaz.println(User.type + ", " + User.name + ", " + User.surname + ", " + User.id + ", " + User.surname + ", " + User.password + ", " + User.balance + ", " + User.dolarBalance + ", " + User.euroBalance + ", " + User.sterlinBalance + ", " + User.waitingCreditCardProcess + ", " + User.waitingCreditProcess + ", " + User.desiredCreditCardLimit + ", " + User.desiredCredit);
				        	else if(User.type == 2) {
				        		BankStaff staff = (BankStaff) User;
				        		yaz.println(staff.type + ", " + staff.name + ", " + staff.surname + ", " + staff.id + ", " + staff.surname + ", " + staff.password + ", " + staff.isApproved + ", " + staff.isWaiting);
				        	}
				        	
				        }
				        yaz.close();
					} catch (Exception e1) {
					}
				}
				if(staffs.size() == 0) {
					userInf.setText(String.format("<html>İşe alınmayı bekleyen kullanıcı yok</html>"));
				}
				else {
					userInf.setText(String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s</html>",staffs.get(staffs.size()-1).name,staffs.get(staffs.size()-1).surname,staffs.get(staffs.size()-1).id));
				}
			}
		});
		btnNewButton_2.setBounds(371, 203, 89, 23);
		contentPane.add(btnNewButton_2);
		
	}
}
