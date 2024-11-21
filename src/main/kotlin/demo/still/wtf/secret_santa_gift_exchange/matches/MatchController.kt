package demo.still.wtf.secret_santa_gift_exchange.match

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/matches")
class MatchController {

    @Autowired lateinit var matchRepository: MatchRepository

    @GetMapping fun getAllMatches() = matchRepository.findAll()

    @GetMapping("/{id}")
    fun getMatchById(@PathVariable id: Long): ResponseEntity<Match> {
        val match = matchRepository.findById(id).orElse(null)
        return match?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createMatch(@RequestBody match: Match): ResponseEntity<Match> {
        val savedMatch = matchRepository.save(match)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch)
    }

    @DeleteMapping("/{id}")
    fun deleteMatch(@PathVariable id: Long): ResponseEntity<Void> {
        matchRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
