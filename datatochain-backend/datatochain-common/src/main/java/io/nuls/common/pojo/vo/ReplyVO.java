package io.nuls.common.pojo.vo;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.util.MessageSourceUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用响应对象
 *
 * @author Long
 * @date 2020/03/16
 */
@ApiModel
public class ReplyVO<T> extends AbstractVO {

    public static final String SUCCESS_CODE = "0";
    public static final String DEFAULT_SUCCESS_MSG_KEY = "success";
    public static final String DEFAULT_ERROR_CODE = "-1";

    @ApiModelProperty(notes = "响应代码【0正确,非0错误】", example = SUCCESS_CODE, required = true)
    private String code;

    @ApiModelProperty(notes = "结果描述", example = "success", required = true)
    private String message;

    @ApiModelProperty(notes = "返回数据")
    private T data;

    public ReplyVO() {
    }

    public ReplyVO(T data) {
        this.data = data;
    }

    public ReplyVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ReplyVO<T> fail(String message) {
        return new ReplyVO<>(DEFAULT_ERROR_CODE, message);
    }

    public static <T> ReplyVO<T> fail(ErrorCode errorCode) {
        return new ReplyVO<>(errorCode.getValue(), MessageSourceUtil.getMessage(errorCode.getDesc()));
    }

    public static <T> ReplyVO<T> success() {
        return new ReplyVO<>(SUCCESS_CODE, MessageSourceUtil.getMessage("reply.success", DEFAULT_SUCCESS_MSG_KEY));
    }

    public static <T> ReplyVO<T> success(T data) {
        ReplyVO<T> replyVO = new ReplyVO<>(SUCCESS_CODE, MessageSourceUtil.getMessage("reply.success", DEFAULT_SUCCESS_MSG_KEY));
        return replyVO.data(data);
    }

    /**
     * 设置数据
     *
     * @param data
     * @return
     */
    public ReplyVO<T> data(T data) {
        setData(data);
        return this;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

