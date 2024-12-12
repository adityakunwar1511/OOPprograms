import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockAccountManagement {
    private double balance;
    private List<Stock> stocks;

    public StockAccountManagement(double initialBalance) {
        this.balance = initialBalance;
        this.stocks = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        if (amount > balance) {
            System.out.println("Debit amount exceeded account balance.");
        } else {
            balance -= amount;
        }
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getBalance() {
        return balance;
    }

    public void printStockReport() {
        System.out.println("Stock Report:");
        for (Stock stock : stocks) {
            System.out.println("Stock Name: " + stock.getName());
            System.out.println("Number of Shares: " + stock.getNumberOfShares());
            System.out.println("Share Price: " + stock.getSharePrice());
            System.out.println("Value: " + stock.calculateValue());
            System.out.println();
        }
        System.out.println("Total Value: " + calculateTotalValue());
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Stock stock : stocks) {
            totalValue += stock.calculateValue();
        }
        return totalValue;
    }

    public static void main(String[] args) {
        StockAccountManagement account = new StockAccountManagement(1000.0);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of stocks: ");
        int numStocks = scanner.nextInt();

        for (int i = 0; i < numStocks; i++) {
            System.out.print("Enter the stock name: ");
            String name = scanner.next();

            System.out.print("Enter the number of shares: ");
            int numberOfShares = scanner.nextInt();

            System.out.print("Enter the share price: ");
            double sharePrice = scanner.nextDouble();

            Stock stock = new Stock(name, numberOfShares, sharePrice);
            account.addStock(stock);
        }

        account.printStockReport();
        
        System.out.println("Initial balance: " + account.getBalance());

        account.deposit(500.0);
        System.out.println("Balance after deposit: " + account.getBalance());

        account.debit(200.0);
        System.out.println("Balance after debit: " + account.getBalance());

        account.debit(1500.0); 
        System.out.println("Balance after debit: " + account.getBalance());

        
    }
}