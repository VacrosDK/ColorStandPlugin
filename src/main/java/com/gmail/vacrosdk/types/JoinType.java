package com.gmail.vacrosdk.types;

import com.gmail.vacrosdk.language.LanguagePath;

public enum JoinType {
    ALREADY_JOINED(LanguagePath.JOIN_REASONS_ALREADY__JOINED),
    GAME_CLOSED(LanguagePath.JOIN_REASONS_GAME__CLOSED),
    OFFLINE_PLAYER(LanguagePath.JOIN_REASONS_OFFLINE__PLAYER),
    GAME_STARTED(LanguagePath.JOIN_REASONS_GAME__STARTED),
    CAN_JOIN(LanguagePath.JOIN_REASONS_CAN__JOIN);
    private final String messagePath;


    JoinType(String messagePath) {
        this.messagePath = messagePath;
    }

    public String getMessagePath() {
        return this.messagePath;
    }
}
