package demo.still.wtf.secret_santa_gift_exchange.customer

import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Embeddable data class GeoLocation(val latitude: Double, val longitude: Double)

@Entity
data class Customer(
        @Id @GeneratedValue val id: Long = 0,
        val name: String,
        val email: String,
        val geoLocation: GeoLocation? = null
)
