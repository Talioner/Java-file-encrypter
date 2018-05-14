import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.crypto.Cipher;

public class FinalApp {

	private static File fileToEncrypt, fileToDecrypt;
	private static String secretKey = "This is a secret";
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		
		int option = 100;
		while(option != 0) {
			System.out.print("Encryption key: " + secretKey + "\nChoose option:\n1. Choose encryption key\n2. Encrypt file\n3. Decrypt file\n0. Close\n");
			option = reader.nextInt();
			reader.nextLine();
			switch(option) {
			case 1:
				System.out.print("Input encryption key:\n");
				secretKey = reader.nextLine();
				break;
			case 2:
				System.out.print("Input path to the file:\n");
				String enc_path = reader.nextLine();
				fileToEncrypt = new File(enc_path);
				if(fileToEncrypt == null) {
					System.out.print("There is no such file\n");
					break;
				}	
				File inputFile = fileToEncrypt;				
				String encryptedFileName = Paths.get(inputFile.getParent(), "encrypted-" + inputFile.getName()).toAbsolutePath().toString();
				File encryptedFile = new File(encryptedFileName);
				Encryptor.processFile(Cipher.ENCRYPT_MODE, secretKey, inputFile, encryptedFile);
				break;
			case 3:
				System.out.print("Input path to the file:\n");
				String dec_path = reader.nextLine();
				fileToDecrypt = new File(dec_path);
				if(fileToDecrypt == null) {
					System.out.print("There is no such file\n");
					break;
				}
				File inputFileDecrypt = fileToDecrypt;				
				String decryptedFileName = Paths.get(inputFileDecrypt.getParent(), "decrypted-" + inputFileDecrypt.getName()).toAbsolutePath().toString();
				File decryptedFile = new File(decryptedFileName);
				Encryptor.processFile(Cipher.DECRYPT_MODE, secretKey, inputFileDecrypt, decryptedFile);
				break;
			case 0:
				reader.close();
				break;
			}
		}

	}
}
