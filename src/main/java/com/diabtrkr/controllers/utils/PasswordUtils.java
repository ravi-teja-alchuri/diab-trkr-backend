package com.diabtrkr.controllers.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

	/**
	 * Pulled from
	 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 */

	@Autowired
	TokenUtils tu;

	private static final Logger logger = LogManager.getLogger();

	private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final String SECURE_RANDAOM_ALGORITHM = "SHA1PRNG";
	private static final int ITERATIONS = 1000;

	public String getEncryptedPassword(String password) {
		String encryptPassword = null;
		try {
			char[] chars = password.toCharArray();
			byte[] salt = getSalt();

			PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
			byte[] hash = skf.generateSecret(spec).getEncoded();
			encryptPassword = ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.error("unable to encrypt password. error: {}", tu.printException(e));
		}
		return encryptPassword;
	}

	public boolean validatePassword(String userPassword, String storedEncryptedPassword) {
		boolean valid = false;
		try {
			String[] parts = storedEncryptedPassword.split(":");
			int iterations = Integer.parseInt(parts[0]);
			byte[] salt = fromHex(parts[1]);
			byte[] hash = fromHex(parts[2]);

			PBEKeySpec spec = new PBEKeySpec(userPassword.toCharArray(), salt, iterations, hash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
			byte[] testHash = skf.generateSecret(spec).getEncoded();

			int diff = hash.length ^ testHash.length;
			for (int i = 0; i < hash.length && i < testHash.length; i++) {
				diff |= hash[i] ^ testHash[i];
			}
			valid = diff == 0;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.error("unable to validate password. error: {}", tu.printException(e));
		}
		return valid;
	}

	private byte[] getSalt() {
		byte[] salt = new byte[16];
		try {
			SecureRandom sr = SecureRandom.getInstance(SECURE_RANDAOM_ALGORITHM);
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			logger.error("unable to get Salt. error: {}", tu.printException(e));
		}
		return salt;
	}

	private String toHex(byte[] array) {
		String hex = null;
		try {
			BigInteger bi = new BigInteger(1, array);
			hex = bi.toString(16);
			int paddingLength = (array.length * 2) - hex.length();
			if (paddingLength > 0) {
				return String.format("%0" + paddingLength + "d", 0) + hex;
			} else {
				return hex;
			}
		} catch (Exception e) {
			logger.error("unable to convert byte array to hex. error: {}", tu.printException(e));
		}
		return hex;
	}

	private byte[] fromHex(String hex) {
		byte[] bytes = null;
		try {
			bytes = new byte[hex.length() / 2];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
			}
		} catch (Exception e) {
			logger.error("unable to convert hex to byte array. error: {}", tu.printException(e));
		}
		return bytes;
	}

	public String getSha256Password(String password) {
		String result = null;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			result = this.toHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			logger.error("unable to convert old password to sha256. error: {}", tu.printException(e));
		}
		return result;
	}

	/*
	 * public static void main(String[] args) throws Exception { String
	 * originalPassword = "andrew"; String dummy = "drowssap"; String
	 * storedPassword; boolean flag = false; PasswordUtils pu = new PasswordUtils();
	 * 
	 * flag = false; storedPassword = pu.getEncryptedPassword(originalPassword);
	 * System.out.println(storedPassword); flag =
	 * pu.validatePassword(originalPassword, storedPassword);
	 * System.out.println(flag);
	 * 
	 * flag = false; storedPassword = pu.getEncryptedPassword(originalPassword);
	 * System.out.println(storedPassword); flag = pu.validatePassword(dummy,
	 * storedPassword); System.out.println(flag);
	 * 
	 * flag = false; storedPassword = pu.getEncryptedPassword(originalPassword);
	 * System.out.println(storedPassword); flag =
	 * pu.validatePassword(originalPassword, storedPassword);
	 * System.out.println(flag);
	 * 
	 * storedPassword =
	 * "1000:185cd94ef4886882174f7a60a72de56b:531d14d85ae40cc10626d4b27a03219713f6c706742367217a00eb4959750a09bdd32b3db3b1c1c8e207ec4e16667910f0bd2202dcd90ee3c2a0372710e538bb";
	 * flag = pu.validatePassword(originalPassword, storedPassword);
	 * System.out.println(flag);
	 * 
	 * }
	 */
}
