package com.example.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Operation {
    private Integer operationId;
    private String operator;
    private String operationContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationTime;
    private String operationType;

    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", operator='" + operator + '\'' +
                ", operationContent='" + operationContent + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", operationTpye='" + operationType + '\'' +
                '}';
    }
}
