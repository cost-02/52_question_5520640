package com.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Arrays;

public class DesEncryptionExample {
    public static void main(String[] args) {
        try {
            String algorithm = "DES";
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);

            // Definizione dei byte per le chiavi
            byte[] encBytes = "12345678".getBytes("UTF8");
            byte[] decBytes = "56781234".getBytes("UTF8");

            // Creazione delle specifiche delle chiavi
            DESKeySpec keySpecEncrypt = new DESKeySpec(encBytes);
            DESKeySpec keySpecDecrypt = new DESKeySpec(decBytes);

            // Generazione delle chiavi
            SecretKey keyEncrypt = keyFactory.generateSecret(keySpecEncrypt);
            SecretKey keyDecrypt = keyFactory.generateSecret(keySpecDecrypt);

            // Creazione dei Cipher
            Cipher cipherEncrypt = Cipher.getInstance(algorithm);
            Cipher cipherDecrypt = Cipher.getInstance(algorithm);

            // Input da cifrare
            String input = "john doe";
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, keyEncrypt);
            byte[] inputBytes = cipherEncrypt.doFinal(input.getBytes());
            System.out.println("inputBytes: " + new String(inputBytes));

            // Decifratura
            cipherDecrypt.init(Cipher.DECRYPT_MODE, keyDecrypt);
            byte[] outputBytes = cipherDecrypt.doFinal(inputBytes);
            System.out.println("outputBytes: " + new String(outputBytes));

            // Stampa delle chiavi
            System.out.println("Key for Encryption: " + Arrays.toString(keyEncrypt.getEncoded()));
            System.out.println("Key for Decryption: " + Arrays.toString(keyDecrypt.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
