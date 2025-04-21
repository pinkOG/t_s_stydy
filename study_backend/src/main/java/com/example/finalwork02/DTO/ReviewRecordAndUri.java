package com.example.finalwork02.DTO;

import com.example.finalwork02.pojo.ReviewRecord;

public class ReviewRecordAndUri {
    ReviewRecord reviewRecord;
    String uriLink;

    public ReviewRecord getReviewRecord() {
        return reviewRecord;
    }

    public void setReviewRecord(ReviewRecord reviewRecord) {
        this.reviewRecord = reviewRecord;
    }

    public String getUriLink() {
        return uriLink;
    }

    public void setUriLink(String uriLink) {
        this.uriLink = uriLink;
    }
}
