import java.io.*;

public class CSVReader2 {
	
	public static void main(String[] args) {
		
		String [] dataset1 = new String [2226383];
		String currentLine;
		int c = 0;
		
		//update the path for where dataset is stored.
		String file = "/Users/lyssa./Downloads/S24/softwareEngineering/realtor-data.zip2.csv";

		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null) { //continue reading the next line
			
				String[] values = line.split(",");
				
				currentLine = values[2] + ", " + values[3] + ", " + values[4]+ ", " + values[5] + ", " + values[9];
										
				dataset1[c] = currentLine;
				c++;
				
				
//				for(String index : row) {
//					System.out.printf("%-10s", index);
//				}
//				System.out.println();
				
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
		

		for(int i = 0; i < 635;i++) {
			
			System.out.println(dataset1[i]);

		}

	}//end of main
	

}//end of CSVR2
