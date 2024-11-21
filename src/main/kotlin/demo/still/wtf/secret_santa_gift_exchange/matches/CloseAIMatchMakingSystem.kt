package demo.still.wtf.secret_santa_gift_exchange.match

import demo.still.wtf.secret_santa_gift_exchange.customer.CustomerRepository
import demo.still.wtf.secret_santa_gift_exchange.gift.GiftRepository
import io.micrometer.core.instrument.MeterRegistry
import kotlin.random.Random
import org.springframework.stereotype.Service

@Service
class CloseAIMatchMakingSystem(
        private val matchRepository: MatchRepository,
        private val customerRepository: CustomerRepository,
        private val giftRepository: GiftRepository,
        private val registry: MeterRegistry
) {
    private val timer = registry.timer("closedai.findMatch.time")

    public fun findMatch(): Match {
        return timer.recordCallable {
            val customers = customerRepository.findAll().toList()
            val gifts = giftRepository.findAll().toList()

            simulateLatency()
            randomThrowException()

            if (customers.size < 2) {
                throw IllegalStateException("Not enough customers for a match.")
            }
            if (gifts.isEmpty()) {
                throw IllegalStateException("No gifts available for matching.")
            }

            val shuffledCustomers = customers.shuffled()
            val giver = shuffledCustomers[0]
            val receiver = shuffledCustomers[1] // Ensure receiver is not the giver

            val gift = gifts[Random.nextInt(gifts.size)]

            Match(giver = giver, receiver = receiver, gift = gift)
        }
                ?: throw IllegalStateException("Match creation failed")
    }

    fun simulateLatency() {
        val randomDelay = Random.nextLong(0, 10_000)
        Thread.sleep(randomDelay)
    }

    private fun randomThrowException() {
        val randomNumber = Random.nextInt(100)
        if (randomNumber < 10) {
            when (randomNumber % 4) {
                0 -> throw ComputerSaysNoException()
                1 -> throw StupidPromptException()
                2 -> throw OtherCustomersPayMoreException()
                3 -> throw YourBankaccountIsEmptyException()
                4 -> throw TooMuchAiException()
            }
        }
    }
}

class ComputerSaysNoException() : Error()

class StupidPromptException() : Error()

class OtherCustomersPayMoreException() : Error()

class YourBankaccountIsEmptyException() : Error()

class TooMuchAiException() : Error()
