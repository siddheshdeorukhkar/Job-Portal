package com.example.jobPortal.jobApp.Reviews.impl;

import com.example.jobPortal.jobApp.Company.Company;
import com.example.jobPortal.jobApp.Company.CompanyService;
import com.example.jobPortal.jobApp.Exception.ReviewNotFoundException;
import com.example.jobPortal.jobApp.Reviews.Review;
import com.example.jobPortal.jobApp.Reviews.ReviewRepository;
import com.example.jobPortal.jobApp.Reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewsRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewsRepository, CompanyService companyService) {
        this.reviewsRepository = reviewsRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews= reviewsRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company= companyService.findCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewsRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews= reviewsRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId,
                                Review updatedReview) {
        if(companyService.findCompanyById(companyId)!= null){
            updatedReview.setCompany(companyService.findCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewsRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.findCompanyById(companyId)!= null
                && reviewsRepository.existsById(reviewId)){
            Review review = reviewsRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            if (company !=null){
                company.getReviews().remove(review);
                companyService.updateCompany(companyId,company);
                reviewsRepository.deleteById(reviewId);
                return true;
            }

        }
        return false;

    }


}
