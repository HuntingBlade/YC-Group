package com.shytong.common.exception;

/**
 * @author sytong
 * @Package com.shytong.common.exception
 * @Description:
 * @date 2018-01-0420:01
 */
public class ApiBizException extends Exception {

    private Throwable cause;
    private int errCode;
    private String errInfo;



    public  ApiBizException(){


          super();
      }
    public  ApiBizException(int errCode,String errInfo){
        super(errInfo);
         this.errCode=errCode;
        this.errInfo=errInfo;

    }

    public ApiBizException(Throwable t) {
        super(t.getMessage(),t);
        this.cause = t;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

}
