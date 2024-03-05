package estateagentguiproject;

public class EstateAgent implements IEstateAgent {
    
    @Override
    public double CalculateCommission(Double price, Double comm){
        //Calculates and returns the final commission cut
        Double decPercent = comm / 100;
        Double commission = price * decPercent;
        return commission;
    }
}
