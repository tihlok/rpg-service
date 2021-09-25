package games.pixelfox.rpgservice.player

import java.time.Instant
import java.util.*

data class PlayerResource(
    var id: UUID? = null,
    var name: String? = null,
    var email: String? = null,
    var username: String? = null,
    var createdAt: Instant? = null,
    var updatedAt: Instant? = null,
    var bannedAt: Instant? = null
)
