public class AttendanceRule implements EligibilityRule {
    @Override
    public boolean isSatisfied(StudentProfile profile) {
        return profile.attendancePct >= 75;
    }

    @Override
    public String getReason() {
        return "attendance below 75";
    }
}
