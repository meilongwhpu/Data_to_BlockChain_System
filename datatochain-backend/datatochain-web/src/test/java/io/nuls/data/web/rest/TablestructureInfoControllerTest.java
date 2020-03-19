package io.nuls.data.web.rest;

import com.google.common.collect.Lists;
import io.nuls.common.util.JsonUtil;
import io.nuls.data.help.TablespaceInfoHelper;
import io.nuls.data.help.TablestructureInfoHelper;
import io.nuls.data.pojo.dto.TablestructureInfoAddDTO;
import io.nuls.data.pojo.dto.TablestructureInfoUpdateDTO;
import io.nuls.data.pojo.po.TablespaceInfoPO;
import io.nuls.data.pojo.po.TablestructureInfoPO;
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
 * 【表结构】单元测试
 *
 * @author Long
 * @date 2020/03/16
 */
public class TablestructureInfoControllerTest extends AbstractWebTest {

    @Autowired
    private TablespaceInfoHelper tablespaceInfoHelper;
    @Autowired
    private TablestructureInfoHelper tablestructureInfoHelper;


    /**
     * 新增【表结构】
     */
    @Test
    public void save() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoAddDTO addDTO = tablestructureInfoHelper.getTablestructureInfoAddDTO(tablespaceInfo.getId());
        restMockMvc.perform(post(WebConst.API_PATH + "/tablestructureInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(addDTO)))
            .andExpect(status().isCreated());
    }

    /**
     * 修改【表结构】
     */
    @Test
    public void update() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        TablestructureInfoUpdateDTO updateDTO = tablestructureInfoHelper.getTablestructureInfoUpdateDTO(tablestructureInfo);
        restMockMvc.perform(put(WebConst.API_PATH + "/tablestructureInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(updateDTO)))
            .andExpect(status().isOk());
    }

    /**
     * 分页查询【表结构】
     */
    @Test
    public void list() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        restMockMvc.perform(get(WebConst.API_PATH + "/tablestructureInfo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【表结构】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        restMockMvc.perform(get(WebConst.API_PATH + "/tablestructureInfo/options"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【表结构】详情
     */
    @Test
    public void show() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        restMockMvc.perform(get(WebConst.API_PATH + "/tablestructureInfo/{id}",tablestructureInfo.getId()))
            .andExpect(status().isOk());
    }

    /**
     * 删除单个【表结构】
     */
    @Test
    public void del() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        restMockMvc.perform(delete(WebConst.API_PATH + "/tablestructureInfo/{id}",tablestructureInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【表结构】
     */
    @Test
    public void deleteBatch() throws Exception {
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        TablestructureInfoPO tablestructureInfo = tablestructureInfoHelper.saveTablestructureInfoExample(tablespaceInfo.getId());
        restMockMvc.perform(delete(WebConst.API_PATH + "/tablestructureInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(tablestructureInfo.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }



}
