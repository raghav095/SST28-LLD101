public class DisciplinaryRule implements EligibilityRule {
    @Override
    public boolean isSatisfied(StudentProfile profile) {
        return profile.disciplinaryFlag == LegacyFlags.NONE;
    }

    @Override
    public String getReason() {
        return "disciplinary flag present";
    }
}
