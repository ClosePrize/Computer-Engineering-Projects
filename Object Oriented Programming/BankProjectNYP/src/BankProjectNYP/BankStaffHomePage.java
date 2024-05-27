package BankProjectNYP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class BankStaffHomePage extends JFrame {

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
					BankStaffHomePage frame = new BankStaffHomePage(user,users,waitingCreditProcess,waitingCreditCardProcess);
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
	public BankStaffHomePage(User user,HashMap<String,User> users,ArrayList<Customer> waitingCreditProcess,ArrayList<Customer> waitingCreditCardProcess) {
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
				SettingsPageForStaff.user = user;
				SettingsPageForStaff.users = users;
				SettingsPageForStaff.waitingCreditCardProcess = waitingCreditCardProcess;
				SettingsPageForStaff.waitingCreditProcess = waitingCreditProcess;
				SettingsPageForStaff ayarlarPage = new SettingsPageForStaff(user,users,waitingCreditProcess,waitingCreditCardProcess);
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
		
		JLabel lblIeAlnacakalanlar = new JLabel("Kredi Onayı Bekleyen Müşteriler");
		lblIeAlnacakalanlar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIeAlnacakalanlar.setBounds(28, 105, 276, 31);
		contentPane.add(lblIeAlnacakalanlar);
		
		
		JButton btnNewButton_3 = new JButton("Görüntüle");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditApprovePrage.user = user;
				CreditApprovePrage.users = users;
				CreditApprovePrage.waitingCreditCardProcess = waitingCreditCardProcess;
				CreditApprovePrage.waitingCreditProcess = waitingCreditProcess;				
				CreditApprovePrage credit = new CreditApprovePrage(user,users,waitingCreditProcess,waitingCreditCardProcess);
				credit.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(44, 147, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblKrediKartOnay = new JLabel("Kredi Kartı Onayı Bekleyen Müşteriler");
		lblKrediKartOnay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKrediKartOnay.setBounds(28, 177, 276, 31);
		contentPane.add(lblKrediKartOnay);
		
		JButton btnNewButton_3_1 = new JButton("Görüntüle");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditCardAprrovePage.user = user;
				CreditCardAprrovePage.users = users;
				CreditCardAprrovePage.waitingCreditCardProcess = waitingCreditCardProcess;
				CreditApprovePrage.waitingCreditProcess = waitingCreditProcess;
				CreditCardAprrovePage creditCard = new CreditCardAprrovePage(user,users,waitingCreditProcess,waitingCreditCardProcess);
				creditCard.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3_1.setBounds(44, 219, 89, 23);
		contentPane.add(btnNewButton_3_1);
	}
}
