package session4.ex5;

public class AccessService {
    public boolean canPerformAction(User user, Action action) {
        Role role = user.getRole();
        switch (role) {
            case ADMIN:
                return true;
            case MODERATOR:
                if (action == Action.DELETE_USER) {
                    return false;
                }
                return true;
            case USER:
                if (action == Action.VIEW_PROFILE) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}