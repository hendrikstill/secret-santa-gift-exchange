package demo.still.wtf.secret_santa_gift_exchange

import demo.still.wtf.secret_santa_gift_exchange.match.MatchService
import jakarta.annotation.PostConstruct
import java.util.Date
import org.slf4j.LoggerFactory
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.stereotype.Component

@Component
class RandomIntervalTask(private val matchService: MatchService) {

    private val scheduler: TaskScheduler = ThreadPoolTaskScheduler().apply { initialize() }
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun startTask() {
        scheduleNext()
    }

    private fun scheduleNext() {
        // Generate a random delay between 1000 ms (1 sec) and 5000 ms (5 sec)
        val randomDelay = 1000L + (Math.random() * 4000).toLong()

        // Schedule the task to run after the calculated delay
        scheduler.schedule({ executeRandomTask() }, Date(System.currentTimeMillis() + randomDelay))
    }

    fun executeRandomTask() {
        // Log or perform your task logic here
        println("Executing task at: ${Date()}")
        try {
            matchService.findMatch()
        } catch (e: Exception) {
            logger.error("Somethink is broken", e)
        } finally {
            // Schedule next execution
            scheduleNext()
        }
    }
}
