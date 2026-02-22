public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public final void send(Notification n) {
        Notification normalized = normalize(n);
        if (!canSend(normalized)) {
            throw new IllegalArgumentException(getFailureMessage(normalized));
        }
        doSend(normalized);
    }

    protected Notification normalize(Notification n) {
        if (n == null) return new Notification("", "", "", "");
        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        String email = n.email == null ? "" : n.email;
        String phone = n.phone == null ? "" : n.phone;
        if (subject == n.subject && body == n.body && email == n.email && phone == n.phone) return n;
        return new Notification(subject, body, email, phone);
    }

    protected boolean canSend(Notification n) { return true; }

    protected String getFailureMessage(Notification n) { return "cannot send"; }

    protected abstract void doSend(Notification n);
}
