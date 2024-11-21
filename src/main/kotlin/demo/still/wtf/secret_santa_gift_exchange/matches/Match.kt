package demo.still.wtf.secret_santa_gift_exchange.match

import demo.still.wtf.secret_santa_gift_exchange.customer.Customer
import demo.still.wtf.secret_santa_gift_exchange.gift.Gift
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Match(
        @Id @GeneratedValue val id: Long = 0,
        @ManyToOne @JoinColumn(name = "giver_id") val giver: Customer,
        @ManyToOne @JoinColumn(name = "receiver_id") val receiver: Customer,
        @ManyToOne @JoinColumn(name = "gift_id") val gift: Gift
)
