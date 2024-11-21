package demo.still.wtf.secret_santa_gift_exchange.gift

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository interface GiftRepository : CrudRepository<Gift, Long>
