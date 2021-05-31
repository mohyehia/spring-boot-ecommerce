package com.mohyehia.ecommerce.repository;

import com.mohyehia.ecommerce.model.dto.ProductDTO;
import com.mohyehia.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(int categoryId);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select p.ID as productId," +
            " p.Name as productName, p.PRICE as productPrice," +
            " p.DESCRIPTION as productDescription, c.CODE as categoryCode," +
            " c.NAME as categoryName, p.IMAGE_URL as imageUrl," +
            " s.IN_STOCK as countInStock," +
            " avg(pr.RATING) as rating, count(pr.USER_ID) as numberOfReviews" +
            " from product p" +
            " inner join stock s on p.ID = s.PRODUCT_ID" +
            " inner join product_reviews pr on p.ID = pr.PRODUCT_ID" +
            " inner join category c on p.CATEGORY_ID = c.ID" +
            " group by pr.PRODUCT_ID")
    List<ProductDTO> retrieveAllProducts();

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select p.ID as productId," +
            " p.Name as productName, p.PRICE as productPrice," +
            " p.DESCRIPTION as productDescription, c.CODE as categoryCode," +
            " c.NAME as categoryName, p.IMAGE_URL as imageUrl," +
            " s.IN_STOCK as countInStock," +
            " avg(pr.RATING) as rating, count(pr.USER_ID) as numberOfReviews" +
            " from product p" +
            " inner join stock s on p.ID = s.PRODUCT_ID" +
            " inner join product_reviews pr on p.ID = pr.PRODUCT_ID" +
            " inner join category c on p.CATEGORY_ID = c.ID" +
            " group by pr.PRODUCT_ID, p.CATEGORY_ID having p.CATEGORY_ID = :categoryId")
    List<ProductDTO> findProductsByCategoryId(@Param("categoryId") int categoryId);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select p.ID as productId," +
            " p.Name as productName, p.PRICE as productPrice," +
            " p.DESCRIPTION as productDescription, c.CODE as categoryCode," +
            " c.NAME as categoryName, p.IMAGE_URL as imageUrl," +
            " s.IN_STOCK as countInStock," +
            " avg(pr.RATING) as rating, count(pr.USER_ID) as numberOfReviews" +
            " from product p" +
            " inner join stock s on p.ID = s.PRODUCT_ID" +
            " inner join product_reviews pr on p.ID = pr.PRODUCT_ID" +
            " inner join category c on p.CATEGORY_ID = c.ID" +
            " group by pr.PRODUCT_ID having pr.PRODUCT_ID = :productId")
    ProductDTO findProductById(@Param("productId") long id);
}
