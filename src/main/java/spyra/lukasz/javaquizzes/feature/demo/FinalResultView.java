package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
@Getter
final class FinalResultView {

    private final long id;

    private final int score;

    private final Duration attemptTime;

}
