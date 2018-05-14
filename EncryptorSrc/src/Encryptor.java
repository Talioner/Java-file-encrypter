import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {

	public static void processFile(int cipherMode, String key, File fileInput, File fileOutput) {
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);
			
			FileInputStream inputStream = new FileInputStream(fileInput);
			byte[] inputData = new byte[(int) fileInput.length()];
			inputStream.read(inputData);
			inputStream.close();
			
			byte[] outputData = cipher.doFinal(inputData);
			
			FileOutputStream outputStream = new FileOutputStream(fileOutput);
			outputStream.write(outputData);
			outputStream.close();
			
		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
       }
		
	}
	
}
