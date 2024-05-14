public class Driver {
    public static void main(String[] args) {
        // Sample dataset (features and labels)
        double[][] X = {
            {5.1, 3.5, 1.4, 0.2},
            {4.9, 3.0, 1.4, 0.2},
            {6.2, 3.4, 5.4, 2.3},
            {5.9, 3.0, 5.1, 1.8},
            {5.1, 3.5, 1.4, 0.2},
            {4.9, 3.0, 1.4, 0.2},
            // Add more samples as needed
        };
        int[] y = {0, 0, 1, 1, 0, 0}; // Sample target labels (0 and 1 for binary classification)

        // Create and train a Random Forest Classifier
        RandomForest forest = new RandomForest(100, 3); // 100 trees, maximum depth 3
        forest.train(X, y);

        // Make predictions on new instances
        double[] newSample = {5.5, 2.8, 4.5, 1.3}; // New sample to predict
        int prediction = forest.predict(newSample);
        System.out.println("Prediction for new sample: " + prediction);
    }
}
