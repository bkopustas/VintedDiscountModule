package enitity;

import enums.ShipmentSize;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private final LocalDate date;
    private final ShipmentSize size;
    private final ShippingProvider provider;

    private double price;
    private double discount;

    public Transaction(LocalDate date, ShipmentSize size, ShippingProvider provider, double price, double discount) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }
        this.date = date;
        this.size = size;
        this.provider = provider;
        this.price = price;
        this.discount = discount;
    }

    public LocalDate getDate() {
        return date;
    }

    public ShipmentSize getSize() {
        return size;
    }

    public ShippingProvider getProvider() {
        return provider;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(price, that.price) == 0 &&
                Double.compare(discount, that.discount) == 0 &&
                Objects.equals(date, that.date) &&
                size == that.size &&
                Objects.equals(provider, that.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, size, provider, price, discount);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s",
                date,
                size,
                provider.getShortName(),
                String.format("%1.2f", price),
                discount == 0 ? "-" :  String.format("%1.2f", discount)
        );
    }

}
