/*
 * Created on Dec 13, 2005
 *
 */
package com.metamorf.eform.common.encryptor;


/**
 * @author eRyan
 *
 */
public class XOREncrypter {

    byte[] secret = "com/bankbii/rscf/collsys/util/encryptor/XOREncrypter".getBytes();

    public String encrypt(String password) {

        byte[] encrypted = xor(password.getBytes(), secret);

        return Base64Converter.encode(encrypted);
    }

    public byte[] decrypt(String encPassword) {
        return xor(Base64Converter.decode(encPassword), secret);
    }

    public static byte[] xor(byte[] password, byte[] secret) {
        byte[] result = new byte[password.length];
        for (int i=0; i<password.length; i++) {
            result[i] = (byte)(password[i] ^ secret[i]);
        }
        return result;
    }

}
