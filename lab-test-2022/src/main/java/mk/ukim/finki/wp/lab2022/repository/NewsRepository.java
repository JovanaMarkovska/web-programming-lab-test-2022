package mk.ukim.finki.wp.lab2022.repository;

import mk.ukim.finki.wp.lab2022.model.News;
import mk.ukim.finki.wp.lab2022.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {
    List<News> findByPriceLessThan(Double price);
    List<News> findByCategory(Double price);

    List<News> findByType(NewsType newsType);
    List<News> findByPriceLessThanAndType(Double price, NewsType newsType);
}
