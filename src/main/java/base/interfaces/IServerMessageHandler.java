package base.interfaces;

import base.messages.MessageIn;

public interface IServerMessageHandler {
    void handleInput(MessageIn message);
}
