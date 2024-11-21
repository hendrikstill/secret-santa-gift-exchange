package demo.still.wtf.secret_santa_gift_exchange.customer

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(private val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun getAllCustomers(): MutableIterable<Customer> {
        return customerRepository.findAll()
    }

    @PostMapping("/customers")
    fun createCustomer(@RequestBody customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    @DeleteMapping("/customers/{id}")
    fun deleteCustomer(@PathVariable id: Long): String {
        customerRepository.deleteById(id)
        return "Customer deleted successfully."
    }
}
