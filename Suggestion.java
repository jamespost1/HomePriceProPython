import java.io.*;
import java.util.Scanner;

public class Suggestion {

    public Suggestion(String path, int zip, double[] sample) throws Exception{
        try {   
            RandomForest forest = new RandomForest(100, 3);
            int prediction = forest.predict(sample);
            File file = new File(path);
            Scanner sc = new Scanner(file).useDelimiter(",");
            double[][] values = null;
            String cur = sc.next();
            //find same zip first
            while(cur != Integer.toString(zip)){
                cur = sc.next();
            }
            //go to next price
            for(int i=0; i < 4; i++){
                cur = sc.next();
            }
            int row = 0;
            for(int i = 0; i < 100; i++){
                if(values.length > 10){
                    break;
                }
                if(Double.parseDouble(cur) > sample[0]){
                    
                }
            }
            if(Double.parseDouble(cur) > sample[0])
            while(values.length < 11){
                if (Double.parseDouble(sc.next()) < sample[0]){
                    //go to next price
                    for(int i=0; i < 10; i++){
                        sc.next();
                    }
                }
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}