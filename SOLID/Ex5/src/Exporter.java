public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        ExportRequest normalized = normalize(req);
        if (!canExport(normalized)) {
            throw new IllegalArgumentException(getUnsupportedMessage(normalized));
        }
        return doExport(normalized);
    }

    protected ExportRequest normalize(ExportRequest req) {
        if (req == null) return new ExportRequest("", "");
        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;
        if (title == req.title && body == req.body) return req;
        return new ExportRequest(title, body);
    }

    protected boolean canExport(ExportRequest req) { return true; }

    protected String getUnsupportedMessage(ExportRequest req) {
        return "unsupported export";
    }

    protected abstract ExportResult doExport(ExportRequest req);
}
