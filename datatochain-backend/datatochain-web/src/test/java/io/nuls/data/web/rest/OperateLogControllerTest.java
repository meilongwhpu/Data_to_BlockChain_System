package io.nuls.data.web.rest;

import io.nuls.common.util.JsonUtil;
import io.nuls.data.help.OperateLogHelper;
import io.nuls.data.pojo.dto.OperateLogAddDTO;
import io.nuls.data.pojo.dto.OperateLogUpdateDTO;
import io.nuls.data.pojo.po.OperateLogPO;
import io.nuls.data.web.AbstractWebTest;
import io.nuls.data.web.constant.WebConst;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 【操作日志】单元测试
 *
 * @author Long
 * @date 2020/03/16
 */
public class OperateLogControllerTest extends AbstractWebTest {

    @Autowired
    private OperateLogHelper operateLogHelper;


    /**
     * 分页查询【操作日志】
     */
    @Test
    public void list() throws Exception {
        OperateLogPO operateLog = operateLogHelper.saveOperateLogExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/operateLog"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.length()").value(is(1)));
    }



}
