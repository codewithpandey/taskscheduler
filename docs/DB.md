# Databse Structure

## Tasks

```
CREATE TABLE tasks (
  id SERIAL PRIMARY KEY,
  task_name VARCHAR(255),
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
