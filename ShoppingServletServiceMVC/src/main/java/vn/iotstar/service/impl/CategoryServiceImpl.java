package vn.iotstar.service.impl;

import java.io.File;
import java.util.List;

import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.model.Category;
import vn.iotstar.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	CategoryDaoImpl categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category category) {
		Category oldCategory = categoryDao.get(category.getCateid());
		oldCategory.setCatename(category.getCatename());
		if (category.getIcon() != null) {
			// XOA ANH CU DI
			String fileName = oldCategory.getIcon();
			final String dir = "E:\\WEB\\ShoppingServletServiceMVC\\upload";
			File file = new File(dir + "\\category\\" + fileName);
			if (file.exists()) {
				file.delete();
			}
			oldCategory.setIcon(category.getIcon());
		}
		categoryDao.edit(oldCategory);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);

	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);

	}

}
