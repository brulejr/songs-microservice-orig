package io.jrb.msvc.songs.config

import io.jrb.msvc.songs.model.Song
import io.jrb.msvc.songs.repository.SongRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.stream.Stream

@Component
class ApplicationJavaConfig {

    @Bean
    fun runner(songRepository: SongRepository, db: DatabaseClient) = ApplicationRunner {

        val initDb = db.execute("""
            CREATE TABLE song (
                id SERIAL PRIMARY KEY,
                title VARCHAR(255) NOT NULL
            );
        """)

        val stream = Stream.of(
                Song(null, "Song 1"),
                Song(null, "Song 2")
        )
        val saveAll = songRepository.saveAll(Flux.fromStream(stream))

        initDb
                .then()
                .thenMany(saveAll)
                .subscribe()
    }

}
