CREATE TABLE public.countries (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    iso_code VARCHAR(2) NOT NULL UNIQUE,
    country_code VARCHAR(10) NOT NULL UNIQUE,
    language VARCHAR(5) NOT NULL,
    identification_document_name VARCHAR(5) NOT NULL,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL
);

CREATE TABLE public.users (
    id UUID PRIMARY KEY,
    default_phone_number_id UUID,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL
);

CREATE TABLE public.phone_numbers (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    country_id UUID NOT NULL,
    area_code VARCHAR(5) NOT NULL,
    number VARCHAR(50) NOT NULL,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

ALTER TABLE public.users
ADD CONSTRAINT fk_default_phone_number_id
FOREIGN KEY (default_phone_number_id) REFERENCES phone_numbers (id);

CREATE TABLE public.clients (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    identification_document_id UUID,
    default_address_id UUID,
    billing_address_id UUID,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE public.addresses (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    country_id UUID NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

ALTER TABLE public.clients
ADD CONSTRAINT fk_default_address_id
FOREIGN KEY (default_address_id) REFERENCES addresses (id);

ALTER TABLE public.clients
ADD CONSTRAINT fk_billing_address_id
FOREIGN KEY (billing_address_id) REFERENCES addresses (id);

CREATE TABLE public.identification_documents (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    country_id UUID NOT NULL,
    document_number VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NULL,
	created_date TIMESTAMP NULL,
	last_modified_by VARCHAR(255) NULL,
	last_modified_date TIMESTAMP NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

ALTER TABLE public.clients
ADD CONSTRAINT fk_identification_document_id
FOREIGN KEY (identification_document_id) REFERENCES identification_documents (id);