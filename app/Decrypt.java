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
*<h1>Decryption class</h1>
*/
public class Decrypt {
	
	public static void processDecrypt() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the decryption password *** [Hint: You must enter the characters in the ASCII table]:");
		String password = br.readLine();
		System.out.println("Please enter decrypt the file name:");
		String fileName = br.readLine();
		
		//Summary of the SHA-256
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] keyBytes = md.digest(password.getBytes());
		
		File fileIn = new File("Cipher");
		FileInputStream fins = new FileInputStream(fileIn);
		
		FileOutputStream fouts = new FileOutputStream(fileName);
		
		SecretKey  key = new SecretKeySpec(keyBytes,"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(md.digest("Hello".getBytes()), 1, 16);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		
		//Decrypt
		int fileSize = (int) fileIn.length();
		byte[] bs = new byte[fileSize]; 
		fins.read(bs);
		fouts.write(cipher.doFinal(bs));
		
		br.close();
		fins.close();
		fouts.close();
		System.out.println("********************Decryption is complete********************");

	}
}
