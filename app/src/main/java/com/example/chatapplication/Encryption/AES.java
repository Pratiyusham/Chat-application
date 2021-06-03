package com.example.chatapplication.Encryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private String stringMessage;
    private byte encryptionKey[] = {9, 115, 51, 86, 105, 4, -31, -23, -68, 88, 17, 20, 3, -105, 119, -53};
    private Cipher cipher, decipher;
    private SecretKeySpec secretKeySpec;

    public AES() {
        try {

            this.cipher = Cipher.getInstance("AES");
            this.decipher = Cipher.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
    }

    public String getStringMessage() {
        return stringMessage;
    }

    public void setStringMessage(String stringMessage) {
        this.stringMessage = stringMessage;
    }

    public byte[] getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(byte[] encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public Cipher getDecipher() {
        return decipher;
    }

    public void setDecipher(Cipher decipher) {
        this.decipher = decipher;
    }

    public SecretKeySpec getSecretKeySpec() {
        return secretKeySpec;
    }

    public void setSecretKeySpec(SecretKeySpec secretKeySpec) {
        this.secretKeySpec = secretKeySpec;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public String AESEncryptionMethod(String string){
        byte[] stringByte = string.getBytes();
        byte[] encryptedByte = new byte[stringByte.length];

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encryptedByte = cipher.doFinal(stringByte);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        String returnString = null;
        //byte[] encrypted = cipher.doFinal(input);
        returnString = Base64.getEncoder().encodeToString(encryptedByte);
        return returnString;

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public String AESDecryptionMethod(String string) throws UnsupportedEncodingException {
        byte[] EncryptedByte =null;
        try {
            EncryptedByte = Base64.getDecoder().decode(new String(string).getBytes("UTF-8"));
        }
        catch (Exception e){
            return string;
        }
        //byte[] EncryptedByte = string.getBytes("ISO-8859-1");
        String decryptedString = string;

        byte[] decryption;

        try {
            decipher.init(cipher.DECRYPT_MODE, secretKeySpec);
            decryption = decipher.doFinal(EncryptedByte);
            decryptedString = new String(decryption);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return decryptedString;
    }
}
