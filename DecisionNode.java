public class DecisionNode {
    private int featureIndex;
    private double splitValue;
    private DecisionNode left;
    private DecisionNode right;
    private int label;

    public DecisionNode(int label) {
        this.label = label;
    }

    public DecisionNode(int featureIndex, double splitValue, DecisionNode left, DecisionNode right) {
        this.featureIndex = featureIndex;
        this.splitValue = splitValue;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public int getFeatureIndex() {
        return featureIndex;
    }

    public double getSplitValue() {
        return splitValue;
    }

    public DecisionNode getLeft() {
        return left;
    }

    public DecisionNode getRight() {
        return right;
    }

    public int getLabel() {
        return label;
    }
}