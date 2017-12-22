package io.reflectoring.jiraalerts.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

/**
 * Service for Hashing the password of the user.
 */
@Service
public class HashService {

	private static final String SHA_512 = "SHA-512";
	private static final String STRING_ENCODING = "UTF-8";

	/**
	 * Hashes the given password with the given salt.
	 *
	 * @param password
	 *            password in clear
	 * @param salt
	 *            salt of the user
	 * @return hashed password
	 */
	public String hashPassword(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance(SHA_512);
			md.update(salt.getBytes(STRING_ENCODING));
			byte[] bytes = md.digest(password.getBytes(STRING_ENCODING));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
			throw new IllegalStateException("Hashing of password failed: ", exception);
		}
	}
}
