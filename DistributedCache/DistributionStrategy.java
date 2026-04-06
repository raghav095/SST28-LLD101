public interface DistributionStrategy {
    int getTargetNodeIndex(String key, int totalNodes);
}
