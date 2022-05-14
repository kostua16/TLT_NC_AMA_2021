package nc.unc.ama.guest.err;

public class FailedSendMailException extends RuntimeException{

    public FailedSendMailException(String text) {
        super(text);
    }
}
