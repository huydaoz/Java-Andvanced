package session2.ex5;
public class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {

        System.out.println("SuperAdmin activity: " + activity);

        UserActions.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }

}