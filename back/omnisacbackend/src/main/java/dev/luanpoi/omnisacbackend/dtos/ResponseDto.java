package dev.luanpoi.omnisacbackend.dtos;

import java.util.List;

public class ResponseDto<T, E> {
    private T data;
    private boolean success;
    private boolean error;
    private List<E>  errors;

    public ResponseDto() {
    }

    public ResponseDto(T data, boolean success, List<E> errors) {
        this.data = data;
        this.success = success;
        this.error = !success;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.error = !success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
        this.success = !error;
    }

    public List<E> getErrors() {
        return errors;
    }

    public void setErrors(List<E> errors) {
        this.errors = errors;
    }
}
