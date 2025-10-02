-- Insert Users
INSERT INTO users (id, username, email, name, image_url, github_id, role, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'johndoe', 'john.doe@example.com', 'John Doe', 'https://avatars.githubusercontent.com/u/1?v=4', 'github123', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'janesmit', 'jane.smith@example.com', 'Jane Smith', 'https://avatars.githubusercontent.com/u/2?v=4', 'github456', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'alexdev', 'alex.developer@example.com', 'Alex Developer', 'https://avatars.githubusercontent.com/u/3?v=4', 'github789', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'sarahcode', 'sarah.code@example.com', 'Sarah Coder', 'https://avatars.githubusercontent.com/u/4?v=4', 'github101', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Collections
INSERT INTO collections (id, user_id, name, description, icon, color, created_at, updated_at)
VALUES
    (gen_random_uuid(), (SELECT id FROM users WHERE username = 'johndoe'), 'React Snippets', 'Useful React code snippets and hooks', '⚛️', '#61DAFB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), (SELECT id FROM users WHERE username = 'johndoe'), 'Java Utilities', 'Common Java utility functions', '☕', '#007396', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), (SELECT id FROM users WHERE username = 'janesmit'), 'Python Scripts', 'Python automation and data processing', '🐍', '#3776AB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), (SELECT id FROM users WHERE username = 'alexdev'), 'SQL Queries', 'Complex SQL queries and optimizations', '🗄️', '#336791', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), (SELECT id FROM users WHERE username = 'sarahcode'), 'CSS Tricks', 'Modern CSS techniques and animations', '🎨', '#1572B6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Tags
INSERT INTO tags (id, name)
VALUES
    (gen_random_uuid(), 'javascript'),
    (gen_random_uuid(), 'react'),
    (gen_random_uuid(), 'java'),
    (gen_random_uuid(), 'spring-boot'),
    (gen_random_uuid(), 'python'),
    (gen_random_uuid(), 'sql'),
    (gen_random_uuid(), 'postgresql'),
    (gen_random_uuid(), 'css'),
    (gen_random_uuid(), 'hooks'),
    (gen_random_uuid(), 'api'),
    (gen_random_uuid(), 'utility'),
    (gen_random_uuid(), 'optimization');

-- Insert Snippets
INSERT INTO snippets (id, user_id, collection_id, title, description, code, language, is_favorite, is_archived, created_at, updated_at)
VALUES
    -- John's React Snippets
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'johndoe'),
     (SELECT id FROM collections WHERE name = 'React Snippets'),
     'useDebounce Hook',
     'Custom React hook for debouncing values',
     'import { useState, useEffect } from ''react'';

export function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedValue(value);
    }, delay);

    return () => {
      clearTimeout(handler);
    };
  }, [value, delay]);

  return debouncedValue;
}',
     'javascript',
     true,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'johndoe'),
     (SELECT id FROM collections WHERE name = 'React Snippets'),
     'useFetch Hook',
     'Custom hook for making API calls',
     'import { useState, useEffect } from ''react'';

export function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err);
        setLoading(false);
      });
  }, [url]);

  return { data, loading, error };
}',
     'javascript',
     true,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    -- John's Java Snippets
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'johndoe'),
     (SELECT id FROM collections WHERE name = 'Java Utilities'),
     'Generic Response Wrapper',
     'REST API response wrapper for Spring Boot',
     'public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}',
     'java',
     false,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    -- Jane's Python Snippets
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'janesmit'),
     (SELECT id FROM collections WHERE name = 'Python Scripts'),
     'CSV to JSON Converter',
     'Convert CSV files to JSON format',
     'import csv
import json

def csv_to_json(csv_file, json_file):
    data = []

    with open(csv_file, ''r'') as file:
        csv_reader = csv.DictReader(file)
        for row in csv_reader:
            data.append(row)

    with open(json_file, ''w'') as file:
        json.dump(data, file, indent=4)

    print(f"Converted {len(data)} rows to JSON")

# Usage
csv_to_json(''input.csv'', ''output.json'')',
     'python',
     true,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    -- Alex's SQL Snippets
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'alexdev'),
     (SELECT id FROM collections WHERE name = 'SQL Queries'),
     'Find Duplicate Records',
     'Query to find duplicate records in a table',
     'SELECT
    email,
    COUNT(*) as count
