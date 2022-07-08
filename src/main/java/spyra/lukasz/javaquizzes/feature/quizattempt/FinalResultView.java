package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
final class FinalResultView {

    private final UUID id;

    private final int score;

    private final Duration attemptTime;

}
