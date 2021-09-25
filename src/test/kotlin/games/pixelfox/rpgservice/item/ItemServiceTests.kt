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

import games.pixelfox.rpgservice.builders.ItemResourceBuilder
import games.pixelfox.rpgservice.exceptions.ItemMissingPropertiesException
import games.pixelfox.rpgservice.exceptions.ItemNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ItemServiceTests {
    @Autowired
    private val itemService: ItemService? = null

    @Test
    fun createOneItem() {
        val item = ItemResourceBuilder.anItemResource()
            .withNoID()
            .withNoDates()
            .build()

        val saved = itemService?.save(item)
        assertThat(saved).isNotNull
        assertThat(saved?.id).isNotNull
        assertThat(saved?.name).startsWith("name ")
        assertThat(saved?.slotType).isEqualTo("ARMOR")
        assertThat(saved?.power).isGreaterThan(0)
        assertThat(saved?.dropRate).isGreaterThan(0.0)
        assertThat(saved?.createdAt).isNotNull
        assertThat(saved?.updatedAt).isNotNull
        assertThat(saved?.bannedAt).isNull()
    }

    @Test
    fun findOneItem() {
        val resource = itemService?.save(ItemResourceBuilder.anItemResource().build())

        val find = itemService?.findByID(resource?.id!!)
        assertThat(find).isNotNull
        assertThat(find?.id).isNotNull
        assertThat(find?.name).startsWith("name ")
        assertThat(find?.slotType).isEqualTo("ARMOR")
        assertThat(find?.power).isGreaterThan(0)
        assertThat(find?.dropRate).isGreaterThan(0.0)
        assertThat(find?.createdAt).isNotNull
        assertThat(find?.updatedAt).isNotNull
        assertThat(find?.bannedAt).isNull()
    }

    @Test
    fun updateOneItem() {
        val resource = itemService?.save(ItemResourceBuilder.anItemResource().build())
        resource?.dropRate = 0.9
        resource?.slotType = "HEAD"

        val updated = itemService?.save(resource!!)
        assertThat(updated).isNotNull
        assertThat(updated?.createdAt).isEqualTo(resource?.createdAt)
        assertThat(updated?.updatedAt).isAfter(resource?.updatedAt)

        val find = itemService?.findByID(resource?.id!!)
        assertThat(find).isNotNull
        assertThat(find?.id).isNotNull
        assertThat(find?.name).startsWith("name ")
        assertThat(find?.slotType).isEqualTo("HEAD")
        assertThat(find?.power).isGreaterThan(0)
        assertThat(find?.dropRate).isEqualTo(0.9)
        assertThat(find?.createdAt).isNotNull
        assertThat(find?.updatedAt).isNotNull
        assertThat(find?.bannedAt).isNull()
    }

    @Test
    fun failsToUpdateItem() {
        val resource = itemService?.save(ItemResourceBuilder.anItemResource().build())
        resource?.slotType = "WRONG SLOT TYPE"

        val exception = assertThrows<ItemMissingPropertiesException> {
            itemService?.save(resource!!)
        }
        assertThat(exception).isNotNull
        assertThat(exception.resource).isEqualTo(resource)
        assertThat(exception.message).isEqualTo("Missing Properties: [$resource]")
    }

    @Test
    fun failsToFindItem() {
        val id = UUID.randomUUID()
        val exception = assertThrows<ItemNotFoundException> {
            itemService?.findByID(id)
        }
        assertThat(exception).isNotNull
        assertThat(exception.id).isEqualTo(id)
        assertThat(exception.message).isEqualTo("Item Not Found: [$id]")
    }
}
