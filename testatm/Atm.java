package testatm;

import java.util.Objects;

public class Atm {

    private String accountNumber;
    private String password;
    private long accountBalance;
    
    
    public Atm() {
    }

    public Atm(String accountNumber, String password, long accountBalance) {
        this.password = password;
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Atm{" + "accountNumbet=" + accountNumber + ", password=" + password + ", accountBalance=" + accountBalance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

}
