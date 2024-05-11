public class DataContainer {

    private double inputBeds;
    private double inputBaths;
    private double inputBudget;

    //Passing through the 3 main inputs that would check for profit
    public DataContainer(double inputBudget , double inputBeds , double inputBaths){
        if (isWithinBudget(inputBudget) && isWithinBedInput(inputBaths) && isWithinBathInput(inputBaths)){
            this.inputBudget = inputBudget;
            this.inputBeds = inputBeds;
            this.inputBaths = inputBaths;
        }
    }
    
    //Collection of methods to give a range of what the user can be in inorder to make profit
    public boolean isWithinBudget(double userInputBudget){
        double budgetRange = 0.20 * inputBudget;
        double upperBound = inputBudget + budgetRange;
        double lowerBound = inputBudget - budgetRange ;
        return userInputBudget >= lowerBound && userInputBudget <= upperBound;
    }


    public boolean isWithinBedInput(double userInputBeds){
        return inputBeds == userInputBeds - 1 || inputBeds == userInputBeds + 1;
    }

    public boolean isWithinBathInput(double userInputBaths){
        return inputBaths == userInputBaths - 1 || inputBaths == userInputBaths + 1;
    }

    //Looks to see if each input is within its range and will notify the user that there will be profit
    public String checkInputRange (double userInputBudget , double userInputBed, double userInputBath  ){
        if (isWithinBudget(userInputBudget) && isWithinBedInput(userInputBed) && isWithinBathInput(userInputBath)){
            return "You will receive profit";
        }else {
            return  "You will not receive profit";
        }
    }

   // public void targetLabelling()

}
