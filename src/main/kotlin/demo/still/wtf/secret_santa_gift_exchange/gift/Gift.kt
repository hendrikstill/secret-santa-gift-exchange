package demo.still.wtf.secret_santa_gift_exchange.gift

import demo.still.wtf.secret_santa_gift_exchange.customer.Customer
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class Gift(
        @Id @GeneratedValue val id: Long = 0,
        val name: String,
        val description: String,
        @ManyToOne val customer: Customer
)
