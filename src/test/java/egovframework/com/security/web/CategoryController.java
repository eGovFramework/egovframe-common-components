package egovframework.com.security.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("categoryController")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @RequestMapping("/sale/listCategory.do")
    public void selectCategoryList() {
        LOGGER.debug("### CategoryController selectCategoryList() ");
    }

    @RequestMapping("/sale/addCategoryView.do")
    public String addCategoryView() {
        return "/sale/registerCategory";
    }

    @RequestMapping("/sale/addCategory.do")
    public String addCategory() {
        return "forward:/sale/listCategory.do";
    }

    @RequestMapping("/sale/updateCategoryView.do")
    public String updateCategoryView() {
        return "/sale/registerCategory";
    }

    @RequestMapping("/sale/updateCategory.do")
    public String updateCategory() {
        return "forward:/sale/listCategory.do";
    }

    @RequestMapping("/sale/deleteCategory.do")
    public String deleteCategory() {
        return "forward:/sale/listCategory.do";
    }

    @RequestMapping("/system/accessDenied.do")
    public String accessDenyView() {
        return "/system/accessDenied";
    }

}
