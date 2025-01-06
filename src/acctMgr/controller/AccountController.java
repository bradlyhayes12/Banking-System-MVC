package acctMgr.controller;

import acctMgr.model.Account;
import java.math.BigDecimal;
import acctMgr.model.OverdrawException;

import javax.swing.SwingUtilities;
import acctMgr.view.AccountView;
@SuppressWarnings("unused")
public class AccountController extends AbstractController {
	public void operation(String option) {
	Account account = (Account)getModel();
	BigDecimal amount = ((AccountView)getView()).getAmount();
	if (option == AccountView.Withdraw) {
		try {
			account.withdraw(amount);
		}
		catch(OverdrawException ex) {
			((AccountView)getView()).showError(ex.getMessage());
		}
		System.out.println("Withdraw pressed");
	}else if(option == AccountView.Deposit) {
		account.deposit(amount);
		System.out.println("Deposit pressed");
		}
	}
}