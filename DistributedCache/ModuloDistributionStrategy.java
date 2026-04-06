public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public int getTargetNodeIndex(String key, int totalNodes) {
        if (totalNodes <= 0) return 0;
        return Math.abs(key.hashCode() % totalNodes);
    }
}
