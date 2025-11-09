CREATE TABLE IF NOT EXISTS "users" (
                                       id UUID PRIMARY KEY,
                                       email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    -- This line is the fix. It matches your entity's 'LocalDate'
    -- and provides a default value to satisfy 'nullable = false'
    created_date DATE NOT NULL DEFAULT CURRENT_DATE
    );

-- Insert the user if no existing user with the same id or email exists
-- This INSERT statement no longer needs to specify 'created_date',
-- as the database will add it automatically using DEFAULT.
INSERT INTO "users" (id, email, password, role)
SELECT '223e4567-e89b-12d3-a456-426614174006', 'testuser@test.com',
       '$2b$12$7hoRZfJrRKD2nIm2vHLs7OBETy.LWenXXMLKf99W8M4PUwO6KB7fu', 'ADMIN'
    WHERE NOT EXISTS (
    SELECT 1
    FROM "users"
    WHERE id = '223e4567-e89b-12d3-a456-426614174006'
       OR email = 'testuser@test.com'
);

