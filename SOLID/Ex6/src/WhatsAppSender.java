public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    protected boolean canSend(Notification n) {
        return n.phone.startsWith("+");
    }

    @Override
    protected String getFailureMessage(Notification n) {
        return "phone must start with + and country code";
    }

    @Override
    protected void doSend(Notification n) {
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
