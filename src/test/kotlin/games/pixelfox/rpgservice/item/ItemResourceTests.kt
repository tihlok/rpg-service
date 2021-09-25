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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemResourceTests {
    @Test
    fun createANewItemResourceWithEmptyFields() {
        val item = ItemResource()
        assertThat(item.id).isNull()
        assertThat(item.name).isNull()
        assertThat(item.slotType).isNull()
        assertThat(item.power).isNull()
        assertThat(item.dropRate).isNull()
        assertThat(item.createdAt).isNull()
        assertThat(item.updatedAt).isNull()
        assertThat(item.bannedAt).isNull()
    }

    @Test
    fun createANewItemResourceWithAllFields() {
        val item = ItemResourceBuilder.anItemResource().build()

        assertThat(item.id).isNotNull
        assertThat(item.name).startsWith("name ")
        assertThat(item.slotType).isEqualTo("ARMOR")
        assertThat(item.power).isGreaterThan(0)
        assertThat(item.dropRate).isGreaterThan(0.0)
        assertThat(item.createdAt).isNotNull
        assertThat(item.updatedAt).isNotNull
        assertThat(item.bannedAt).isNull()
    }
}
