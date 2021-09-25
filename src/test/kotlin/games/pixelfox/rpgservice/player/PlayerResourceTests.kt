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

class PlayerResourceTests {
    @Test
    fun createANewPlayerResourceWithEmptyFields() {
        val player = PlayerResource()
        assertThat(player.id).isNull()
        assertThat(player.name).isNull()
        assertThat(player.email).isNull()
        assertThat(player.username).isNull()
        assertThat(player.createdAt).isNull()
        assertThat(player.updatedAt).isNull()
        assertThat(player.bannedAt).isNull()
    }

    @Test
    fun createANewPlayerResourceWithAllFields() {
        val player = PlayerResourceBuilder.aPlayerResource().build()

        assertThat(player.id).isNotNull
        assertThat(player.name).startsWith("name ")
        assertThat(player.username).startsWith("@username_")
        assertThat(player.email).startsWith("test@")
        assertThat(player.createdAt).isNotNull
        assertThat(player.updatedAt).isNotNull
        assertThat(player.bannedAt).isNull()
    }
}
