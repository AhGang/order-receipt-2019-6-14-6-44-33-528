package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    public static final String SALES_TAX = "Sales Tax";
    public static final String TOTAL_AMOUNT = "Total Amount";
    public static final String RECEIPT_HEADER = "======Printing Orders======\n";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        output.append(RECEIPT_HEADER);
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

        GetLineItems getLineItems = new GetLineItems(output).invoke();
        double totSalesTx = getLineItems.getTotalSalesTax();
        double tot = getLineItems.getTotal();

        output.append(SALES_TAX).append('\t').append(totSalesTx);
        output.append(TOTAL_AMOUNT).append('\t').append(tot);
        return output.toString();
    }

    private class GetLineItems {
        private StringBuilder output;
        private double totalSalesTax;
        private double total;

        public GetLineItems(StringBuilder output) {
            this.output = output;
        }

        public double getTotalSalesTax() {
            return totalSalesTax;
        }

        public double getTotal() {
            return total;
        }

        public GetLineItems invoke() {
            totalSalesTax = 0d;
            total = 0d;
            for (LineItem lineItem : order.getLineItems()) {
                lineItemOutputAppend(lineItem);

                double salesTax = lineItem.totalAmount() * .10;
                totalSalesTax += salesTax;
                total += lineItem.totalAmount() + salesTax;
            }
            return this;
        }

        private void lineItemOutputAppend(LineItem lineItem) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
        }
    }
}