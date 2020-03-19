package io.nuls.data.web.rest;

import com.google.common.collect.Lists;
import io.nuls.common.util.JsonUtil;
import io.nuls.data.help.SystemDictHelper;
import io.nuls.data.pojo.dto.SystemDictAddDTO;
import io.nuls.data.pojo.dto.SystemDictUpdateDTO;
import io.nuls.data.pojo.po.SystemDictPO;
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
 * 【系统参数】单元测试
 *
 * @author Long
 * @date 2020/03/16
 */
public class SystemDictControllerTest extends AbstractWebTest {

    @Autowired
    private SystemDictHelper systemDictHelper;


    /**
     * 新增【系统参数】
     */
    @Test
    public void save() throws Exception {
        SystemDictAddDTO addDTO = systemDictHelper.getSystemDictAddDTO();
        restMockMvc.perform(post(WebConst.API_PATH + "/systemDict")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(addDTO)))
            .andExpect(status().isCreated());
    }

    /**
     * 修改【系统参数】
     */
    @Test
    public void update() throws Exception {
        SystemDictPO systemDict = systemDictHelper.saveSystemDictExample();
        SystemDictUpdateDTO updateDTO = systemDictHelper.getSystemDictUpdateDTO(systemDict);
        restMockMvc.perform(put(WebConst.API_PATH + "/systemDict")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(updateDTO)))
            .andExpect(status().isOk());
    }

    /**
     * 分页查询【系统参数】
     */
    @Test
    public void list() throws Exception {
        SystemDictPO systemDict = systemDictHelper.saveSystemDictExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/systemDict"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查看【系统参数】详情
     */
    @Test
    public void show() throws Exception {
        SystemDictPO systemDict = systemDictHelper.saveSystemDictExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/systemDict/{id}",systemDict.getId()))
            .andExpect(status().isOk());
    }

    /**
     * 删除单个【系统参数】
     */
    @Test
    public void del() throws Exception {
        SystemDictPO systemDict = systemDictHelper.saveSystemDictExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/systemDict/{id}",systemDict.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【系统参数】
     */
    @Test
    public void deleteBatch() throws Exception {
        SystemDictPO systemDict = systemDictHelper.saveSystemDictExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/systemDict")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(systemDict.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }



}
