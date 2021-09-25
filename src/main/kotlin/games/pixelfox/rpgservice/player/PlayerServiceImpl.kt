package games.pixelfox.rpgservice.player

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerServiceImpl : PlayerService {
    @Autowired
    private val playerRepository: PlayerRepository? = null

    @Autowired
    private val playerResourceAssembler: PlayerResourceAssembler? = null

    override fun save(resource: PlayerResource): PlayerResource {
        val entity = playerResourceAssembler?.from(resource)!!
        return playerRepository?.save(entity)!!
            .let {
                playerResourceAssembler.from(it)
            }
    }

    override fun findByID(id: UUID): PlayerResource {
        return playerRepository?.findById(id)
            ?.orElseThrow()
            .let {
                playerResourceAssembler?.from(it)!!
            }
    }
}
