package by.alisa.cource.service;

import by.alisa.cource.entity.Article;
import by.alisa.cource.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository repository;


    public void save(Article newArticle) {
        repository.save(newArticle);
    }

    public String getCringeById() {
        return repository.findById(1L).orElse(new Article("aha")).getText();
    }
}
