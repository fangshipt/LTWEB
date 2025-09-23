package vn.iotstar.service;

import java.util.List;

import vn.iotstar.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	CategoryModel findById(int id);
	void insert (CategoryModel category);
	void update (CategoryModel category);
	void delete(int id);
	List<CategoryModel> findName (String keyword);

}
