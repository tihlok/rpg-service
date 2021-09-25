package games.pixelfox.rpgservice.player

import java.util.*

interface PlayerService {
    fun save(resource: PlayerResource): PlayerResource

    fun findByID(id: UUID): PlayerResource
}
