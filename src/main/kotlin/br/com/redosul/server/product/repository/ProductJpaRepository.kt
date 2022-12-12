package br.com.redosul.server.product.repository

import br.com.redosul.server.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository: JpaRepository<Product, Long> {


    fun findAllByCategoryId(id: Long): List<Product>

}