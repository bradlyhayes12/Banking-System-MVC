package acctMgr.controller;

import acctMgr.view.AccountListView;
import acctMgr.view.AccountView;
import acctMgr.view.AccountView.ViewType;

import javax.swing.JComboBox;

import acctMgr.model.Account;
import acctMgr.model.AccountList;

@SuppressWarnings("unused")
public class AccountListController extends AbstractController {
	public void operation(String option) {
		if (option == AccountListView.Edit1) {
			System.out.println("edit in $ pressed");
			String selectedAccount = ((AccountListView)getView()).getSelectedAccount();
			
			Account account = ((AccountList)getModel()).getAccount(selectedAccount); 
			AccountController controller = new AccountController();
			controller.setModel(account);
			AccountView accountView = new AccountView(account, controller, ViewType.Dollar);
			controller.setView(accountView);
			accountView.setVisible(true); 
		}else if(option == AccountListView.Edit2) {
			System.out.println("edit in \u20ac pressed");
			String selectedAccount = ((AccountListView)getView()).getSelectedAccount();
			
			Account account = ((AccountList)getModel()).getAccount(selectedAccount);
			AccountController controller = new AccountController();
			controller.setModel(account);
			AccountView accountView = new AccountView(account, controller, ViewType.Yen);
			controller.setView(accountView);
			accountView.setVisible(true); 
		}else if(option == AccountListView.Edit3) {
			System.out.println("edit in \u00a5");
			String selectedAccount = ((AccountListView)getView()).getSelectedAccount();
			
			Account account = ((AccountList)getModel()).getAccount(selectedAccount); 
			AccountController controller = new AccountController();
			controller.setModel(account);
			AccountView accountView = new AccountView(account, controller, ViewType.Euro);
			controller.setView(accountView);
			accountView.setVisible(true); 
		}else if(option == AccountListView.Save){
			System.out.println("save pressed");
			((AccountList) getModel()).save();
		}else if(option == AccountListView.Exit) {
			System.out.println("exit pressed");
			System.exit(0);
		}
		
	}
}
