package com.howthere.app.type;

public enum QuestionType {
    EVENT("이벤트"),
    PAY("결재"),
    CANCEL("환불/취소"),
    BENEFIT("혜택"),
    USE("서비스 이용"),
    MEMBER("회원");

    private final String kor;

    public String getKor() {
        return kor;
    }

    QuestionType(String kor) {
        this.kor = kor;
    }
}
