package app.excpetions;

public class FileStorageException extends RuntimeException {

    private final String msg;

    public FileStorageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
