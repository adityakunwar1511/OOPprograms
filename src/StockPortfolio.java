import java.util.ArrayList;
import java.util.List;

public class StockPortfolio {
    private List<Stock> stocks;

    public StockPortfolio() {
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Stock stock : stocks) {
            totalValue += stock.calculateValue();
        }
        return totalValue;
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
}