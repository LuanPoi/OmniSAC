export class Response<T, E> {
    data?: T;
    success?: boolean;
    error?: boolean;
    errors?: E[];
}