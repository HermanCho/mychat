package cn.edu.scau.dbclub.mychat.result;

public class ErrorResponse {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, String message) {
        this.errorCode = error;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
