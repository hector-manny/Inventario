CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    freight_percentage DECIMAL(10, 2) NOT NULL,
    import_expenses_percentage DECIMAL(10, 2) NOT NULL,
    insurance_percentage DECIMAL(10, 2) NOT NULL,
    tax_percentage DECIMAL(10, 2) NOT NULL
);
