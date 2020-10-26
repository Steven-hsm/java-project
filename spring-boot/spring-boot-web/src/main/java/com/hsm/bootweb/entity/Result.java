package com.hsm.bootweb.entity;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: Result
 * @description: TODO
 * @date 2020/10/26 17:28
 */

public class Result<T> {
    /** 响应码 */
    private Integer status;
    /** 请求是否成功 */
    private Boolean success;
    /** 返回的具体实体 */
    private T t;
    /** 返回的错误消息 */
    private String desc;
    /** 响应编码 */
    private Integer code;
}
