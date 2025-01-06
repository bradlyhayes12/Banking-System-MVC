package acctMgr.view;

import acctMgr.model.Account;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.controller.AccountController;
import acctMgr.view.JFrameView;
import acctMgr.view.AccountView.Handler;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

@SuppressWarnings("unused")
public class AccountView extends JFrameView {
	public static final String Deposit = "Deposit";
	public static final String Withdraw = "Withdraw";
	
	public static final String Dollars = "in $";
	public static final String Yen = "in \\u20ac";
	public static final String euro = "in \\u00a5";
	
	public String typeString;
	
	private ViewType type = ViewType.Dollar;
	
	public enum ViewType{Dollar, Yen, Euro};
	
	private JPanel topPanel;
	private JPanel buttonPanel;
	private JPanel textFieldPanel;
	private JButton depositButton; 
	private JButton withdrawButton; 
	private JTextField balanceField;
	private JTextField amountField;
	private JLabel balanceLabel;
	private JLabel amountLabel;
	
	public static final RoundingMode ROUNDING = RoundingMode.HALF_EVEN;
	private static final BigDecimal[] rates = {new BigDecimal("1.0"), new BigDecimal("0.93"), new BigDecimal("158.1")};

	
	public AccountView(Model model, AccountController controller,ViewType type) {
		super(model, controller);
		this.type = type;
		
		addWindowListener (new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				dispose();
			}
		});
		
		String name = ((Account)getModel()).getName();
		Border blackline = BorderFactory.createTitledBorder(name);
		
		Handler handler = new Handler();
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1,5,5));
		topPanel.setBorder(blackline);
		this.getContentPane().add(topPanel);
		
		textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new GridLayout(2,1,5,5));
		topPanel.add(textFieldPanel);
		
		balanceLabel = new JLabel("Balance: ");
		
		balanceField = new JTextField();
		balanceField.setEditable(false);
		BigDecimal balance = ((Account)model).getBalance();
		
		BigDecimal rate = rates[type.ordinal()];
		BigDecimal value = balance.multiply(rate);
		value = value.setScale(2, ROUNDING);
		balanceField.setText(value.toString());
		textFieldPanel.add(balanceLabel);
		textFieldPanel.add(balanceField);
		
		amountLabel = new JLabel("Amount: ");
		amountField = new JTextField("30.00");
		textFieldPanel.add(amountLabel);
		textFieldPanel.add(amountField);
		
		depositButton = new JButton(Deposit);
		depositButton.addActionListener(handler);
		
		withdrawButton = new JButton(Withdraw);
		withdrawButton.addActionListener(handler);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2,5,5));
		
		buttonPanel.add(depositButton);
		
		buttonPanel.add(withdrawButton);
		
		topPanel.add(buttonPanel);
		
		setResizable(false);
		
		pack();
		
		setLocationRelativeTo(null);
		
	}
	
	public BigDecimal getAmount() {
		BigDecimal amount = new BigDecimal("40.00");
		try {
			amount = new BigDecimal(amountField.getText());
			amount = amount.setScale(2, ROUNDING);
		}
		catch(NumberFormatException e) {
			showError("Only decimals allowed");
		}
		if(amount.signum() <= 0) {
			showError("Amount should be poistive");
		}
		
		return amount; 
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	@Override
	public void modelChanged(ModelEvent me) {
		BigDecimal balance = me.getBalance();
		BigDecimal rate = rates[type.ordinal()];

		BigDecimal value = balance.multiply(rate);
		value = value.setScale(2, ROUNDING);
		balanceField.setText(value.toString());
	}
	
	class Handler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
		}
	}
}


