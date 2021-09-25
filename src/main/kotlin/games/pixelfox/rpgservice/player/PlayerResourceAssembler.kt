package games.pixelfox.rpgservice.player

import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class PlayerResourceAssembler {
    fun from(player: Player?): PlayerResource = PlayerResource(
        id = player?.id,
        name = player?.name,
        email = player?.email,
        username = player?.username,
        createdAt = player?.createdAt,
        updatedAt = player?.updatedAt,
        bannedAt = player?.bannedAt,
    )

    fun from(resource: PlayerResource?): Player = Player(
        id = resource?.id ?: UUID.randomUUID(),
        name = resource?.name,
        email = resource?.email,
        username = resource?.username,
        createdAt = resource?.createdAt ?: Instant.now(),
        updatedAt = resource?.updatedAt ?: Instant.now(),
        bannedAt = resource?.bannedAt,
    )
}
