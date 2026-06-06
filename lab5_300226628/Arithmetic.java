public class Arithmetic extends AbstractSeries {

    private int count = 1;
    private int total = 0;

    public double next() {
        total = total + count;
        count++;
        return total;
        
    }
}