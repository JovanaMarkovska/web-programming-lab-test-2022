package mk.ukim.finki.wp.lab2022.service.impl;

import mk.ukim.finki.wp.lab2022.model.News;
import mk.ukim.finki.wp.lab2022.model.NewsCategory;
import mk.ukim.finki.wp.lab2022.model.NewsType;
import mk.ukim.finki.wp.lab2022.model.exceptions.InvalidNewsIdException;
import mk.ukim.finki.wp.lab2022.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.lab2022.repository.NewsRepository;
import mk.ukim.finki.wp.lab2022.service.NewsService;
import org.springframework.stereotype.Service;

import java.nio.channels.NonWritableChannelException;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsCategoryRepository newsCategoryRepository, NewsRepository newsRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> listAllNews() {
        return this.newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return this.newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
    }

    @Override
    public News create(String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory = category!=null ? this.newsCategoryRepository.findById(category).orElse(null) : null;
        News n = new News(name,description,price,type, newsCategory);
        return this.newsRepository.save(n);
    }

    @Override
    public News update(Long id, String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory = category!=null ? this.newsCategoryRepository.findById(category).orElse(null) : null;
        News n = id!=null ? this.newsRepository.findById(id).orElse(null) : null;
        n.setName(name);
        n.setDescription(description);
        n.setPrice(price);
        n.setType(type);
        n.setCategory(newsCategory);
        return this.newsRepository.save(n);
    }

    @Override
    public News delete(Long id) {
        News n = this.newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
        this.newsRepository.deleteById(id);
        return n;
    }

    @Override
    public News like(Long id) {
        News n = this.newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
        n.setLikes(n.getLikes() + 1);
        return this.newsRepository.save(n);
    }

    @Override
    public List<News> listNewsWithPriceLessThanAndType(Double price, NewsType type) {
        if(price == null && type == null){
            return this.newsRepository.findAll();
        }
        else if(price != null && type == null){
            return this.newsRepository.findByPriceLessThan(price);
        }
        else if(price == null && type != null){
            return this.newsRepository.findByType(type);
        }
        else{
            return  this.newsRepository.findByPriceLessThanAndType(price,type);
        }
    }
}
