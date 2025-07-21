package com.data.session_07.controller;

import com.data.session_07.model.entity.Category;
import com.data.session_07.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "listCategory";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categories", new Category());
        return "addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, Model model, RedirectAttributes redirectAttributes) {
        if (category.getCateName() == null || category.getCateName().isEmpty()){
            model.addAttribute("categories", category);
            model.addAttribute("error", "Tên danh mục không được để trống !");
            return "addCategory";
        } else if (categoryService.existsByCateName(category.getCateName())) {
            model.addAttribute("category", category);
            model.addAttribute("error", "Tên danh mục đã tồn tại !");
            return "addCategory";
        }
        categoryService.save(category);
        redirectAttributes.addAttribute("message", "Thêm danh mục thành công !");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute Category category, Model model) {
        Category oldCategory = categoryService.findById(id);
        if (categoryService.existsByCateName(category.getCateName()) && !category.getCateId().equals(id)) {
            model.addAttribute("error", "Tên danh mục đã tồn tại !");
            return "editCategory";
        } else if (categoryService.existsByCateName(category.getCateName()) && !oldCategory.getCateName().equalsIgnoreCase(category.getCateName())) {
            model.addAttribute("categories", category);
            model.addAttribute("error", "Tên danh mục đã tồn tại !");
            return "addCategory";
        }
        category.setCateId(id);
        categoryService.save(category);
        model.addAttribute("message", "Cập nhật thành công !");
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
