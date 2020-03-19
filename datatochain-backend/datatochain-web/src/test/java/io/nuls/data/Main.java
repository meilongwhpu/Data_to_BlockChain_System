package io.nuls.data;

import io.nuls.data.web.rest.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 合并测试类
 *
 * @author Long
 * @date 2020/03/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    OperateLogControllerTest.class,
    AppInfoControllerTest.class,
    TablespaceInfoControllerTest.class,
    TablestructureInfoControllerTest.class,
    TablefieldInfoControllerTest.class,
    SystemDictControllerTest.class,
})
public class Main {


}

