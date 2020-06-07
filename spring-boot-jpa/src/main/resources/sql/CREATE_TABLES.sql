CREATE TABLE CURRENCY (
	CURRENCY_ID bigint IDENTITY(3001,1) NOT NULL,
	CURRENCY_DESC varchar(300) NULL,
	CURRENCY_MNEMONIC varchar(5) NULL,
	CURRENCY_THOUSAND_SEPARATOR nchar(1) NULL,
	CURRENCY_DECIMAL_SEPARATOR nchar(1) NULL,
	CONSTRAINT PK_currency_CURRENCY_ID PRIMARY KEY (CURRENCY_ID),
	CONSTRAINT currency$UQ_CURRENCY_ID UNIQUE (CURRENCY_ID)
) ;
CREATE UNIQUE INDEX currency$UQ_CURRENCY_ID ON CURRENCY (CURRENCY_ID);

CREATE TABLE PAYMENT_TYPE (
	PAYMENT_TYPE_ID bigint IDENTITY(5001,1) NOT NULL,
	PAYMENT_TYPE_DESC varchar(200) NULL,
	PAYMENT_TYPE_NOMENCLATURE varchar(10) NULL,
	CLIENT_CODE varchar(45) NULL,
	PAYMENT_TYPE_IS_ACTIVE tinyint NULL,
	PAYMENT_TYPE_CONTROL_NUMBER tinyint NULL,
	CONSTRAINT PK_payment_type_PAYMENT_TYPE_ID PRIMARY KEY (PAYMENT_TYPE_ID),
	CONSTRAINT payment_type$UQ_PAYMENT_TYPE_ID UNIQUE (PAYMENT_TYPE_ID)
);
CREATE UNIQUE INDEX payment_type$UQ_PAYMENT_TYPE_ID ON PAYMENT_TYPE (PAYMENT_TYPE_ID) ;

CREATE TABLE BANK (
    BANK_ID bigint IDENTITY(1,1) NOT NULL,
	BANK_DESCRIPTION varchar(200) NULL,
	CONSTRAINT PK_bank_BANK_ID PRIMARY KEY (BANK_ID),
);

CREATE TABLE PARTY (
	PARTY_ID bigint IDENTITY(1010,1) NOT NULL,
	FIRST_NAME varchar(100) NOT NULL,
	LAST_NAME varchar(100) NOT NULL,
	CONSTRAINT PK_party_PARTY_ID PRIMARY KEY (PARTY_ID),
	CONSTRAINT party$UQ_PARTY_ID UNIQUE (PARTY_ID),
);


CREATE TABLE PAYMENT_TERM (
	PAYMENT_TERM_ID bigint IDENTITY(2,1) NOT NULL,
	PARTY_ID bigint NOT NULL,
	PAYMENT_TYPE_ID bigint NOT NULL,
	CREDIT_CARD_ID bigint NULL,
	CURRENCY_ID bigint NOT NULL,
	ACCOUNT_NBR varchar(50) NULL,
	INTER_ACCOUNT_NBR varchar(50) NULL,
	EXPIRATION datetime2 NULL,
	PAYMENT_KEY varchar(50) NULL,
	BANK_ID bigint NULL,
	BANK_BRANCH_ID bigint NULL,
	PERIOD_ID bigint NULL,
	CONSTRAINT PK_payment_term_PAYMENT_TERM_ID PRIMARY KEY (PAYMENT_TERM_ID),
	CONSTRAINT payment_term$FK_PAYMENT_TERM_CURRENCY1 FOREIGN KEY (CURRENCY_ID) REFERENCES CURRENCY(CURRENCY_ID),
	CONSTRAINT payment_term$FK_PAYMENT_TERM_PAYMENT_TYPE1 FOREIGN KEY (PAYMENT_TYPE_ID) REFERENCES PAYMENT_TYPE(PAYMENT_TYPE_ID),
	CONSTRAINT payment_term$FK_PAYMENT_TERM_BANK FOREIGN KEY (BANK_ID) REFERENCES BANK(BANK_ID),
	CONSTRAINT payment_term$FK_PAYMENT_TERM_PARTY FOREIGN KEY (PARTY_ID) REFERENCES PARTY(PARTY_ID),

);

CREATE TABLE CONTRACT_HEADER (
	CONTRACT_ID bigint IDENTITY(1,1) NOT NULL,
	AGENCY_ID bigint NOT NULL,
	INSURER_ID bigint NOT NULL,
	INSURED_PARTY_ID bigint NULL,
	HOLDER_PARTY_ID bigint NULL,
	COVERAGE_PLAN_ID bigint NULL,
	CONTRACT_FROM datetime2 NULL,
	CONTRACT_TO datetime2 NULL,
	PAYMENT_PLAN_ID bigint NULL,
	CONSTRAINT PK_contract_header_CONTRACT_ID PRIMARY KEY (CONTRACT_ID),
);

CREATE TABLE CONTRACT_PAYMENT (
	CONTRACT_HEADER_ID bigint NOT NULL,
	PAYMENT_TERM_ID bigint NOT NULL,
	CONSTRAINT PK_CONTRACT_PAYMENT_CONTRACT_PAYMENT PRIMARY KEY (CONTRACT_HEADER_ID,PAYMENT_TERM_ID),
	CONSTRAINT CONTRACT_PAYMENT$FK_CONTRACT_PAYMENT_CONTRACT_HEADER FOREIGN KEY (CONTRACT_HEADER_ID) REFERENCES CONTRACT_HEADER(CONTRACT_ID),
	CONSTRAINT CONTRACT_PAYMENT$FK_CONTRACT_PAYMENT_PAYMENT_TERM FOREIGN KEY (PAYMENT_TERM_ID) REFERENCES PAYMENT_TERM(PAYMENT_TERM_ID)
);