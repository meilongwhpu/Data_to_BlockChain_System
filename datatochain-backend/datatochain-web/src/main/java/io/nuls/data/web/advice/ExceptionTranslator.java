package io.nuls.data.web.advice;

import io.nuls.common.constant.ErrorCode;
import io.nuls.common.exception.BusinessException;
import io.nuls.common.pojo.vo.ReplyVO;
import io.nuls.common.util.JsonUtil;
import io.nuls.common.util.MessageSourceUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 异常信息展示
 *
 * @author Long
 * @date 2020/03/16
 */
@ControllerAdvice
public class ExceptionTranslator {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionTranslator.class);

    /**
     * body参数校验失败
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ReplyVO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return processBindingResult(result);
    }

    /**
     * param参数校验失败
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ReplyVO processValidationError(BindException ex) {
        BindingResult result = ex.getBindingResult();
        return processBindingResult(result);
    }

    private ReplyVO processBindingResult(BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        ReplyVO replyVO = new ReplyVO();
        replyVO.setCode(ErrorCode.ERR_VALIDATION.getValue());
        replyVO.setMessage(ErrorCode.ERR_VALIDATION.getDesc());
        if(CollectionUtils.isNotEmpty(errors)){
            List<String> errorMsgs = errors.stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
            LOGGER.warn(JsonUtil.toJSONString(errorMsgs));
            String message = errorMsgs.stream()
                .collect(Collectors.joining(";"));
            replyVO.setMessage(message);
        }
        return replyVO;
    }


    /**
     * http method有误
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ReplyVO processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ReplyVO.fail(ErrorCode.METHOD_NOT_SUPPORTED);
    }

    /**
     * 未传requestbody
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReplyVO processHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ReplyVO.fail("HttpMessageNotReadableException");
    }

    /**
     * 线程异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ReplyVO processConcurencyError(ConcurrencyFailureException ex) {
        return ReplyVO.fail(ErrorCode.CONCURRENCY_FAILURE);
    }


    /**
     * 唯一键重复
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ReplyVO processDuplicateKeyException(DuplicateKeyException ex) {
        return ReplyVO.fail(ErrorCode.DUPLICATE_KEY);
    }

    /**
     * 自定义异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ReplyVO> processBusinessException(BusinessException ex) {
        LOGGER.error("业务异常", ex);
        ErrorCode code = ex.getErrorCode();
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(Integer.parseInt(code.getValue()));
        ReplyVO replyVO = new ReplyVO(code.getValue(), ex.getMessage());
        return builder.body(replyVO);
    }


    /**
     * 普通异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ReplyVO> processRuntimeException(Exception ex) {
        LOGGER.error("系统内部错误",ex);
        ResponseEntity.BodyBuilder builder;
        ReplyVO replyVO;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            replyVO = new ReplyVO(responseStatus.value().value() + "", MessageSourceUtil.getMessage(responseStatus.reason()));
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            replyVO = ReplyVO.fail(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return builder.body(replyVO);
    }


}

