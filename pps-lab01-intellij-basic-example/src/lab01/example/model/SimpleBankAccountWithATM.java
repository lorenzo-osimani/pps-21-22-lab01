package lab01.example.model;

public class SimpleBankAccountWithATM extends BasicBankAccount {

    private final int fee = 1;

    public SimpleBankAccountWithATM(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    protected boolean isWithdrawAllowed(double amount) {
        return balance >= amount + fee;
    }

    @Override
    protected void modifyBalance(double newBalance) {
        this.balance = newBalance - fee;
    }
}
