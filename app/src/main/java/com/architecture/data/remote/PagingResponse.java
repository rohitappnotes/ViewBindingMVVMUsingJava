package com.architecture.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/*

Api Response Format

{
	"total_number_of_pages": 3,
	"current_page_number": 1,
	"total_number_of_items": 6,
	"item_in_one_page": 2,
	"code": 200,
	"success": true,
	"message": "Data found",
	"data": [{
			"id": "1",
			"title": "Avatar",
			"year": "2009",
			"released": "18 Dec 2009",
			"director": "James Cameron",
			"poster": "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg"
		},
		{
			"id": "1",
			"title": "I Am Legend",
			"year": "2007",
			"released": "14 Dec 2007",
			"director": "Francis Lawrence",
			"poster": "http://ia.media-imdb.com/images/M/MV5BMTU4NzMyNDk1OV5BMl5BanBnXkFtZTcwOTEwMzU1MQ@@._V1_SX300.jpg"
		}
	]
}
*/
public class PagingResponse<T> {

    @SerializedName("total_number_of_pages")
    @Expose
    private int totalNumberOfPages;

    @SerializedName("current_page_number")
    @Expose
    private int currentPageNumber;

    @SerializedName("total_number_of_items")
    @Expose
    private int totalNumberOfItems;

    @SerializedName("item_in_one_page")
    @Expose
    private int itemInOnePage;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<T> data;

    public PagingResponse(int totalNumberOfPages, int currentPageNumber, int totalNumberOfItems, int itemInOnePage, int code, boolean success, String message, ArrayList<T> data) {
        this.totalNumberOfPages = totalNumberOfPages;
        this.currentPageNumber = currentPageNumber;
        this.totalNumberOfItems = totalNumberOfItems;
        this.itemInOnePage = itemInOnePage;
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(int totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public int getItemInOnePage() {
        return itemInOnePage;
    }

    public void setItemInOnePage(int itemInOnePage) {
        this.itemInOnePage = itemInOnePage;
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

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
