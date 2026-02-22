import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final FileStore store = new FileStore();
    private final BillCalculator calculator = new BillCalculator();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        Bill bill = calculator.calculate(subtotal, customerType, lines.size());

        out.append(String.format("Subtotal: %.2f\n", bill.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", TaxRules.taxPercent(customerType), bill.tax));
        out.append(String.format("Discount: -%.2f\n", bill.discount));
        out.append(String.format("TOTAL: %.2f\n", bill.total));

        String printable = InvoiceFormatter.identityFormat(out.toString());
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
