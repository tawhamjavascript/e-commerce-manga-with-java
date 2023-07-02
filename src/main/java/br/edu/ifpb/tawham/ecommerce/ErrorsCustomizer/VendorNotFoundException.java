package br.edu.ifpb.tawham.ecommerce.ErrorsCustomizer;

public class VendorNotFoundException extends RuntimeException {
    public VendorNotFoundException(String email) {
        super("Could not find vendor with email: " + email);
    }
    
}
