package rules;

import enitity.Transaction;
import enums.ShipmentSize;
import database.Database;

public class CheapestSizeRule implements Rule{

    private final Database database;

    private final ShipmentSize size;

    public CheapestSizeRule(Database database) {
        this(ShipmentSize.S, database);
    }

    public CheapestSizeRule(ShipmentSize size, Database database) {
        this.size = size;
        this.database = database;
    }

    @Override
    public Transaction processTransaction(Transaction transaction) {
        // a simple rule that fetches the cheapest small shipment provider from the DB
        // and sets this price for the currently processed transaction
        if (transaction.getSize() == size) {
            double cheapestPrice = database.getCheapestSizeShipmentProvider(size)
                    .getShipmentPrice(size);
            if (transaction.getPrice() > cheapestPrice) {
                double oldPrice = transaction.getPrice();
                transaction.setPrice(cheapestPrice);
                transaction.setDiscount(oldPrice - cheapestPrice);
            }
        }
        return transaction;
    }
}
