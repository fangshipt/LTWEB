package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);

			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String url = req.getRequestURI();
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");

	    if (url.contains("insert")) {
	        CategoryModel category = new CategoryModel();

	        // LẤY ĐÚNG TÊN THAM SỐ
	        String categoryname = req.getParameter("categoryname");     // <-- đúng
	        String statusStr    = req.getParameter("status");           // "0" hoặc "1"

	        // PHÒNG THỦ: nếu trống thì mặc định 1
	        int status = 1;
	        try { if (statusStr != null && !statusStr.isBlank()) status = Integer.parseInt(statusStr); }
	        catch (NumberFormatException ignore) {}

	        category.setCategoryname(categoryname);
	        category.setStatus(status);

	        String uploadPath = DIR;                        // ví dụ: "C:/upload"
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) uploadDir.mkdir();

	        try {
	            Part part = req.getPart("images");
	            if (part != null && part.getSize() > 0) {
	                String original = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	                String ext = "";
	                int dot = original.lastIndexOf('.');
	                if (dot >= 0) ext = original.substring(dot + 1);
	                String fname = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);

	                // GHI ĐÚNG TÊN FILE ĐÃ ĐẶT
	                part.write(uploadPath + "/" + fname);
	                category.setImages(fname);
	            } else {
	                // Ảnh mặc định
	                category.setImages("avatar.png");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            category.setImages("avatar.png");
	        }

	        cateService.insert(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	        return;
	    }

	    if (url.contains("update")) {
	        // LẤY ĐÚNG TÊN THAM SỐ
	        String idStr        = req.getParameter("categoryid");       // <-- đúng
	        String categoryname = req.getParameter("categoryname");     // <-- đúng
	        String statusStr    = req.getParameter("status");

	        // PHÒNG THỦ
	        int categoryid = 0;
	        try { categoryid = Integer.parseInt(idStr); }
	        catch (NumberFormatException e) {
	            // thiếu id => quay lại list
	            resp.sendRedirect(req.getContextPath() + "/admin/categories");
	            return;
	        }

	        int status = 1;
	        try { if (statusStr != null && !statusStr.isBlank()) status = Integer.parseInt(statusStr); }
	        catch (NumberFormatException ignore) {}

	        CategoryModel category = new CategoryModel();
	        category.setCategoryid(categoryid);
	        category.setCategoryname(categoryname);
	        category.setStatus(status);

	        // LƯU ẢNH: nếu không chọn ảnh mới thì giữ ảnh cũ
	        CategoryModel old = cateService.findById(categoryid);
	        String oldFile = (old != null ? old.getImages() : "avatar.png");

	        String uploadPath = DIR;
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) uploadDir.mkdir();

	        try {
	            Part part = req.getPart("images");
	            if (part != null && part.getSize() > 0) {
	                String original = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	                String ext = "";
	                int dot = original.lastIndexOf('.');
	                if (dot >= 0) ext = original.substring(dot + 1);
	                String fname = System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);

	                part.write(uploadPath + "/" + fname);   // ghi đúng tên mới
	                category.setImages(fname);
	            } else {
	                category.setImages(oldFile);            // giữ ảnh cũ
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            category.setImages(oldFile);
	        }

	        cateService.update(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    }
	}
}