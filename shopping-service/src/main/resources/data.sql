INSERT INTO tbl_invoices (number_invoice, description, customer_id, invoice_id, state)
VALUES('0001', 'invoice office items', 1, 1,'CREATED');
INSERT INTO tbl_invoices (number_invoice, description, customer_id, invoice_id, state)
VALUES('0002', 'invoice  hardware store', 2, 3,'CREATED');

INSERT INTO tbl_invoice_items (quantity, price, product_id) VALUES(5,178.89,2);
INSERT INTO tbl_invoice_items (quantity, price, product_id) VALUES(10,12.5,1);
INSERT INTO tbl_invoice_items (quantity, price, product_id) VALUES(8,40.06,1);