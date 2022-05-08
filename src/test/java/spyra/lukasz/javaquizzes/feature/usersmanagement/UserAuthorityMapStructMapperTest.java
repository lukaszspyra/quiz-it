package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.testng.annotations.Test;
import spyra.lukasz.javaquizzes.shared.User;

import static org.testng.Assert.*;

@Test(groups = "Mapper")
public class UserAuthorityMapStructMapperTest {

    @Test(description = "Shall return proper DTO, but it did not", dataProvider = "usersWithCorrespondingAuthorityDTOs", dataProviderClass = UserManagementDataProvider.class)
    public void shallMapUserToAuthorityChangeDTO(User user, ChangeUserRoleDTO dto) {
        //given
        UserAuthorityMapStructMapper mapper = new UserAuthorityMapStructMapperImpl();

        //when
        final ChangeUserRoleDTO result = mapper.toView(user);

        //then
        assertEquals(dto, result);
    }

    @Test(description = "Shall fail to return proper DTO, but it did not", dataProvider = "usersWithDifferentAuthorityDTOs", dataProviderClass = UserManagementDataProvider.class)
    public void shallNotMapUserToRandomAuthorityChangeDTO(User user, ChangeUserRoleDTO dto) {
        //given
        UserAuthorityMapStructMapper mapper = new UserAuthorityMapStructMapperImpl();

        //when
        final ChangeUserRoleDTO result = mapper.toView(user);

        //then
        assertNotEquals(dto, result);
    }

}