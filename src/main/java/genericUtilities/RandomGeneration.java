package genericUtilities;

import java.util.Random;

public class RandomGeneration {
	Random ran= new Random();
	
	public String ranNum() {
		return 1000+ran.nextInt(9000)+"";
	}
	
	public String ranString(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<length;i++) {
			sb.append(chars.charAt(ran.nextInt(chars.length())));
		}
		return sb.toString();
	}

}
