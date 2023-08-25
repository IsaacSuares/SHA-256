import java.nio.charset.StandardCharsets;
import java.lang.*;
	
public class Main
{
	/**
 	* Formata uma representação binária, hexadecimal ou decimal de tamanho específico, preenchendo com zeros à esquerda, se necessário.
	*
 	* @param bin A representação numérica original como uma string.
 	* @param bitSize O tamanho desejado da representação numérica final.
 	* @return A representação numéricaa formatada no tamanho especificado.
 	*/
	public static String toBitSize(String bin, int bitSize){
		StringBuilder binSB = new StringBuilder();
		int remainingZeros = bitSize - bin.length();
		for(int i = 0; i < remainingZeros; i++){
			binSB.append("0");
		}
		return binSB + bin;
	}
	
	/**
 	* Realiza uma operação de "soma" XOR bit a bit entre duas representações binárias de mesmo tamanho.
 	*
 	* @param s1 A primeira representação binária a ser usada na operação XOR.
 	* @param s2 A segunda representação binária a ser usada na operação XOR.
 	* @return Uma nova representação binária resultante da operação XOR entre as duas entradas.
 	*         Retorna null se as representações binárias não tiverem o mesmo tamanho.
 	*/
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

	/**
 	* Realiza uma operação de "soma" XOR bit a bit em três representações binárias de mesmo tamanho,
 	* usando uma abordagem sequencial de duas operações de xorSum.
 	*
 	* @param s1 A primeira representação binária a ser usada na operação XOR intermediária.
 	* @param s2 A segunda representação binária a ser usada na operação XOR intermediária.
 	* @param s3 A terceira representação binária a ser usada na operação XOR final.
 	* @return Uma nova representação binária resultante da operação XOR entre as três entradas.
 	* @see #xorSum(String, String) Função usada na primeira etapa da operação.
 	*/
	public static String xorSum(String s1, String s2, String s3){
		String firstStep = xorSum(s1, s2);
		return xorSum(firstStep, s3);
	}

	/**
 	* Realiza a operação lógica AND entre duas representações binárias de mesmo tamanho e retorna o resultado.
 	*
 	* @param s1 A primeira representação binária a ser usada na operação AND.
 	* @param s2 A segunda representação binária a ser usada na operação AND.
 	* @return Uma nova string resultante da operação lógica AND entre as duas entradas.
 	*/
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

	/**
 	* Calcula a negação lógica de uma representação binária, trocando '0' por '1' e '1' por '0'.
 	*
 	* @param s1 A string binária a ser negada.
 	* @return Uma nova string resultante da negação lógica da entrada.
 	*/
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

	/**
 	* Realiza uma rotação à direita em uma string, movendo um "pedaço" especificado do final para o início da string.
 	*
 	* @param toRotate A string a ser rotacionada.
 	* @param piece O tamanho do "pedaço" que será movido do final para o início da string.
 	* @return Uma nova string resultante da rotação à direita.
 	*/
	public static String rightRotate (String toRotate, int piece){
		if (piece >= toRotate.length()) {
        		// Tratamento para quando o "pedaço" é maior ou igual ao tamanho da string
        		throw new StringIndexOutOfBoundsException("O tamanho do 'pedaço' é maior ou igual ao tamanho da string");
    		}
		String strPiece = toRotate.substring(toRotate.length()-piece);
		String strRemaining = toRotate.substring(0, toRotate.length()-piece);
		return strPiece + strRemaining;
	}

	/**
 	* Realiza um deslocamento à direita em uma string, removendo um número especificado de caracteres do final e preenchendo
 	* com zeros à esquerda para que a string resultante tenha um comprimento de 32 caracteres.
 	*
 	* @param toShift A string a ser deslocada.
 	* @param steps O número de caracteres a serem removidos do final da string.
 	* @return Uma nova string resultante do deslocamento à direita e preenchimento com zeros à esquerda.
 	* @see #toBitSize(String, int) Função usada para completar a string com zeros à esquerda.
 	*/
	public static String rightShift(String toShift, int steps){
		if (steps >= toShift.length()) {
        		throw new StringIndexOutOfBoundsException("O número de 'steps' é maior ou igual ao tamanho da string");
    		}
		String rightPiece = toShift.substring(0, toShift.length() - steps);
		return toBitSize(rightPiece, 32);
	}

	/**
 	* Realiza uma adição binária entre duas representações binárias e retorna os últimos 32 bits do resultado.
 	*
 	* @param x A primeira representação binária a ser somada.
 	* @param y A segunda representação binária a ser somada.
 	* @return Uma nova string representando os últimos 32 bits da adição binária das entradas.
 	*/
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

