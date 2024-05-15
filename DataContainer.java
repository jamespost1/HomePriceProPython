import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataContainer{
	
    
     //this is the data set array that is first read in
	static String dataSet[][] = new String [636][5];	
	 int[][] targetLabel = new int[636][2];

	
    
    //reads in CSV raw data and stores in dataSet[][]
	public static void readCSV() {
		
		int c = 0; //row index
		
		
		String file = "HomePricePro/.gitattributes";

		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null) { //continue reading the next line
			
				String[] values = line.split(",");
				
				
				dataSet[c][0] = values[2]; //price
				dataSet[c][1] = values[3]; //bed 
				dataSet[c][2] = values[4]; //bath
				dataSet[c][3] = values[5]; //acre lot
				dataSet[c][4] = values[9]; //zipcode

				c++;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}//end of readCSV()
	
	public static String[][] dataImputation(String data[][]) { //removes data points if missing any attribute
		
		String imputed[][] = new String[dataSet.length][5];
		int m = 0;
		int nl = 0;
		int bk = 0;
		int i;
		
		//enter dataset
		for(int r = 0; r < dataSet.length; r++) {

            //checking for null attributes
			if((data[r][0] == null) || (data[r][0] == null) || (data[r][0] == null) || (data[r][0] == null) || (data[r][0] == null)) {
				nl++;
				continue;
			}else
			
			//per row, check if any attributes are empty
			if(!(data[r][0].isEmpty()) && !(data[r][1].isEmpty()) && !(data[r][2].isEmpty()) && !(data[r][3].isEmpty()) && !(data[r][4].isEmpty())) 
			{
				//add to imputed[][]
				imputed[m][0] = dataSet[r][0];
				imputed[m][1] = dataSet[r][1];
				imputed[m][2] = dataSet[r][2];
				imputed[m][3] = dataSet[r][3];
				imputed[m][4] = dataSet[r][4];
				
				m++;//iterating through rows
			}else 
				bk++; //counting # of data points with missing values
			continue;
		}
		for(i = 0; i < imputed.length; i++) {
			if(imputed[i][0] == null) {
				
				System.out.println("Length: " + i); 
				System.out.println("Null: " + nl); 
				System.out.println("Blank: " + bk); 
				break;
				
			}				                                                                                                                                         
		}
		
		// Create a new 2D array with the desired dimensions
        String[][] newImputed = new String[i][5];

        // Copy elements from the original array to the new array to remove all null values
        for (int q = 0; q < i; q++) {
            for (int j = 0; j < 5; j++) {
                newImputed[q][j] = imputed[q][j];
            }
        }
		
			return newImputed;
	}
	

	//Collection of methods to give a range of what the user can be in order to make profit
     public static boolean isWithinBudget(String userInputBudget, String price){
    	
    	
    	double budgetInput = Double.parseDouble(userInputBudget);
    	double Price = Double.parseDouble(price);
    	
        double range = 0.20 * budgetInput;
        int budgetRange = (int) Math.round(range);
        
        double upperBound = budgetInput + budgetRange;
        double lowerBound = budgetInput - budgetRange ;
                
        return ((Price >= lowerBound) && (Price <= upperBound));
    }
    

	public static void targetLabeling(String userInput, String dataArr[][]) {
		int p = 0;
		int[] targetLabel = new int[dataArr.length]; //capturing the targetlabel for data points
		 
	    for (int i = 0; i < dataArr.length; i++) {
	          	targetLabel[i] = 0;
	    }


		//enter  dataSet[][] to begin comparison at each attribute
    	System.out.println("Listing within 20% of budget: \n");

        for (int i = 0; i < dataArr.length; i++) {
        	
        	//if price is within range, label as possibly profitable -(1)
            if(isWithinBudget(userInput, dataArr[i][0])) {
            		
                System.out.println(i + ". " + dataArr[i][0]);
            	p++;
        		targetLabel[i] = 1;             		
	        }
        }
             //printing targetLabel[]   
        //	for (int k = 0; k < dataArr.length; k++) {
        //        System.out.print(targetLabel[k] + "  \n");
        //	}
    
	System.out.println("Congrats!! You have " + p + " Potential Listings After Budget!");
        
       
       
	}//end of targetLabel()

	
	public static void main(String[] args) {
		
		readCSV(); //reads in raw data and creates dataSet[][]
		
		
      //userInputTEST
    	String userTestInput [][] = new String [2226383][5];
    	userTestInput[0][0] = "100000.0"; //price
    	userTestInput[0][1] = "2";		//bed
    	userTestInput[0][2] = "1";		//bath
    	userTestInput[0][3] = "0.22";	//acre
    	userTestInput[0][4] = "00641";	//zipcode

    	

    	dataSet = dataImputation(dataSet);
    	
    	targetLabeling(userTestInput[0][0], dataSet);


    	//Printing the 2D array 
//      for (int i = 0; i < dataSet.length; i++) {
//          for (int j = 0; j < 5; j++) {
//              System.out.print(dataSet[i][j] + "      ");
//          }
//          System.out.println();
//      }
        
	}//end of main in
	
	
}//end of class
