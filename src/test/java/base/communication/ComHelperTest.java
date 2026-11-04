package base.communication;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ComHelperTest {

    @Test
    public void readsOneCompleteMessagePerLine() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(
                "{\"command\":\"test\"}\n{\"command\":\"next\"}\n"));
        StringBuilder message = new StringBuilder();

        ComHelper.readBufferedReaderAndCheck(reader, message);

        assertEquals("{\"command\":\"test\"}", message.toString());
    }

    @Test
    public void reportsEndOfStreamInsteadOfReturningAnEmptyMessage() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(""));

        try {
            ComHelper.readBufferedReaderAndCheck(reader, new StringBuilder());
            fail("Expected EOFException");
        } catch (EOFException expected) {
            // End of stream is the signal used to trigger reconnection.
        }
    }
}
