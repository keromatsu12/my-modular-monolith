package com.example.modularmonolith.modules.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EncryptionUtilTest {

    @Test
    fun `should encrypt and decrypt correctly`() {
        val originalText = "Hello, World!"
        // 16 bytes key for AES-128
        val secretKey = "1234567890123456"

        val encrypted = EncryptionUtil.encrypt(originalText, secretKey)
        assertNotEquals(originalText, encrypted)

        val decrypted = EncryptionUtil.decrypt(encrypted, secretKey)
        assertEquals(originalText, decrypted)
    }

    @Test
    fun `should throw exception with invalid key length`() {
        val originalText = "Hello"
        val invalidKey = "short"

        assertThrows<Exception> {
            EncryptionUtil.encrypt(originalText, invalidKey)
        }
    }
}
