package io.jrb.msvc.songs.config

import io.jrb.msvc.songs.model.SongEntity
import io.jrb.msvc.songs.repository.SongEntityRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux
import java.util.*
import java.util.stream.Stream

@Configuration
class ApplicationJavaConfig {

    @Bean
    fun runner(songEntityRepository: SongEntityRepository, db: DatabaseClient) = ApplicationRunner {

        val initDb = db.execute("""
            CREATE TABLE song (
                id SERIAL PRIMARY KEY,
                guid VARCHAR(36) NOT NULL,
                title VARCHAR(255) NOT NULL
            );
        """)

        val stream = Stream.of(
                SongEntity(null, UUID.randomUUID().toString(),"Song 1"),
                SongEntity(null, UUID.randomUUID().toString(),"Song 2")
        )
        val saveAll = songEntityRepository.saveAll(Flux.fromStream(stream))

        initDb.then()
                .thenMany(saveAll)
                .subscribe()
    }

}
