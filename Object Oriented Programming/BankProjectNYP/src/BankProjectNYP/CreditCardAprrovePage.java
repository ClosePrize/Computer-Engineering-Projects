package BankProjectNYP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class CreditCardAprrovePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	static ArrayList<Customer> waitingCreditProcess;
	static ArrayList<Customer> waitingCreditCardProcess;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardAprrovePage frame = new CreditCardAprrovePage(user,users,waitingCreditProcess, waitingCreditCardProcess);
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
	public CreditCardAprrovePage(User user, HashMap<String,User> users, ArrayList<Customer> waitingCreditProcess, ArrayList<Customer> waitingCreditCardProcess) {
		String message = "";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(waitingCreditCardProcess.size() == 0) {
			message = String.format("<html>Onay bekleyen kullanıcı yok</html>");
		}
		else {
			message = String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s<br>İstenilen kredi kartı limiti: %s</html>",waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).name,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).surname,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).id,Double.toString(waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).desiredCreditCardLimit));		
		}
		
		JLabel userInf = new JLabel(message);
		userInf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userInf.setBounds(35, 44, 140, 77);
		contentPane.add(userInf);
		
		JButton btnNewButton = new JButton("Onayla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(waitingCreditCardProcess.size() != 0) {
					waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).waitingCreditCardProcess = false;
					User temp = waitingCreditCardProcess.remove(waitingCreditCardProcess.size()-1);
					temp.takeCreditCard(temp.desiredCreditCardLimit);
					try {
						FileOutputStream f=new FileOutputStream("users.txt",false) ;
				        PrintStream yaz=new PrintStream(f);
				        users.replace(temp.id, temp);
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
					if(waitingCreditCardProcess.size() == 0) {
						userInf.setText(String.format("<html>Onay bekleyen kullanıcı yok</html>"));
					}
					else {
						userInf.setText(String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s<br>İstenilen kredi tutarı: %s</html>",waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).name,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).surname,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).id,Double.toString(waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).desiredCredit)));
					}
				}
			}
		});
		btnNewButton.setBounds(212, 98, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Reddet");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(waitingCreditCardProcess.size() != 0) {
					waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).waitingCreditCardProcess = false;				
					waitingCreditCardProcess.remove(waitingCreditCardProcess.size()-1);
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
				if(waitingCreditCardProcess.size() == 0) {
					userInf.setText(String.format("<html>Onay bekleyen kullanıcı yok</html>"));
				}
				else {
					userInf.setText(String.format("<html>Adı: %s<br>Soyadı: %s<br>Id: %s<br>İstenilen kredi tutarı: %s</html>",waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).name,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).surname,waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).id,Double.toString(waitingCreditCardProcess.get(waitingCreditCardProcess.size()-1).desiredCredit)));
				}
			}
		});
		btnNewButton_2.setBounds(311, 98, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblKrediKartOnay = new JLabel("Kredi Kartı Onayı Bekleyen Müşteriler");
		lblKrediKartOnay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKrediKartOnay.setBounds(25, 11, 305, 31);
		contentPane.add(lblKrediKartOnay);
		
		JButton homeButton = new JButton("Ana Menü");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankStaffHomePage.user = user;
				BankStaffHomePage.users = users;
				BankStaffHomePage.waitingCreditCardProcess = waitingCreditCardProcess;
				BankStaffHomePage.waitingCreditProcess = waitingCreditProcess;
				BankStaffHomePage homePage = new BankStaffHomePage(user, users,waitingCreditProcess, waitingCreditCardProcess);
				homePage.setVisible(true);
				dispose();	
			}
		});
		homeButton.setBounds(317, 17, 107, 23);
		contentPane.add(homeButton);
	}

}
