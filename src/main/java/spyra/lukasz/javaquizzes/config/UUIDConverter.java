package spyra.lukasz.javaquizzes.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

/**
 * Heroku official docs suggest to add this class before deploying to production
 *
 * It is required to use UUID as primary keys in PostgreSQL on production
 *
 * @see <a href=https://devcenter.heroku.com/articles/preparing-a-spring-boot-app-for-production-on-heroku>Heroku Docs</a>
 */
@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, UUID> {
    @Override
    public UUID convertToDatabaseColumn(UUID attribute) {
        return attribute;
    }
    @Override
    public UUID convertToEntityAttribute(UUID dbData) {
        return dbData;
    }
}
