package com.kshrd.kroya_api.controller;

import com.kshrd.kroya_api.enums.ItemType;
import com.kshrd.kroya_api.exception.InvalidValueExceptionHandler;
import com.kshrd.kroya_api.payload.BaseResponse;
import com.kshrd.kroya_api.payload.Feedback.FeedbackRequest;
import com.kshrd.kroya_api.payload.Feedback.FeedbackResponse;
import com.kshrd.kroya_api.service.Feedback.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(
            summary = "📝 Add Feedback",
            description = """
                    Allows users to submit feedback on a specific item.
                    **📩 Request Body**:
                    - **feedbackRequest**: JSON object containing the feedback details.
                    - **itemType**: Specifies the type of item (e.g., FOOD_RECIPE, FOOD_SELL).
                    
                    **📩 Response Summary**:
                    - **200**: ✅ Feedback submitted successfully.
                    - **400**: 🚫 Invalid rating value or incorrect data provided.
                    """
    )
    @PostMapping
    public BaseResponse<FeedbackResponse> addFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest,
                                                      @RequestParam ItemType itemType) {

        try {
            int ratingValue = Integer.parseInt(feedbackRequest.getRatingValue());
            if (ratingValue < 1 || ratingValue > 5) {
                throw new InvalidValueExceptionHandler("Rating must be a whole integer between 1 and 5.");
            }
            feedbackRequest.setRatingValue(Integer.toString(ratingValue));
        } catch (NumberFormatException ex) {
            throw new InvalidValueExceptionHandler("Rating must be a whole integer between 1 and 5.");
        }

        return feedbackService.addFeedback(feedbackRequest, itemType);
    }

    @Operation(
            summary = "🔄 Update Feedback",
            description = """
                    Updates an existing feedback entry.
                    **📩 Request Parameters**:
                    - **feedbackId**: ID of the feedback to be updated.
                    
                    **📩 Request Body**:
                    - **feedbackRequest**: JSON object containing the updated feedback details.
                    
                    **📩 Response Summary**:
                    - **200**: ✅ Feedback updated successfully.
                    - **400**: 🚫 Invalid rating value or incorrect data provided.
                    - **404**: 🚫 Feedback not found.
                    """
    )
    @PutMapping("/{feedbackId}")
    public BaseResponse<FeedbackResponse> updateFeedback(@PathVariable Long feedbackId,
                                                         @Valid @RequestBody FeedbackRequest feedbackRequest) {
        try {
            int ratingValue = Integer.parseInt(feedbackRequest.getRatingValue());
            if (ratingValue < 1 || ratingValue > 5) {
                throw new InvalidValueExceptionHandler("Rating must be a whole integer between 1 and 5.");
            }
            feedbackRequest.setRatingValue(Integer.toString(ratingValue));
        } catch (NumberFormatException ex) {
            throw new InvalidValueExceptionHandler("Rating must be a whole integer between 1 and 5.");
        }

        return feedbackService.updateFeedback(feedbackId, feedbackRequest);
    }

    @Operation(
            summary = "🗑️ Delete Feedback",
            description = """
                    Deletes a feedback entry from the system.
                    **📩 Request Parameters**:
                    - **feedbackId**: ID of the feedback to be deleted.
                    
                    **📩 Response Summary**:
                    - **200**: ✅ Feedback deleted successfully.
                    - **404**: 🚫 Feedback not found.
                    """
    )
    @DeleteMapping("/{feedbackId}")
    public BaseResponse<String> deleteFeedback(@PathVariable Long feedbackId) {
        return feedbackService.deleteFeedback(feedbackId);
    }
}

