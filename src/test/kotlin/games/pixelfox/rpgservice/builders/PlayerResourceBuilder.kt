package games.pixelfox.rpgservice.builders

import games.pixelfox.rpgservice.player.PlayerResource

class PlayerResourceBuilder private constructor() : PlayerAbstractBuilder<PlayerResource>() {
    fun empty(): PlayerResource = PlayerResource(
        name = name,
        email = email,
        username = username
    )

    override fun build(): PlayerResource = PlayerResource(
        id = id,
        name = name,
        email = email,
        username = username,
        createdAt = createdAt,
        updatedAt = updatedAt,
        bannedAt = bannedAt
    )

    companion object {
        fun aPlayerResource(): PlayerResourceBuilder {
            return PlayerResourceBuilder()
        }
    }
}
