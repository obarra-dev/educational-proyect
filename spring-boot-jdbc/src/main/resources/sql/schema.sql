CREATE TABLE BILLING
(
    billing_id INT NOT NULL AUTO_INCREMENT,
    policy_id INT,
    billing_type_id INT,
    create_date DATETIME2,
    amount FLOAT,
    PRIMARY KEY (billing_id)
);





