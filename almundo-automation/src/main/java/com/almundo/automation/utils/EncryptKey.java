package com.almundo.automation.utils;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptKey {
	
	private static byte[] sharedvector = {
	    0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
	    };
	 static byte[] keyArray = new byte[24];
	 
//	public static void main (String [] args){
//		System.out.println(encryptText("5592f8fd99325b40cba48649"));
//        System.out.println(decryptText("64XDVjq03EJTPuiHwu2P5BQCjxLpdv8HvNUnEQ6UH2c="));
//
//	}
	
	 public static String encryptText(String RawText)
	    {
	        String EncText = "";
	        byte[] keyArray = new byte[24];
	        byte[] temporaryKey;
	        String key = "almundodotcom";
	        byte[] toEncryptArray = null;
	  
	        try
	        {
	 
	            toEncryptArray =  RawText.getBytes("UTF-8");        
	            MessageDigest m = MessageDigest.getInstance("MD5");
	            temporaryKey = m.digest(key.getBytes("UTF-8"));
	 
	            if(temporaryKey.length < 24) // DESede require 24 byte length key
	            {
	                int index = 0;
	                for(int i=temporaryKey.length;i< 24;i++)
	                {                   
	                    keyArray[i] =  temporaryKey[index];
	                }
	            }        
	 
	            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");            
	            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
	            byte[] encrypted = c.doFinal(toEncryptArray);            
	            EncText = Base64.encodeBase64String(encrypted);
	 
	        }
	        catch(NoSuchAlgorithmException nsa)
	        {
	            nsa.printStackTrace();
	        } catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	        return EncText;        
	    }
	
	public static String decryptText(String EncText)
    {
 
        String RawText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = "almundodotcom";

        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));           
 
            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {                  
                    keyArray[i] =  temporaryKey[index];
                }
            }
            
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decodeBase64(EncText));   
 
            RawText = new String(decrypted, "UTF-8");                    
        }
        catch(NoSuchAlgorithmException nsa)
        {
            nsa.printStackTrace();
        } catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
 
        return RawText; 
 
    }
	
	

}
