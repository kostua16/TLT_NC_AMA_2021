package nc.unc.ama.operation_service.err;

public class OperationNotFoundException extends RuntimeException{

    public OperationNotFoundException(Long operationId) {
        super(String.format("Operation with id = '%s' not found", operationId));
    }
}
