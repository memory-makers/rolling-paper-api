package com.memorymakerpeople.memoryrollingpaper.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(1000, "요청 성공."),

    //    2000 : Request 오류
    REQUEST_ERROR( 2000, "적합하지 않은 입력값입니다. 입력값을 확인해주세요."),
    EMPTY_JWT( 2001, "JWT를 입력해주세요."),
    INVALID_JWT( 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT( 2003, "권한이 없는 유저의 접근입니다."),
    INVALID_USER_STATUS( 2004, "삭제되거나 휴면인 계정의 접근입니다."),
    INVALID_USER_PASSWORD( 2005, "잘못된 비밀번호입니다."),
    INVALID_TYPE_MISMATCH( 2006, "잘못된 타입의 입력값입니다. 입력값을 확인해주세요."),

    // [PATCH] /membersdsad
    PATCH_PRE_DELETED_USER( 2040, "이미 탈퇴한 회원입니다."),

    // [PUT] /members
    FOUND_USER_INFO_NULL( 2041, "회원 정보를 찾을 수 없습니다."),

    // [POST] /members
    POST_USER_INFO_NULL( 2042, "빠진 가입 정보가 없는지 확인해주세요."),

    // [POST] /members
    GET_USER_INFO_NULL( 2043, "로그인 후 다시 이용해주세요."),

    // [GET] /Papers
    EMPTY_PAPER_ID( 2050, "PaperId 값을 입력해주세요."),
    FOUND_URL_INFO_NULL( 2051,"해당 URL에 맞는 paperId가 없습니다."),
    FOUND_NICKNAME_INFO_NULL( 2052,"해당 paperId에 맞는 닉네임을 찾을 수 없습니다."),
    FOUND_PAPER_INFO_NULL( 2053,"해당 paperId에 맞는 롤링 페이퍼를 찾을 수 없습니다."),

    // [Put] /Papers
    FAILED_TO_PAPER_UPDATE( 2054, "본인의 게시물이 아니면 수정할 수 없습니다."),

    // [GET] /Card
    EMPTY_CARD_LIST(2201, "생성된 카드가 없습니다."),

    // [POST] /Card
    INVALID_CARD_DUE_DATE(2202,"해당 롤링페이퍼는 기간이 지났음으로 더 이상 수정이나 삭제가 불가능합니다."),

    // [GET] /Stickers
    FAILED_TO_LOAD_STICKERS( 2100, "잘못된 PaperId 입니다."),
    FAILED_TO_LOAD_STICKER( 2101, "잘못된 StickerId 입니다."),

    // [Post] /Stickers
    EMPTY_STICKER_LIST( 2120, "스티커 리스트를 찾을 수 없습니다."),

    //   3000 : Response 오류
    // Common
    RESPONSE_ERROR( 3000, "값을 불러오는데 실패하였습니다."),
    RESPONSE_NULL_ERROR_BY_IDX( 3001, "[NULL]입력된 IDX 값로 접근한 DB의 유효한 ROW가 존재하지 않습니다."),
    RESPONSE_NULL_ERROR( 3002, "[NULL]접근한 데이터 중 유효한 ROW가 존재하지 않습니다."),

    // [POST] /users
    FAILED_TO_LOGIN( 3014, "없는 아이디거나 비밀번호가 틀렸습니다."),

    //    4000 : Database, Server 오류
    DATABASE_ERROR( 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR( 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME( 4014, "유저네임 수정 실패"),

    // 5000
    LOGOUT_JWT( 5000, "이미 로그아웃 된 JWT 입니다."),
    INVALID_ACCESS_TOKEN( 5001, "유효하지 않은 토큰입니다."),
    KAKAO_LOGIN_REQUEST_FAILED( 5002, "카카오 소셜 로그인 중 응답 받기에 실패했습니다."),
    INVALID_IDX( 5003, "잘못된 IDX 값입니다."),
    INVALID_OFFSET( 5004, "잘못된 OFFSET 값입니다."),
    NULL_STRING( 5005, "검색어가 없습니다.."),
    NOT_LOGIN( 5006, "NOT_LOGIN."),
    NULL_ID( 5007, "아이디가 존재하지 않습니다."),
    USER_NOT_MATCH( 5008, "아이디가 존재하지 않습니다."),

    // 9000
    FAIL( 9000, "실패.");


    private final int code;
    private final String message;

    private BaseResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
