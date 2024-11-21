package demo.still.wtf.secret_santa_gift_exchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
class SecretSantaGiftExchangeApplication

fun main(args: Array<String>) {
    runApplication<SecretSantaGiftExchangeApplication>(*args)
}
