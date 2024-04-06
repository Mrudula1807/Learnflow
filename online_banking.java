import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class Transaction {
    private String fromAccount;
    private String toAccount;
    private double amount;

    public Transaction(String fromAccount, String toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }
}

class BankingSystem {
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public BankingSystem() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void deposit(String accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.deposit(amount);
                transactions.add(new Transaction("Deposit", accountNumber, amount));
                System.out.println("Deposit successful.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void withdraw(String accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                if (account.withdraw(amount)) {
                    transactions.add(new Transaction(accountNumber, "Withdraw", amount));
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Insufficient funds.");
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        boolean fromExists = false, toExists = false;
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(fromAccount)) {
                fromExists = true;
            }
            if (account.getAccountNumber().equals(toAccount)) {
                toExists = true;
            }
            if (fromExists && toExists) break;
        }
        if (!fromExists) {
            System.out.println("Source account not found.");
        } else if (!toExists) {
            System.out.println("Destination account not found.");
        } else {
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(fromAccount)) {
                    if (account.withdraw(amount)) {
                        transactions.add(new Transaction(fromAccount, toAccount, amount));
                        for (Account recipient : accounts) {
                            if (recipient.getAccountNumber().equals(toAccount)) {
                                recipient.deposit(amount);
                                System.out.println("Transfer successful.");
                                return;
                            }
                        }
                    } else {
                        System.out.println("Insufficient funds for transfer.");
                        return;
                    }
                }
            }
        }
    }

    public void printTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFromAccount() + " -> " + transaction.getToAccount() + ": $" + transaction.getAmount());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();

        Account account1 = new Account("12345", 1000);
        Account account2 = new Account("54321", 2000);

        bankingSystem.addAccount(account1);
        bankingSystem.addAccount(account2);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Transaction History\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String depositAccount = scanner.next();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankingSystem.deposit(depositAccount, depositAmount);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String withdrawAccount = scanner.next();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankingSystem.withdraw(withdrawAccount, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter source account number: ");
                    String sourceAccount = scanner.next();
                    System.out.print("Enter destination account number: ");
                    String destinationAccount = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    bankingSystem.transfer(sourceAccount, destinationAccount, transferAmount);
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    bankingSystem.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
