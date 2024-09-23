# bati_cuisine

### dataBase script

CREATE TABLE clients
(
id SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
phone VARCHAR(20)  NOT NULL,
isProfessional BOOLEAN NOT NULL
);

CREATE TYPE project_status AS ENUM ('IN_PROGRESS', 'COMPLETED', 'CANCELLED');

CREATE TABLE projects
(
id SERIAL PRIMARY KEY,
name VARCHAR(255)     NOT NULL,
profitMargin DOUBLE PRECISION NOT NULL,
totalCost DOUBLE PRECISION NOT NULL,
projectStatus project_status NOT NULL,
client_id INTEGER REFERENCES clients (id) ON DELETE SET NULL
);

CREATE TABLE component
(
id SERIAL PRIMARY KEY,
name VARCHAR(255),
componentType VARCHAR(255),
vatRate DOUBLE PRECISION,
project_id INTEGER REFERENCES projects (id) ON DELETE cascade ON UPDATE CASCADE
);

CREATE TABLE labor
(
id INTEGER PRIMARY KEY REFERENCES component (id),
hourlyRate DOUBLE PRECISION,
workingHours DOUBLE PRECISION,
workerProductivity DOUBLE PRECISION
);

CREATE TABLE material
(
id INTEGER PRIMARY KEY REFERENCES component (id),
unitCost DOUBLE PRECISION,
quantity DOUBLE PRECISION,
transportCost DOUBLE PRECISION,
qualityCoefficient DOUBLE PRECISION
);

CREATE TABLE quotes
(
id SERIAL PRIMARY KEY,
estimatedAmount DOUBLE PRECISION NOT NULL,
issueDate DATE NOT NULL,
validityDate DATE NOT NULL,
accepted BOOLEAN NOT NULL,
project_id INTEGER REFERENCES projects (id) ON DELETE CASCADE
);

INSERT INTO clients (name, address, phone, isprofessional)
VALUES ('Acme Corp', '123 Business Rd, Business City', '123-456-7890', TRUE),
('John Doe', '456 Residential St, Hometown', '987-654-3210', FALSE),
('Jane Smith', '789 Industrial Ave, Metropolis', '555-123-4567', TRUE);

INSERT INTO projects (name, profitmargin, totalcost, projectstatus, client_id)
VALUES ('Project Alpha', 15.5, 10000.00, 'IN_PROGRESS', 1),
('Project Beta', 10.0, 5000.00, 'COMPLETED', 2);

INSERT INTO components (name, "componentType", vatrate, project_id)
VALUES ('Component X', 'Material', 0.20, 1),
('Component Y', 'Labor', 0.15, 1),
('Component Z', 'Material', 0.10, 2);

INSERT INTO labors (id, hourlyrate, workinghours, workerproductivity)
VALUES (129, 25.00, 40.0, 0.90),
(131, 20.00, 45.0, 0.95);

INSERT INTO materials (id, unitcost, quantity, transportcost, qualitycoefficient)
VALUES (130, 50.00, 100, 200.00, 1);

INSERT INTO quotes (estimatedamount, issuedate, validitydate, accepted, project_id)
VALUES (12000.00, '2024-09-01', '2024-12-01', TRUE, 1),
(5500.00, '2024-09-10', '2024-11-10', FALSE, 2),
(16000.00, '2024-09-15', '2024-12-15', TRUE, 3);
