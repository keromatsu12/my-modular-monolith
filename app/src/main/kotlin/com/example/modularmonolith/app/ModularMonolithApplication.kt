package com.example.modularmonolith.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.modularmonolith"])
class ModularMonolithApplication

fun main(args: Array<String>) {
    runApplication<ModularMonolithApplication>(*args)
}
