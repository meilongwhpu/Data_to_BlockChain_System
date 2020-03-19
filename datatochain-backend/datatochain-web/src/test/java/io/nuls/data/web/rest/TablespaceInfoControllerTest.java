package io.nuls.data.web.rest;

import com.google.common.collect.Lists;
import io.nuls.common.util.JsonUtil;
import io.nuls.data.help.TablespaceInfoHelper;
import io.nuls.data.pojo.dto.TablespaceInfoAddDTO;
import io.nuls.data.pojo.dto.TablespaceInfoUpdateDTO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
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
 * 【表空间】单元测试
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablespaceInfoControllerTest extends AbstractWebTest {

    @Autowired
    private TablespaceInfoHelper tablespaceInfoHelper;


    /**
     * 新增【表空间】
     */
    @Test
    public void save() throws Exception {
        TablespaceInfoAddDTO addDTO = tablespaceInfoHelper.getTablespaceInfoAddDTO();
        restMockMvc.perform(post(WebConst.API_PATH + "/tablespaceInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(addDTO)))
            .andExpect(status().isCreated());
    }

    /**
     * 修改【表空间】
     */
    @Test
    public void update() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablespaceInfoUpdateDTO updateDTO = tablespaceInfoHelper.getTablespaceInfoUpdateDTO(tablespaceInfo);
        restMockMvc.perform(put(WebConst.API_PATH + "/tablespaceInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(updateDTO)))
            .andExpect(status().isOk());
    }

    /**
     * 分页查询【表空间】
     */
    @Test
    public void list() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/tablespaceInfo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【表空间】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/tablespaceInfo/options"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【表空间】详情
     */
    @Test
    public void show() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/tablespaceInfo/{id}",tablespaceInfo.getId()))
            .andExpect(status().isOk());
    }

    /**
     * 删除单个【表空间】
     */
    @Test
    public void del() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/tablespaceInfo/{id}",tablespaceInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【表空间】
     */
    @Test
    public void deleteBatch() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/tablespaceInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(tablespaceInfo.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }



}
