public class Iterative {

	public static BitList complement( BitList in ) {

		BitList newBit = new BitList();
		Iterator inBI = in.iterator();
		Iterator newBI = newBit.iterator();
		while(inBI.hasNext()){
			int current = inBI.next();
			if (current == 1){
				newBI.add(0);
			} else{
				newBI.add(1);
			}
		}
		return newBit;
	}

	public static BitList or( BitList a, BitList b ) {
		Iterator bIA = a.iterator();
		if((a.toString().length() != b.toString().length()) || (!bIA.hasNext())){
			throw new IllegalArgumentException("BitLists cannot be empty or have different sizes");
		}
		BitList newBit = new BitList();
		Iterator newBI = newBit.iterator();
		Iterator bIB = b.iterator();
		while(bIA.hasNext()){
			int currentA = bIA.next();
			int currentB = bIB.next();
			if ((currentA == 1) || (currentB == 1)){
				newBI.add(1);
			} else{
				newBI.add(0);
			}
		}
		return newBit;
			

	}

	public static BitList and( BitList a, BitList b ) {
		Iterator bIA = a.iterator();
		if((a.toString().length() != b.toString().length()) || (!bIA.hasNext())){
			throw new IllegalArgumentException("BitLists cannot be empty or have different sizes");
		}
		BitList newBit = new BitList();
		Iterator newBI = newBit.iterator();
		
		Iterator bIB = b.iterator();
		while(bIA.hasNext()){
			int currentA = bIA.next();
			int currentB = bIB.next();
			if ((currentA == 1) && (currentB == 1)){
				newBI.add(1);
			} else{
				newBI.add(0);
			}
		}
		return newBit;
	}

	public static BitList xor( BitList a, BitList b ) {
		Iterator bIA = a.iterator();
		if((a.toString().length() != b.toString().length()) || (!bIA.hasNext())){
			throw new IllegalArgumentException("BitLists cannot be empty or have different sizes");
		}
		BitList newBit = new BitList();
		Iterator newBI = newBit.iterator();
		
		Iterator bIB = b.iterator();
		while(bIA.hasNext()){
			int currentA = bIA.next();
			int currentB = bIB.next();
			if (currentA == currentB){
				newBI.add(0);
			} else{
				newBI.add(1);
			}
		}
		return newBit;
	}
}