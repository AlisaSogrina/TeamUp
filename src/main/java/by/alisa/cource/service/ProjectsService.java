package by.alisa.cource.service;

import by.alisa.cource.entity.Article;
import by.alisa.cource.repository.ArticlesRepository;
import by.alisa.cource.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repository;


}
