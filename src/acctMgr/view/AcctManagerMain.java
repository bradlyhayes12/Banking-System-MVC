package acctMgr.view;

import javax.swing.JFrame;

import acctMgr.controller.AccountListController;
import acctMgr.model.AccountList;

public class AcctManagerMain {

	public static void main(String[] args) {
		if (args.length == 1 ) {
			String filename = args[0];
		
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			AccountList accountList = new AccountList(filename);
			AccountListController controller = new AccountListController();
			controller.setModel(accountList);
			AccountListView listView = new AccountListView(accountList, controller);
			controller.setView(listView);
			listView.setVisible(true);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		}
		else {
			System.out.println("Filename is expected");
		}

	}

}
