package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.testng.annotations.DataProvider;
import spyra.lukasz.javaquizzes.shared.Role;
import spyra.lukasz.javaquizzes.shared.User;

public class UserManagementDataProvider {

    private static User user1 = new User(1, "First", "email@email.com", null, null, new Role(1L, "User"));
    private static ChangeUserRoleDTO view1 = new ChangeUserRoleDTO(1L, "First", "email@email.com", "User");
    private static User user2 = new User(2, "Second", "email2@email.com", null, null, new Role(1L, "Admin"));
    private static ChangeUserRoleDTO view2 = new ChangeUserRoleDTO(2L, "Second", "email2@email.com", "Admin");
    private static User user3 = new User(3, "Third", "email3@email.com", null, null, new Role(1L, "User"));
    private static ChangeUserRoleDTO view3 = new ChangeUserRoleDTO(3L, "Third", "email3@email.com", "User");


    @DataProvider
    public static Object[][] usersWithCorrespondingAuthorityDTOs() {
        return new Object[][]{
                {user1, view1},
                {user2, view2},
                {user3, view3}
        };
    }

    @DataProvider
    public static Object[][] usersWithDifferentAuthorityDTOs() {
        return new Object[][]{
                {user1, view2},
                {user2, view3},
                {user3, view1}
        };
    }
}
