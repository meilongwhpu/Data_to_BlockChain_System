package io.nuls.data.web;

import io.nuls.data.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * web单元测试抽象类
 *
 * @author Long
 * @date 2020/03/16
 */
@AutoConfigureMockMvc(printOnlyOnFailure=false)
public abstract class AbstractWebTest extends AbstractTest {

    @Autowired
    protected MockMvc restMockMvc;

}

