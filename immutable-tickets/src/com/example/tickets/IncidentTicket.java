package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An immutable record-like object for incident tickets.
 * 
 * FIXES APPLIED:
 * - All fields are private final.
 * - No public setters or constructors.
 * - Created exclusively through the nested Builder.
 * - Centralized validation in Builder.build().
 * - getTags() returns an unmodifiable list to prevent external mutation.
 * - Added toBuilder() to allow creating modified copies.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority; // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags; // defensive copy
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes; // optional
    private final String source; // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // Defensive copy of the list passed from the builder
        this.tags = builder.tags == null ? Collections.emptyList() : new ArrayList<>(builder.tags);
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    // Return an unmodifiable view so external code cannot mutate our internal list
    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public boolean isCustomerVisible() {
        return customerVisible;
    }

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    public static final class Builder {
        private String id;
        private String reporterEmail;
        private String title;
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        private Builder() {
        }

        private Builder(IncidentTicket ticket) {
            this.id = ticket.id;
            this.reporterEmail = ticket.reporterEmail;
            this.title = ticket.title;
            this.description = ticket.description;
            this.priority = ticket.priority;
            this.tags = new ArrayList<>(ticket.tags);
            this.assigneeEmail = ticket.assigneeEmail;
            this.customerVisible = ticket.customerVisible;
            this.slaMinutes = ticket.slaMinutes;
            this.source = ticket.source;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder addTag(String tag) {
            if (this.tags == null)
                this.tags = new ArrayList<>();
            this.tags.add(tag);
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags == null ? new ArrayList<>() : new ArrayList<>(tags);
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public IncidentTicket build() {
            // Centralized validation
            Validation.requireTicketId(this.id);
            Validation.requireEmail(this.reporterEmail, "reporterEmail");
            Validation.requireNonBlank(this.title, "title");
            Validation.requireMaxLen(this.title, 80, "title");

            Validation.requireOneOf(this.priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");

            if (this.assigneeEmail != null) {
                Validation.requireEmail(this.assigneeEmail, "assigneeEmail");
            }
            Validation.requireRange(this.slaMinutes, 5, 7200, "slaMinutes");

            return new IncidentTicket(this);
        }
    }
}