	/**
 	* Realiza uma adição binária entre três representações binárias e retorna o resultado.
 	*
 	* @param x A primeira representação binária a ser somada.
 	* @param y A segunda representação binária a ser somada.
 	* @param z A terceira representação binária a ser somada.
 	* @return Uma nova string representando a adição binária das três entradas.
 	* @see #addBinary(String, String) Função usada para as etapas intermediárias de adição.
 	*/
	public static String addBinary(String x, String y, String z) {
		String firstStep = addBinary(x, y);
		return addBinary(firstStep, z);
	}

	/**
 	* Realiza uma adição binária entre quatro representações binárias e retorna o resultado.
 	*
 	* @param x A primeira representação binária a ser somada.
 	* @param y A segunda representação binária a ser somada.
 	* @param z A terceira representação binária a ser somada.
 	* @param a A quarta representação binária a ser somada.
 	* @return Uma nova string representando a adição binária das quatro entradas.
 	* @see #addBinary(String, String, String) Função usada para as etapas intermediárias de adição.
 	*/
	public static String addBinary(String x, String y, String z, String a) {
		String firstStep = addBinary(x, y, z);
		return addBinary(firstStep, a);
	}

	/**
 	* Realiza uma adição binária entre cinco representações binárias e retorna o resultado.
 	*
 	* @param x A primeira representação binária a ser somada.
 	* @param y A segunda representação binária a ser somada.
 	* @param z A terceira representação binária a ser somada.
 	* @param a A quarta representação binária a ser somada.
 	* @param b A quinta representação binária a ser somada.
 	* @return Uma nova string representando a adição binária das cinco entradas.
 	* @see #addBinary(String, String, String, String) Função usada para as etapas intermediárias de adição.
 	*/
	public static String addBinary(String x, String y, String z, String a, String b) {
		String firstStep = addBinary(x, y, z, a);
		return addBinary(firstStep, b);
	}

	/**
 	* Converte um valor inteiro para sua representação binária como uma string.
 	*
 	* @param toConvert O valor inteiro a ser convertido.
 	* @return Uma string contendo a representação binária do valor inteiro.
 	*/
	public static String toBinary(int toConvert){
	    return Integer.toBinaryString(toConvert);
	}

	/**
 	* Converte um número decimal em sua representação binária como uma string, com a precisão especificada.
 	*
 	* @param d O número decimal a ser convertido.
 	* @param precision O número de casas decimais a serem consideradas na conversão.
 	* @return Uma string contendo a representação binária do número decimal com a precisão especificada.
 	* @see #wholeToBinary(long) Função usada para converter a parte inteira do número.
 	* @see #fractionalToBinary(double, int) Função usada para converter a parte fracionária do número.
	*/
	public static String toBinary(double d, int precision) {
        	long wholePart = (long) d;
        	return wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, precision);
    	}

	/**
 	* Converte a parte inteira de um número em sua representação binária como uma string.
 	*
 	* @param l A parte inteira do número a ser convertida.
 	* @return Uma string contendo a representação binária da parte inteira do número.
 	*/
    	private static String wholeToBinary(long l) {
    		return Long.toBinaryString(l);
    	}

	/**
 	* Converte a parte fracionária de um número em sua representação binária como uma string, com a precisão especificada.
 	*
 	* @param num A parte fracionária do número a ser convertida.
 	* @param precision O número de casas decimais a serem consideradas na conversão.
 	* @return Uma string contendo a representação binária da parte fracionária do número.
 	*/
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

	/**
 	* Define todos os elementos de um array de booleanos como verdadeiros (true).
 	*
 	* @param array O array de booleanos a ser modificado.
 	*/
	public static void setAllTrue(boolean[] toTrue){
        	for (int i = 0; i < toTrue.length; i++){
            		toTrue[i] = true;
        	}
    	}

	/**
 	* Converte um array de booleanos em um array de inteiros contendo os índices onde os valores são verdadeiros (true).
 	*
 	* @param BooleanArray O array de booleanos a ser convertido.
 	* @return Um novo array de inteiros contendo os índices onde os valores são verdadeiros (true) no array original.
 	*/
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

	/**
 	* Encontra e retorna um array contendo todos os números primos dentro de uma faixa de números especificada.
 	*
 	* @param limit O limite superior da faixa de números a serem verificados.
 	* @return Um array de inteiros contendo todos os números primos dentro da faixa especificada.
 	* @see #setAllTrue(boolean[]) Função usada para inicializar o array de números naturais.
 	* @see #booleanToInt(boolean[]) Função usada para converter o array de booleanos em um array de inteiros.
 	*/
    	public static int[]  findPrimes(int limit){
        	boolean[] naturalNumbers = new boolean[limit];
        	//System.out.println(naturalNumbers[2]);
        	setAllTrue(naturalNumbers);
        	//System.out.println(naturalNumbers[2]);
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
