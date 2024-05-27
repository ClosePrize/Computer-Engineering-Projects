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

public class WithdrawPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawPage frame = new WithdrawPage(user,users);
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
	public WithdrawPage(User user, HashMap<String,User> users) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 160);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField numberField = new JTextField() {
		  public void processKeyEvent(KeyEvent ev) {
		    char c = ev.getKeyChar();
		    if (c >= 48 && c <= 57) { // c = '0' ... c = '9'
		      super.processKeyEvent(ev);
		    }
		    else if(c==8)
		    	super.processKeyEvent(ev);
		  }
		};
		numberField.setBounds(53, 48, 101, 20);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Çekmek istediğiniz para miktarını giriniz!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 229, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = Integer.parseInt(numberField.getText());
				if(money>user.balance) {
					WarningPage warningPage = new WarningPage(String.format("Yeterli paranız bulunmamakta.(%,.2f)", user.balance));
					warningPage.setVisible(true);
				}
				else {
					user.withdrawMoney(money);
					CustomerHomePage.user = user;
					CustomerHomePage.users = users;
					CustomerHomePage homePage = new CustomerHomePage(user, users);
					homePage.setVisible(true);
					WarningPage warningPage = new WarningPage("Para başarıyla çekildi!");
					warningPage.setVisible(true);
					dispose();
				}
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
		});
		btnNewButton.setBounds(225, 87, 89, 23);
		contentPane.add(btnNewButton);
		
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
		btnAnaMen.setBounds(53, 87, 101, 23);
		contentPane.add(btnAnaMen);
	}

}
