import java.nio.charset.StandardCharsets;

public class Main
{
	public static String toBitSize(String bin, int bitSize){
		StringBuilder binSB = new StringBuilder();
		int remainingZeros = bitSize - bin.length();
		for(int i = 0; i < remainingZeros; i++){
			binSB.append("0");
		}
		return binSB.toString() + bin;
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
        
    }
}
