package nc.unc.ama.operation_service.err;

public class OperTypeNotFoundException extends RuntimeException{

    public OperTypeNotFoundException(Long operTypeId) {
        super(String.format("Operation type with id = '%s' not found", operTypeId));
    }
}
