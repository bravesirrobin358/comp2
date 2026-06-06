public abstract class AbstractSeries implements Series {

    public double[] take(int k) {

        double[] partialSums = new double[k];
        for (int x=0; x<k; x++){
            partialSums[x] = next();
        }
        return partialSums;
        
    }

}