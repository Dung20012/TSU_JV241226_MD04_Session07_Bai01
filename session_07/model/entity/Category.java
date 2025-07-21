package com.data.session_07.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // Ánh xạ class với 1 bảng trong DB
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "categories") // Tên bảng trong DB
public class Category {
    @Id // Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự tăng auto_increment
    private Long cateId;
    private String cateName;
    private String description;
    private Boolean status;
}
