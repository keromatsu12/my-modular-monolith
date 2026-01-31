package com.example.modularmonolith.modules.common

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class EncryptionUtil {

    companion object {
        private const val ALGORITHM = "AES"

        fun encrypt(data: String, secret: String): String {
            val key = SecretKeySpec(secret.toByteArray(), ALGORITHM)
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedBytes = cipher.doFinal(data.toByteArray())
            return Base64.getEncoder().encodeToString(encryptedBytes)
        }

        fun decrypt(encryptedData: String, secret: String): String {
            val key = SecretKeySpec(secret.toByteArray(), ALGORITHM)
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decodedBytes = Base64.getDecoder().decode(encryptedData)
            val decryptedBytes = cipher.doFinal(decodedBytes)
            return String(decryptedBytes)
        }
    }
}
