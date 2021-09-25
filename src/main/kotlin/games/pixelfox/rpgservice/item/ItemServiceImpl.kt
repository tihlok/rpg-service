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

import games.pixelfox.rpgservice.exceptions.ItemMissingPropertiesException
import games.pixelfox.rpgservice.exceptions.ItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemServiceImpl : ItemService {
    @Autowired
    private val itemRepository: ItemRepository? = null

    @Autowired
    private val itemResourceAssembler: ItemResourceAssembler? = null

    override fun save(resource: ItemResource): ItemResource =
        try {
            val entity = itemResourceAssembler!!.from(resource)
            itemRepository!!.save(entity)
                .let { itemResourceAssembler.from(it) }
        } catch (e: DataIntegrityViolationException) {
            throw ItemMissingPropertiesException(resource)
        }

    override fun findByID(id: UUID): ItemResource =
        itemRepository!!.findById(id)
            .orElseThrow { ItemNotFoundException(id) }
            .let { itemResourceAssembler?.from(it!!)!! }
}
