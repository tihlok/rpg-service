package games.pixelfox.rpgservice.player

import games.pixelfox.rpgservice.builders.PlayerBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class PlayerTests {
    @Test
    fun createANewPlayerWithEmptyFields() {
        val player = Player()
        assertThat(player.id).isNull()
        assertThat(player.name).isNull()
        assertThat(player.createdAt).isNull()
        assertThat(player.updatedAt).isNull()
        assertThat(player.bannedAt).isNull()
    }

    @Test
    fun createANewPlayerWithAllFields() {
        val uuid = UUID.randomUUID()
        val now = Instant.now()
        val player = Player(
            id = uuid,
            name = "Tiago",
            createdAt = now,
            updatedAt = now,
            bannedAt = now
        )

        assertThat(player.id).isEqualTo(uuid)
        assertThat(player.name).isEqualTo("Tiago")
        assertThat(player.createdAt).isEqualTo(now)
        assertThat(player.updatedAt).isEqualTo(now)
        assertThat(player.bannedAt).isEqualTo(now)
    }

    @Test
    fun playersShouldBeEquals() {
        val uuid = UUID.randomUUID()
        val playerOne = Player(id = uuid, name = "Tiago")
        val playerTwo = Player(id = uuid, name = "Tiago")
        assertThat(playerOne).isEqualTo(playerTwo)
    }

    @Test
    fun playersShouldNoBeEquals() {
        val playerOne = PlayerBuilder.aPlayer().build()
        val playerTwo = PlayerBuilder.aPlayer().build()
        assertThat(playerOne).isNotEqualTo(playerTwo)
    }

    @Test
    fun playerString() {
        val uuid = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val player = Player(id = uuid, name = "Tiago", email = "strife.tiago@gmail.com", username = "tihlok")
        assertThat(player).asString().isEqualTo("Player(id=00000000-0000-0000-0000-000000000000,name=Tiago,email=strife.tiago@gmail.com,username=tihlok)")
    }

    @Test
    fun playerHashCode() {
        val player = PlayerBuilder.aPlayer().build()
        assertThat(player.hashCode()).isEqualTo(0)
    }
}
