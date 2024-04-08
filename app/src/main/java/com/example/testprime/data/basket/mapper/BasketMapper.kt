package com.example.testprime.data.basket.mapper

import com.example.testprime.data.basket.model.AddAndDecreaseBasketBodyDto
import com.example.testprime.data.basket.model.DeleteBasketBodyDto
import com.example.testprime.data.basket.model.ResultBasketProductsDto
import com.example.testprime.domain.basket.model.AddAndDecreaseBasketBody
import com.example.testprime.domain.basket.model.BasketProduct
import com.example.testprime.domain.basket.model.Category
import com.example.testprime.domain.basket.model.DeleteBasketBody
import com.example.testprime.domain.basket.model.Image
import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.model.Options
import javax.inject.Inject

class BasketMapper @Inject constructor() {

    fun mapBasketDtoToEntity(resultBasketProductsDto: ResultBasketProductsDto): List<ItemBasket>{
        val listItemBasketDto = resultBasketProductsDto.result?.items
        val listItemBasket = listItemBasketDto?.map { itemBasketDto ->
            ItemBasket(
                quantity = itemBasketDto?.quantity,
                product = BasketProduct(
                    category = Category(
                        description = itemBasketDto?.product?.category?.description,
                        id = itemBasketDto?.product?.category?.id,
                        mainImageLink = itemBasketDto?.product?.category?.mainImageLink,
                        name = itemBasketDto?.product?.category?.name,
                    ),
                    description = itemBasketDto?.product?.description,
                    id = itemBasketDto?.product?.id,
                    images = itemBasketDto?.product?.images?.map { imageDto ->
                        Image(
                            link = imageDto?.link
                        )
                    },
                    measure = itemBasketDto?.product?.measure,
                    name = itemBasketDto?.product?.name,
                    options = Options(
                        carbohydrate = itemBasketDto?.product?.options?.carbohydrate,
                        carbohydratePerHundredGrams = itemBasketDto?.product?.options?.carbohydratePerHundredGrams,
                        energy = itemBasketDto?.product?.options?.energy,
                        energyPerHundredGrams = itemBasketDto?.product?.options?.energyPerHundredGrams,
                        fat = itemBasketDto?.product?.options?.fat,
                        fatPerHundredGrams = itemBasketDto?.product?.options?.fatPerHundredGrams,
                        protein = itemBasketDto?.product?.options?.protein,
                        proteinPerHundredGrams = itemBasketDto?.product?.options?.proteinPerHundredGrams,
                        weight = itemBasketDto?.product?.options?.weight
                    ),
                    price = itemBasketDto?.product?.price
                )
            )
        }
        return listItemBasket ?: emptyList<ItemBasket>()
    }

    fun mapAddAndDecreaseBasketBodyEntityToDto(basketBody: AddAndDecreaseBasketBody) = AddAndDecreaseBasketBodyDto(
        quantity = basketBody.quantity,
        productId = basketBody.productId
    )

    fun mapDeleteBasketBodyEntityToDto(basketBody: DeleteBasketBody) = DeleteBasketBodyDto(
        productId = basketBody.productId
    )

}