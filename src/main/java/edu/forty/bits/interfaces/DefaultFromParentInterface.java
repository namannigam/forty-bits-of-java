package edu.forty.bits.interfaces;

public interface DefaultFromParentInterface extends Another<Invoice> {
    // some other methods here
    default Invoice save(Invoice invoice) {
        return Another.super.someMethod();
    }
}