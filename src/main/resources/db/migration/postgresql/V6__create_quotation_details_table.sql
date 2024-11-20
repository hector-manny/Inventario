CREATE TABLE quotation_details (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quotation_id INT NOT NULL,
    product_id INT NOT NULL,
    freight_cost DECIMAL(10, 2) NOT NULL,
    import_expenses_cost DECIMAL(10, 2) NOT NULL,
    insurance_cost DECIMAL(10, 2) NOT NULL,
    tax_cost DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_quotation FOREIGN KEY (quotation_id) REFERENCES quotations(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);
