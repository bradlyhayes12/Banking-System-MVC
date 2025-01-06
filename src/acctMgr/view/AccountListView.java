package acctMgr.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import acctMgr.controller.AccountListController;
import acctMgr.model.AccountList;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;


@SuppressWarnings("unused")
public class AccountListView extends JFrameView{
	public static final String Edit1 = "edit in $";
	public static final String Edit2 = "edit in \u20ac";
	public static final String Edit3 = "edit in \u00a5";
	
	public static final String Save = "Save";
	public static final String Exit = "Exit";
	@SuppressWarnings("rawtypes")
	private JComboBox accountComboBox;
	private JPanel editButtonsPanel;
	private JPanel saveExitButtonsPanel;
	private JPanel comboBoxPanel;
	private JButton edit1;
	private JButton edit2;
	private JButton edit3;
	private JButton saveButton;
	private JButton exitButton; 

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AccountListView(AccountList model, AccountListController controller) {
		super(model, controller);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt) {
				dispose(); 
			}
		});
		
		String[] accountNames = ((AccountList) this.getModel()).listAccounts();
		setTitle("Account Selection");
		
		accountComboBox = new JComboBox(accountNames);
		
		Handler handler = new Handler();
		editButtonsPanel = new JPanel();
		editButtonsPanel.setLayout(new GridLayout(1,3,5,5));
		this.getContentPane().add(editButtonsPanel, BorderLayout.CENTER);
		
		saveExitButtonsPanel = new JPanel();
		saveExitButtonsPanel.setLayout(new GridLayout(2,1,5,5));
		this.getContentPane().add(saveExitButtonsPanel, BorderLayout.SOUTH);

		comboBoxPanel = new JPanel();
		this.getContentPane().add(comboBoxPanel, BorderLayout.NORTH);
		comboBoxPanel.add(accountComboBox);
		
		edit1 = new JButton(Edit1);
		edit1.addActionListener(handler);
		editButtonsPanel.add(edit1);
		
		edit2 = new JButton(Edit2);
		edit2.addActionListener(handler);
		editButtonsPanel.add(edit2);
		
		edit3 = new JButton(Edit3);
		edit3.addActionListener(handler);
		editButtonsPanel.add(edit3);
		
		saveButton = new JButton(Save);
		saveButton.addActionListener(handler);
		exitButton = new JButton(Exit);
		exitButton.addActionListener(handler);

		saveExitButtonsPanel.add(saveButton);
		saveExitButtonsPanel.add(exitButton);
		
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void messageBox (String message) { JOptionPane.showMessageDialog(null, message); }
	
	class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			((AccountListController)getController()).operation(e.getActionCommand());
		}
	}
	
	@Override
	public void modelChanged(ModelEvent me) {
		
	}
	public String getSelectedAccount() {
		return accountComboBox.getSelectedItem().toString();
	}
}
