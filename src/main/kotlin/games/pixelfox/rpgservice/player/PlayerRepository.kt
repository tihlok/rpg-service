package games.pixelfox.rpgservice.player

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PlayerRepository : JpaRepository<Player, UUID> {
}

