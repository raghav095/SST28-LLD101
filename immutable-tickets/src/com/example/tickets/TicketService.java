package com.example.tickets;

/**
 * Service layer that creates tickets.
 *
 * FIXES APPLIED:
 * - Uses the Builder to create tickets securely with all data upfront.
 * - No longer mutates existing tickets; instead "updates" return a new instance
 * via toBuilder().
 * - Validation is no longer scattered here; handled inside the Builder.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // Validation belongs in the Builder now.
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Fix: Do not mutate. Return a new instance with the copied data + changes.
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // Validation belongs in the Builder now.
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
