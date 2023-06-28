package br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer;

public class NoProductException extends RuntimeException{
    public NoProductException(String message) {
        super(message);
    }
    
}
