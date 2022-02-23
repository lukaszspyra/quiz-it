package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.User;

@Mapper(componentModel = "spring")
interface UserAuthorityMapStructMapper {

    @Mapping(source = "role.name", target = "role")
    ChangeUserRoleDTO toView(User user);
}
