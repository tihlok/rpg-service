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

package games.pixelfox.rpgservice.item

import games.pixelfox.rpgservice.builders.ItemBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ItemRepositoryTests {
    companion object {
        private val ITEM = ItemBuilder
            .anItem()
            .build()
    }

    @Autowired
    private val itemRepository: ItemRepository? = null

    @Test
    fun checkDependenciesInjected() {
        assertThat(itemRepository).isNotNull
    }

    @Test
    fun ITEMsEmpty() {
        val all = itemRepository?.findAll()
        assertThat(all).hasSize(0)
    }

    @Test
    fun createANewITEMAndSave() {
        val saved = itemRepository?.save(ITEM)
        assertThat(saved).isNotNull
        assertThat(saved?.id).isNotNull
        assertThat(saved?.name).startsWith("name ")
        assertThat(saved?.slotType).isEqualTo(ItemSlotType.ARMOR)
        assertThat(saved?.power).isGreaterThan(0)
        assertThat(saved?.dropRate).isGreaterThan(0.0)
        assertThat(saved?.createdAt).isNotNull
        assertThat(saved?.updatedAt).isNotNull
        assertThat(saved?.bannedAt).isNull()
    }

    @Test
    fun createANewITEMAndSaveAndGet() {
        assertThat(itemRepository?.findAll()).hasSize(0)

        val saved = itemRepository?.save(ITEM)
        assertThat(saved).isNotNull

        val allAfterSave = itemRepository?.findAll()
        assertThat(allAfterSave).hasSize(1)

        val item = itemRepository?.findById(saved?.id!!)?.get()
        assertThat(allAfterSave?.get(0)).isEqualTo(item)
    }

    @Test
    fun createMultipleITEMsAndSaveAndGet() {
        assertThat(itemRepository?.findAll()).hasSize(0)
        for (i in 0..99) itemRepository?.save(ItemBuilder.anItem().build())
        assertThat(itemRepository?.findAll()).hasSize(100)
    }

    @Test
    fun updateOneITEM() {
        val saved = itemRepository?.save(ITEM)!!
        assertThat(saved).isNotNull
        assertThat(saved.name).startsWith("name ")

        saved.name = "ITEM v2"
        saved.power = 1000
        val updated = itemRepository.save(saved)
        assertThat(updated).isNotNull
        assertThat(updated.name).isEqualTo("ITEM v2")
        assertThat(updated.power).isEqualTo(1000)
    }

    @Test
    fun deleteOneITEM() {
        assertThat(itemRepository?.findAll()).hasSize(0)

        val saved = itemRepository?.save(ITEM)!!
        assertThat(itemRepository.findAll()).hasSize(1)

        itemRepository.delete(saved)
        assertThat(itemRepository.findAll()).hasSize(0)
    }
}

