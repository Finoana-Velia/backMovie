package com.example.movies.Core;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

@Builder
public record ErrorResponse(
			int statusCode,
			ErrorType errorType,
			Map<String, String> details,
			String suggestion,
			LocalDateTime timestamp
		) {
}
