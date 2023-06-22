package com.netcompany.oopdemo.enums;

public enum ActivityStatus {
    Draft(1),
    Published(2),
    Cancelled(3);

    private final int intValue;

    private ActivityStatus(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public static ActivityStatus getFromInt(int value) {
        switch (value) {
            case 1:
                return ActivityStatus.Draft;
            case 2:
                return ActivityStatus.Published;
            case 3:
                return ActivityStatus.Cancelled;
        }
        return null;
    }
}
