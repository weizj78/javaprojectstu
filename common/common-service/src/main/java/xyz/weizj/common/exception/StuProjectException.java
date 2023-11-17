package xyz.weizj.common.exception;

import lombok.Data;
import xyz.weizj.model.vo.common.ResultCodeEnum;

@Data
public class StuProjectException extends RuntimeException{
    private Integer code;
    private String message;

    private ResultCodeEnum resultCodeEnum;

    public StuProjectException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
