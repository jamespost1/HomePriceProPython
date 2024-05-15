import java.io.*;
import java.util.Scanner;

public class Suggestion {

    public Suggestion(String path, int zip, double[] sample) throws Exception{
        try {   
            RandomForest forest = new RandomForest(100, 3);
            int prediction = forest.predict(sample);
            File file = new File(path);
            Scanner sc = new Scanner(file).useDelimiter(",");
            while(sc.next() != Integer.toString(zip)){
                sc.next();
            }
            for(int i=0; i < 5; i++){
                sc.next();
            }
            
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}