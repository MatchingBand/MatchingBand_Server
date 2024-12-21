package com.matching.band.global.util.exception.handler;

import com.matching.band.global.util.response.ApiResponse;
import com.matching.band.global.util.response.ErrorData;
import com.matching.band.global.util.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * [Global Exception]
 * Controller 내에서 발생하는 Exception 관리
 * @author 최성민
 * @since 2024-12-03
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalApiExceptionHandler {

    /**
     * [Exception] 잘못된 주소로 요청 한 경우
     * @param exception NoHandlerFoundException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiResponse<ErrorData> handleNoHandlerFoundExceptionException(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorCode.NOT_FOUND.getErrorCode())
                .errorMessage(ErrorCode.NOT_FOUND.getErrorMessage())
                .build();
        return ApiResponse.fail(ErrorCode.NOT_FOUND.getStatusCode(), response);
    }

    /**
     * [Exception] Input / Output 내에서 발생한 경우
     * @param exception IOException
     * @return ApiResult<ErrorResponse>
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiResponse<ErrorData> handleIOException(IOException exception) {
        log.error("IOException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER.getErrorCode())
                .errorMessage(ErrorCode.INTERNAL_SERVER.getErrorMessage())
                .build();
        return ApiResponse.fail(ErrorCode.INTERNAL_SERVER.getStatusCode(), response);
    }

    /**
     * [Exception] NULL 값이 발생한 경우
     * @param exception NullPointerException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiResponse<ErrorData> handleNullPointerException(NullPointerException exception) {
        log.error("NullPointerException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorCode.NULL_POINT.getErrorCode())
                .errorMessage(ErrorCode.NULL_POINT.getErrorMessage())
                .build();
        return ApiResponse.fail(ErrorCode.NULL_POINT.getStatusCode(), response);
    }

    /**
     * [Exception] 모든 Exception 경우
     * @param exception Exception
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(Exception.class)
    protected final ApiResponse<ErrorData> handleAllExceptions(Exception exception) {
        log.error("Other Error", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorCode.OTHER_ERROR.getErrorCode())
                .errorMessage(ErrorCode.OTHER_ERROR.getErrorMessage())
                .build();
        return ApiResponse.fail(ErrorCode.OTHER_ERROR.getStatusCode(), response);
    }

}