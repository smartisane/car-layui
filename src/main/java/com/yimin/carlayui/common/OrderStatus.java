package com.yimin.carlayui.common;

public enum  OrderStatus {
    //-3已取消，-2待签合同，-1待付款，0生效中，1已到期，2退租申请，3退租申请不通过
    CANCEL(-3),
    NOT_AGREEMENT(-2),
    NOT_PAY(-1),
    NORMAL(0),
    FINISH(1),
    END_RENT_APPLY(2),
    END_RENT_NOT_ALLOW(3);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
