package BankProjectNYP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TransferPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user;
	static HashMap<String,User> users;
	private JTextField ıdField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferPage frame = new TransferPage(user,users);
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
	public TransferPage(User user, HashMap<String,User> users) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
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
		
		numberField.setBounds(154, 80, 101, 20);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Transfer etmek istediğiniz para miktarını ve kişinin Id'sini giriniz!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 342, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Transfer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = Integer.parseInt(numberField.getText());
				if(money>user.balance) {
					WarningPage warningPage = new WarningPage(String.format("Yeterli paranız bulunmamakta.(%,.2f)", user.balance));
					warningPage.setVisible(true);
				}
				else {
					String id = ıdField.getText();
					user.transferMoney(users,id, money);
					CustomerHomePage.user = user;
					CustomerHomePage.users = users;
					CustomerHomePage homePage = new CustomerHomePage(user, users);
					homePage.setVisible(true);
					WarningPage warningPage = new WarningPage("Para başarıyla transfer edildi!");
					warningPage.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(263, 127, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(47, 51, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Para Miktarı");
		lblNewLabel_1_1.setBounds(47, 86, 89, 14);
		contentPane.add(lblNewLabel_1_1);
		
		ıdField = new JTextField();
		ıdField.setBounds(154, 45, 101, 20);
		contentPane.add(ıdField);
		ıdField.setColumns(10);
		
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
		btnAnaMen.setBounds(47, 127, 101, 23);
		contentPane.add(btnAnaMen);
	}

}
