create table customer_order
(
    id          bigint primary key,
    customer_id bigint,
    amount      decimal
);
INSERT INTO customer_order (id, customer_id, amount)
VALUES (1, 1, 20);
INSERT INTO customer_order (id, customer_id, amount)
VALUES (2, 1, 80);
INSERT INTO customer_order (id, customer_id, amount)
VALUES (3, 1, 80);
INSERT INTO customer_order (id, customer_id, amount)
VALUES (4, 2, 100);
INSERT INTO customer_order (id, customer_id, amount)
VALUES (5, 2, 120);