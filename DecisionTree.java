import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecisionTree {
    private DecisionNode root;

    public void train(double[][] X, int[] y, int maxDepth) {
        this.root = buildTree(X, y, maxDepth);
    }

    public int predict(double[] instance) {
        return predict(instance, root);

    }

    private int predict(double[] instance, DecisionNode node) {
        if (node.isLeaf()) {
            return node.getLabel();
        }
        if (instance[node.getFeatureIndex()] <= node.getSplitValue()) {
            return predict(instance, node.getLeft());
        } else {
            return predict(instance, node.getRight());
        }
    }

    private DecisionNode buildTree(double[][] X, int[] y, int maxDepth) {
        if (X.length == 0 || maxDepth == 0) {
            return new DecisionNode(majorityClass(y));
        }

        int numFeatures = X[0].length;
        int numInstances = X.length;

        double[] featureSplit = null;
        int featureIndex = -1;
        double bestGain = Double.MIN_VALUE;

        for (int i = 0; i < numFeatures; i++) {
            double[] values = new double[numInstances];
            for (int j = 0; j < numInstances; j++) {
                values[j] = X[j][i];
            }
            double[] splitInfo = findBestSplit(values, y);
            double infoGain = splitInfo[0];
            if (infoGain > bestGain) {
                bestGain = infoGain;
                featureIndex = i;
                featureSplit = splitInfo;
            }
        }
        if (bestGain == 0) {
            return new DecisionNode(majorityClass(y));
        }

        double splitValue = featureSplit[1];
        int[] leftIndices = new int[(int)featureSplit[2] + 1];
        int[] rightIndices = new int[numInstances - leftIndices.length + 1];
        int leftSize = 0;
        int rightSize = 0;

        for (int i = 0; i < numInstances; i++) {
            if (X[i][featureIndex] <= splitValue) {
                leftIndices[leftSize++] = i;
            } else {
                rightIndices[rightSize++] = i;
            }
        }

        double[][] leftX = new double[leftIndices.length][numFeatures];
        int[] leftY = new int[leftIndices.length];
        for (int i = 0; i < leftIndices.length; i++) {
            int index = leftIndices[i];
            System.arraycopy(X[index], 0, leftX[i], 0, numFeatures);
            leftY[i] = y[index];
        }

        double[][] rightX = new double[rightIndices.length][numFeatures];
        int[] rightY = new int[rightIndices.length];
        for (int i = 0; i < rightIndices.length; i++) {
            int index = rightIndices[i];
            System.arraycopy(X[index], 0, rightX[i], 0, numFeatures);
            rightY[i] = y[index];
        }

        DecisionNode left = buildTree(leftX, leftY, maxDepth - 1);
        DecisionNode right = buildTree(rightX, rightY, maxDepth - 1);

        return new DecisionNode(featureIndex, splitValue, left, right);
    }

    private double[] findBestSplit(double[] values, int[] y) {
        double bestEntropy = Double.MAX_VALUE;
        double bestSplitValue = 0;
        int bestSplitIndex = -1;

        //iterate through possible splits
        for (int i = 1; i < values.length; i++) {
            double splitValue = (values[i - 1] + values[i]) / 2.0;
            int[] leftLabels = Arrays.copyOfRange(y, 0, i);
            int[] rightLabels = Arrays.copyOfRange(y, i, values.length);

            double entropy = (double) i / values.length * calculateEntropy(leftLabels)
                    + (double) (values.length - i) / values.length * calculateEntropy(rightLabels);

            // update
            if (entropy < bestEntropy) {
                bestEntropy = entropy;
                bestSplitValue = splitValue;
                bestSplitIndex = i;
            }
        }

        return new double[] { bestEntropy, bestSplitValue, bestSplitIndex };
    }

    private double calculateEntropy(int[] labels) {
        int n = labels.length;
        if (n == 0) {
            return 0;
        }

        double entropy = 0.0;
        List<Integer> uniqueClasses = getUniqueClasses(labels);
        for (int c : uniqueClasses) {
            double p = (double) countOccurrences(labels, c) / n;
            entropy -= p * Math.log(p) / Math.log(2);
        }
        return entropy;
    }

    private int countOccurrences(int[] array, int value) {
        int count = 0;
        for (int v : array) {
            if (v == value) {
                count++;
            }
        }
        return count;
    }

    private List<Integer> getUniqueClasses(int[] labels) {
        List<Integer> uniqueClasses = new ArrayList<>();
        for (int label : labels) {
            if (!uniqueClasses.contains(label)) {
                uniqueClasses.add(label);
            }
        }
        return uniqueClasses;
    }

    private int majorityClass(int[] y) {
        int[] counts = new int[2];
        for (int value : y) {
            counts[value]++;
        }
        return counts[0] > counts[1] ? 0 : 1;
    }

}
