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

import games.pixelfox.rpgservice.hellpers.EntityModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "items")
data class Item(
    var name: String? = null,
    var slotType: ItemSlotType? = null,
    var power: Long? = null,
    var dropRate: Double? = null
) : EntityModel() {
    override fun equals(other: Any?): Boolean {
        other as Item
        return id != null && id == other.id
    }

    override fun hashCode() = id.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(" +
                "id=$id," +
                "name=$name," +
                "slotType=$slotType," +
                "power=$power," +
                "dropRate=$dropRate," +
                "createdAt=$createdAt," +
                "updatedAt=$updatedAt," +
                "bannedAt=$bannedAt" +
                ")"
    }
}
