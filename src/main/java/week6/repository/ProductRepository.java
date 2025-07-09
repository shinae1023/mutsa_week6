package week6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import week6.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductSummaryResponseDto> findByProductNameContaining(String productName);
}