FROM users
GROUP BY email
HAVING COUNT(*) > 1
ORDER BY count DESC;',
     'sql',
     true,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'alexdev'),
     (SELECT id FROM collections WHERE name = 'SQL Queries'),
     'Recursive CTE for Hierarchy',
     'Query hierarchical data using recursive CTE',
     'WITH RECURSIVE category_tree AS (
    SELECT id, name, parent_id, 0 as level
    FROM categories
    WHERE parent_id IS NULL

    UNION ALL

    SELECT c.id, c.name, c.parent_id, ct.level + 1
    FROM categories c
    INNER JOIN category_tree ct ON c.parent_id = ct.id
)
SELECT * FROM category_tree
ORDER BY level, name;',
     'sql',
     false,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    -- Sarah's CSS Snippets
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'sarahcode'),
     (SELECT id FROM collections WHERE name = 'CSS Tricks'),
     'Glassmorphism Card',
     'Modern glassmorphism effect for cards',
     '.glass-card {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.2);
  padding: 24px;
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
}',
     'css',
     true,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    -- Additional snippets without collection
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'janesmit'),
     NULL,
     'Email Validator Regex',
     'Regular expression for email validation',
     'const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

function isValidEmail(email) {
  return emailRegex.test(email);
}',
     'javascript',
     false,
     false,
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP);

-- Insert Snippet-Tag relationships
INSERT INTO snippet_tags (snippet_id, tag_id)
VALUES
    ((SELECT id FROM snippets WHERE title = 'useDebounce Hook'), (SELECT id FROM tags WHERE name = 'react')),
    ((SELECT id FROM snippets WHERE title = 'useDebounce Hook'), (SELECT id FROM tags WHERE name = 'hooks')),
    ((SELECT id FROM snippets WHERE title = 'useDebounce Hook'), (SELECT id FROM tags WHERE name = 'javascript')),

    ((SELECT id FROM snippets WHERE title = 'useFetch Hook'), (SELECT id FROM tags WHERE name = 'react')),
    ((SELECT id FROM snippets WHERE title = 'useFetch Hook'), (SELECT id FROM tags WHERE name = 'hooks')),
    ((SELECT id FROM snippets WHERE title = 'useFetch Hook'), (SELECT id FROM tags WHERE name = 'api')),

    ((SELECT id FROM snippets WHERE title = 'Generic Response Wrapper'), (SELECT id FROM tags WHERE name = 'java')),
    ((SELECT id FROM snippets WHERE title = 'Generic Response Wrapper'), (SELECT id FROM tags WHERE name = 'spring-boot')),
    ((SELECT id FROM snippets WHERE title = 'Generic Response Wrapper'), (SELECT id FROM tags WHERE name = 'api')),

    ((SELECT id FROM snippets WHERE title = 'CSV to JSON Converter'), (SELECT id FROM tags WHERE name = 'python')),
    ((SELECT id FROM snippets WHERE title = 'CSV to JSON Converter'), (SELECT id FROM tags WHERE name = 'utility')),

    ((SELECT id FROM snippets WHERE title = 'Find Duplicate Records'), (SELECT id FROM tags WHERE name = 'sql')),
    ((SELECT id FROM snippets WHERE title = 'Find Duplicate Records'), (SELECT id FROM tags WHERE name = 'postgresql')),
    ((SELECT id FROM snippets WHERE title = 'Find Duplicate Records'), (SELECT id FROM tags WHERE name = 'optimization')),

    ((SELECT id FROM snippets WHERE title = 'Recursive CTE for Hierarchy'), (SELECT id FROM tags WHERE name = 'sql')),
    ((SELECT id FROM snippets WHERE title = 'Recursive CTE for Hierarchy'), (SELECT id FROM tags WHERE name = 'postgresql')),

    ((SELECT id FROM snippets WHERE title = 'Glassmorphism Card'), (SELECT id FROM tags WHERE name = 'css')),

    ((SELECT id FROM snippets WHERE title = 'Email Validator Regex'), (SELECT id FROM tags WHERE name = 'javascript')),
    ((SELECT id FROM snippets WHERE title = 'Email Validator Regex'), (SELECT id FROM tags WHERE name = 'utility'));

-- Insert Comments
INSERT INTO comments (id, user_id, snippet_id, comment_text, created_at, updated_at)
VALUES
    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'janesmit'),
     (SELECT id FROM snippets WHERE title = 'useDebounce Hook'),
     'This is really useful! I''ve been looking for something like this.',
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'alexdev'),
     (SELECT id FROM snippets WHERE title = 'useDebounce Hook'),
     'Great implementation! You might want to add a cleanup function to prevent memory leaks.',
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'johndoe'),
     (SELECT id FROM snippets WHERE title = 'CSV to JSON Converter'),
     'Nice script! Does it handle large CSV files efficiently?',
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'sarahcode'),
     (SELECT id FROM snippets WHERE title = 'Find Duplicate Records'),
     'This saved me hours of work. Thanks for sharing!',
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP),

    (gen_random_uuid(),
     (SELECT id FROM users WHERE username = 'janesmit'),
     (SELECT id FROM snippets WHERE title = 'Glassmorphism Card'),
     'Beautiful effect! Works perfectly on my project.',
     CURRENT_TIMESTAMP,
     CURRENT_TIMESTAMP);