package au.edu.swu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.swu.dao.PageDao;
import au.edu.swu.domain.File;
import au.edu.swu.domain.Page;


@Service
public class PageService {

	@Autowired
	private PageDao pageDao;
    /**
     * 传入一页有多少条数据
     * 计算总页数
     */
    @Transactional
    public Integer pageTotal(int pageSize) {
    	int totalCounts=pageDao.getCounts();
    	int totalPages=(totalCounts+pageSize-1)/pageSize;
    	return totalPages;
    }
    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Transactional
    public Page queryForPage(int currentPage,int pageSize) {
        // TODO Auto-generated method stub

        Page page = new Page();        
        //总记录数
        int allRow = pageDao.getCounts();
       
        int offset = page.countOffset(currentPage,pageSize);  
        //分页查询结果集
        List<File> list = pageDao.queryForPage(offset, pageSize); 

        //封装page
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
    }
    
}
