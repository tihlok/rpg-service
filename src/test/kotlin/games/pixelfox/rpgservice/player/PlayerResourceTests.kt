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
