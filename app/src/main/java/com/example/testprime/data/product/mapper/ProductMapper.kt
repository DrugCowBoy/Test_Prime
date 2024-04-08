package com.example.testprime.data.product.mapper

import com.example.testprime.data.product.model.ProductItemDto
import com.example.testprime.domain.product.model.Category
import com.example.testprime.domain.product.model.Image
import com.example.testprime.domain.product.model.Options
import com.example.testprime.domain.product.model.ProductItem
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapProductItemDtoToEntity(productItemDto: ProductItemDto) = ProductItem(
        description = productItemDto.description,
        id = productItemDto.id,
        images = productItemDto.images?.map{imageDto->
            Image(
                link = imageDto?.link
            )
        },
        measure = productItemDto.measure,
        name = productItemDto.name,
        category = Category(
            description = productItemDto.category?.description,
            id = productItemDto.category?.id,
            mainImageLink = productItemDto.category?.mainImageLink,
            name = productItemDto.category?.name
        ),
        price = productItemDto.price,
        options = Options(
            carbohydrate = productItemDto.options?.carbohydrate,
            carbohydratePerHundredGrams = productItemDto.options?.carbohydratePerHundredGrams,
            energy = productItemDto.options?.energy,
            energyPerHundredGrams = productItemDto.options?.energyPerHundredGrams,
            fat = productItemDto.options?.fat,
            fatPerHundredGrams = productItemDto.options?.fatPerHundredGrams,
            protein = productItemDto.options?.protein,
            proteinPerHundredGrams = productItemDto.options?.proteinPerHundredGrams,
            weight = productItemDto.options?.weight
        )
    )

}