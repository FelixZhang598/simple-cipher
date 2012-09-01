/**
*@author Felix-Zhang
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
*<h1>Encryption Class</h1>
*/
public class Encrypt {
	
	public static void processEncrypt() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the name of the file you want to encrypt:");
		String fileName = br.readLine();
		System.out.println("Please enter the encryption password *** [Hint: You must enter the characters in the ASCII table]:");
		String password = br.readLine();
		
		//Summary of the SHA-256
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] keyBytes = md.digest(password.getBytes());
		
		FileOutputStream fouts = new FileOutputStream("Cipher");
		
		File fileIn = new File(fileName);
		FileInputStream fins = new FileInputStream(fileIn);
		
		//Convert key according to the input character
		SecretKey  key = new SecretKeySpec(keyBytes,"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(md.digest("Hello".getBytes()), 1, 16);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		
		//Encrypt
		int fileSize = (int) fileIn.length();
		byte[] bs = new byte[fileSize]; 
		fins.read(bs);
		fouts.write(cipher.doFinal(bs));
		
		br.close();
		fins.close();
		fouts.close();
		System.out.println("********************Encryption is complete********************");

	}
}
