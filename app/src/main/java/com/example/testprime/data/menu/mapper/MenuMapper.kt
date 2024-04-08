package com.example.testprime.data.menu.mapper

import com.dot.prime.data.menu.model.ProductDto
import com.dot.prime.data.menu.model.ResultCategoriesProductDto
import com.dot.prime.data.menu.model.ResultProductsDto
import com.example.testprime.domain.menu.model.CategoryProduct
import com.example.testprime.domain.menu.model.Image
import com.example.testprime.domain.menu.model.Options
import com.example.testprime.domain.menu.model.Product
import javax.inject.Inject

class MenuMapper @Inject constructor() {

    fun mapCategoriesProductDtoToEntity(resultCategoriesProductDto: ResultCategoriesProductDto): List<CategoryProduct>{
        val listCategoriesDto = resultCategoriesProductDto.result
        val listCategories = listCategoriesDto?.map { categoryDto ->
            CategoryProduct(
                description = categoryDto?.description,
                id = categoryDto?.id,
                mainImageLink = categoryDto?.mainImageLink,
                name = categoryDto?.name
            )
        }
        return listCategories ?: emptyList<CategoryProduct>()
    }

    fun mapProductsDtoToEntity(resultProductsDto: ResultProductsDto): List<Product>{
        val listProductsDto = resultProductsDto.result
        val listProducts = listProductsDto?.map { productDto ->
            mapProductDtoToEntity(productDto)
        }
        return listProducts ?: emptyList<Product>()
    }

    fun mapProductDtoToEntity(productDto: ProductDto) = Product(
        category = CategoryProduct(
            description = productDto.category?.description,
            id = productDto.category?.id,
            mainImageLink = productDto.category?.mainImageLink,
            name = productDto.category?.name
        ),
        description = productDto.description,
        id = productDto.id,
        images = productDto.images?.map { imageDto ->
            Image(
                link = imageDto?.link
            )
        },
        measure = productDto.measure,
        name = productDto.name,
        options = Options(
            carbohydrate = productDto.options?.carbohydrate,
            carbohydratePerHundredGrams = productDto.options?.carbohydratePerHundredGrams,
            energy = productDto.options?.energy,
            energyPerHundredGrams = productDto.options?.energyPerHundredGrams,
            fat = productDto.options?.fat,
            fatPerHundredGrams = productDto.options?.fatPerHundredGrams,
            protein = productDto.options?.protein,
            proteinPerHundredGrams = productDto.options?.proteinPerHundredGrams,
            weight = productDto.options?.weight
        ),
        price = productDto.price
    )

}