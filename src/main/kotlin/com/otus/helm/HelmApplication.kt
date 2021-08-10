package com.otus.helm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelmApplication

fun main(args: Array<String>) {
	runApplication<HelmApplication>(*args)
}
