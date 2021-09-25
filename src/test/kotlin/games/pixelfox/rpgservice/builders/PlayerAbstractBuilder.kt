package games.pixelfox.rpgservice.builders

import java.time.Instant
import java.util.*

abstract class PlayerAbstractBuilder<T> {
    protected var id: UUID? = UUID.randomUUID()
    protected var name = "TEST PLAYER - $id"
    protected var email = "test.player@$id.com"
    protected var username = "@player_$id"
    protected var createdAt: Instant? = Instant.now()
    protected var updatedAt: Instant? = Instant.now()
    protected var bannedAt: Instant? = null

    fun withName(name: String): PlayerAbstractBuilder<T> {
        this.name = name
        return this
    }

    fun withNoID(): PlayerAbstractBuilder<T> {
        this.id = null
        return this
    }

    fun withNoDates(): PlayerAbstractBuilder<T> {
        this.createdAt = null
        this.updatedAt = null
        return this
    }

    abstract fun build(): T
}
