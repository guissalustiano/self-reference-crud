package br.com.redosul.server.product.repository

import br.com.redosul.server.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>