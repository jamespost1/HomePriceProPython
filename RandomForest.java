import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomForest {
    private List<DecisionTree> trees;
    private int numTrees;
    private int maxDepth;
    private Random random;

    public RandomForest(int numTrees, int maxDepth) {
        this.numTrees = numTrees;
        this.maxDepth = maxDepth;
        this.trees = new ArrayList<>();
        this.random = new Random();
    }

    public void train(double[][] X, int[] y) {
        for(int i = 0; i < numTrees; i++){
            // create numTrees Decision Trees
            DecisionTree tree = new DecisionTree();
            // generate random indices for bootstrapping
            int[] bootstrapIndices = generateBootstrapSampleIndices(X.length);
            double[][] bootstrapX = new double[bootstrapIndices.length][X[0].length];
            int[] bootstrapY = new int[bootstrapIndices.length];
            for(int j=0; j <bootstrapIndices.length; j++){
                int index = bootstrapIndices[j];
                bootstrapX[j] = X[index];
                bootstrapY[j] = y[index];
            }
            tree.train(bootstrapX, bootstrapY, maxDepth);
            trees.add(tree);

        }
    }

    public int predict(double[] instance) {
        int[] predictions = new int[trees.size()];
        for(int i=0; i < trees.size(); i++){
            predictions[i] = trees.get(i).predict(instance);
        }
        return aggregate(predictions);
    }

    private int aggregate(int[] predictions){
        int[] counts = new int[2];
        for(int prediction : predictions) {
            counts[prediction]++;
        }
        if (counts[0] > counts[1]) {
            return 0;
        }
        else {
            return 1;
        }
    }

    private int[] generateBootstrapSampleIndices(int dataSize) {
        int[] indices = new int[dataSize];
        for(int i = 0; i < dataSize; i++) {
            indices[i] = random.nextInt(dataSize);
        }
        return indices;
    }
}