DROP TABLE IF EXISTS tbl_invoices;
CREATE TABLE tbl_invoices
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number_invoice VARCHAR(255) NOT NULL,
    description    VARCHAR(255),
    customer_id    BIGINT       NOT NULL,
    create_at      TIMESTAMP,
    invoice_id     BIGINT,
    state          VARCHAR(255)
);

DROP TABLE IF EXISTS tbl_invoice_items;
CREATE TABLE tbl_invoice_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity   DOUBLE,
    price      DOUBLE,
    product_id BIGINT NOT NULL,
    subTotal   DOUBLE
);