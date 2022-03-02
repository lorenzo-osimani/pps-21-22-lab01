package lab01.example.model;

public abstract class BasicBankAccount implements BankAccount {
    protected final AccountHolder holder;
    protected double balance;

    public BasicBankAccount(final double balance, final AccountHolder holder) {
        this.balance = balance;
        this.holder = holder;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            modifyBalance(this.balance + amount);
        }
    }

    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            modifyBalance(balance - amount);
        }
    }

    protected abstract boolean isWithdrawAllowed(final double amount);

    protected boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

    protected abstract void modifyBalance(final double newBalance);
}
