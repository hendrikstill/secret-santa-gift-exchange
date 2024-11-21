package demo.still.wtf.secret_santa_gift_exchange.match

import net.logstash.logback.argument.StructuredArguments.kv
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchService(
        private val matchRepository: MatchRepository,
        private val closeAIMatchMakingSystem: CloseAIMatchMakingSystem
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun findMatch(): Match {

        val match = closeAIMatchMakingSystem.findMatch()

        // Log the match details as structured arguments
        logger.info(
                "Find match from ${match.giver.name} to ${match.receiver.name}. So nice!",
                kv("giverName", match.giver.name),
                kv("giverLatitude", match.giver.geoLocation?.latitude),
                kv("giverLongitude", match.giver.geoLocation?.longitude),
                kv("receiverName", match.receiver.name),
                kv("receiverLatitude", match.receiver.geoLocation?.latitude),
                kv("receiverLongitude", match.receiver.geoLocation?.longitude),
                kv("giftName", match.gift.name),
                kv("giftDescription", match.gift.description)
        )

        return matchRepository.save(match)
    }
}
