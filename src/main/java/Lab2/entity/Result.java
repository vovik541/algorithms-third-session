package Lab2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;
import static Lab2.entity.ResultCodes.*;

@Getter
@Setter
@AllArgsConstructor(access = PRIVATE)
public class Result {
    private int bestF;
    private ResultCodes resultCode;
    private Optional<Node> solution;

    public static Result of(int fBest, ResultCodes resultCodes, Node solution) {
        return new Result(fBest, resultCodes, Optional.ofNullable(solution));
    }

    public static Result of(ResultCodes resultCodes, Node solution) {
        return new Result(0, resultCodes, Optional.ofNullable(solution));
    }

    public boolean isCutOff() {
        return resultCode.equals(CUT_OFF);
    }

    public boolean isFailure() {
        return resultCode.equals(FAILURE);
    }

    public boolean isSolution() {
        return resultCode.equals(SOLUTION);
    }
}
