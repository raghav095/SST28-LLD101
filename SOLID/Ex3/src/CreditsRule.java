public class CreditsRule implements EligibilityRule {
    @Override
    public boolean isSatisfied(StudentProfile profile) {
        return profile.earnedCredits >= 20;
    }

    @Override
    public String getReason() {
        return "credits below 20";
    }
}
