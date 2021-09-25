package games.pixelfox.rpgservice.player

import org.hibernate.Hibernate
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "players")
data class Player(
    @Id var id: UUID? = null,
    var name: String? = null,
    var email: String? = null,
    var username: String? = null,
    var createdAt: Instant? = null,
    var updatedAt: Instant? = null,
    var bannedAt: Instant? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Player
        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String = this::class.simpleName + "(id=$id,name=$name,email=$email,username=$username)"
}
