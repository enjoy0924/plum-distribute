package com.plum.cas.spring.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-hibernate.xml","classpath:applicationContext-config.xml"})
public abstract class AbstractTestBaseService {
    @Autowired
    protected ApplicationContext applicationContext;
}
