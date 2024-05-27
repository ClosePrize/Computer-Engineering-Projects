package BankProjectNYP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreditCardPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	private JTextField creditCardText;
	private JTextField creditText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardPage frame = new CreditCardPage(user,users);
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
	@SuppressWarnings("serial")
	public CreditCardPage(User user, HashMap<String,User> users) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton homeButton = new JButton("Ana Menü");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerHomePage.user = user;
				CustomerHomePage.users = users;
				CustomerHomePage home = new CustomerHomePage(user, users);
				home.setVisible(true);
				dispose();
			}
		});
		homeButton.setBounds(296, 208, 107, 23);
		contentPane.add(homeButton);
		
		JLabel lblNewLabel = new JLabel("İstenilen Limit");
		lblNewLabel.setBounds(27, 70, 107, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblKrediKartTalep_1 = new JLabel("Kredi Kartı Talep Et");
		lblKrediKartTalep_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKrediKartTalep_1.setBounds(10, 36, 174, 31);
		contentPane.add(lblKrediKartTalep_1);
		
		creditCardText = new JTextField(){
		  public void processKeyEvent(KeyEvent ev) {
			    char c = ev.getKeyChar();
			    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
			      super.processKeyEvent(ev);
			    }
			    else if(c==8)
			    	super.processKeyEvent(ev);
			  }
		};
		creditCardText.setBounds(133, 71, 86, 20);
		contentPane.add(creditCardText);
		creditCardText.setColumns(10);
		
		JButton btnTalepEt = new JButton("Talep Et");
		btnTalepEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.desiredCreditCardLimit == 0){
					if(user.waitingCreditCardProcess == false) {
						user.takeCreditCard(Double.parseDouble(creditCardText.getText()));
						WarningPage warningPage = new WarningPage(String.format("Talebiniz başarıyla alındı.(%,.2f)", user.desiredCreditCardLimit));
						warningPage.setVisible(true);
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
					else {
						WarningPage warningPage = new WarningPage(String.format("Beklemekte olan talebiniz bulunmakta.(%,.2f)", user.desiredCreditCardLimit));
						warningPage.setVisible(true);
					}
				}
				else {
					WarningPage warningPage = new WarningPage(String.format("Birden fazla kredi kartınız olamaz!"));
					warningPage.setVisible(true);
				}
			}
		});
		btnTalepEt.setBounds(267, 70, 107, 23);
		contentPane.add(btnTalepEt);
		
		JLabel lblKrediKartTalep_1_1_1 = new JLabel("Kredi Çekme");
		lblKrediKartTalep_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKrediKartTalep_1_1_1.setBounds(10, 98, 188, 31);
		contentPane.add(lblKrediKartTalep_1_1_1);
		
		JLabel lblIstenilenMiktar = new JLabel("İstenilen Miktar");
		lblIstenilenMiktar.setBounds(27, 134, 107, 23);
		contentPane.add(lblIstenilenMiktar);
		
		creditText = new JTextField(){
		  public void processKeyEvent(KeyEvent ev) {
		    char c = ev.getKeyChar();
		    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
		      super.processKeyEvent(ev);
		    }
		    else if(c==8)
		    	super.processKeyEvent(ev);
		  }
		};
		creditText.setColumns(10);
		creditText.setBounds(133, 135, 86, 20);
		contentPane.add(creditText);
		
		JButton btnTalepEt_1 = new JButton("Talep Et");
		btnTalepEt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.waitingCreditProcess == false) {
					user.takeCredit(Double.parseDouble(creditText.getText()));
					WarningPage warningPage = new WarningPage(String.format("Talebiniz başarıyla alındı.(%,.2f)", user.desiredCredit));
					warningPage.setVisible(true);
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
				else {
					WarningPage warningPage = new WarningPage(String.format("Beklemekte olan talebiniz bulunmakta.(%,.2f)", user.desiredCredit));
					warningPage.setVisible(true);
				}
			}
		});
		btnTalepEt_1.setBounds(267, 134, 107, 23);
		contentPane.add(btnTalepEt_1);
	}
}
