public class CGRRule implements EligibilityRule {
    @Override
    public boolean isSatisfied(StudentProfile profile) {
        return profile.cgr >= 8.0;
    }

    @Override
    public String getReason() {
        return "CGR below 8.0";
    }
}
