package spyra.lukasz.javaquizzes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@EnableCaching
@RequiredArgsConstructor
public class CacheConfiguration {

    /**
     * Autowire to clear user's cache on logout
     */
    private final CacheManager cacheManager;

    private final String[] cacheEvictOnLogout = new String[]{"AttemptDetails", "UserProfile", "RestrictedQuizzes"};

    /**
     * Clears caches as defined in {@link CacheConfiguration#cacheEvictOnLogout}
     *
     * @return handler for Spring Security
     */
     public LogoutHandler clearCache() {
        return (request, response, authentication) -> {
            cacheManager.getCacheNames().forEach(this::clearUserSpecificCache);
        };
    }

    private void clearUserSpecificCache(String s) {
        for (var cache : cacheEvictOnLogout) {
            if (cache.equals(s)) {
                cacheManager.getCache(s).clear();
                return;

            }
        }
    }

}
