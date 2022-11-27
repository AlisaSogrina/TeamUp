package by.alisa.cource.repository;

import by.alisa.cource.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends CrudRepository<Article, Long> {

}
