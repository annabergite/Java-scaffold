package com.chashugu.lowcodecrud;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.alibaba.fastjson.JSON;

@Getter
@Setter
@SuppressWarnings("ALL")
@Accessors(chain = true)
public class Result<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final int TIMEOUT_CODE = 503;
    /**
     * 统一参数验证异常
     */
    public static final int VALID_EX_CODE = 400;
    public static final int OPERATION_EX_CODE = 400;
    /**
     * 调用是否成功标识，0：成功，-1:系统繁忙，此时请开发者稍候再试 详情见[ExceptionCode]
     */
    @ApiModelProperty(value = "响应编码:0/200-请求处理成功")
    private int state;

    /**
     * 调用结果
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 结果消息，如果调用成功，消息通常为空T
     */
    @ApiModelProperty(value = "提示消息")
    private String msg = "SUCCESS";

    /**
     * 附加数据
     */
    // @ApiModelProperty(value = "附加数据")
    // private Map<Object, Object> extra;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间戳")
    private long timestamp = System.currentTimeMillis();

    /**
     * 系统报错时，抛出的原生信息
     */
    @ApiModelProperty(value = "异常消息")
    private String errorMsg = null;

    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, T data, String msg) {
        this.state = code;
        this.data = data;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, T data, String msg, String errorMsg) {
        this(code, data, msg);
        this.errorMsg = errorMsg;
    }

    public Result(String message, Object data, String msg) {
    }

    public Result(String message, Object data, String message1, String errorMsg) {
    }

    public static <E> Result<E> result(int code, E data, String msg) {
        return new Result<>(code, data, msg);
    }

    public static <E> Result<E> result(int code, E data, String msg, String errorMsg) {
        return new Result<>(code, data, msg, errorMsg);
    }

    /**
     * 请求成功消息
     *
     * @param data 结果
     * @return RPC调用结果
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(SUCCESS_CODE, data, "SUCCESS");
    }

    public static Result<Boolean> success() {
        return new Result<>(SUCCESS_CODE, true, "SUCCESS");
    }


    public static <E> Result<E> successDef(E data) {
        return new Result<>(SUCCESS_CODE, data, "SUCCESS");
    }

    public static <E> Result<E> successDef() {
        return new Result<>(SUCCESS_CODE, null, "SUCCESS");
    }

    public static <E> Result<E> successDef(E data, String msg) {
        return new Result<>(SUCCESS_CODE, data, msg);
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data 结果
     * @param msg  消息
     * @return RPC调用结果
     */
    public static <E> Result<E> success(E data, String msg) {
        return new Result<>(SUCCESS_CODE, data, msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> Result<E> fail(int code, String msg) {
        return new Result<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> Result<E> fail(int code, String msg, String errorMsg) {
        return new Result<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg, errorMsg);
    }

    public static <E> Result<E> fail(String msg) {
        return fail(OPERATION_EX_CODE, msg);
    }

    public static <E> Result<E> fail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new Result<>(OPERATION_EX_CODE, null, String.format(message, args));
    }

    public static <E> Result<E> fail(Exception exception, String errorMsg) {
        if (exception == null) {
            return fail(DEF_ERROR_MESSAGE);
        }
        return new Result<>(exception.getMessage(), null, exception.getMessage(), errorMsg);
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return RPC调用结果
     */
    public static <E> Result<E> fail(Throwable throwable) {
        String msg = throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE;
        return fail(FAIL_CODE, msg, msg);
    }

    public static <E> Result<E> validFail(String msg) {
        return new Result<>(VALID_EX_CODE, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> Result<E> validFail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new Result<>(VALID_EX_CODE, null, String.format(message, args));
    }

    public static <E> Result<E> validFail(Exception exceptionCode) {
        return new Result<>(exceptionCode.getMessage(), null,
                (exceptionCode.getMessage() == null || exceptionCode.getMessage().isEmpty()) ? DEF_ERROR_MESSAGE : exceptionCode.getMessage());
    }

    public static <E> Result<E> timeout() {
        return fail(TIMEOUT_CODE, HYSTRIX_ERROR_MESSAGE);
    }

    public Boolean getIsSuccess() {
        return this.state == SUCCESS_CODE || this.state == 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}