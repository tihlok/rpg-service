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
        assertThat(saved?.name).startsWith("name ")
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
        assertThat(find?.name).startsWith("name ")
        assertThat(find?.createdAt).isNotNull
        assertThat(find?.updatedAt).isNotNull
        assertThat(find?.bannedAt).isNull()
    }
}
