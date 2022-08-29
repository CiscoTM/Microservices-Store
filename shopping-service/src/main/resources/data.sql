-- INSERT INTO tbl_invoices (number_invoice ,description, customer_id, invoice_id, state)
-- VALUES ('0001', 'Factura articulos de oficina', 1, 1, 'CREATED');
--
-- INSERT INTO tbl_invoice_items (product_id, quantity, price) VALUES (1, 1, 178.89);
-- INSERT INTO tbl_invoice_items (product_id, quantity, price) VALUES (2, 2, 12.50);
-- INSERT INTO tbl_invoice_items (product_id, quantity, price) VALUES (3, 1, 40.06);





INSERT INTO tbl_invoices (number_invoice, description, customer_id, create_at, invoice_id, state)
VALUES('0001', 'invoice office items', 1, NOW(), 1,'CREATED');

INSERT INTO tbl_invoice_items ( product_id, quantity, price ) VALUES(1, 1, 178.89);
INSERT INTO tbl_invoice_items ( product_id, quantity, price)  VALUES(2, 2, 12.5);
INSERT INTO tbl_invoice_items ( product_id, quantity, price)  VALUES(3, 1, 40.06);