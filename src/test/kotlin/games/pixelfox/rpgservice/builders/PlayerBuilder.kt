package games.pixelfox.rpgservice.builders

import games.pixelfox.rpgservice.player.Player

class PlayerBuilder private constructor() : PlayerAbstractBuilder<Player>() {
    override fun build(): Player = Player(
        id = id,
        name = name,
        email = email,
        username = username,
        createdAt = createdAt,
        updatedAt = updatedAt,
        bannedAt = bannedAt
    )

    companion object {
        fun aPlayer(): PlayerBuilder {
            return PlayerBuilder()
        }
    }
}
