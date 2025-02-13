package ac.dnd.dodal.domain.plan.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;

@Getter
@RequiredArgsConstructor
public enum PlanStatus {
    
    NONE("none"),
    SUCCESS("success"),
    FAILURE("failure"),
    ;

    private final String value;

    public static PlanStatus of(String value) {
        return Arrays.stream(PlanStatus.values())
            .filter(status -> status.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new ForbiddenException(PlanExceptionCode.INVALID_PLAN_STATUS));
    }
}
