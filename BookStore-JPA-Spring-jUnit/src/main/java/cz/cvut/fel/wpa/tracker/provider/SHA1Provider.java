/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.provider;

import java.security.MessageDigest;
import org.springframework.stereotype.Component;

/**
 * SHA1 hash functionprovider
 * @author mickapa1
 */
@Component("hashProvider")
public class SHA1Provider implements HashProvider{
    private static String convertToHex(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    builder.append((char) ('0' + halfbyte));
                } else {
                    builder.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return builder.toString();
    }

    @Override
    public String computeHash(String s) {
        MessageDigest md = null;
        byte[] sha1hash = null;
        try {
            md = MessageDigest.getInstance("SHA-1");

            sha1hash = new byte[40];
            md.update(s.getBytes("utf8"), 0, s.length());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}
