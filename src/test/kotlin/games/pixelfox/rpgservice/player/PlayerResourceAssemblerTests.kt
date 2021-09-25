package games.pixelfox.rpgservice.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*

@SpringBootTest
class PlayerResourceAssemblerTests {
    @Autowired
    private val playerResourceAssembler: PlayerResourceAssembler? = null

    @Test
    fun entityToResource() {
        val entity = Player(
            id = UUID.randomUUID(),
            name = "Tiago",
            email = "strife.tiago@gmail.com",
            username = "tihlok",
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            bannedAt = Instant.now()
        )
        val resource = playerResourceAssembler?.from(entity)

        assertThat(resource?.id).isEqualTo(entity.id)
        assertThat(resource?.name).isEqualTo(entity.name)
        assertThat(resource?.email).isEqualTo(entity.email)
        assertThat(resource?.username).isEqualTo(entity.username)
        assertThat(resource?.createdAt).isEqualTo(entity.createdAt)
        assertThat(resource?.updatedAt).isEqualTo(entity.updatedAt)
        assertThat(resource?.bannedAt).isEqualTo(entity.bannedAt)
    }

    @Test
    fun resourceBlankToEntity() {
        val resource = PlayerResource(
            name = "Tiago"
        )
        val entity = playerResourceAssembler?.from(resource)

        assertThat(entity?.id).isNotNull
        assertThat(entity?.name).isEqualTo(resource.name)
        assertThat(entity?.createdAt).isNotNull
        assertThat(entity?.updatedAt).isNotNull
        assertThat(entity?.bannedAt).isNull()
    }

    @Test
    fun resourceAllFieldsToEntity() {
        val resource = PlayerResource(
            id = UUID.randomUUID(),
            name = "Tiago",
            email = "strife.tiago@gmail.com",
            username = "tihlok",
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            bannedAt = Instant.now()
        )
        val entity = playerResourceAssembler?.from(resource)

        assertThat(entity?.id).isEqualTo(resource.id)
        assertThat(entity?.name).isEqualTo(resource.name)
        assertThat(entity?.email).isEqualTo(resource.email)
        assertThat(entity?.username).isEqualTo(resource.username)
        assertThat(entity?.createdAt).isEqualTo(resource.createdAt)
        assertThat(entity?.updatedAt).isEqualTo(resource.updatedAt)
        assertThat(entity?.bannedAt).isEqualTo(resource.bannedAt)
    }
}
