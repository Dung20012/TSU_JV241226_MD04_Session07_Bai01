package com.data.session_07.model.service;

import com.data.session_07.model.entity.Category;
import com.data.session_07.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public boolean save(Category category) {
        return categoryRepository.save(category);
    }

   @Transactional(readOnly = true)
    public Category findById(long id) {
        return categoryRepository.findById(id);
   }

   @Transactional
   public boolean deleteById(long id) {
        return categoryRepository.delete(id);
   }

   @Transactional
    public boolean existsByCateName(String cateName) {
        return categoryRepository.existsByCateName(cateName);
    }
}
