package base.messages.admin;

import base.messages.RequestObject;

public class SetAdmin extends RequestObject {

    public final static String COMMAND = "Admin";
    public SetAdmin(boolean admin) {
        this.command = COMMAND;
        this.params.addProperty("isAdmin",admin);
    }
}
