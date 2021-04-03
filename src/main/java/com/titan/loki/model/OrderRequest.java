package com.titan.loki.model;

import java.util.Objects;

public class OrderRequest {

    private String userID;
    private String symbol;
    private Long quantity;
    private Double price;
    private String side;

    public String getUserID() { return userID; }
    public Long getQuantity() {
        return quantity;
    }
    public String getSymbol() {
        return symbol;
    }
    public Double getPrice() { return price; }
    public String getSide() { return side; }

    public void setUserID(String userID) { this.userID = userID; }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setPrice(Double price) { this.price = price; }
    public void setSide(String side) { this.side = side; }

    public OrderRequest() {}

    public OrderRequest(String userID, String symbol, Long quantity, Double price, String side) {
        this.userID = userID;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest that = (OrderRequest) o;
        return Objects.equals(userID, that.userID) && Objects.equals(symbol, that.symbol) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(side, that.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, symbol, quantity, price, side);
    }

    @Override
    public String toString() {
        return "OrderRequest [symbol=" + this.symbol + ", userID=" + this.userID +
                ", quantity=" + this.quantity + ", price=" + this.price +
                ", side=" + this.side + "]";
    }

    public String validate() {
        if (userID.isEmpty()) return "Username cannot be empty...";
        if (symbol.isEmpty()) return "Symbol cannot be empty...";
        if (quantity <= 0) return "Quantity cannot be less than 0";
        if (price <= 0.0) return "Price cannot be less than 0";
        if (side.isEmpty()) return "Side cannot be empty";
        if (!side.equals("buy") && !side.equals("sell")) return "Side must either be 'buy' or 'sell'";
        return "";
    }

    public String toFixMessage(OrderRequest order) {
        return "8=FIX"
                + "?1="
                + order.getUserID()
                + "?2="
                + order.getSymbol()
                + "?3="
                + order.getQuantity()
                + "?4="
                + order.getPrice()
                + "?5="
                + order.getSide();
    }

}
