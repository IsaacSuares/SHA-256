import java.nio.charset.StandardCharsets;
import java.lang.*;

public class Main
{
	public static String toBitSize(String bin, int bitSize){
		StringBuilder binSB = new StringBuilder();
		int remainingZeros = bitSize - bin.length();
		for(int i = 0; i < remainingZeros; i++){
			binSB.append("0");
		}
		return binSB + bin;
	}

	public static String xorSum(String s1, String s2){
		StringBuilder result = new StringBuilder();
		if(s1.length() != s2.length()){
			System.out.println("Different sizes of strings");
			return null;
		}else{
			for(int i = 0; i < s1.length(); i++){
				if(s1.charAt(i) == s2.charAt(i)){
					result.append("0");
				}else {
					result.append("1");
				}
			}
		}
		return result.toString();
	}

	public static String xorSum(String s1, String s2, String s3){
		String firstStep = xorSum(s1, s2);
		return xorSum(firstStep, s3);
	}

	public static String rightRotate (String toRotate, int piece){
		String strPiece = toRotate.substring(toRotate.length()-piece);
		String strRemaining = toRotate.substring(0, toRotate.length()-piece);
		return strPiece + strRemaining;
	}

	public static String rightShift(String toShift, int steps){
		String rightPiece = toShift.substring(0, toShift.length() - steps);
		return toBitSize(rightPiece, 32);
	}

