package demo.still.wtf.secret_santa_gift_exchange.gift

import demo.still.wtf.secret_santa_gift_exchange.customer.CustomerRepository
import kotlin.random.Random
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GiftDataInitializer : InitializingBean {

    @Autowired private lateinit var giftRepository: GiftRepository

    @Autowired private lateinit var customerRepository: CustomerRepository

    override fun afterPropertiesSet() {
        // Populate only if there are no gifts already
        if (giftRepository.count() > 0) {
            println("Gifts are already initialized.")
            return
        }

        // Check for customers to link with gifts
        if (customerRepository.count() == 0L) {
            println("Please ensure there are some customers in the database.")
            return
        }

        val customers = customerRepository.findAll().toList()
        if (customers.isEmpty()) {
            println("No customers found for gifts association.")
            return
        }

        // Sample stupid gift ideas
        val giftNames =
                listOf(
                        "Rubber Chicken",
                        "Whoopee Cushion",
                        "Inflatable Unicorn Horn for Cats",
                        "The Nothing Box",
                        "Bacon Bandages",
                        "Yodeling Pickle"
                )

        val descriptions =
                listOf(
                        "A classic gag gift, perfect for a laugh.",
                        "Ideal for pranking friends or family.",
                        "Turn your cat into a mythical creature - beware of scratches!",
                        "Absolutely does nothing, mystifies everyone.",
                        "The most delicious way to cover up cuts and scrapes.",
                        "Adds a musical touch to the mundane act of picking."
                )

        (1..1000).forEach { index ->
            val customer = customers[Random.nextInt(customers.size)]
            val name = giftNames[Random.nextInt(giftNames.size)]
            val description = descriptions[giftNames.indexOf(name)]

            val gift = Gift(name = "$name #$index", description = description, customer = customer)
            giftRepository.save(gift)
        }

        println("Generated 1000 random stupid gifts for Secret Santa.")
    }
}
