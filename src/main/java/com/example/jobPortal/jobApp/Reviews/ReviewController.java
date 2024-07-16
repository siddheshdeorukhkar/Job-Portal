package com.example.jobPortal.jobApp.Reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewsService;

    public ReviewController(ReviewService reviewsService) {
        this.reviewsService = reviewsService;
    }

    ////////////////////////////////////////////////////////////////////

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>>getAllReviews(@PathVariable Long companyId){
        List<Review>reviews= reviewsService.getAllReviews(companyId);
        return new ResponseEntity(reviews, HttpStatus.OK);
    }


    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
       boolean isReviewSaved=  reviewsService.addReview(companyId,review);
       if(isReviewSaved){
           return new ResponseEntity<>("Review added successfully",
                   HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("Review NOT added",
                   HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review>getReview(@PathVariable Long companyId,
                                           @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewsService.getReview(companyId,reviewId),HttpStatus.OK);

    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String>updateReview(@PathVariable Long companyId,
                                              @PathVariable Long reviewId,
                                              @RequestBody Review review){
        boolean isReviewUpdated = reviewsService.updateReview(companyId,
                reviewId, review);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",
                    HttpStatus.OK);
        else
            return new ResponseEntity<>("Review NOT updated",
                    HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String>deleteReview(@PathVariable Long companyId,
                                              @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewsService.deleteReview(companyId, reviewId);

        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not deleted",
                    HttpStatus.NOT_FOUND);
        }


    }

}
