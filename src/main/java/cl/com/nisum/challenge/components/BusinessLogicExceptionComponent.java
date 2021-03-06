package cl.com.nisum.challenge.components;

import cl.com.nisum.challenge.exception.ApiEntityError;
import cl.com.nisum.challenge.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public void throwExceptionEntityNotFound(String entityName, UUID id){
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The" + entityName + "with id" + id + "does not exist"
        );

        throw new BusinessLogicException(
                entityName + "does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException getExceptionEntityNotFound(String entityName, UUID id){
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The" + entityName + "with id" + id + "does not exist"
        );

        return new BusinessLogicException(
                entityName + "does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException getExceptionEmailUserAlreadyExists(String email) {
        ApiEntityError apiEntityError = new ApiEntityError(
                "User",
                "EamilUserAlreadyExists",
                "email already exists " + email
        );
        return new BusinessLogicException(
                "email user already exists",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

}

