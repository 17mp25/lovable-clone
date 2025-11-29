package com.mp.projects.lovable_clone.entity;

import com.mp.projects.lovable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls;//Json array of tools called
    Integer tokenUsed;
    Instant createdAt;

}
