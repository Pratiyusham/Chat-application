package com.example.chatapplication.Models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.chatapplication.Encryption.AES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Message {
    private String messageId, message, senderId;
    private long timestamp;
    private int feeling = -1;

    AES encrypt = new AES();

    public Message() {
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Message(String message, String senderId, long timestamp) {
        this.message = encrypt.AESEncryptionMethod(message);
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public String getMessage() {
        try {
            return encrypt.AESDecryptionMethod(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void setMessage(String message) {
        this.message = encrypt.AESEncryptionMethod(message);
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getFeeling() {
        return feeling;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }


}
