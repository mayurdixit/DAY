package org.DAY.utility;

import org.DAY.db.entity.AccessRole;
import org.DAY.db.entity.KendraInfo;
import org.DAY.db.entity.User;

public class AppUserData {
    private User user;
    private AccessRole role;
    private KendraInfo kendra;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccessRole getRole() {
        return role;
    }

    public void setRole(AccessRole role) {
        this.role = role;
    }

    public KendraInfo getKendra() {
        return kendra;
    }

    public void setKendra(KendraInfo kendra) {
        this.kendra = kendra;
    }

    @Override
    public String toString() {
        return "AppUserData{" +
                "user=" + user +
                ", role=" + role +
                ", kendra=" + kendra +
                '}';
    }
}
