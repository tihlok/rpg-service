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

import games.pixelfox.rpgservice.builders.PlayerBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class PlayerRepositoryTests {
    companion object {
        private val PLAYER = PlayerBuilder
            .aPlayer()
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
        val saved = playerRepository?.save(PLAYER)
        assertThat(saved).isNotNull
        assertThat(saved?.id).isNotNull
        assertThat(saved?.name).startsWith("name ")
        assertThat(saved?.createdAt).isNotNull
        assertThat(saved?.updatedAt).isNotNull
        assertThat(saved?.bannedAt).isNull()
    }

    @Test
    fun createANewPlayerAndSaveAndGet() {
        assertThat(playerRepository?.findAll()).hasSize(0)

        val saved = playerRepository?.save(PLAYER)
        assertThat(saved).isNotNull

        val allAfterSave = playerRepository?.findAll()
        assertThat(allAfterSave).hasSize(1)

        val player = playerRepository?.findById(saved?.id!!)?.get()
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
        val saved = playerRepository?.save(PLAYER)!!
        assertThat(saved).isNotNull
        assertThat(saved.name).startsWith("name ")

        saved.name = "PLAYER v2"
        val updated = playerRepository.save(saved)
        assertThat(updated).isNotNull
        assertThat(updated.name).isEqualTo("PLAYER v2")
    }

    @Test
    fun deleteOnePlayer() {
        assertThat(playerRepository?.findAll()).hasSize(0)

        val saved = playerRepository?.save(PLAYER)!!
        assertThat(playerRepository.findAll()).hasSize(1)

        playerRepository.delete(saved)
        assertThat(playerRepository.findAll()).hasSize(0)
    }
}