	public static String addBinary(String x, String y){
		int i = x.length() - 1, j = y.length() - 1;
		int carry = 0;
		StringBuilder result = new StringBuilder();
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (i >= 0) {
				sum += x.charAt(i) - '0';
			}
			if (j >= 0) {
				sum += y.charAt(j) - '0';
			}
			if (sum == 0 || sum == 1) {
				result.append(sum);
				carry = 0;
			}
			else if (sum == 2) {
				result.append("0");
				carry = 1;
			}
			else {
				result.append("1");
				carry = 1;
			}
			i--;
			j--;
		}
		if (carry == 1) {
			result.append("1");
		}
		String finalResult = result.reverse().toString();
		return finalResult.substring(finalResult.length()-32);
	}

	public static String addBinary(String x, String y, String z) {
		String firstStep = addBinary(x, y);
		return addBinary(firstStep, z);
	}

	public static String addBinary(String x, String y, String z, String a) {
		String firstStep = addBinary(x, y, z);
		return addBinary(firstStep, a);
	}
	
	public static String addBinary(String x, String y, String z, String a, String b) {
		String firstStep = addBinary(x, y, z, a);
		return addBinary(firstStep, b);
	}
	
	public static String toBinary(int toConvert){
	    return Integer.toBinaryString(toConvert);
	}
	
	public static String toBinary(double d, int precision) {
        long wholePart = (long) d;
        return wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, precision);
    }

    private static String wholeToBinary(long l) {
    return Long.toBinaryString(l);
    }

    private static String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }
	
	public static void setAllTrue(boolean[] array){
        for (int i = 0; i < array.length; i++){
            array[i] = true;
        }
    }
    
    public static int[] booleanToInt(boolean[] BooleanArray){
        int count = 0;
        for(boolean bool : BooleanArray){
            if (bool == true){
                count++;
            }
        }
        int[] result = new int[count];
        int index = 0;
        for(int i = 0; i  < BooleanArray.length; i++){
            if (BooleanArray[i] == true){
                result[index] = i;
                index++;
            }
        }
        return result;
    }
    
    public static int[]  findPrimes(int limit){
        boolean[] naturalNumbers = new boolean[limit];
        System.out.println(naturalNumbers[2]);
        setAllTrue(naturalNumbers);
        System.out.println(naturalNumbers[2]);
        int j;
        for (int i = 2; i < Math.sqrt(limit)+1; i++){
            if (naturalNumbers[i] == true){
                j = i*i;
                while(j < limit){
                    naturalNumbers[j] = false;
                    j += i;
                }
            }
        }
        naturalNumbers[0] = false;
        naturalNumbers[1] = false;
        booleanToInt(naturalNumbers);
        return booleanToInt(naturalNumbers);
    }
    
    public static String andSum(String s1, String s2){
        StringBuilder result = new StringBuilder();
		if(s1.length() != s2.length()){
			System.out.println("Different sizes of strings");
			return null;
		}else{
			for(int i = 0; i < s1.length(); i++){
				if(s1.charAt(i) == '1' && s2.charAt(i) == '1'){
					result.append("1");
				}else {
					result.append("0");
				}
			}
		}
		return result.toString();
        
    }
    
    public static String notBin(String s1){
        StringBuilder result = new StringBuilder();
		for(int i = 0; i < s1.length(); i++){
			if(s1.charAt(i) == '1'){
				result.append("0");
			}else{
			    result.append("1");
			}
		}
		return result.toString();
        
    }

    public static void main(String[] args) {
        String s = "hello world";
		int bits = 512;
        byte[] data = null;
		StringBuilder stb = new StringBuilder();
	
        try{
            data = s.getBytes(StandardCharsets.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
        }//Converte a string num array dos respectivos valores UTF-8  


		for (byte b : data){
			String toBin = toBinary(b);
			String byteToBin = toBitSize(toBin, 8);
			//System.out.println("string length=" + byteToBin.length());
			stb.append(byteToBin);
		}//Converte a array de UTF-8 em uma String binária

		stb.append("1");//Add "1" ao final da String binária

		int intBitSize = s.length() * 8;//Converte o tamanho da String inicial em bits

		while(true){
			if(bits < intBitSize + 65){
				bits += 512;
			}else{
				break;
			}
		}//regula o tamanho do message block

		int zeros = (intBitSize + 65 - bits) * -1;
		String strBin = toBinary(intBitSize);
		String strBinSize = toBitSize(strBin, 64);

		System.out.println("zeros " + zeros);
		for(int i = 0; i < zeros; i++){
			stb.append("0");
		}
		//System.out.println("size dec =" + intBitSize);
		//System.out.println("size bin =" + strBinSize);
		stb.append(strBinSize);
		String str = stb.toString();

		//System.out.println(str);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		int lastIndex = 0;
		String[] w = new String[64];
		for(int i = 0; i < 16; i++){
			w[i] = str.substring(lastIndex, lastIndex + 32);
			lastIndex += 32;
			System.out.println("w[" + i + "] " +w[i]);
		}

		for (int i = 16; i < w.length ; i++) {
			String mixStr0 = xorSum(rightRotate(w[i-15], 7), rightRotate(w[i-15], 18), rightShift(w[i-15], 3));
			String mixStr1 = xorSum(rightRotate(w[i-2], 17), rightRotate(w[i-2], 19), rightShift(w[i-2], 10));
			w[i] = addBinary(w[i-16], mixStr0, w[i-7], mixStr1);
			System.out.println("w[" + i + "] " +w[i]);
		}
		
		String[] H = new String[8];
		int[] primes = findPrimes(312);
		for (int i = 0; i < 8; i++){
		    double doubleFrac = Math.sqrt(primes[i])%1;
		    String binFrac = toBinary(doubleFrac, 32).substring(2);
		    H[i] = binFrac;
		    //System.out.println(binFrac);
		}
		
	    final String[] K = new String[64];
		for (int i = 0; i < 64; i++){
		    double doubleFrac = Math.cbrt(primes[i])%1;
		    String binFrac = toBinary(doubleFrac, 32).substring(2);
		    K[i] = binFrac;
		    //System.out.println(binFrac);
		}
		
		String a = H[0];
		String b = H[1];
		String c = H[2];
		String d = H[3];
		String e = H[4];
		String f = H[5];
		String g = H[6];
		String h = H[7];
		
		for(int i = 0; i < 64; i++){
		    String majority = xorSum((andSum(a, b)), (andSum(a, c)), (andSum(b, c)));
		    String soma1 = xorSum(rightRotate(e, 6), rightRotate(e, 11), rightRotate(e, 25));
		    String soma0 = xorSum(rightRotate(a, 2), rightRotate(a, 13), rightRotate(a, 22));
		    String choice = xorSum((andSum(e, f)), (andSum(notBin(e), g)));
		    String temp1 = addBinary(h, soma1, choice, K[i], w[i]);
		    String temp2 = addBinary(soma0, majority);
		    
	    	h = g;
		    g = f;
		    f = e;
		    e = addBinary(d, temp1);
		    d = c;
		    c = b;
		    b = a;
		    a = addBinary(temp1, temp2);
		    
		    System.out.println("["+i+"] "+a);
		}
		
		H[0] = addBinary(H[0], a);
		H[1] = addBinary(H[1], b);
		H[2] = addBinary(H[2], c);
		H[3] = addBinary(H[3], d);
		H[4] = addBinary(H[4], e);
		H[5] = addBinary(H[5], f);
		H[6] = addBinary(H[6], g);
		H[7] = addBinary(H[7], h);
		
		StringBuilder sha2 = new StringBuilder();
		for(int i = 0; i < 8; i++){
		    long decimal = Long.parseLong(H[i],2);
            String hexStr = Long.toString(decimal,16);
            sha2.append(toBitSize(hexStr, 8));
		}
		
		String SHA256 = sha2.toString();
		System.out.println(SHA256);
		
		
		
		

    }
}
