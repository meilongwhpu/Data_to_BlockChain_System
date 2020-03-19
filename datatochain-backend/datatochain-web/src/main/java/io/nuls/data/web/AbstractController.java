package io.nuls.data.web;

import io.nuls.common.convert.MyCustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

/**
 * 抽象controller
 *
 * @author Long
 * @date 2020/03/16
 */
public abstract class AbstractController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new MyCustomDateEditor());
    }

}

