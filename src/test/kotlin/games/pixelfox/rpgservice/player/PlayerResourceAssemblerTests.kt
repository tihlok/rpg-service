/*
 * Copyright (c) 2021-2021 PixelFox.
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

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
