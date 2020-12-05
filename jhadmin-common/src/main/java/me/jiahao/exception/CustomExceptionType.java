package me.jiahao.exception;

public enum CustomExceptionType {

    // 成功
    SUCCESS(200),

    // 失败
    FAIL(400),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 资源不可用
    UNFORBIDDEN(403),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    CustomExceptionType(int code) {
        this.code = code;
    }
}