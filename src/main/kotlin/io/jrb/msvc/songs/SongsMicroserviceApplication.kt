package io.jrb.msvc.songs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SongsMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<SongsMicroserviceApplication>(*args) {
	}
}
