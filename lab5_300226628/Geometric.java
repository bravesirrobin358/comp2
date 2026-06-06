public class Geometric extends AbstractSeries {

    private int count = 0;
    private double total = 0;
    
    public double next() {

        total = total + 1/Math.pow(2,count);
        count++;
        return total;

    }
}