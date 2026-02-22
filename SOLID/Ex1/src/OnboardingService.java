import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final InputParser parser;
    private final InputValidator validator;

    public OnboardingService(StudentStore store) {
        this.store = store;
        this.parser = new InputParser();
        this.validator = new InputValidator();
    }

    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> kv = parser.parse(raw);

        List<String> errors = validator.getValidationErrors(kv);
        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String name = kv.get("name");
        String email = kv.get("email");
        String phone = kv.get("phone");
        String program = kv.get("program");

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        store.save(rec);

        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + store.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
