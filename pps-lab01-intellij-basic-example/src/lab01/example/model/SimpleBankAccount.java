package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends BasicBankAccount {

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    protected boolean isWithdrawAllowed(double amount) {
        return this.balance >= amount;
    }

    @Override
    protected void modifyBalance(double newBalance) {
        this.balance = newBalance;
    }
}
