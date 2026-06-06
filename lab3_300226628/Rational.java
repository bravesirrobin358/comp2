public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
	     this.numerator = numerator;
       this.denominator = 1;
       this.reduce();
    }

    public Rational(int numerator, int denominator) {
	     this.numerator = numerator;
       this.denominator = denominator;
       this.reduce();
    }

    // getters

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
	     Rational sum = new Rational(1,1);
       sum.denominator = this.getDenominator() * other.getDenominator();
       sum.numerator = this.getNumerator() * other.getDenominator() + this.getDenominator() * other.getNumerator();
       sum.reduce();
       return sum;
    }

    public static Rational plus(Rational a, Rational b) {
    	Rational sum = new Rational(1,1);
      sum.denominator = a.getDenominator() * b.getDenominator();
      sum.numerator = a.getNumerator() * b.getDenominator() + a.getDenominator() * b.getNumerator();
      sum.reduce();
      return sum;
    }

    // Transforms this number into its reduced form

    private void reduce() {
      int greatestCommonDenominator = gcd(this.getNumerator(), this.getDenominator());
      this.numerator = this.getNumerator() / greatestCommonDenominator;
      this.denominator = this.getDenominator() / greatestCommonDenominator;
    }

    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
      // Note that the loop below, as-is, will time out on negative inputs.
      // The gcd should always be a positive number.
      // Add code here to pre-process the inputs so this doesn't happen.
      boolean negative = false;
      if (a <0 && b<0){
        a = a*-1;
        b = b*-1;
        negative = true;
      } else if (a<0) {
        a = a*-1;
      } else if (b<0) {
        b = b*-1;
      }
    	while (a != b){
    	    if (a > b){
    		     a = a - b;
          }else{
    		     b = b - a;
          } 
      }
      if (negative){
        a = a*-1;
      }
    	return a;
    }

    public int compareTo(Rational other) {
      if (this.getDenominator() == other.getDenominator()){
        return this.getNumerator() - other.getNumerator();
      } else {
        return (this.getNumerator() * other.getDenominator() - other.getNumerator() * this.getDenominator());
      }
    }

    public boolean equals(Rational other) {
      this.reduce();
      other.reduce();
      if (this.getDenominator() == other.getDenominator() && this.getNumerator() == other.getNumerator()){
        return true;
      }else{
        return false;
      }
    }

    public String toString() {
    	String result;
    	if (denominator == 1) {
    	    result = "" + this.getNumerator();
    	} else {
    	    result = this.getNumerator() + "/" + this.getDenominator();
    	}
    	return result;
    }

    public static void main(String[] args){
      Rational c = new Rational(-13,9);
      Rational d = new Rational(5,4);
      c.reduce();
      d.reduce();
      System.out.println("try");
      Rational sum = Rational.plus(c,d);
      System.out.println(c);
      System.out.println(d);
      System.out.println(sum);

    }

}