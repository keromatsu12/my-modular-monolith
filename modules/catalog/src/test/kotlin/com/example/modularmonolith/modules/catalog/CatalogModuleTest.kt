package com.example.modularmonolith.modules.catalog

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules

class CatalogModuleTest {

    @Test
    fun verify() {
        val modules = ApplicationModules.of("com.example.modularmonolith.modules")
        modules.verify()
    }
}
