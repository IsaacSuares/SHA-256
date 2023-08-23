public class Main
{
    public static void main(String[] args) {
        String s = "Hello Worldaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	int bits = 512;
        byte[] data = null;
	StringBuilder stb = new StringBuilder();
	
	
        try{
            data = s.getBytes("UTF-8");    
        }catch(Exception e){
            e.printStackTrace();
        }
	for (byte b : data){
		String byteToBin = Integer.toBinaryString((b+256)%256);
		stb.append(byteToBin);
	}
	stb.append("1");
	String str = stb.toString();
	System.out.println(str);

	int intBitSize = s.length() * 8;

	while(true){
		if(bits < intBitSize + 65){
			bits += bits;
		}else{
			break;
		}
	}

	System.out.println(intBitSize);
	System.out.println(bits);
	int zeros = (intBitSize + 65 - bits) * -1;
	String strBitSize = Integer.toBinaryString((intBitSize+256)%256);
	//System.out.println(strBitSize);
	System.out.println(zeros);
        
    }
}
