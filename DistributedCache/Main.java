public class Main {
    public static void main(String[] args) {
        System.out.println("--- Distributed Cache Demonstration ---");

        Database<String, String> database = new Database<>();
        database.put("user:1", "Alice");
        database.put("user:2", "Bob");
        database.put("user:3", "Charlie");
        database.put("user:4", "David");
        database.put("user:5", "Eve");

        DistributedCache<String, String> cacheManager = new DistributedCache<>(
                3, 2, new ModuloDistributionStrategy(), database);

        System.out.println("\n--- Step 1: Initial Population ---");
        System.out.println("Fetched: " + cacheManager.get("user:1"));
        System.out.println("Fetched: " + cacheManager.get("user:1"));

        System.out.println("\n--- Step 2: Distribution Demo ---");
        cacheManager.put("user:1", "Alice UPDATED");
        cacheManager.put("user:2", "Bob");
        cacheManager.put("user:3", "Charlie");
        cacheManager.put("user:4", "David");
        cacheManager.put("user:5", "Eve");

        System.out.println("\n--- Step 3: Eviction Policy (LRU) Demo ---");
        System.out.println("Fetched: " + cacheManager.get("user:1"));
        
        cacheManager.put("user:1", "Alice Final");
        
        cacheManager.put("item:100", "Data 100");
        cacheManager.put("item:200", "Data 200");
        cacheManager.put("item:300", "Data 300");
        cacheManager.put("item:400", "Data 400");

        System.out.println("\n--- Step 4: Final Fetch Check ---");
        System.out.println("Fetched (Cache or DB): " + cacheManager.get("user:1"));
    }
}
