package com.mitocode.payment.chaos;

public record ChaosConfig(
        boolean enabled,
        ChaosType type,
        int delayMs,
        int failTimes
) {
    public static ChaosConfig disabled() {
        return new ChaosConfig(false, ChaosType.NONE, 0, 0);
    }
}