package spyra.lukasz.javaquizzes.config;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class CacheStatisticsController {

    private final CacheManager cacheManager;


    private Cache<Object, Object> inspectCacheByName(String name) {
        CaffeineCache caffeineCache = (CaffeineCache) Objects.requireNonNull(cacheManager.getCache(name));
        return caffeineCache.getNativeCache();
    }


    @GetMapping("/superadmin/cache-stats")
    public Map<String, Set<Stream<@NonNull Object>>> inspectAll() {

        return cacheManager.getCacheNames()
                .stream()
                .collect(Collectors.groupingBy(
                        String::toString,
                        Collectors.mapping(s -> inspectCacheByName(s).asMap().keySet().stream(), Collectors.toSet())));
    }
}
