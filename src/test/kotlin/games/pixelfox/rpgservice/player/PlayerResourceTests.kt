package games.pixelfox.rpgservice.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class PlayerResourceTests {
    @Test
    fun createANewPlayerResourceWithEmptyFields() {
        val player = PlayerResource()
        assertThat(player.id).isNull()
        assertThat(player.name).isNull()
        assertThat(player.createdAt).isNull()
        assertThat(player.updatedAt).isNull()
        assertThat(player.bannedAt).isNull()
    }

    @Test
    fun createANewPlayerResourceWithAllFields() {
        val uuid = UUID.randomUUID()
        val now = Instant.now()
        val player = PlayerResource(
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
}
