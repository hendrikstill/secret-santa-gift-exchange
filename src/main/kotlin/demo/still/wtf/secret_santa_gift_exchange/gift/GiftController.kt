package demo.still.wtf.secret_santa_gift_exchange.gift

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GiftController(val giftRepository: GiftRepository) {
    @GetMapping("/gifts")
    fun getAllGifts(): Iterable<Gift> {
        return giftRepository.findAll()
    }

    @PostMapping("/gifts")
    fun addGift(@RequestBody gift: Gift): Gift {
        return giftRepository.save(gift)
    }

    @GetMapping("/gifts/{id}")
    fun getGiftById(@PathVariable id: Long): Gift {
        return giftRepository.findById(id).orElseThrow {
            RuntimeException("Gift not found with id: $id")
        }
    }

    @DeleteMapping("/gifts/{id}")
    fun deleteGift(@PathVariable id: Long): String {
        giftRepository.deleteById(id)
        return "Gift deleted successfully"
    }
}
