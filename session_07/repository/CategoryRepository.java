package com.data.session_07.repository;

import com.data.session_07.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // đánh dấu là 1 repository (tầng truy xuất dữ liệu)
public class CategoryRepository {
    @Autowired // Inject đối tượng SessionFactory do Spring quản lý
    private SessionFactory sessionFactory;

    // Lấy toàn bộ danh sách Category
    public List<Category> findAll() {
        Session session = sessionFactory.openSession(); // mở session mới
        return session.createQuery("from Category", Category.class).list(); // truy vấn tất cả category
    }

    // Tìm Category theo Id
    public Category findById(Long id) {
        Session session = sessionFactory.getCurrentSession(); // lấy session hiện tại
        try {
            return session.get(Category.class, id); // lấy đối tượng theo id (primary_key)
        }catch (Exception e){
            return null;
        }
    }

    // Thêm mới hoặc cập nhật Category
    public boolean save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(category); // nếu đã có thì update, chưa thì insert
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // Xóa Category theo id
    public boolean delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = findById(id); // tìm category theo id
        if (category != null) {
            session.delete(category); // tìm thấy thì xóa
            return true;
        }else  {
            return false;
        }
    }

    // Ktra tên danh mục đã tồn tại hay chưa
    public boolean existsByCateName(String cateName) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.createQuery("from Category where cateName = :cateName", Category.class).
                setParameter("cateName", cateName) // truyền giá trị cateName vào ô truy vấn
                .uniqueResult(); // lấy duy nhất 1 kết quả (hoặc null nếu k có)
        return category != null; // trả về true nếu tồn tại
    }
}
