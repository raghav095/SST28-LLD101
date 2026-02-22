public class BillCalculator {
    public Bill calculate(double subtotal, String customerType, int distinctLines) {
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        
        double discount = DiscountRules.discountAmount(customerType, subtotal, distinctLines);
        
        double total = subtotal + tax - discount;
        
        return new Bill(subtotal, tax, discount, total);
    }
}
