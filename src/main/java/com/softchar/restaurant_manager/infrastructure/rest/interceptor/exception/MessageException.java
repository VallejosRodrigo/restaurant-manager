package com.softchar.restaurant_manager.infrastructure.rest.interceptor.exception;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MessageException {
    String message;
    String uri;
}
