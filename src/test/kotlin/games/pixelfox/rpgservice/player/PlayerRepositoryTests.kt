package games.pixelfox.rpgservice.player

import games.pixelfox.rpgservice.builders.PlayerBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class PlayerRepositoryTests {
    companion object {
        private val PLAYER_TIAGO = PlayerBuilder
            .aPlayer()
            .withName("Tiago")
            .build()
    }

    @Autowired
    private val playerRepository: PlayerRepository? = null

    @Test
    fun checkDependenciesInjected() {
        assertThat(playerRepository).isNotNull
    }

    @Test
    fun playersEmpty() {
        val all = playerRepository?.findAll()
        assertThat(all).hasSize(0)
    }

    @Test
    fun createANewPlayerAndSave() {
        val saved = playerRepository?.save(PLAYER_TIAGO)
        assertThat(saved).isNotNull
        assertThat(saved?.name).isEqualTo("Tiago")
        assertThat(saved?.id).isEqualTo(PLAYER_TIAGO.id)
        assertThat(saved?.createdAt).isNotNull
        assertThat(saved?.updatedAt).isNotNull
        assertThat(saved?.bannedAt).isNull()
    }

    @Test
    fun createANewPlayerAndSaveAndGet() {
        assertThat(playerRepository?.findAll()).hasSize(0)

        val saved = playerRepository?.save(PLAYER_TIAGO)
        assertThat(saved).isNotNull

        val allAfterSave = playerRepository?.findAll()
        assertThat(allAfterSave).hasSize(1)

        val player = playerRepository?.findById(PLAYER_TIAGO.id!!)?.get()
        assertThat(allAfterSave?.get(0)).isEqualTo(player)
    }

    @Test
    fun createMultiplePlayersAndSaveAndGet() {
        assertThat(playerRepository?.findAll()).hasSize(0)
        for (i in 0..99) playerRepository?.save(PlayerBuilder.aPlayer().build())
        assertThat(playerRepository?.findAll()).hasSize(100)
    }

    @Test
    fun updateOnePlayer() {
        val saved = playerRepository?.save(PLAYER_TIAGO)!!
        assertThat(saved).isNotNull
        assertThat(saved.name).isEqualTo("Tiago")

        saved.name = "Tiago V2"
        val updated = playerRepository.save(saved)
        assertThat(updated).isNotNull
        assertThat(updated.name).isEqualTo("Tiago V2")
    }

    @Test
    fun deleteOnePlayer() {
        assertThat(playerRepository?.findAll()).hasSize(0)

        val saved = playerRepository?.save(PLAYER_TIAGO)!!
        assertThat(playerRepository.findAll()).hasSize(1)

        playerRepository.delete(saved)
        assertThat(playerRepository.findAll()).hasSize(0)
    }
}

