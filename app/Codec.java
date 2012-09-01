/**
*@author Felix-Zhang
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
*<h1>Encryption and decryption entrance class Please replace the Java policy file before running, the official website of the method reference projects</h1>
*/
public class Codec {
	public static void main(String[] args) throws Exception {
	
		//Screen prompts to encrypt or decrypt choice
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
		System.out.println("**************************\n\t Encryption: 0\n\t Decryption: 1\n**************************\n\nPlease enter 0  OR  1");
		String flag = br.readLine();
		if(flag.equals("0")) {Encrypt.processEncrypt(); break;}
		else if(flag.equals("1")) {Decrypt.processDecrypt(); break;}
		else {System.out.println("Input errors, please re-enter:");}
		
		}
	}
}
