package demo.still.wtf.secret_santa_gift_exchange.customer

import jakarta.annotation.PostConstruct
import kotlin.random.Random
import org.springframework.stereotype.Component

@Component
class CustomerDataInitializer(private val customerRepository: CustomerRepository) {

    companion object {
        private const val NUMBER_OF_CUSTOMERS = 10_000
    }

    @PostConstruct
    fun init() {
        val customers = generateCustomers(NUMBER_OF_CUSTOMERS)
        customerRepository.saveAll(customers)
    }

    private fun generateCustomers(number: Int): List<Customer> {
        return List(number) {
            Customer(
                    name = generateRandomName(),
                    email = generateRandomEmail(),
                    geoLocation = generateRandomGeoLocation()
            )
        }
    }

    private fun generateRandomName(): String {
        val names =
                listOf(
                        "Alice",
                        "Bob",
                        "Carol",
                        "David",
                        "Eve",
                        "Frank",
                        "Grace",
                        "Hank",
                        "Isabella",
                        "Jack"
                )
        return names[Random.nextInt(names.size)]
    }

    private fun generateRandomEmail(): String {
        val domains = listOf("@example.com", "@test.com", "@demo.com", "@sample.com", "@email.com")
        return generateRandomName().lowercase() +
                Random.nextInt(100).toString() +
                domains[Random.nextInt(domains.size)]
    }

    private fun generateRandomGeoLocation(): GeoLocation {
        // Extended to include more continents with some sample latitude and longitude ranges
        val regions =
                listOf(
                        GeoLocation(
                                Random.nextDouble(24.396308, 49.384358),
                                -Random.nextDouble(67.0, 125.0)
                        ), // North America
                        GeoLocation(
                                Random.nextDouble(-34.603722, -0.007650),
                                -Random.nextDouble(58.381592, 81.602884)
                        ), // South America
                        GeoLocation(
                                Random.nextDouble(34.0, 52.0),
                                Random.nextDouble(8.0, 140.0)
                        ), // Asia
                        GeoLocation(
                                Random.nextDouble(36.0, 71.0),
                                Random.nextDouble(-10.0, 40.0)
                        ), // Europe
                        GeoLocation(
                                Random.nextDouble(-33.0, 37.0),
                                Random.nextDouble(18.0, 51.0)
                        ), // Africa
                        GeoLocation(
                                Random.nextDouble(-39.0, -11.0),
                                Random.nextDouble(113.0, 153.0)
                        ) // Australia
                )
        return regions[Random.nextInt(regions.size)]
    }
}
