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

package games.pixelfox.rpgservice.builders

import games.pixelfox.rpgservice.item.ItemResource
import games.pixelfox.rpgservice.item.ItemSlotType

class ItemResourceBuilder private constructor() : AbstractBuilder<ItemResource>() {
    override fun build(): ItemResource {
        val resource = ItemResource(
            name = name,
            power = randomLong,
            slotType = ItemSlotType.ARMOR.toString(),
            dropRate = randomDouble,
        )
        resource.id = id
        resource.createdAt = createdAt
        resource.updatedAt = updatedAt
        resource.bannedAt = bannedAt
        return resource
    }

    companion object {
        fun anItemResource(): ItemResourceBuilder {
            return ItemResourceBuilder()
        }
    }
}
