package games.pixelfox.rpgservice.player

import games.pixelfox.rpgservice.builders.PlayerResourceBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PlayerServiceTests {
    @Autowired
    private val playerService: PlayerService? = null

    @Test
    fun createOnePlayer() {
        val player = PlayerResourceBuilder.aPlayerResource()
            .withNoID()
            .withNoDates()
            .build()

        val saved = playerService?.save(player)
        assertThat(saved).isNotNull
        assertThat(saved?.id).isNotNull
        assertThat(saved?.name).startsWith("TEST PLAYER - ")
        assertThat(saved?.createdAt).isNotNull
        assertThat(saved?.updatedAt).isNotNull
        assertThat(saved?.bannedAt).isNull()
    }

    @Test
    fun findOnePlayer() {
        val resource = playerService?.save(PlayerResourceBuilder.aPlayerResource().empty())

        val find = playerService?.findByID(resource?.id!!)
        assertThat(find).isNotNull
        assertThat(find?.id).isNotNull
        assertThat(find?.name).startsWith("TEST PLAYER - ")
        assertThat(find?.createdAt).isNotNull
        assertThat(find?.updatedAt).isNotNull
        assertThat(find?.bannedAt).isNull()
    }
}
