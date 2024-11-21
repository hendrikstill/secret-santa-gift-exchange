package demo.still.wtf.secret_santa_gift_exchange.customer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository interface CustomerRepository : CrudRepository<Customer, Long>
