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

public class ForeignCurrencyPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	private JTextField sterlinField;
	private JTextField dolarField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForeignCurrencyPage frame = new ForeignCurrencyPage(user,users);
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
	public ForeignCurrencyPage(User user, HashMap<String,User> users) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField euroField = new JTextField() {
		  public void processKeyEvent(KeyEvent ev) {
		    char c = ev.getKeyChar();
		    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
		      super.processKeyEvent(ev);
		    }
		    else if(c==8)
		    	super.processKeyEvent(ev);
		  }
		};
		
		euroField.setBounds(154, 80, 101, 20);
		contentPane.add(euroField);
		euroField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Almak istediğiniz döviz miktarını TL cinsinden giriniz!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 342, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Satın Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dolar=0,euro=0,sterlin =0;
				if(!dolarField.getText().equals(""))
					dolar = Integer.parseInt(dolarField.getText());
				if(!euroField.getText().equals(""))
					euro = Integer.parseInt(euroField.getText());
				if(!sterlinField.getText().equals(""))
					sterlin = Integer.parseInt(sterlinField.getText());
				int money = dolar+euro+sterlin;
				if(money>user.balance) {
					WarningPage warningPage = new WarningPage(String.format("Yeterli paranız bulunmamakta.(%,.2f)", user.balance));
					warningPage.setVisible(true);
				}
				else {
					user.buyForeignCurrency(1, dolar);
					user.buyForeignCurrency(2, euro);
					user.buyForeignCurrency(3, sterlin);
					CustomerHomePage.user = user;
					CustomerHomePage.users = users;
					CustomerHomePage homePage = new CustomerHomePage(user, users);
					homePage.setVisible(true);
					WarningPage warningPage = new WarningPage("Döviz başarıyla satın alındı!");
					warningPage.setVisible(true);
					dispose();
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
				}
			}
		});
		btnNewButton.setBounds(263, 177, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Dolar");
		lblNewLabel_1.setBounds(47, 51, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Euro");
		lblNewLabel_1_1.setBounds(47, 83, 89, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnAnaMen = new JButton("Ana Menü");
		btnAnaMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerHomePage.user = user;
				CustomerHomePage.users = users;
				CustomerHomePage home = new CustomerHomePage(user, users);
				home.setVisible(true);
				dispose();
			}
		});
		btnAnaMen.setBounds(47, 177, 101, 23);
		contentPane.add(btnAnaMen);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sterlin");
		lblNewLabel_1_1_1.setBounds(47, 114, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		sterlinField = new JTextField() {
			public void processKeyEvent(KeyEvent ev) {
			    char c = ev.getKeyChar();
			    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
			      super.processKeyEvent(ev);
			    }
			    else if(c==8)
			    	super.processKeyEvent(ev);
			  }
		};
		sterlinField.setColumns(10);
		sterlinField.setBounds(154, 111, 101, 20);
		contentPane.add(sterlinField);
		
		dolarField = new JTextField() {
			public void processKeyEvent(KeyEvent ev) {
			    char c = ev.getKeyChar();
			    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
			      super.processKeyEvent(ev);
			    }
			    else if(c==8)
			    	super.processKeyEvent(ev);
			  }
		};
		dolarField.setColumns(10);
		dolarField.setBounds(154, 48, 101, 20);
		contentPane.add(dolarField);
		
		JLabel lblNewLabel_4 = new JLabel("Güncel Döviz Kurları");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(316, 22, 145, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Dolar : 29.72");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(343, 47, 102, 37);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Euro : 32.68");
		lblNewLabel_3_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(343, 77, 102, 37);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Sterlin : 37.74");
		lblNewLabel_3_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(343, 109, 102, 37);
		contentPane.add(lblNewLabel_3_2);
	}

}
