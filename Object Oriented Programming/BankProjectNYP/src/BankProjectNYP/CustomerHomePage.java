package BankProjectNYP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class CustomerHomePage extends JFrame {

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
					CustomerHomePage frame = new CustomerHomePage(user,users);
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
	public CustomerHomePage(User user,HashMap<String,User> users) {
		int cardNum = 0;
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

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel(String.format("Para: %,.2f TL",user.balance));
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel1.setBounds(288, 142, 145, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel nameLabel = new JLabel(String.format("Hoşgeldin, %s %s", user.name, user.surname));
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(28, 53, 389, 31);
		contentPane.add(nameLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanıcı İşlemleri");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(28, 105, 167, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Para Yatırma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositPage.user = user;
				DepositPage.users = users;
				DepositPage deposit = new DepositPage(user,users);
				deposit.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(28, 142, 167, 23);
		contentPane.add(btnNewButton);
		
		JButton btnParaekme = new JButton("Para Çekme");
		btnParaekme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawPage.user = user;
				WithdrawPage.users = users;				
				WithdrawPage withdrawPage = new WithdrawPage(user, users);
				withdrawPage.setVisible(true);
				dispose();
			}
		});
		btnParaekme.setBounds(28, 176, 167, 23);
		contentPane.add(btnParaekme);
		
		JButton btnParaTransferi = new JButton("Para Transferi");
		btnParaTransferi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferPage.user = user;
				TransferPage.users = users;
				TransferPage transferPage = new TransferPage(user, users);
				transferPage.setVisible(true);
				dispose();
			}
		});
		btnParaTransferi.setBounds(28, 208, 167, 23);
		contentPane.add(btnParaTransferi);
		
		JButton btnDvizAlsat = new JButton("Döviz Alış-Satış");
		btnDvizAlsat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForeignCurrencyPage.user = user;
				ForeignCurrencyPage.users = users;
				ForeignCurrencyPage foreignCurrencyPage = new ForeignCurrencyPage(user, users);
				foreignCurrencyPage.setVisible(true);
				dispose();
			}
		});
		btnDvizAlsat.setBounds(28, 242, 167, 23);
		contentPane.add(btnDvizAlsat);
		
		JButton btnKrediKartIlemleri = new JButton("Kredi İşlemleri");
		btnKrediKartIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCardPage.user = user;
				CreditCardPage.users = users;
				CreditCardPage cardPage = new CreditCardPage(user, users);
				cardPage.setVisible(true);
				dispose();
			}
		});
		btnKrediKartIlemleri.setBounds(28, 277, 167, 23);
		contentPane.add(btnKrediKartIlemleri);
		
		JButton btnNewButton_1 = new JButton("Ayarlar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsPage.user = user;
				SettingsPage.users = users;
				SettingsPage ayarlarPage = new SettingsPage(user,users);
				ayarlarPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(371, 18, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel1_1 = new JLabel(String.format("Dolar: %,.2f $",user.dolarBalance));
		lblNewLabel1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel1_1.setBounds(288, 162, 145, 14);
		contentPane.add(lblNewLabel1_1);
		
		JLabel lblNewLabel1_2 = new JLabel(String.format("Euro: %,.2f €",user.euroBalance));
		lblNewLabel1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel1_2.setBounds(288, 183, 145, 14);
		contentPane.add(lblNewLabel1_2);
		
		JLabel lblNewLabel1_3 = new JLabel(String.format("Sterlin: %,.2f £",user.sterlinBalance));
		lblNewLabel1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel1_3.setBounds(288, 202, 145, 14);
		contentPane.add(lblNewLabel1_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Bakiye Bilgisi");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(266, 101, 167, 23);
		contentPane.add(lblNewLabel_2_1);
		
		if(user.desiredCreditCardLimit != 0) {
			cardNum = 1;
		}
		JLabel cardNumber = new JLabel(String.format("Kredi Kartı Sayısı: %o",cardNum ));
		cardNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cardNumber.setBounds(266, 229, 194, 23);
		contentPane.add(cardNumber);
	}
}
