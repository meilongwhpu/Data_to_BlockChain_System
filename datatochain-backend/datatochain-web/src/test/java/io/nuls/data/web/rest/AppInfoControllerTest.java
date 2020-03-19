package io.nuls.data.web.rest;

import com.google.common.collect.Lists;
import io.nuls.common.util.JsonUtil;
import io.nuls.data.help.AppInfoHelper;
import io.nuls.data.help.TablespaceInfoHelper;
import io.nuls.data.pojo.dto.AppInfoAddDTO;
import io.nuls.data.pojo.dto.AppInfoUpdateDTO;
import io.nuls.data.pojo.po.AppInfoPO;
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
 * 【应用】单元测试
 *
 * @author Long
 * @date 2020/03/16
 */
public class AppInfoControllerTest extends AbstractWebTest {

    @Autowired
    private AppInfoHelper appInfoHelper;
    @Autowired
    private TablespaceInfoHelper tablespaceInfoHelper;


    /**
     * 新增【应用】
     */
    @Test
    public void save() throws Exception {
        AppInfoAddDTO addDTO = appInfoHelper.getAppInfoAddDTO();
        restMockMvc.perform(post(WebConst.API_PATH + "/appInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(addDTO)))
            .andExpect(status().isCreated());
    }

    /**
     * 修改【应用】
     */
    @Test
    public void update() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        AppInfoUpdateDTO updateDTO = appInfoHelper.getAppInfoUpdateDTO(appInfo);
        restMockMvc.perform(put(WebConst.API_PATH + "/appInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(updateDTO)))
            .andExpect(status().isOk());
    }

    /**
     * 分页查询【应用】
     */
    @Test
    public void list() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/appInfo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    /**
     * 查询【应用】选项列表
     */
    @Test
    public void findOptions() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/appInfo/options"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(is(1)));
    }

    /**
     * 查看【应用】详情
     */
    @Test
    public void show() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/appInfo/{id}",appInfo.getId()))
            .andExpect(status().isOk());
    }

    /**
     * 删除单个【应用】
     */
    @Test
    public void del() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/appInfo/{id}",appInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 批量删除【应用】
     */
    @Test
    public void deleteBatch() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/appInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(appInfo.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }

    /**
     * 添加/移除【表空间】关联
     */
    @Test
    public void addRemoveTablespaceInfo2() throws Exception {
        AppInfoPO appInfo = appInfoHelper.saveAppInfoExample();
        TablespaceInfoPO tablespaceInfo = tablespaceInfoHelper.saveTablespaceInfoExample();
        // 先测试添加【表空间】关联
        restMockMvc.perform(post(WebConst.API_PATH + "/appInfo/{id}/tablespaceInfo",
            appInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(tablespaceInfo.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
        // 再测试查询【表空间】关联
        restMockMvc.perform(get(WebConst.API_PATH + "/appInfo/{id}/tablespaceInfo",
            appInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(is(1)));
        // 最后测试移除【表空间】关联
        restMockMvc.perform(delete(WebConst.API_PATH + "/appInfo/{id}/tablespaceInfo",
            appInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJSONString(Lists.newArrayList(tablespaceInfo.getId()))))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value(is(1)));
    }



}
