package com.plum.cas.spring.service;

import com.plum.cas.dto.Resource;
import com.plum.cas.service.ResourceService;
import com.plum.core.filter.PageSortFilter;
import com.plum.core.utils.JacksonJsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by G_dragon on 2015/7/30.
 */
public class ResourceServiceTest0 extends AbstractTestBaseService {
    @Autowired
    private ResourceService resourceService;

    @Test
    public void findResourceTest(){

        int sizePerPage = 10;
        int currentPage = 2;

        PageSortFilter pageSortFilter = new PageSortFilter(sizePerPage, currentPage);

        List<Resource> resources = resourceService.findAllByPage(pageSortFilter);

        try {
            System.out.println(JacksonJsonUtil.beanToJson(resources));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print(pageSortFilter.getTotalCount() + " " + pageSortFilter.getTotalPage());
    }
}
