package base.interfaces;

import base.messages.Message;

public interface IClientMessageHandler {
    void handleInput(Message input);
}
