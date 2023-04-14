package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>JtsErrorStatus</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Getter
public enum JtsErrorStatus implements RestStatus {
    SUCCESS(200, "成功"),
    FAILED(400, "失败"),
    /** timeout */
    UNKNOWN_ERROR(8888, "未知错误"),
    UNSUPPORTED(9999, "不支持"),
    TIME_OUT(10000, "访问超时"),
    /** base */
    PARAM_ERROR(10010, "参数错误"),
    PARAM_INVALID(10012, "参数无效"),
    PARAM_MISSING(10011, "参数缺失"),
    /** json */
    PARSE_ERROR(10100, "解析错误"),
    JSON_PARSE_ERROR(10110, "JSON解析错误"),
    JSON_PARSE_BEAN(10111, "JSON解析为Bean出错"),
    JSON_PARSE_CONVERT(10112, "JSON解析转换类型出错"),
    JSON_PARSE_LIST(10113, "JSON解析为List出错"),
    JSON_PARSE_SET(10114, "JSON解析为Set出错"),
    JSON_PARSE_MAP(10115, "JSON解析为Map出错"),
    /** deserialize */
    JSON_DESERIALIZE_ERROR(10120, "JSON反序列化错误"),

    /** resource */
    RESOURCE_ERROR(10200, "资源错误"),
    RESOURCE_NOT_FOUND(10201, "资源不存在"),
    RESOURCE_UNAVAILABLE(10202, "资源不可用"),

    /** file */
    FILE_ERROR(10210, "文件错误"),
    FILE_NOT_EXIST(10211, "文件不存在"),
    FILE_IS_EXIST(10212, "文件已经存在"),
    FILE_UNAVAILABLE(10213, "文件不可用"),

    FILE_CREATE_ERROR(10214, "文件创建错误"),
    FILE_COPY_ERROR(10215, "文件复制错误"),

    /** access */
    AUTH_ERROR(10300, "权限错误"),
    AUTH_DENIED(10302, "拒绝访问"),
    AUTH_FORBIDDEN(10301, "权限不足"),

    /** auth */
    TOKEN_ERROR(10310, "认证错误"),
    TOKEN_EXPIRED(10313, "认证过期"),
    TOKEN_FAILED(10311, "认证失败"),
    TOKEN_SERVICE_(10312, "认证无效"),

    /** service */
    SERVICE_ERROR(10400, "服务器错误"),
    SERVICE_UNAVAILABLE(10401, "服务不可用"),

    /** config */
    CONFIG_ERROR(10410, "配置错误"),
    CONFIG_INVALID(10411, "配置无效"),
    CONFIG_UNAVAILABLE(10412, "配置不可用"),


    CONVERT_ERROR(10420, "转换错误"),
    CONVERT_TYPE_UNSUPPORTED(10421, "转换类型不支持"),
    CONVERT_TYPE_UNKNOWN(10422, "转换类型未知"),

    /** field */
    FIELD_ERROR(10500, "字段错误"),
    FIELD_IS_NULL(10501, "字段为空"),
    FIELD_NOT_EXIST(10502, "对象不存在"),
    FIELD_IS_EXIST(10503, "对象已存在"),
    /** name */
    NAME_ERROR(10510, "名称错误"),
    NAME_IS_NULL(10511, "名称为空"),
    NAME_REPEATED(10512, "名称重复"),
    /** IDENTITY */
    IDENTITY_ERROR(10520, "Id错误"),
    IDENTITY_IS_NULL(10521, "Id为空"),
    IDENTITY_REPEATED(10522, "Id重复"),
    /** data */
    DATA_ERROR(10600, "数据错误"),
    DATA_CREATE_FAILED(10601, "数据创建失败"),
    DATA_UPDATE_FAILED(10602, "数据更新失败"),
    DATA_SAVE_FAILED(10603, "数据保存失败"),
    DATA_DELETE_FAILED(10604, "数据删除失败"),
    DATA_QUERY_FAILED(10605, "数据查询失败"),
    DATA_TRANSFORM_FAILED(10606, "数据转换失败"),

    /** data all */
    DATA_ALL_ERROR(10610, "数据批量处理错误"),
    DATA_INSERT_ALL_FAILED(10611, "数据批量插入失败"),
    DATA_UPDATE_ALL_FAILED(10612, "数据批量更新失败"),
    DATA_SAVE_ALL_FAILED(10613, "数据批量保存失败"),
    DATA_DELETE_ALL_FAILED(10614, "数据批量删除失败"),
    DATA_QUERY_ALL_FAILED(10615, "数据批量查询失败"),

    /** stream */
    STREAM_ERROR(10620, "数据流错误"),
    STREAM_READ_ERROR(10621, "数据流读取错误"),
    STREAM_WRITE_ERROR(10622, "数据流写入错误"),
    STREAM_TRANSFER_ERROR(10623, "数据流迁移错误"),

    /** jts */
    JTS_ERROR(10700, "空间数据错误"),
    JTS_PARSE_ERROR(10701, "Jts相关数据解析错误"),
    JTS_BOX_INVALID_ERROR(10702, "Jts相关box范围无效"),

    /** shape */
    SHAPE_PARAMS_ERROR(10711, "shape文件读取数据源参数params构建错误"),
    SHAPE_DATA_STORE_ERROR(10712, "shape文件读取数据源dataStore构建错误"),
    SHAPE_FEATURES_ERROR(10713, "shape文件读取数据features读取错误"),
    SHAPE_PARAMS_UNINITIALIZED_ERROR(10714, "shape文件数据源params未初始化错误"),
    SHAPE_DATA_STORE_UNINITIALIZED_ERROR(10715, "shape文件数据源未初始化错误"),
    SHAPE_FEATURES_UNINITIALIZED_ERROR(10716, "shape文件数据features未初始化错误"),
    SHAPE_READER_UNINITIALIZED_ERROR(10717, "shape文件数据reader未初始化错误"),
    SHAPE_WRITER_UNINITIALIZED_ERROR(10718, "shape文件数据writer未初始化错误"),

    SHAPE_READER_PARAMS_ERROR(10719, "shape文件读取数据源参数params构建错误"),
    SHAPE_READER_DATA_STORE_ERROR(10720, "shape文件读取数据源dataStore构建错误"),
    SHAPE_READER_FEATURES_ERROR(10721, "shape文件读取数据features读取错误"),

    SHAPE_WRITER_PARAMS_ERROR(10722, "shape文件写入数据源参数params构建错误"),
    SHAPE_WRITER_DATA_STORE_ERROR(10723, "shape文件写入数据源dataStore构建错误"),
    SHAPE_WRITER_FEATURES_ERROR(10724, "shape文件写入数据features错误"),


    ;

    private final Integer status;
    private final String message;

    JtsErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
