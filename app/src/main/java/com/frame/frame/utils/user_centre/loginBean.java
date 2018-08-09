package com.frame.frame.utils.user_centre;

/**
 * describe:
 * author:zhuang
 */

public class loginBean {

    /**
     * docId : DOCTOR0127
     * status : true
     * errorMsg : null
     */

    private String docId;
    private boolean status;
    private Object errorMsg;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }
}
