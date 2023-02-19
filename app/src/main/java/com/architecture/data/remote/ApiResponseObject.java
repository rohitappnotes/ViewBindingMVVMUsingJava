package com.architecture.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*

Api Response Format

{
	"code": 200,
	"success": true,
	"message": "Data found",
	"data": {
		"id": "1",
		"title": "Avatar",
		"year": "2009",
		"released": "18 Dec 2009",
		"director": "James Cameron",
		"poster": "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg"
	}
}
*/
public class ApiResponseObject<T> {

    /**
     * Http Status Codes
     */
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * success is true if
     * Login successfully
     * Your account has been created successfully
     * You have successfully registered
     * Verification code send successfully
     * data found (example list) etc.,
     * success is false if
     * Invalid credentials. Login failed,
     * Sorry, Email is already exists, use another Email,
     * Sorry, Phone number is already exists, use another Phone number,
     * You have entered an incorrect verification code,
     * data not found (example list) etc.,
     */
    @SerializedName("success")
    @Expose
    private boolean success;

    /**
     * message
     * Login successfully
     * Your account has been created successfully
     * You have successfully registered
     * Verification code send successfully
     * data found (example list),
     * Invalid credentials. Login failed,
     * Sorry, Email is already exists, use another Email,
     * Sorry, Phone number is already exists, use another Phone number,
     * You have entered an incorrect verification code,
     * data not found (example list) etc.,
     */
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private T data;

    public ApiResponseObject(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
