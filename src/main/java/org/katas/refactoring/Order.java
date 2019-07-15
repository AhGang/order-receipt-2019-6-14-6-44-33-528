package org.katas.refactoring;

import java.util.List;

public class Order {
    private String customrerName;
    private String customerAddress;
    private List<LineItem> lineItems;

    public Order(String name, String address, List<LineItem> lineItems) {
        this.customrerName = name;
        this.customerAddress = address;
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return customrerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
