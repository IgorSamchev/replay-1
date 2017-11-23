package play.exceptions;

import org.hibernate.exception.GenericJDBCException;

public class JPAException extends PlayException implements SourceAttachment {

    public JPAException(String message) {
        super(message, null);
    }

    public JPAException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getErrorTitle() {
        return "JPA error";
    }

    @Override
    public String getErrorDescription() {
        if(getCause() != null && getCause() instanceof GenericJDBCException) {
            String SQL = ((GenericJDBCException)getCause()).getSQL();
            return String.format("A JPA error occurred (%s): <strong>%s</strong>. This is likely because the batch has broken some referential integrity. Check your cascade delete. SQL: (%s)", 
                    getMessage(), getCause() == null ? "" : getCause().getMessage(), SQL);
        }
        return String.format("A JPA error occurred (%s): <strong>%s</strong>", getMessage(), getCause() == null ? "" : getCause().getMessage());
    }

    @Override
    public Integer getLineNumber() {
        return 1;
    }

    @Override
    public String getSourceFile() {
        return "SQL Statement";
    }   
    
    
    
}
