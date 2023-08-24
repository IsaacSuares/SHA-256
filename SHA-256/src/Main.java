import java.nio.charset.StandardCharsets;

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
			String toBin = Integer.toBinaryString(b);
			String byteToBin = toBitSize(toBin, 8);
			System.out.println("tamanho da string =" + byteToBin.length());
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
		}

		int zeros = (intBitSize + 65 - bits) * -1;
		String strBin = Integer.toBinaryString(intBitSize);
		String strBinSize = toBitSize(strBin, 64);

		System.out.println("zeros " + zeros);
		for(int i = 0; i < zeros; i++){
			stb.append("0");
		}
		System.out.println("size dec =" + intBitSize);
		System.out.println("size bin =" + strBinSize);
		stb.append(strBinSize);
		String str = stb.toString();

		System.out.println(str);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		int lastIndex = 0;
		System.out.println("str size" + str.length());
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

    }
}
