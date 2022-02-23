package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.List;

@Mapper(componentModel = "spring")
interface UserAuthorityMapStructMapper {

    List<ChangeUserRoleDTO> toView(List<User> user);

    @Mapping(source = "role.name", target = "role")
    ChangeUserRoleDTO toView(User user);
}
